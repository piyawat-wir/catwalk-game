/*
	Java Project 3 | Catwalk Game
	Created by:
		6313124 Chanawee Sateinteeraphap
		6313125 Chayakorn Jullanee
		6313132 Piyawat Wirotkitphaiboon
*/

package Game;

public class GameTileSet {
	public static GameTile[] StartLand = {
			new GrassTile(Prefab.TileGrass,
					Prefab.Tree1.duplicate().setPosition(0,0).setDirection(Direction.NORTH),
					Prefab.Tree2.duplicate().setPosition(7,0).setDirection(Direction.NORTH)
					),
			new GrassTile(Prefab.TileGrass,
					Prefab.Rock2.duplicate().setPosition(0,0),
					Prefab.Rock1.duplicate().setPosition(7,0)
			),
			new GrassTile(Prefab.TileGrass,
					Prefab.Tree2.duplicate().setPosition(0,0),
					Prefab.Tree1.duplicate().setPosition(7,0)
			)
	};
	public static GameTile[] debug = {
			new GrassTile( Prefab.TileGrass,
					Prefab.Tree1.duplicate().setPosition(0,0),
					Prefab.Cat1.duplicate().setPosition(1,0).setDirection(Direction.NORTH),
					Prefab.Cat2.duplicate().setPosition(2,0).setDirection(Direction.NORTH),
					Prefab.Cat3.duplicate().setPosition(3,0).setDirection(Direction.NORTH),
					Prefab.Cat4.duplicate().setPosition(4,0).setDirection(Direction.NORTH),
					Prefab.Rock1.duplicate().setPosition(6,0).setDirection(Direction.NORTH),
					Prefab.Rock2.duplicate().setPosition(7,0).setDirection(Direction.NORTH)
			),
			new CarTile( Prefab.TileRoad,
					Prefab.Car1.duplicate().setPosition(1,0),
					Prefab.Car2.duplicate().setPosition(3,0),
					Prefab.Car3.duplicate().setPosition(5,0)
			),
			new RiverTile( Prefab.TileRiver,
					Prefab.Log.duplicate().setPosition(0,0),
					Prefab.LilyPad1.duplicate().setPosition(7,0).setDirection(Direction.NORTH)
			).setRiverSpeed(0.01)
	};

	// 1-Tile Grassland
	public static GameTile[] Land11 = {
			new GrassTile( Prefab.TileGrass,
					Prefab.Tree1.duplicate().setPosition(0,0),
					Prefab.Tree1.duplicate().setPosition(1,0),
					Prefab.Tree1.duplicate().setPosition(5,0),
					Prefab.Tree1.duplicate().setPosition(7,0)
			)
	};
	public static GameTile[] Land12 = {
			new GrassTile( Prefab.TileGrass,
					Prefab.Rock2.duplicate().setPosition(0,0).setDirection(Direction.SOUTH),
					Prefab.Tree2.duplicate().setPosition(1,0),
					Prefab.Tree1.duplicate().setPosition(3,0),
					Prefab.Rock1.duplicate().setPosition(6,0),
					Prefab.Tree2.duplicate().setPosition(7,0)
			)
	};
	public static GameTile[] Land13 = {
			new GrassTile( Prefab.TileGrass,
					Prefab.Rock1.duplicate().setPosition(0,0).setDirection(Direction.SOUTH),
					Prefab.Rock2.duplicate().setPosition(1,0),
					Prefab.Tree1.duplicate().setPosition(3,0),
					Prefab.Rock1.duplicate().setPosition(7,0),
					Prefab.Tree1.duplicate().setPosition(6,0)
			)
	};

