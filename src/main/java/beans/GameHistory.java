package beans;

public class GameHistory {
	
	private int number;
	private User looser, winner;
	private int bestScore, looserScore;
	
	public GameHistory(int number, User looser, User winner, int bestScore, int looserScore) {
		
		this.number = number;
		this.looser = looser;
		this.winner = winner;
		this.bestScore = bestScore;
		this.looserScore = looserScore;
	}
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public User getWinner() {
		return winner;
	}

	public void setWinner(User winner) {
		this.winner = winner;
	}

	public User getLooser() {
		return looser;
	}

	public void setLooser(User looser) {
		this.looser = looser;
	}
	
	public int getBestScore() {
		return bestScore;
	}

	public void setBestScore(int bestScore) {
		this.bestScore = bestScore;
	}

	public int getLooserScore() {
		return looserScore;
	}

	public void setLooserScore(int looserScore) {
		this.looserScore = looserScore;
	}

}
