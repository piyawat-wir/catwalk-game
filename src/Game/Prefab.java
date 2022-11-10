/*
	Java Project 3 | Catwalk Game
	Created by:
		6313124 Chanawee Sateinteeraphap
		6313125 Chayakorn Jullanee
		6313132 Piyawat Wirotkitphaiboon
*/

package Game;

public class Prefab {

	//Tiles
	public static GameObject TileGrass = new GameObject(GameImage.tile_grass).setSize(1000, 208).setZIndex(-3);
	public static GameObject TileRiver = new GameObject(GameImage.tile_river1, GameImage.tile_river2).setSize(1000, 208).setZIndex(-5);
	public static GameObject TileRoad = new GameObject(GameImage.tile_road).setSize(1000, 208).setZIndex(-4);

	//Land Objects
	public static GameObject Tree1 = new GameObject(GameImage.tree1).setSize(208, 208).setOffset(-0.5,1.5);
	public static GameObject Tree2 = new GameObject(GameImage.tree2).setSize(208, 208).setOffset(-0.5,1.5);
	public static GameObject Rock1 = new GameObject(GameImage.rock1, GameImage.rock11).setSize(208, 208).setOffset(-0.3,0.63);
	public static GameObject Rock2 = new GameObject(GameImage.rock2, GameImage.rock21).setSize(208, 208).setOffset(-0.3,0.725);

	//Player Characters
	public static GameObject Cat1 = new GameObject(GameImage.cat1n, GameImage.cat1s, GameImage.cat1e, GameImage.cat1w)
			.setSize(208, 208).setOffset(-0.3,0.625);
	public static GameObject Cat2 = new GameObject(GameImage.cat2n, GameImage.cat2s, GameImage.cat2e, GameImage.cat2w)
			.setSize(208, 208).setOffset(-0.3,0.575);
	public static GameObject Cat3 = new GameObject(GameImage.cat3n, GameImage.cat3s, GameImage.cat3e, GameImage.cat3w)
			.setSize(208, 208).setOffset(-0.3,0.625);
	public static GameObject Cat4 = new GameObject(GameImage.cat4n, GameImage.cat4s, GameImage.cat4e, GameImage.cat4w)
			.setSize(208, 208).setOffset(-0.3,0.625);
	public static GameObject Cat5 = new GameObject(GameImage.cat5n, GameImage.cat5s, GameImage.cat5e, GameImage.cat5w)
			.setSize(208, 208).setOffset(-0.3,0.625);

	//Cars
	public static GameObject Car1 = new GameObject(GameImage.car1e, GameImage.car1w).setSize(208, 208).setOffset(-0.35,0.825);
	public static GameObject Car2 = new GameObject(GameImage.car2e, GameImage.car2w).setSize(208, 208).setOffset(-0.35,0.825);
	public static GameObject Car3 = new GameObject(GameImage.car3e, GameImage.car3w).setSize(208, 208).setOffset(-0.35,0.825);

	//River Objects
	public static GameObject Log = new GameObject(GameImage.log1, GameImage.log2).setSize(312, 156)
			.setOffset(-0.775,0.025).setZIndex(-2);
	public static GameObject LilyPad1 = new GameObject(GameImage.lilypad1, GameImage.lilypad3, GameImage.lilypad5, GameImage.lilypad7)
			.setSize(208, 208).setOffset(-0.3,0.475).setZIndex(-2);
	public static GameObject LilyPad2 = new GameObject(GameImage.lilypad2, GameImage.lilypad4, GameImage.lilypad6, GameImage.lilypad8)
			.setSize(208, 208).setOffset(-0.3,0.475).setZIndex(-2);

	//Collectibles
	public static GameObject Coin = new GameObject(GameImage.coin).setSize(208, 208).setOffset(-0.3,0.565).setZIndex(-2);

	//Misc
	public static GameObject Splash = new GameObject(GameImage.splash).setSize(208, 208).setOffset(-0.3,0.625).setZIndex(-1);
	public static GameObject Corpse = new GameObject(GameImage.corpse).setSize(208, 208).setOffset(-0.3,0.625).setZIndex(-1);
}