	// 2-Tile Grassland
	public static GameTile[] Land21 = {
			new GrassTile( Prefab.TileGrass,
					Prefab.Tree1.duplicate().setPosition(0,0),
					Prefab.Rock2.duplicate().setPosition(1,0),
					Prefab.Rock1.duplicate().setPosition(3,0),
					Prefab.Tree1.duplicate().setPosition(4,0),
					Prefab.Tree2.duplicate().setPosition(5,0),
					Prefab.Tree1.duplicate().setPosition(7,0)
			),
			new GrassTile( Prefab.TileGrass,
					Prefab.Rock1.duplicate().setPosition(0,0).setDirection(Direction.SOUTH),
					Prefab.Tree1.duplicate().setPosition(1,0),
					Prefab.Tree2.duplicate().setPosition(3,0),
					Prefab.Tree1.duplicate().setPosition(5,0),
					Prefab.Rock1.duplicate().setPosition(7,0)
			)
	};
	public static GameTile[] Land22 = {
			new GrassTile( Prefab.TileGrass	),
			new GrassTile( Prefab.TileGrass,
					Prefab.Tree2.duplicate().setPosition(1,0),
					Prefab.Tree1.duplicate().setPosition(3,0),
					Prefab.Tree2.duplicate().setPosition(5,0),
					Prefab.Tree1.duplicate().setPosition(7,0)
			)
	};
	public static GameTile[] Land23 = {
			new GrassTile( Prefab.TileGrass,
					Prefab.Rock1.duplicate().setPosition(2,0),
					Prefab.Tree1.duplicate().setPosition(4,0),
					Prefab.Rock2.duplicate().setPosition(5,0).setDirection(Direction.SOUTH)
			),
			new GrassTile( Prefab.TileGrass	)
	};

	// 3-Tile Grassland
	public static GameTile[] Land31 = {
			new GrassTile( Prefab.TileGrass,
					Prefab.Tree2.duplicate().setPosition(0,0),
					Prefab.Tree1.duplicate().setPosition(1,0),
					Prefab.Rock2.duplicate().setPosition(2,0),
					Prefab.Rock2.duplicate().setPosition(7,0).setDirection(Direction.SOUTH)
			),
			new GrassTile( Prefab.TileGrass,
					Prefab.Tree1.duplicate().setPosition(0,0),
					Prefab.Rock2.duplicate().setPosition(1,0),
					Prefab.Rock2.duplicate().setPosition(6,0).setDirection(Direction.SOUTH),
					Prefab.Tree1.duplicate().setPosition(7,0)
			),
			new GrassTile( Prefab.TileGrass,
					Prefab.Rock2.duplicate().setPosition(0,0),
					Prefab.Rock2.duplicate().setPosition(5,0).setDirection(Direction.SOUTH),
					Prefab.Tree1.duplicate().setPosition(6,0),
					Prefab.Tree2.duplicate().setPosition(7,0)
			)
	};
	public static GameTile[] Land32 = {
			new GrassTile( Prefab.TileGrass),
			new GrassTile( Prefab.TileGrass,
					Prefab.Rock1.duplicate().setPosition(0,0),
					Prefab.Tree1.duplicate().setPosition(1,0),
					Prefab.Tree1.duplicate().setPosition(3,0),
					Prefab.Tree1.duplicate().setPosition(4,0),
					Prefab.Tree1.duplicate().setPosition(6,0),
					Prefab.Rock2.duplicate().setPosition(7,0)
			),
			new GrassTile( Prefab.TileGrass)
	};

	// 5-Tile Grassland
	public static GameTile[] Land5 = {
			new GrassTile( Prefab.TileGrass,
					Prefab.Rock2.duplicate().setPosition(1,0),
					Prefab.Tree1.duplicate().setPosition(3,0),
					Prefab.Rock1.duplicate().setPosition(5,0),
					Prefab.Tree2.duplicate().setPosition(7,0)
			),
			new GrassTile( Prefab.TileGrass,
					Prefab.Tree1.duplicate().setPosition(1,0),
					Prefab.Tree2.duplicate().setPosition(2,0),
					Prefab.Rock2.duplicate().setPosition(3,0).setDirection(Direction.SOUTH),
					Prefab.Tree1.duplicate().setPosition(7,0)
			),
			new GrassTile( Prefab.TileGrass,
					Prefab.Tree1.duplicate().setPosition(3,0),
					Prefab.Tree2.duplicate().setPosition(4,0),
					Prefab.Tree1.duplicate().setPosition(6,0),
					Prefab.Rock1.duplicate().setPosition(7,0).setDirection(Direction.SOUTH)
			),
			new GrassTile( Prefab.TileGrass,
					Prefab.Rock2.duplicate().setPosition(0,0),
					Prefab.Tree1.duplicate().setPosition(1,0),
					Prefab.Tree1.duplicate().setPosition(7,0)
			),
			new GrassTile( Prefab.TileGrass,
					Prefab.Tree1.duplicate().setPosition(0,0),
					Prefab.Tree2.duplicate().setPosition(3,0),
					Prefab.Tree1.duplicate().setPosition(4,0),
					Prefab.Rock1.duplicate().setPosition(6,0),
					Prefab.Tree1.duplicate().setPosition(7,0)
			)
	};

