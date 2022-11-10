/*
	Java Project 3 | Catwalk Game
	Created by:
		6313124 Chanawee Sateinteeraphap
		6313125 Chayakorn Jullanee
		6313132 Piyawat Wirotkitphaiboon
*/

package Main;
import Game.GameImage;

import java.awt.*;
import javax.swing.*;

public class Manu extends JFrame {
	
	private JLabel contentpane,logo;
	private JButton charselectButton, creatorButton, settingButton, playButton;
	private ImageIcon charselectIcon, creatorIcon, settingIcon, backgroundIcon, logoIcon, playIcon;
	private JFrame charFrame, creatorFrame, settingFrame, difficultyFrame;
	private final int frameWidth;
	private final int frameHeight;

	private SoundController soundCtrl;
	
	public Manu(int w, int h, String title) {
		frameWidth = w; frameHeight = h;
		setTitle(title + " | Manu");
        setBounds(50, 50, frameWidth, frameHeight);
        setResizable(false);
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
		setLocationRelativeTo(null);
		
    	AddComponents();    
	}
	public void AddComponents() {

		int halfW = frameWidth/2;
		int halfH = frameHeight/2;

		//Create ImageIcon
		backgroundIcon = new GameImage("resources/img/gui/bg.jpg");
		logoIcon = new GameImage("resources/img/gui/Logo.png");
		charselectIcon = new GameImage("resources/img/gui/icon_charselect.png");
		creatorIcon = new GameImage("resources/img/gui/icon_creator.png");
		settingIcon = new GameImage("resources/img/gui/icon_settings.png");
		playIcon = new GameImage("resources/img/gui/button.png");
		
		//Set background
        setContentPane(contentpane = new JLabel());
        contentpane.setIcon(backgroundIcon);
    	contentpane.setLayout( null );

    	//Set Logo
    	logo = new JLabel();
    	logo.setIcon(logoIcon);
    	logo.setBounds(halfW-logoIcon.getIconWidth()/2, 50, logoIcon.getIconWidth(), logoIcon.getIconHeight());
    	contentpane.add(logo);
    	
    	//Set playButton
    	playButton = new JButton();
    	playButton.setIcon(playIcon);
    	playButton.setBounds(halfW-95, 380, 190, 90);
    	playButton.setText("Play");
    	playButton.setHorizontalTextPosition(JButton.CENTER);
    	playButton.setVerticalTextPosition(JButton.CENTER);
    	playButton.setFont(new Font("MV Boli",Font.BOLD,40));
    	playButton.setFocusable(false);
    	playButton.setBackground(Color.PINK);
    	playButton.setBorder(BorderFactory.createEtchedBorder());
		contentpane.add(playButton);
		playButton.addActionListener(e -> {
			soundCtrl.playSFX(SoundController.SFX_ButtonClick);
			switchFrame(difficultyFrame);
		});
    	
    	//Set CharSelectButton
    	charselectButton = new JButton();
    	charselectButton.setIcon(charselectIcon);
    	charselectButton.setBounds(50, frameHeight-150, 100, 100);
    	charselectButton.setFocusable(false);
    	charselectButton.setBackground(Color.PINK);
    	charselectButton.setBorder(BorderFactory.createEtchedBorder());
		contentpane.add(charselectButton);
		charselectButton.addActionListener(e -> {
			soundCtrl.playSFX(SoundController.SFX_ButtonClick);
			switchFrame(charFrame);
		});
		
		//Set CreatorButton
		creatorButton = new JButton();
		creatorButton.setIcon(creatorIcon);
		creatorButton.setBounds(frameWidth-250, frameHeight-150, 100, 100);
		creatorButton.setFocusable(false);
		creatorButton.setBackground(Color.PINK);
		creatorButton.setBorder(BorderFactory.createEtchedBorder());
		contentpane.add(creatorButton);
		creatorButton.addActionListener(e -> {
			soundCtrl.playSFX(SoundController.SFX_ButtonClick);
			switchFrame(creatorFrame);
		});
		
		//Set SettingButton
		settingButton = new JButton();
		settingButton.setIcon(settingIcon);
		settingButton.setBounds(frameWidth-150, frameHeight-150, 100, 100);
		settingButton.setFocusable(false);
		settingButton.setBackground(Color.PINK);
		settingButton.setBorder(BorderFactory.createEtchedBorder());
		contentpane.add(settingButton);
		settingButton.addActionListener(e -> {
			soundCtrl.playSFX(SoundController.SFX_ButtonClick);
			switchFrame(settingFrame);
		});

		validate();
		repaint();
		
	}

	public void linkFrame(JFrame charsel, JFrame creator, JFrame setting, JFrame diff) {
		charFrame = charsel;
		creatorFrame = creator;
		settingFrame = setting;
		difficultyFrame = diff;
	}
	public void setSoundController(SoundController sctrl) { soundCtrl = sctrl; }

	public void switchFrame(JFrame frame) { setVisible(false); frame.setVisible(true); }

}
