/*
	Java Project 3 | Catwalk Game
	Created by:
		6313124 Chanawee Sateinteeraphap
		6313125 Chayakorn Jullanee
		6313132 Piyawat Wirotkitphaiboon
*/

package Game;

import Main.GameOver;
import Main.SoundController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

enum Direction { NORTH, SOUTH, EAST, WEST }

public class Game {

	private JFrame frame;
	private GameRenderer renderer;
	private GameTicker ticker;

	private ArrayList<GameObject> gameObjects = new ArrayList<>();
	private ArrayList<GameTile> tiles = new ArrayList<>();
	private ArrayList<GameObject> coins = new ArrayList<>();
	private ArrayList<double[]> coinsPosition = new ArrayList<>();

	private GameTile[][] grassTileSet = {
			GameTileSet.Land11, GameTileSet.Land12, GameTileSet.Land13,
			GameTileSet.Land21, GameTileSet.Land22, GameTileSet.Land23,
			GameTileSet.Land31, GameTileSet.Land32, GameTileSet.Land5
	};
	private GameTile[][] riverTileSet = { GameTileSet.River11, GameTileSet.River12, GameTileSet.River13 };
	private GameTile[][] roadTileSet = { GameTileSet.Road11, GameTileSet.Road12, GameTileSet.Road13 };

	private int clearedTiles = 0;
	private String recentTileSet = GrassTile.class.getName();
	private int difficulty = 1;
	private int score = 0, collectedCoins = 0;

	private GameObject player = Prefab.Cat1.duplicate().setPosition(3,1);
	private boolean isOver = false;
	private GameOver overFrame;

	private SoundController soundCtrl;

	public Game(JFrame fr) {
		frame = fr;
		renderer = new GameRenderer(this);
		ticker = new GameTicker(this);

		frame.add(renderer);
		ticker.setCameraTargetPosition(0,0);
		addTilePiece(GameTileSet.StartLand, false, 0, 0);
		generateMap();

		addListener();

		gameObjects.add(player);
		renderer.calculateRenderPipeline(gameObjects);
	}
	public JFrame getFrame() { return frame; }
	public GameRenderer getRenderer() { return renderer; }
	public GameTicker getTicker() { return ticker; }

	public ArrayList<GameTile> getTiles() { return tiles; }
	public ArrayList<GameObject> getGameObjects() { return gameObjects; }
	public GameObject getPlayer() { return player; }
	public void setPlayer(GameObject prefab) {
		gameObjects.remove(player);
		player = prefab.duplicate().setPosition(3,1);
		gameObjects.add(player);
	}
	public void setDifficulty(int diff) { difficulty = diff; System.out.println("[Game] Difficulty = " + difficulty);}
	public void setOverFrame(GameOver over) { overFrame = over; }
	public void setSoundController(SoundController sctrl) { soundCtrl = sctrl; }
	public SoundController getSoundController() { return soundCtrl; }

	public void setScore(int n) { score = n; }
	public int getScore() { return score; }
	public void addCollectedCoins() { collectedCoins += 1; }
	public int getCollectedCoins() { return collectedCoins; }

