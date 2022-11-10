/*
	Java Project 3 | Catwalk Game
	Created by:
		6313124 Chanawee Sateinteeraphap
		6313125 Chayakorn Jullanee
		6313132 Piyawat Wirotkitphaiboon
*/

package Game;

public class GameObject {
	private double x, y, sizeX, sizeY, offsetX, offsetY;
	private int direction, zIndex;
	private GameImage[] sprites = new GameImage[4];
	private GameImage[] resizedSprites = new GameImage[4];

	public GameObject() {
		x = 0; y = 0; zIndex = 0;
		sizeX = 0; sizeY = 0;
		offsetX = 0; offsetY = 0;
		direction = 0;
	}
	public GameObject(GameImage... img) {
		this();
		for (int i = 0 ; i < img.length; i++) sprites[i] = img[i];
		resizedSprites = sprites;
		sizeX = sprites[direction].getIconWidth(); sizeY = sprites[direction].getIconHeight();
	}

	public GameObject setPosition(double nx, double ny) { x = nx; y = ny; return this; }
	public GameObject setSize(double w, double h) {
		sizeX = w; sizeY = h;
		for (int i = 0 ; i < sprites.length ; i++)
			if (sprites[i] != null)	resizedSprites[i] = sprites[i].resize((int)sizeX, (int)sizeY);
		return this;
	}
	public GameObject setOffset(double nx, double ny) { offsetX = nx; offsetY = ny; return this; }
	public GameObject setDirection(Direction d) { direction = d.ordinal(); return this; }
	public GameObject setZIndex(int z) { zIndex = z; return this; }

	public double getX() { return x; }
	public double getY() { return y; }
	public double getOffsetX() { return offsetX; }
	public double getOffsetY() { return offsetY; }
	public int getZIndex() { return zIndex; }
	public GameImage getCurrentSprite() { return resizedSprites[direction]; }

	public GameObject duplicate() {
		GameObject output = new GameObject();
		output.direction = direction;
		output.sprites = sprites;
		output.resizedSprites = resizedSprites;
		output.x = x; output.y = y;
		output.sizeX = sizeX; output.sizeY = sizeY;
		output.offsetX = offsetX; output.offsetY = offsetY;
		output.zIndex = zIndex;
		return output;
	}
}
