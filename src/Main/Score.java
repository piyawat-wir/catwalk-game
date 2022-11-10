/*
	Java Project 3 | Catwalk Game
	Created by:
		6313124 Chanawee Sateinteeraphap
		6313125 Chayakorn Jullanee
		6313132 Piyawat Wirotkitphaiboon
*/

package Main;

public class Score implements Comparable<Score> {
	private String playerName;
	private int scorePoint;
	
	public Score(String pn, int sp) {
		setPlayerName(pn);
		setScorePoint(sp);
	}
	
	public void setPlayerName(String pn) {playerName = pn;}
	public void setScorePoint(int sp) {scorePoint = sp;}
	
	public String getPlayerName() {return playerName;}
	public int getScorePoint() {return scorePoint;}

	@Override
	public int compareTo(Score other) {
		int x = Integer.compare(other.scorePoint, scorePoint);
		if (x != 0) return x;
		else return playerName.compareToIgnoreCase(other.playerName);
	}

	
}
