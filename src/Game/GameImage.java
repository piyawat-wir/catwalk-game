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

public class GameImage extends ImageIcon {
	public static GameImage tile_grass = new GameImage("resources/img/obj/tile_grass.png");
	public static GameImage tile_river1 = new GameImage("resources/img/obj/tile_river1.png");
	public static GameImage tile_river2 = new GameImage("resources/img/obj/tile_river2.png");
	public static GameImage tile_road = new GameImage("resources/img/obj/tile_road.png");
	public static GameImage tree1 = new GameImage("resources/img/obj/tree1.png");
	public static GameImage tree2 = new GameImage("resources/img/obj/tree2.png");
	public static GameImage rock1 = new GameImage("resources/img/obj/rock1.png");
	public static GameImage rock11 = new GameImage("resources/img/obj/rock11.png");
	public static GameImage rock2 = new GameImage("resources/img/obj/rock2.png");
	public static GameImage rock21 = new GameImage("resources/img/obj/rock21.png");
	public static GameImage log1 = new GameImage("resources/img/obj/log1.png");
	public static GameImage log2 = new GameImage("resources/img/obj/log2.png");
	public static GameImage lilypad1 = new GameImage("resources/img/obj/lilypad1.png");
	public static GameImage lilypad2 = new GameImage("resources/img/obj/lilypad2.png");
	public static GameImage lilypad3 = new GameImage("resources/img/obj/lilypad3.png");
	public static GameImage lilypad4 = new GameImage("resources/img/obj/lilypad4.png");
	public static GameImage lilypad5 = new GameImage("resources/img/obj/lilypad5.png");
	public static GameImage lilypad6 = new GameImage("resources/img/obj/lilypad6.png");
	public static GameImage lilypad7 = new GameImage("resources/img/obj/lilypad7.png");
	public static GameImage lilypad8 = new GameImage("resources/img/obj/lilypad8.png");
	public static GameImage coin = new GameImage("resources/img/obj/coin.png");
	public static GameImage cat1n = new GameImage("resources/img/obj/cat1n.png");
	public static GameImage cat1s = new GameImage("resources/img/obj/cat1s.png");
	public static GameImage cat1e = new GameImage("resources/img/obj/cat1e.png");
	public static GameImage cat1w = new GameImage("resources/img/obj/cat1w.png");
	public static GameImage cat2n = new GameImage("resources/img/obj/cat2n.png");
	public static GameImage cat2s = new GameImage("resources/img/obj/cat2s.png");
	public static GameImage cat2e = new GameImage("resources/img/obj/cat2e.png");
	public static GameImage cat2w = new GameImage("resources/img/obj/cat2w.png");
	public static GameImage cat3n = new GameImage("resources/img/obj/cat3n.png");
	public static GameImage cat3s = new GameImage("resources/img/obj/cat3s.png");
	public static GameImage cat3e = new GameImage("resources/img/obj/cat3e.png");
	public static GameImage cat3w = new GameImage("resources/img/obj/cat3w.png");
	public static GameImage cat4n = new GameImage("resources/img/obj/cat4n.png");
	public static GameImage cat4s = new GameImage("resources/img/obj/cat4s.png");
	public static GameImage cat4e = new GameImage("resources/img/obj/cat4e.png");
	public static GameImage cat4w = new GameImage("resources/img/obj/cat4w.png");
	public static GameImage cat5n = new GameImage("resources/img/obj/cat5n.png");
	public static GameImage cat5s = new GameImage("resources/img/obj/cat5s.png");
	public static GameImage cat5e = new GameImage("resources/img/obj/cat5e.png");
	public static GameImage cat5w = new GameImage("resources/img/obj/cat5w.png");
	public static GameImage car1e = new GameImage("resources/img/obj/car1e.png");
	public static GameImage car1w = new GameImage("resources/img/obj/car1w.png");
	public static GameImage car2e = new GameImage("resources/img/obj/car2e.png");
	public static GameImage car2w = new GameImage("resources/img/obj/car2w.png");
	public static GameImage car3e = new GameImage("resources/img/obj/car3e.png");
	public static GameImage car3w = new GameImage("resources/img/obj/car3w.png");
	public static GameImage splash = new GameImage("resources/img/obj/splash.png");
	public static GameImage corpse = new GameImage("resources/img/obj/corpse.png");

	public GameImage(Image img) { super(img); }
	public GameImage(String filename) { super(filename); }
	public GameImage resize(int width, int height) {
		return new GameImage(this.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH));
	}
}

