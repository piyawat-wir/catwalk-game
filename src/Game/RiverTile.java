/*
	Java Project 3 | Catwalk Game
	Created by:
		6313124 Chanawee Sateinteeraphap
		6313125 Chayakorn Jullanee
		6313132 Piyawat Wirotkitphaiboon
*/

package Game;

import Main.SoundController;

public class RiverTile extends GameTile {

	private double riverSpeed = 0.002;
	private double delay = 4;
	private int tickCount = 0;
	private int riverRileFrame = 0;

	public RiverTile(GameObject tilePrefab, GameObject... obj) {
		super(tilePrefab, obj);
		hasEvent = true;
	}

	public RiverTile setRiverSpeed(double spd) {
		riverSpeed = spd;
		if (riverSpeed < 0) for (GameObject obj : elements)
			obj.setDirection(Direction.SOUTH);
		else for (GameObject obj : elements)
			obj.setDirection(Direction.NORTH);
		return this;
	}
	public double getRiverSpeed() { return riverSpeed; }
	public RiverTile setDelay(double t) { delay = t; return this; }
	public RiverTile addOffset(double x) {
		for (GameObject obj : elements)
			obj.setPosition((obj.getX() + x) % (12+delay), obj.getY());
		return this;
	}

	@Override
	public void tileEvent(Game game) {
		double sx, sy;
		double sx1 = 1.25, sy1 = 0.5; //Log Rectangle Hit-box Dimension
		double sx2 = 0.5, sy2 = 0.5; //LilyPad Rectangle Hit-box Dimension
		double r = 0.275; //Player Circle Hit-box Radius
		GameObject player = game.getPlayer();
		boolean isPlayerOnALog = false;
		boolean isOnRiver = false;

		for (GameObject obj : elements) {
			//Move elements
			obj.setPosition(obj.getX() + riverSpeed, obj.getY());
			if (obj.getX() > 12 + delay) obj.setPosition(-4, obj.getY());
			else if (obj.getX() < -4) obj.setPosition(12+delay, obj.getY());

			if (game.isOver()) continue;

			//Determine Object's hitbox
			if (obj.getOffsetX() == -0.775) { sx = sx1; sy = sy1; }
			else { sx = sx2; sy = sy2; }

			//Player-Log Collision Detection
			double distX = Math.abs(obj.getX()-0.125 - player.getX());
			double distY = Math.abs(obj.getY() - player.getY());
			boolean isPlayerOnTile = Math.abs(tile.getY() - player.getY()) < 0.2;
			boolean isPlayerCollideWithObject = distX < sx / 2 + r && distY < sy / 2 + r;

			if (isPlayerOnTile) {
				isOnRiver = true;
				if (isPlayerCollideWithObject)
					isPlayerOnALog = true;
			}
		}

		//RiverTile-Player handling
		if (isOnRiver)
			if (isPlayerOnALog) {
				if (!game.getTicker().isPlayerMoving()) {
					player.setPosition(player.getX() + riverSpeed, player.getY());
					game.getTicker().playerIsOnLog();
				}
				if (player.getX() > 7.5 || player.getX() < -0.5) {
					System.out.println("waterfall!!");
					game.spawnAtPlayer(Prefab.Splash);
					game.getSoundController().playSFX(SoundController.SFX_CatDrown);
					game.getSoundController().playSFX(SoundController.SFX_WaterSplash);
					game.stop();
				}
			} else { //Player is in water
				System.out.println("water!!");
				game.spawnAtPlayer(Prefab.Splash);
				game.getSoundController().playSFX(SoundController.SFX_CatDrown);
				game.getSoundController().playSFX(SoundController.SFX_WaterJump);
				game.stop();
			}

		//Tile Animation
		if (tickCount == 0) {
			riverRileFrame = (riverRileFrame+1) % 2;
			switch(riverRileFrame) {
				case 0: tile.setDirection(Direction.NORTH); break;
				case 1: tile.setDirection(Direction.SOUTH); break;
			}
		}
		tickCount = (tickCount+1) % 250;
	}

	@Override
	public RiverTile duplicate() {
		GameObject[] objarr = new GameObject[elements.size()];
		objarr = elements.toArray(objarr);
		RiverTile output = new RiverTile(tile,objarr);
		output.setDelay(delay);
		output.setRiverSpeed(riverSpeed);
		return output;
	}
}