/*
	Java Project 3 | Catwalk Game
	Created by:
		6313124 Chanawee Sateinteeraphap
		6313125 Chayakorn Jullanee
		6313132 Piyawat Wirotkitphaiboon
*/

package Game;

import Main.SoundController;

import java.util.ArrayList;

public class GameTicker extends Thread {

	private Game game;

	private ArrayList<GameTile> tiles = new ArrayList<>();

	//Camera
	private double[] cameraTargetPos = {0,0};
	private double[] cameraPos = {0,0};
	private double cameraPanSpeed = 0.01; //per tick

	private double[] playerDirection = {0, 0};
	private double playerMoveSpeed = 0.05; //per tick
	private int playerAnimationTick = 0;
	private boolean isPlayerOnLog;

	public GameTicker(Game g) {
		game = g;
	}

	private double round(double x) { return (double) Math.round(100000*x)/100000; }

	private void updateCamera() {
		cameraTargetPos[1] = game.getPlayer().getY()-0.5;
		cameraPos[0] += (cameraTargetPos[0] - cameraPos[0]) * cameraPanSpeed;
		cameraPos[1] += (cameraTargetPos[1] - cameraPos[1]) * cameraPanSpeed;
		cameraPos[0] = round(cameraPos[0]);
		cameraPos[1] = round(cameraPos[1]);
	}
	private void updatePlayer() {
		GameObject player = game.getPlayer();
		if (isPlayerMoving()) {
			player.setPosition(
					round(player.getX() + playerDirection[0]*playerMoveSpeed),
					round(player.getY() + playerDirection[1]*playerMoveSpeed)
			);
			playerAnimationTick--;
		} else { playerAnimationTick = 0; }
	}
	public void movePlayer(double dx, double dy) {
		GameObject player = game.getPlayer();
		if (!isPlayerMoving()) {
			//Calculate target position
			double[] targetPos = { player.getX()+dx, player.getY()+dy };


			//Translate player to exact block position after get off from log
			if (isPlayerOnLog && dy != 0) {
				isPlayerOnLog = false;
				targetPos[0] = Math.round(player.getX() + dx);
			}
			if (!isPlayerOnLog) {
				targetPos[0] = Math.round(targetPos[0]);
				targetPos[1] = Math.round(targetPos[1]);
			}
			//Set Horizontal Boundaries
			if (targetPos[0] < 0 || targetPos[0] > 8) return;

			//Get target tile
			GameTile targetTile = game.getTileAtPosition((int)targetPos[1]);
			if (targetTile != null) {
				if (targetTile.getClass() == GrassTile.class) {
					GrassTile tile = (GrassTile) targetTile;

					//Check for blocked area
					if (tile.hasObstacleAt((int) targetPos[0])) return;

					//Check for coin
					int coinIndex = game.getCoinIndex((int) targetPos[0], (int) targetPos[1]);
					if (coinIndex >= 0) {
						game.removeCoin(coinIndex);
						game.addCollectedCoins();
						game.getSoundController().playSFX(SoundController.SFX_Coin);
					}
				}

				if (dy == -1 && player.getY()+4 < game.getScore()) return;

				//Update score
				if (dy == 1 && player.getY()-1 > game.getScore())
					game.setScore((int)player.getY() - 1);

				//Start player movement
				playerAnimationTick = (int) (1 / playerMoveSpeed);
				playerDirection[0] = targetPos[0] - player.getX();
				playerDirection[1] = targetPos[1] - player.getY();
				if (targetTile.getClass() == RiverTile.class) {
					playerDirection[0] += (((RiverTile) targetTile).getRiverSpeed()*playerAnimationTick);
				}

				game.getSoundController().playSFX(SoundController.SFX_PlayerMove);

			}
		}
	}
	public boolean isPlayerMoving() { return playerAnimationTick > 0; }
	public void playerIsOnLog() { isPlayerOnLog = true; }

	private void updateMap() {
		if (game.getTiles().get(game.getTiles().size()-1).getTile().getY() - game.getPlayer().getY() < 10) {
			game.generateMap();
			game.cleanMap();
		}
	}
	private void callTileEvent() {
		ArrayList<GameTile> tiles = game.getTiles();
		for (GameTile tile : tiles)
			if (tile.isHasEvent()) tile.tileEvent(game);
	}

	@Override
	public void run() {
		long then, delta, sleeptime;
		then = System.currentTimeMillis();

		while(!game.getRenderer().isStopped()) {
			//Call methods for updating game Object
			updateMap();
			callTileEvent();
			updatePlayer();
			updateCamera();

			//Limit each game tick by 2 ms
			delta = System.currentTimeMillis() - then;
			sleeptime = 2 - delta;
			if (sleeptime < 0) sleeptime = 2;

			try { Thread.sleep(sleeptime); }
			catch (InterruptedException e) { e.printStackTrace(); }

			then = System.currentTimeMillis();
		}
		game.reportScore();
	}

	public void setCameraTargetPosition(double x, double y) {
		cameraTargetPos[0] = x; cameraTargetPos[1] = y;
	}
	public double[] getCameraPosition() { return cameraPos; }
}