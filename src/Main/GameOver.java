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

public class GameOver extends JFrame {

		private JTextField nameTextField;
		private JLabel contentpane, askNameLabel;
		private ImageIcon backgroundImg, buttonImg;
		private JButton doneButton;
		private ScoreBoard nextFrame;

		private final int frameWidth;
		private final int frameHeight;
		private String playerName;

		private int difficulty = 1;
		private int score = 0;

		private SoundController soundCtrl;
		
		public GameOver(int w, int h, String title) {
			frameWidth = w; frameHeight = h;
			setTitle(title + " | Game Over!");
	        setBounds(50, 50, frameWidth, frameHeight);
	        setResizable(false);
	        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
			
	    	AddComponents(); 
		}
		public void AddComponents() {
			
			//Create MyImageIcon
			backgroundImg = new GameImage("resources/img/gui/game_over_bg.jpg").resize(frameWidth, frameHeight);
			buttonImg = new GameImage("resources/img/gui/button.png").resize(150, 50);
			
			//Set background
	        setContentPane(contentpane = new JLabel());
	        contentpane.setIcon(backgroundImg);
	    	contentpane.setLayout( null );
	    	
	    	//set Done Button
	    	doneButton = new JButton();
	    	doneButton.setIcon(buttonImg);
	    	doneButton.setBounds(850, 550, 150, 50);
	    	doneButton.setText("Done");
	    	doneButton.setHorizontalTextPosition(JButton.CENTER);
	    	doneButton.setVerticalTextPosition(JButton.CENTER);
	    	doneButton.setFont(new Font("MV Boli",Font.BOLD,40));
	    	doneButton.addActionListener(e -> {
				playerName = nameTextField.getText();
				nextFrame.addScore(difficulty, playerName, score);
				nextFrame.showScore(difficulty);
				soundCtrl.playSFX(SoundController.SFX_ButtonClick);
				switchFrame(nextFrame);
			});
	    	doneButton.setFocusable(false);
	    	doneButton.setBackground(Color.PINK);
	    	doneButton.setBorder(BorderFactory.createEtchedBorder());
			contentpane.add(doneButton);
	    	
			//set name text field
	    	nameTextField = new JTextField();
	    	nameTextField.setBounds(300, 550, 450, 50);
	    	nameTextField.setFont(new Font("Consolas",Font.PLAIN,35));
	    	nameTextField.setForeground(new Color(0x00FF00));
	    	nameTextField.setBackground(Color.black);
	    	nameTextField.setCaretColor(Color.white);
	    	nameTextField.setText("Satoshi");
	    	contentpane.add(nameTextField);
	    	
	    	//Ask name label
	    	askNameLabel = new JLabel();
	    	askNameLabel.setText("Enter Your Name");
	    	askNameLabel.setFont(new Font("MV Boli",Font.BOLD,40));
	    	askNameLabel.setForeground(new Color(0x00FF00));
	    	askNameLabel.setBounds(300, 400, 400, 200);
	    	contentpane.add(askNameLabel);
	    	repaint();
		}

		public void linkFrame(ScoreBoard next) {
			nextFrame = next;
		}
		public void setSoundController(SoundController sctrl) { soundCtrl = sctrl; }

		public void switchFrame(JFrame frame) { setVisible(false); frame.setVisible(true); }
		public void reportScore(int diff, int sc) {
			difficulty = diff; score = sc;
		}
}

