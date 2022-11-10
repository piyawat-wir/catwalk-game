/*
	Java Project 3 | Catwalk Game
	Created by:
		6313124 Chanawee Sateinteeraphap
		6313125 Chayakorn Jullanee
		6313132 Piyawat Wirotkitphaiboon
*/

package Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import static java.lang.Math.round;
import static java.util.Comparator.reverseOrder;

public class GameRenderer extends JLabel {

	private Game game;
	private ArrayList<GameObject> renderPipeline = new ArrayList<>();
	private boolean isStopped = false;

	public GameRenderer(Game g) {
		game = g;
		setSize(game.getFrame().getWidth(),game.getFrame().getHeight());
	}

	private Comparator<GameObject> byDistance = Comparator.comparing(GameObject::getZIndex)
			.thenComparing(GameObject::getY, reverseOrder())
			.thenComparing(GameObject::getX);
	public void calculateRenderPipeline(ArrayList<GameObject> obj) {
		renderPipeline = (ArrayList<GameObject>) obj.stream()
				.sorted(byDistance)
				.filter(gameObject -> (gameObject.getX() >= -2 && gameObject.getX() <= 10))
				.collect(Collectors.toList());
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.white);
		g.fillRect(0,0, getWidth(), getHeight());

		calculateRenderPipeline(game.getGameObjects());
		for (GameObject obj : renderPipeline) {
			double[] pos = getScreenPosition(obj);
			g.drawImage(obj.getCurrentSprite().getImage(), (int)pos[0], (int)pos[1], null);
		}

		double[] cameraPos = game.getTicker().getCameraPosition();
		double[] p1 = getScreenPosition(new GameObject().setPosition(cameraPos[0], cameraPos[1]+20));
		double[] p2 = getScreenPosition(new GameObject().setPosition(cameraPos[0]-20, cameraPos[1]+20));
		double[] p3 = getScreenPosition(new GameObject().setPosition(cameraPos[0], cameraPos[1]-20));
		Polygon p = new Polygon();
		p.addPoint((int)p1[0], (int)p1[1]);
		p.addPoint((int)p2[0], (int)p2[1]);
		p.addPoint((int)p3[0], (int)p3[1]);
		g.fillPolygon(p);

		p1 = getScreenPosition(new GameObject().setPosition(cameraPos[0]+8.5, cameraPos[1]-20));
		p2 = getScreenPosition(new GameObject().setPosition(cameraPos[0]+8.5, cameraPos[1]+20));
		p3 = getScreenPosition(new GameObject().setPosition(cameraPos[0]+28.5, cameraPos[1]-20));
		p = new Polygon();
		p.addPoint((int)p1[0], (int)p1[1]);
		p.addPoint((int)p2[0], (int)p2[1]);
		p.addPoint((int)p3[0], (int)p3[1]);
		g.fillPolygon(p);

		g.setColor(Color.black);
		g.setFont(new Font("Tahoma", Font.PLAIN, 50));
		g.drawString("SCORE: "+(game.getScore()+game.getCollectedCoins()*10), 20,50);
	}

	public double[] getScreenPosition(GameObject obj) {
		double[] cameraPos = game.getTicker().getCameraPosition();
		double x = obj.getX()+obj.getOffsetX()-cameraPos[0], y = obj.getY()+obj.getOffsetY()-cameraPos[1];
		double[] output = new double[2];
		double[] basisX = {1.20236425504,-0.118351294131};
		double[] basisY = {0.183,0.762};
		x += 0; y += 0;
		x *= 98.45; y *= 98.45;
		output[0] = round(x*basisX[0] + y*basisY[0]);
		output[1] = round(x*basisX[1] + y*basisY[1]);
		output[0] += getParent().getWidth()/2.0 - 518;
		output[1] -= getParent().getHeight() - 209;
		output[1] = -output[1];
		return output;
	}

	public void start() { rendererThread.start(); }
	public boolean isStopped() { return isStopped; }
	private Thread rendererThread = new Thread(() -> {
		long then, delta, sleeptime;
		then = System.currentTimeMillis();

		int fadeCount = 0;

		while (true) {

			//GameOver Event
			if (game.isOver()) fadeCount++;
			if (fadeCount > 120) break; //Delay 2 seconds then break loop

			//Paint the scene.
			repaint();

			//Delay each rendering frame at maximum of 60 fps
			delta = System.currentTimeMillis()-then;
			sleeptime = (1000/60) - delta;
			if (sleeptime < 0) sleeptime = 2;

			try { Thread.sleep(sleeptime); }
			catch (InterruptedException e) { e.printStackTrace(); }

			then = System.currentTimeMillis();
		}
		game.getFrame().remove(this);
		isStopped = true;
	});
}