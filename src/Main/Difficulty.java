/*
	Java Project 3 | Catwalk Game
	Created by:
		6313124 Chanawee Sateinteeraphap
		6313125 Chayakorn Jullanee
		6313132 Piyawat Wirotkitphaiboon
*/

package Main;
import Game.Game;
import Game.GameImage;
import Game.GameObject;
import Game.Prefab;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.*;

public class Difficulty extends JFrame {

	private JRadioButton easyButton, normalButton, hardButton;
	private ButtonGroup group;
	private JLabel contentpane, text, radioLabel;
	private ImageIcon backgroundImg, buttonImg, xIcon, selectIcon;
	private JButton startButton;
	private JFrame gameFrame;
	private CatSelection charFrame;
	private GameOver overFrame;
	private final int frameWidth;
	private final int frameHeight;
	private int difficultyLevel; // 0 easy, 1 normal, 2 hard

	private SoundController soundCtrl;

	public Difficulty(int w, int h, String title) {
		frameWidth = w;
		frameHeight = h;
		setTitle(title + " | Choose Difficulty! ");
		setBounds(50, 50, frameWidth, frameHeight);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		AddComponents();
	}

	public void AddComponents() {
		int halfW = frameWidth / 2;
		int halfH = frameHeight / 2;
		//Create MyImageIcon
		backgroundImg = new GameImage("resources/img/gui/bg.jpg").resize(frameWidth, frameHeight);
		buttonImg = new GameImage("resources/img/gui/button.png");
		xIcon = new GameImage("resources/img/gui/xicon.png");
		selectIcon = new GameImage("resources/img/gui/selectedIcon.png");

		//Set background
		setContentPane(contentpane = new JLabel());
		contentpane.setIcon(backgroundImg);
		contentpane.setLayout(null);

		//Title of screen
		text = new JLabel();
		text.setText("DIFFICULTY");
		text.setHorizontalAlignment(JLabel.CENTER);
		text.setBounds(halfW - 400, 20, 800, 100);
		text.setFont(new Font("Tahoma", Font.BOLD, 90));
		contentpane.add(text);

		//Set radio Button
		easyButton = new JRadioButton("EASY ");
		easyButton.setFont(new Font("MV Boli", Font.BOLD, 50));
		easyButton.setOpaque(false);
		easyButton.setFocusable(false);
		easyButton.setForeground(new Color(0x006600));
		easyButton.setIcon(xIcon);
		easyButton.setSelectedIcon(selectIcon);
		normalButton = new JRadioButton("NORMAL ");
		normalButton.setFont(new Font("MV Boli", Font.BOLD, 50));
		normalButton.setOpaque(false);
		normalButton.setFocusable(false);
		normalButton.setForeground(new Color(0x0047b3));
		normalButton.setIcon(xIcon);
		normalButton.setSelectedIcon(selectIcon);
		hardButton = new JRadioButton("HARD ");
		hardButton.setFont(new Font("MV Boli", Font.BOLD, 50));
		hardButton.setOpaque(false);
		hardButton.setFocusable(false);
		hardButton.setForeground(new Color(0xb30059));
		hardButton.setIcon(xIcon);
		hardButton.setSelectedIcon(selectIcon);

		group = new ButtonGroup();
		group.add(easyButton);
		group.add(normalButton);
		group.add(hardButton);

		easyButton.addActionListener(e -> {
			difficultyLevel = 1;
			if (!startButton.isEnabled()) startButton.setEnabled(true);
			soundCtrl.playSFX(SoundController.SFX_ButtonClick);
		});
		normalButton.addActionListener(e -> {
			difficultyLevel = 2;
			if (!startButton.isEnabled()) startButton.setEnabled(true);
			soundCtrl.playSFX(SoundController.SFX_ButtonClick);
		});
		hardButton.addActionListener(e -> {
			difficultyLevel = 3;
			if (!startButton.isEnabled()) startButton.setEnabled(true);
			soundCtrl.playSFX(SoundController.SFX_ButtonClick);
		});

		//set radio Label
		radioLabel = new JLabel();
		radioLabel.setLayout(new FlowLayout());
		radioLabel.setHorizontalAlignment(JLabel.CENTER);
		radioLabel.setBounds(0, 300, frameWidth, 200);
		radioLabel.add(easyButton);
		radioLabel.add(normalButton);
		radioLabel.add(hardButton);
		contentpane.add(radioLabel);


		//set Start Button
		startButton = new JButton();
		startButton.setIcon(buttonImg);
		startButton.setBounds(halfW - 95, 500, 190, 90);
		startButton.setText("Start");
		startButton.setHorizontalTextPosition(JButton.CENTER);
		startButton.setVerticalTextPosition(JButton.CENTER);
		startButton.setFont(new Font("MV Boli", Font.BOLD, 40));
		startButton.setFocusable(false);
		startButton.setBackground(Color.PINK);
		startButton.setBorder(BorderFactory.createEtchedBorder());
		startButton.setEnabled(false);
		contentpane.add(startButton);
		startButton.addActionListener(e -> {
			Game game = new Game(gameFrame);
			game.setDifficulty(difficultyLevel);
			game.setOverFrame(overFrame);
			game.setSoundController(soundCtrl);

			GameObject[] charList = { Prefab.Cat1, Prefab.Cat2, Prefab.Cat3, Prefab.Cat4, Prefab.Cat5 };
			game.setPlayer( charList[charFrame.getCatSelectedIndex()] );

			soundCtrl.playSFX(SoundController.SFX_GameStart);
			game.start();
			switchFrame(gameFrame);
		});
		validate();
		repaint();
	}

	public void linkFrame(JFrame game, GameOver over, CatSelection charsel ) {
		gameFrame = game; overFrame = over; charFrame = charsel;
	}
	public void setSoundController(SoundController sctrl) { soundCtrl = sctrl; }

	public void switchFrame(JFrame frame) { setVisible(false); frame.setVisible(true); }
}