	// 1-Tile Road
	public static CarTile[] Road11 = {
			new CarTile( Prefab.TileRoad,
					Prefab.Car1.duplicate().setPosition(2,0),
					Prefab.Car2.duplicate().setPosition(5,0),
					Prefab.Car1.duplicate().setPosition(8,0),
					Prefab.Car2.duplicate().setPosition(9.5,0),
					Prefab.Car3.duplicate().setPosition(12,0),
					Prefab.Car1.duplicate().setPosition(15,0)
			).setDelay(3)
	};
	public static CarTile[] Road12 = {
			new CarTile( Prefab.TileRoad,
					Prefab.Car1.duplicate().setPosition(0,0),
					Prefab.Car3.duplicate().setPosition(3,0),
					Prefab.Car3.duplicate().setPosition(4.5,0),
					Prefab.Car2.duplicate().setPosition(8,0),
					Prefab.Car1.duplicate().setPosition(9.5,0),
					Prefab.Car1.duplicate().setPosition(17.5,0)
			).setDelay(6)
	};
	public static CarTile[] Road13 = {
			new CarTile( Prefab.TileRoad,
					Prefab.Car2.duplicate().setPosition(0,0),
					Prefab.Car3.duplicate().setPosition(1.5,0),
					Prefab.Car1.duplicate().setPosition(3,0),
					Prefab.Car3.duplicate().setPosition(5,0),
					Prefab.Car2.duplicate().setPosition(12,0),
					Prefab.Car3.duplicate().setPosition(13.5,0),
					Prefab.Car1.duplicate().setPosition(19,0),
					Prefab.Car2.duplicate().setPosition(20.5,0),
					Prefab.Car3.duplicate().setPosition(23,0)
			).setDelay(12)
	};

	// 1-Tile River
	public static GameTile[] River11 = {
			new RiverTile( Prefab.TileRiver,
					Prefab.Log.duplicate().setPosition(0,0),
					Prefab.Log.duplicate().setPosition(5,0),
					Prefab.Log.duplicate().setPosition(7.5,0)
			).setDelay(6)
	};
	public static GameTile[] River12 = {
			new RiverTile( Prefab.TileRiver,
					Prefab.Log.duplicate().setPosition(0,0),
					Prefab.Log.duplicate().setPosition(2.5,0),
					Prefab.Log.duplicate().setPosition(9,0),
					Prefab.Log.duplicate().setPosition(12,0),
					Prefab.Log.duplicate().setPosition(13,0),
					Prefab.Log.duplicate().setPosition(16,0),
					Prefab.Log.duplicate().setPosition(19,0),
					Prefab.Log.duplicate().setPosition(22.5,0)
			).setDelay(12)
	};
	public static GameTile[] River13 = {
			new RiverTile( Prefab.TileRiver,
					Prefab.LilyPad1.duplicate().setPosition(2,0).setDirection(Direction.SOUTH),
					Prefab.LilyPad1.duplicate().setPosition(3,0),
					Prefab.LilyPad1.duplicate().setPosition(4,0).setDirection(Direction.WEST),
					Prefab.LilyPad2.duplicate().setPosition(6,0).setDirection(Direction.EAST)
			)
	};
}
