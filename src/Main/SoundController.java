/*
	Java Project 3 | Catwalk Game
	Created by:
		6313124 Chanawee Sateinteeraphap
		6313125 Chayakorn Jullanee
		6313132 Piyawat Wirotkitphaiboon
*/

package Main;

public class SoundController {

	public static MySoundEffect SFX_GameStart = new MySoundEffect("resources/sounds/button_start.wav");
	public static MySoundEffect SFX_ButtonClick = new MySoundEffect("resources/sounds/button_click.wav");
	public static MySoundEffect SFX_PlayerMove = new MySoundEffect("resources/sounds/player_move.wav");
	public static MySoundEffect SFX_CarHorn = new MySoundEffect("resources/sounds/car_horn.wav");
	public static MySoundEffect SFX_CatHit = new MySoundEffect("resources/sounds/cat_hit.wav");
	public static MySoundEffect SFX_CatDrown = new MySoundEffect("resources/sounds/cat_drown.wav");
	public static MySoundEffect SFX_Coin = new MySoundEffect("resources/sounds/coin.wav");
	public static MySoundEffect SFX_WaterSplash = new MySoundEffect("resources/sounds/water_splash.wav");
	public static MySoundEffect SFX_WaterJump = new MySoundEffect("resources/sounds/water_jump.wav");
	public static MySoundEffect BGM_Menu = new MySoundEffect("resources/sounds/BGM_Menu.wav");

	private MySoundEffect BGM;
	private float BGMvolume = -37f, SFXvolume = -37f;
	private boolean BGMmute = false, SFXmute = false;

	public SoundController() {}

	public void setBGMmute(boolean b) { BGMmute = b; if (BGMmute) BGM.stop(); else BGM.playLoop(); }
	public void setSFXmute(boolean b) { SFXmute = b; }

	public void setBGM(MySoundEffect bgm) {
		BGM = bgm;
		BGM.setCurrentVolume(BGMvolume);
	}
	public void playBGM() { BGM.playLoop(); }
	public void stopBGM() { BGM.stop(); }
	public void adjustBGMVolume(float volume) {
		BGMvolume = volume;
		BGM.setCurrentVolume(volume);
	}
	public void adjustSFXVolume(float volume) { SFXvolume = volume; }
	public void playSFX(MySoundEffect sfx) {
		if (!SFXmute) {
			sfx.setCurrentVolume(SFXvolume);
			sfx.playOnce();
		}
	}
}
