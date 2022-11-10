/*
	Java Project 3 | Catwalk Game
	Created by:
		6313124 Chanawee Sateinteeraphap
		6313125 Chayakorn Jullanee
		6313132 Piyawat Wirotkitphaiboon
*/

package Game;

import java.util.ArrayList;

public abstract class GameTile {
	protected GameObject tile;
	protected ArrayList<GameObject> elements = new ArrayList<>();
	protected boolean hasEvent = false;

	protected GameTile() {}
	public GameTile(GameObject tilePrefab, GameObject... objects) {
		tile = tilePrefab.duplicate();
		for (int i = 0 ; i < objects.length; i++) elements.add(objects[i].duplicate());
		setPosition(0,0);
	}
	public GameTile setPosition(double x, double y) {
		tile.setPosition(x,y);
		for (int i = 0 ; i < elements.size(); i++)
			elements.get(i).setPosition(elements.get(i).getX()+x, elements.get(i).getY()+y);
		return this;
	}
	public GameObject getTile() { return tile; }
	public ArrayList<GameObject> getElements() { return elements; }
	public ArrayList<GameObject> getGameObjects() {
		ArrayList<GameObject> output = new ArrayList<>(elements);
		output.add(tile);
		return output;
	}

	public abstract void tileEvent(Game game);
	public boolean isHasEvent() { return hasEvent; }

	public abstract <T extends GameTile> T duplicate();
}

