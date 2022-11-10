/*
	Java Project 3 | Catwalk Game
	Created by:
		6313124 Chanawee Sateinteeraphap
		6313125 Chayakorn Jullanee
		6313132 Piyawat Wirotkitphaiboon
*/

package Main;
import Game.GameImage;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Setting extends JFrame {
	
	private JSlider volumeSlider, volumeSlider2;
	private JLabel contentpane, text, bgmLabel, sfxLabel;
	private JCheckBox muteCheckBox, muteCheckBox2;
	private ImageIcon backgroundImg, buttonImg, BGMImg, SFXImg, xIcon, selectIcon;
	private JButton backButton;
	private JFrame backFrame;
	private final int frameWidth;
	private final int frameHeight;

	private SoundController soundCtrl;
	
	public Setting(int w, int h, String title) {
		frameWidth = w; frameHeight = h;
		setTitle(title + " | Settings");
        setBounds(50, 50, frameWidth, frameHeight);
        setResizable(false);
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
		setLocationRelativeTo(null);
		
    	AddComponents(); 
	}
	public void AddComponents() {

		int halfW = frameWidth/2;
		int halfH = frameHeight/2;
		
		//Create MyImageIcon
		backgroundImg = new GameImage("resources/img/gui/SmoothBg.jpg").resize(frameWidth, frameHeight);
		buttonImg = new GameImage("resources/img/gui/button.png");
		BGMImg = new GameImage("resources/img/gui/icon_bgm.png");
		SFXImg = new GameImage("resources/img/gui/icon_sfx.png").resize(120, 120);
		xIcon = new GameImage("resources/img/gui/xicon.png");
    	selectIcon = new GameImage("resources/img/gui/selectedIcon.png");
		
		//Set background
        setContentPane(contentpane = new JLabel());
        contentpane.setIcon(backgroundImg);
    	contentpane.setLayout( null );
    	
    	//Title of screen
    	text = new JLabel();
    	text.setText("SETTING");
		text.setHorizontalAlignment(JLabel.CENTER);
		text.setBounds(halfW-400,20,800,100);
		text.setFont(new Font("Tahoma",Font.BOLD,90));
    	contentpane.add(text);
    	
    	//Set sfxLabel (Icon , text , Slider , CheckBox)
    	sfxLabel = new JLabel();
    	sfxLabel.setLayout(new FlowLayout());
    	sfxLabel.setIcon(SFXImg);
    	sfxLabel.setText("SFX");
    	sfxLabel.setFont(new Font("MV Boli",Font.BOLD,50));
    	sfxLabel.setBounds(halfW-400, 200, 800, 100);
    	sfxLabel.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 3));
    	
    	volumeSlider = new JSlider(-80, 6);
    	volumeSlider.setBounds(260, 15, 250, 70);
    	volumeSlider.setOpaque(false);
    	volumeSlider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				soundCtrl.adjustSFXVolume(volumeSlider.getValue());
				soundCtrl.playSFX(SoundController.SFX_ButtonClick);
				System.out.println("SFX volume: "+volumeSlider.getValue());
			}
		});
    	sfxLabel.add(volumeSlider);
    	
    	muteCheckBox = new JCheckBox();
    	muteCheckBox.setText("Mute");
    	muteCheckBox.setFocusable(false);
    	muteCheckBox.setOpaque(false);
    	muteCheckBox.setFont(new Font("MV Boli",Font.BOLD,25));
    	muteCheckBox.setIcon(xIcon);
    	muteCheckBox.setSelectedIcon(selectIcon);
    	muteCheckBox.setBounds(600, 15, 150, 70);
		muteCheckBox.addItemListener(e -> {
			soundCtrl.setSFXmute(e.getStateChange() == 1);
			soundCtrl.playSFX(SoundController.SFX_ButtonClick);
		});

		sfxLabel.add(muteCheckBox);
		contentpane.add(sfxLabel); // add all(Icon , text , Slider , CheckBox) to sfxLabel
    	
    	//Set bgmLabel (Icon , text , Slider , CheckBox)
    	bgmLabel = new JLabel();
    	bgmLabel.setLayout(new FlowLayout());
    	bgmLabel.setIcon(BGMImg);
    	bgmLabel.setText("BGM");
    	bgmLabel.setFont(new Font("MV Boli",Font.BOLD,50));
    	bgmLabel.setBounds(halfW-400, 350, 800, 100);
    	bgmLabel.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 3));
    	
    	volumeSlider2 = new JSlider(-80, 6);
    	volumeSlider2.setBounds(260, 15, 250, 70);
    	volumeSlider2.setOpaque(false);
    	volumeSlider2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {

				soundCtrl.adjustBGMVolume(volumeSlider2.getValue());
				soundCtrl.playSFX(SoundController.SFX_ButtonClick);
				System.out.println("BGM volume: "+volumeSlider2.getValue());
			}
		});
    	bgmLabel.add(volumeSlider2);
    	
    	muteCheckBox2 = new JCheckBox();
    	muteCheckBox2.setText("Mute");
    	muteCheckBox2.setFocusable(false);
    	muteCheckBox2.setOpaque(false);
    	muteCheckBox2.setFont(new Font("MV Boli",Font.BOLD,25));
    	muteCheckBox2.setIcon(xIcon);
    	muteCheckBox2.setSelectedIcon(selectIcon);
    	muteCheckBox2.setBounds(600, 15, 150, 70);
		muteCheckBox2.addItemListener(e -> {
			soundCtrl.playSFX(SoundController.SFX_ButtonClick);
			soundCtrl.setBGMmute(e.getStateChange() == 1);
		});

    	bgmLabel.add(muteCheckBox2);
    	contentpane.add(bgmLabel); // add all(Icon , text , Slider , CheckBox) to bgmLabel
    	
    	//set Back Button
    	backButton = new JButton();
    	backButton.setIcon(buttonImg);
    	backButton.setBounds(halfW-95, 500, 190, 90);
    	backButton.setText("Back");
    	backButton.setHorizontalTextPosition(JButton.CENTER);
    	backButton.setVerticalTextPosition(JButton.CENTER);
    	backButton.setFont(new Font("MV Boli",Font.BOLD,40));
    	backButton.setFocusable(false);
    	backButton.setBackground(Color.PINK);
    	backButton.setBorder(BorderFactory.createEtchedBorder());
		contentpane.add(backButton);
		backButton.addActionListener(e -> {
			soundCtrl.playSFX(SoundController.SFX_ButtonClick);
			switchFrame(backFrame);
		});

		validate();
		repaint();
    	
	}

	public void linkFrame(JFrame back) {
		backFrame = back;
	}
	public void switchFrame(JFrame frame) { setVisible(false); frame.setVisible(true); }
	public void setSoundController(SoundController sctrl) { soundCtrl = sctrl; }

}
