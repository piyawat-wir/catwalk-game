/*
	Java Project 3 | Catwalk Game
	Created by:
		6313124 Chanawee Sateinteeraphap
		6313125 Chayakorn Jullanee
		6313132 Piyawat Wirotkitphaiboon
*/

package Main;

import javax.swing.*;

public class Main {

	public static final int frameWidth = 1280;//1366;
	public static final int frameHeight = 720;//768;
	public static final String gameName = "Catwalk >w<";

	public static SoundController soundCtrl = new SoundController();

	public static void main(String[] args) {

		//Initialize gui frames
		Manu manu = new Manu(frameWidth, frameHeight, gameName);
		CatSelection catSelection = new CatSelection(frameWidth, frameHeight, gameName);
		Setting setting = new Setting(frameWidth, frameHeight, gameName);
		Difficulty difficulty = new Difficulty(frameWidth, frameHeight, gameName);
		GameOver gameOver = new GameOver(frameWidth, frameHeight, gameName);
		ScoreBoard scoreboard = new ScoreBoard(frameWidth, frameHeight, gameName);
		Creator creator = new Creator(frameWidth, frameHeight, gameName);
		JFrame gameFrame = new JFrame();
		gameFrame.setSize(frameWidth, frameHeight);
		gameFrame.setResizable(false);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setTitle(gameName);

		//Link frames and set sound system to each frame
		manu.linkFrame(catSelection, creator, setting, difficulty);
		manu.setSoundController(soundCtrl);
		difficulty.linkFrame(gameFrame, gameOver, catSelection);
		difficulty.setSoundController(soundCtrl);
		catSelection.linkFrame(manu);
		catSelection.setSoundController(soundCtrl);
		setting.linkFrame(manu);
		setting.setSoundController(soundCtrl);
		gameOver.linkFrame(scoreboard);
		gameOver.setSoundController(soundCtrl);
		scoreboard.linkFrame(manu);
		scoreboard.setSoundController(soundCtrl);
		creator.linkFrame(manu);
		creator.setSoundController(soundCtrl);

		//Set and play default BGM
		soundCtrl.setBGM(SoundController.BGM_Menu);
		soundCtrl.playBGM();

		//Display first game menu frame
		manu.setVisible(true);
	}
}
