/*
	Java Project 3 | Catwalk Game
	Created by:
		6313124 Chanawee Sateinteeraphap
		6313125 Chayakorn Jullanee
		6313132 Piyawat Wirotkitphaiboon
*/

package Game;

import Main.SoundController;

public class CarTile extends GameTile {

	private double carSpeed = 0.005;
	private double delay = 4;

	public CarTile(GameObject tilePrefab, GameObject... obj) {
		super(tilePrefab, obj);
		hasEvent = true;
	}

	public CarTile setCarSpeed(double spd) {
		carSpeed = spd;
		if (carSpeed < 0) for (GameObject obj : elements)
			obj.setDirection(Direction.SOUTH);
		else for (GameObject obj : elements)
			obj.setDirection(Direction.NORTH);
		return this;
	}
	public CarTile setDelay(double t) { delay = t; return this; }
	public CarTile addOffset(double x) {
		for (GameObject obj : elements)
			obj.setPosition((obj.getX() + x) % (12+delay), obj.getY());
		return this;
	}

	@Override
	public void tileEvent(Game game) {
		for (GameObject obj : getElements()) {

			//Car Movement
			obj.setPosition(obj.getX()+carSpeed, obj.getY() );
			if (obj.getX() > 12+delay) obj.setPosition(-4,obj.getY());
			else if (obj.getX() < -4) obj.setPosition(12+delay, obj.getY());

			if (game.isOver()) continue;

			//Player-Car Collision Detection
			double sx = 0.9, sy = 0.75; //Car Rectangle Hit-box Dimension
			double r = 0.275; //Player Circle Hit-box Radius
			GameObject player = game.getPlayer();

			double distX = Math.abs(obj.getX()-player.getX());
			double distY = Math.abs(obj.getY()-player.getY());

			if (distX < sx/2 + r && distY < sy/2 + r) { //Collision Handling
				System.out.println("HIT! " + distX + "," + distY);
				game.spawnAtPlayer(Prefab.Corpse);
				game.getSoundController().playSFX(SoundController.SFX_CatHit);
				game.getSoundController().playSFX(SoundController.SFX_CarHorn);
				game.stop();
			}
		}
	}

	@Override
	public CarTile duplicate() {
		GameObject[] objarr = new GameObject[elements.size()];
		objarr = elements.toArray(objarr);
		CarTile output = new CarTile(tile,objarr);
		output.setDelay(delay);
		output.setCarSpeed(carSpeed);
		return output;
	}
}