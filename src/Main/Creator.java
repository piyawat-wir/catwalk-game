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
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Creator extends JFrame {

	private JLabel contentpane, text;
	private JLabel[] devname;
	private ImageIcon backgroundImg, buttonImg;
	private JButton okButton;
	private JFrame backFrame;

	private final int frameWidth;
	private final int frameHeight;
	private final int catSize = 600;
	private int catSelectedIndex = 0; // 0 to 4

	private SoundController soundCtrl;

	public Creator(int w, int h, String title) {
		frameWidth = w; frameHeight = h;
		setTitle(title + " | Creator");
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

		//Set background
		setContentPane(contentpane = new JLabel());
		contentpane.setIcon(backgroundImg);
		contentpane.setLayout( null );

		//Title of screen
		text = new JLabel();
		text.setText("CREATOR TEAM");
		text.setHorizontalAlignment(JLabel.CENTER);
		text.setBounds(halfW-400,20,800,100);
		text.setFont(new Font("Tahoma",Font.BOLD,90));
		contentpane.add(text);

		//set Button
		okButton = new JButton();
		okButton.setIcon(buttonImg);
		okButton.setBounds(halfW-95, frameHeight-150-90, 190, 90);
		okButton.setText("Okay");
		okButton.setHorizontalTextPosition(JButton.CENTER);
		okButton.setVerticalTextPosition(JButton.CENTER);
		okButton.setFont(new Font("MV Boli",Font.BOLD,40));
		okButton.setFocusable(false);
		okButton.setBackground(Color.PINK);
		okButton.setBorder(BorderFactory.createEtchedBorder());
		contentpane.add(okButton);
		okButton.addActionListener(e -> {
			soundCtrl.playSFX(SoundController.SFX_ButtonClick);
			switchFrame(backFrame);
		});

		//Set Name
		devname = new JLabel[3];
		String[] name = {
				"Chanawee Sateinteeraphap   6313124",
				"Chayakorn Jullanee          6313125",
				"Piyawat Wirotkitphaiboon   6313132"
		};
		for (int i = 0; i < devname.length; i++) {
			devname[i] = new JLabel();
			devname[i].setBounds(0, 200+60*i, frameWidth, 90);
			devname[i].setText(name[i]);
			devname[i].setHorizontalAlignment(JLabel.CENTER);
			devname[i].setFont(new Font("MV Boli",Font.BOLD,40));
			devname[i].setFocusable(false);
			contentpane.add(devname[i]);
		}

		repaint();
		contentpane.validate();
	}

	public void linkFrame(JFrame back) {
		backFrame = back;
	}
	public void setSoundController(SoundController sctrl) { soundCtrl = sctrl; }

	public void switchFrame(JFrame frame) { setVisible(false); frame.setVisible(true); }
}