	public GameTile getTileAtPosition(int y) {
		int i = y - clearedTiles;
		if (i >= 0 && i < tiles.size()) return tiles.get(i);
		return null;
	}
	private void generateTilePieces() {
		double maxSpeed = 0.02;
		double minSpeed = maxSpeed*Math.tanh(difficulty/12.0*Math.log10(score+1));
		double currentMaxSpeed = maxSpeed*Math.tanh(difficulty/6.0*Math.log10(100*(score+1)));
		double rand = Math.random();
		int index;
		if (recentTileSet.equals(GrassTile.class.getName())) {
			int laneVariance = (int) Math.floor((Math.random()*4)-2);
			int laneCount = (int) Math.floor(Math.log(2+score)/Math.log(5.0/difficulty)) + laneVariance;
			if (laneCount <= 0) laneCount = 1;

			for (int i = 0; i < laneCount; i++) {
				double offset = Math.random()*7;
				double speed = Math.random()*(currentMaxSpeed-minSpeed)+minSpeed;
				speed *= Math.round(Math.random())*2-1;

				if (rand < 0.5) { //Generate Road
					index = (int) Math.round(Math.random()*(roadTileSet.length-1));
					addTilePiece( roadTileSet[index], false, offset, speed );
				} else { //Generate River
					index = (int) Math.round(Math.random()*(riverTileSet.length-1));
					if (index == 2) { speed = 0; offset = 0; }
					addTilePiece( riverTileSet[index], false, offset, speed );
				}
			}
			recentTileSet = CarTile.class.getName();
		} else {
			index = (int) Math.round(rand*(grassTileSet.length-1));
			addTilePiece( grassTileSet[index], true, 0, 0 );
			recentTileSet = GrassTile.class.getName();
		}
	}
	public void generateMap() {
		System.out.println("[Game] Generating Map");
		for (int i = 0; i < 10; i++) {
			generateTilePieces();
		}
		System.out.println("[Game] Generation Completed");
	}
	public void cleanMap() {
		System.out.println("[Game] Clean Map");

		int removed = 0;
		for (int i = 0 ; i < coins.size()-removed; i++) {
			if (coinsPosition.get(i)[1] < score - 10) {
				coinsPosition.remove(i);
				coins.remove(i);
				removed++; i--;
			}
		}
		removed = 0;
		for (int i = 0 ; i < tiles.size()-removed; i++) {
			if (tiles.get(i).getTile().getY() < score - 10) {
				for (GameObject obj : tiles.get(i).getElements())
					gameObjects.remove(obj);
				tiles.remove(i);
				removed++; i--;
			}
		}
		clearedTiles += removed;
	}
	private void addTilePiece(GameTile[] tilePiece, boolean genCoin, double offset, double speed) {
		for (int i = tilePiece.length-1 ; i >= 0; i--) {
			GameTile tile = tilePiece[i].duplicate();
			tile.setPosition(0, tiles.size()+clearedTiles);
			tiles.add(tile);
			gameObjects.addAll(tile.getGameObjects());
			if (tile.getClass() == CarTile.class) {
				((CarTile) tile).setCarSpeed(speed);
				((CarTile) tile).addOffset(offset);
			} else if (tile.getClass() == RiverTile.class) {
				((RiverTile) tile).setRiverSpeed(speed);
				((RiverTile) tile).addOffset(offset);
			} else if (genCoin && tile.getClass() == GrassTile.class)
				generateCoinsOnTile((GrassTile) tile);
		}
	}
	private void generateCoinsOnTile(GrassTile tile) {
		if (Math.random() < 0.5) {
			double rand = Math.random();
			int count = 1;
			if (rand < 0.001) count = 5;
			else if (rand < 0.01) count = 3;
			else if (rand < 0.1) count = 2;
			while (count > 0) {
				int x = (int) Math.round(7*Math.random());
				int y = (int) tile.getTile().getY();
				if (!tile.hasObstacleAt(x))
					if (getCoinIndex(x, y) < 0) {
						GameObject coin = Prefab.Coin.duplicate().setPosition(x,y);
						double[] pos = {x,y};
						coins.add(coin);
						coinsPosition.add(pos);
						gameObjects.add(coin);
					}
				count--;
			}
		}
	}
	public int getCoinIndex(int x, int y) {
		for (int i = 0 ; i < coinsPosition.size(); i++)
			if (coinsPosition.get(i)[0] == x && coinsPosition.get(i)[1] == y) return i;
		return -1;
	}
	public void removeCoin(int index) {
		coinsPosition.remove(index);
		gameObjects.remove(coins.get(index));
		coins.remove(index);
	}
	public void spawnAtPlayer(GameObject prefab) {
		gameObjects.add(prefab.duplicate().setPosition(player.getX(), player.getY()));
	}

	public boolean isOver() { return isOver; }

	private int keyDelay = 100; //ms
	private long lastKeyPressed = 0;
	private KeyListener keyListener;
	private MouseListener mouseListener;

	private void addListener() {
		keyListener = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				long now = System.currentTimeMillis();
				if (now - lastKeyPressed > keyDelay) {
					switch (e.getKeyCode()) {
						case KeyEvent.VK_UP:
						case KeyEvent.VK_W:
							ticker.movePlayer(0, 1);
							player.setDirection(Direction.NORTH);
							break;
						case KeyEvent.VK_LEFT:
						case KeyEvent.VK_A:
							ticker.movePlayer(-1, 0);
							player.setDirection(Direction.WEST);
							break;
						case KeyEvent.VK_DOWN:
						case KeyEvent.VK_S:
							ticker.movePlayer(0, -1);
							player.setDirection(Direction.SOUTH);
							break;
						case KeyEvent.VK_RIGHT:
						case KeyEvent.VK_D:
							ticker.movePlayer(1, 0);
							player.setDirection(Direction.EAST);
							break;
					}
					lastKeyPressed = now;
				}
			}
		};
		mouseListener = new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				long now = System.currentTimeMillis();
				if (now - lastKeyPressed > keyDelay) {
					double[] deltaPos = renderer.getScreenPosition(player);
					deltaPos[0] = e.getX() - deltaPos[0] - 104;
					deltaPos[1] = e.getY() - deltaPos[1] - 104;
					int dx = 0, dy = 0;
					if (deltaPos[0] >= Math.abs(deltaPos[1])) {
						dx = 1;
						player.setDirection(Direction.EAST);
					} else if (deltaPos[0] <= -Math.abs(deltaPos[1])) {
						dx = -1;
						player.setDirection(Direction.WEST);
					} else if (deltaPos[1] > Math.abs(deltaPos[0])) {
						dy = -1;
						player.setDirection(Direction.SOUTH);
					} else if (deltaPos[1] < -Math.abs(deltaPos[0])) {
						dy = 1;
						player.setDirection(Direction.NORTH);
					}
					ticker.movePlayer(dx, dy);
					lastKeyPressed = now;
				}
			}
		};
		frame.addKeyListener(keyListener);
		frame.addMouseListener(mouseListener);
	}

	public void reportScore() {
		System.out.println("[Game] Reporting Score: " + (score+10*collectedCoins) );
		overFrame.reportScore(difficulty, score+10*collectedCoins);
		frame.setVisible(false);
		overFrame.setVisible(true);
		System.out.println("[Game] Ended!");
	};

	public void start() {
		renderer.start();
		ticker.start();
	}
	public void stop() {
		isOver = true;
		gameObjects.remove(player);
		frame.removeKeyListener(keyListener);
		frame.removeMouseListener(mouseListener);
	}
}
