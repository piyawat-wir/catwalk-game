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

public class CatSelection extends JFrame {
	
	private JLabel contentpane, text;
	private JLabel[] cat;
	private JComboBox comboBox;
	private ImageIcon backgroundImg, buttonImg;
	private ImageIcon[] catImg;
	private JButton okButton;
	private JFrame backFrame;

	private SoundController soundCtrl;

	private final int frameWidth;
	private final int frameHeight;
	private final int catSize = 600;
	private int catSelectedIndex = 0; // 0 to 4
	
	public CatSelection(int w, int h, String title) {
		frameWidth = w; frameHeight = h;
		setTitle(title + " | Cat Selection");
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
		catImg = new ImageIcon[] {
				GameImage.cat1s.resize(catSize, catSize),
				GameImage.cat2s.resize(catSize, catSize),
				GameImage.cat3s.resize(catSize, catSize),
				GameImage.cat4s.resize(catSize, catSize),
				GameImage.cat5s.resize(catSize, catSize)
		};
		buttonImg = new GameImage("resources/img/gui/button.png");
		
		//Set background
        setContentPane(contentpane = new JLabel());
        contentpane.setIcon(backgroundImg);
    	contentpane.setLayout( null );
    	
    	//Title of screen
    	text = new JLabel();
    	text.setText("CAT SELECTION");
		text.setHorizontalAlignment(JLabel.CENTER);
    	text.setBounds(halfW-400,20,800,100);
    	text.setFont(new Font("Tahoma",Font.BOLD,90));
    	contentpane.add(text);
    	
    	//set Button
    	okButton = new JButton();
    	okButton.setIcon(buttonImg);
    	okButton.setBounds(halfW, halfH-45+40, 190, 90);
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
    	
    	//Create Cats Label and set Bounds,Icon
		cat = new JLabel[5];
		for (int i = 0 ; i < cat.length; i++) {
			cat[i] = new JLabel();
			cat[i].setIcon(catImg[i]);
			cat[i].setBounds(halfW-catSize+50, halfH-catSize/2-20, catSize, catSize);
		}
    	
		//Set Combobox
		String[] animals = {"Lion Meow","Sensei Meow","Happy Meow","Abe Meow","Cute Meow"};
		comboBox = new JComboBox(animals);
		comboBox.setBounds(halfW, halfH-25-80, 400, 70);
		comboBox.setFont(new Font("MV Boli",Font.BOLD,40));
		contentpane.add(comboBox);
		comboBox.addActionListener(e -> {
			System.out.println(comboBox.getSelectedItem() + "-" + comboBox.getSelectedIndex());

			for (int i = 0 ; i < cat.length; i++)
				contentpane.remove(cat[i]);
			contentpane.add(cat[comboBox.getSelectedIndex()]);
			catSelectedIndex = comboBox.getSelectedIndex();

			soundCtrl.playSFX(SoundController.SFX_ButtonClick);

			repaint();
		});
		
		//Set beginning CatSelect
    	contentpane.add(cat[0]);
    	catSelectedIndex = 0;

		repaint();
		contentpane.validate();
	}

	public void linkFrame(JFrame back) {
		backFrame = back;
	}
	public void setSoundController(SoundController sctrl) { soundCtrl = sctrl; }

	public void switchFrame(JFrame frame) { setVisible(false); frame.setVisible(true); }

	public int getCatSelectedIndex() { return catSelectedIndex; }
}
