/*
	Java Project 3 | Catwalk Game
	Created by:
		6313124 Chanawee Sateinteeraphap
		6313125 Chayakorn Jullanee
		6313132 Piyawat Wirotkitphaiboon
*/

package Game;

public class GrassTile extends GameTile {

	private boolean[] obstacleMap = new boolean[8];

	public GrassTile(GameObject tilePrefab, GameObject... objects) {
		super(tilePrefab, objects);
		generateObstacleMap();
	}
	private GrassTile(boolean noObstacleMapRegen, GameObject tilePrefab, GameObject... objects) {
		this(tilePrefab, objects);
		if (!noObstacleMapRegen) generateObstacleMap();
	}
	private void generateObstacleMap() {
		for (int i = 0 ; i < obstacleMap.length; i++) obstacleMap[i] = false;
		for (GameObject obj : elements)
			obstacleMap[ (int) Math.round(obj.getX()) ] = true;
	}

	@Override
	public GrassTile duplicate() {
		GameObject[] objarr = new GameObject[elements.size()];
		objarr = elements.toArray(objarr);
		GrassTile output = new GrassTile(true,tile,objarr);
		output.obstacleMap = obstacleMap;
		return output;
	}

	public boolean hasObstacleAt(int x) { return obstacleMap[x]; }

	@Override
	public void tileEvent(Game game) {}
}