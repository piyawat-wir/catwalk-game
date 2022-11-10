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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import javax.swing.*;

public class ScoreBoard extends JFrame {

	private JTextArea textArea;
	private JLabel contentpane, text, textAreaLabel;
	private ImageIcon backgroundImg, buttonImg;
	private JButton backButton;
	private Stream<Score> myStream;
	private JFrame backFrame;
	
	private final int frameWidth;
	private final int frameHeight;
	private String fileName = "save_score_";

	private SoundController soundCtrl;
	
	public static ArrayList<Score> scoreList = new ArrayList<Score>();
	
	public ScoreBoard(int w, int h, String title) {
		frameWidth = w; frameHeight = h;
		setTitle(title + " | Scoreboard");
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
		backgroundImg = new GameImage("resources/img/gui/scorebg_resize.png").resize(frameWidth, frameHeight);
		buttonImg = new GameImage("resources/img/gui/button.png").resize(150, 50);
		
		//Set background
        setContentPane(contentpane = new JLabel());
        contentpane.setIcon(backgroundImg);
    	contentpane.setLayout( null );
    	
    	//Title of screen
    	text = new JLabel();
    	text.setText("SCOREBOARD");
		text.setHorizontalAlignment(JLabel.CENTER);
		text.setBounds(halfW-400,20,800,100);
		text.setFont(new Font("Tahoma",Font.BOLD,90));
    	text.setForeground(Color.WHITE);
    	contentpane.add(text);
    	
    	//set text area
    	textAreaLabel = new JLabel();
    	textAreaLabel.setBounds(halfW-400, 200, 800, 800);
    	textAreaLabel.setLayout(new FlowLayout());
    	textArea = new JTextArea(10, 40);
   	 	textArea.setFont(new Font("MV Boli",Font.BOLD,20));
    	textArea.setLineWrap(true);
    	textArea.setText(String.format("%-48s %s\n","NAME","SCORE"));
    	textArea.setWrapStyleWord(true);
    	textArea.setEditable(false);
    	textAreaLabel.add(new JScrollPane(textArea));
   	 	contentpane.add(textAreaLabel);
   	 	validate();
   	 	
    	//set Back Button
    	backButton = new JButton();
    	backButton.setIcon(buttonImg);
    	backButton.setBounds(halfW-75, 600, 150, 50);
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

		repaint();
	}

	public void showScore(int difficulty) {

		scoreList.clear();
		try { FileManager.fileReader(fileName + difficulty + ".txt"); }
		catch (IOException e) { failedToAccessScoreboard(); }

		myStream = scoreList.stream();
		List<Score> sortedScoreList = myStream.sorted().toList();
		textArea.setText(String.format("%-48s %s\n","NAME","SCORE"));
		sortedScoreList.stream().forEach(arg -> {
			textArea.append( String.format("%-50s %-4d\n", arg.getPlayerName(), arg.getScorePoint()) );
		});
		setVisible(true);
	}
	public void addScore(int difficulty, String playerName, int score) {

		try { FileManager.fileWriter(fileName + difficulty + ".txt", playerName, score); }
		catch (IOException e) { System.err.println("Scoreboard cannot be accessed."); }
	}
	private void failedToAccessScoreboard() {
		JOptionPane.showMessageDialog(this,"");
		setVisible(false);
	}

	public void linkFrame(JFrame back) {
		backFrame = back;
	}
	public void setSoundController(SoundController sctrl) { soundCtrl = sctrl; }

	public void switchFrame(JFrame frame) { setVisible(false); frame.setVisible(true); }
}
