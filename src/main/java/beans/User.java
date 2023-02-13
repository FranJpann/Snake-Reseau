package beans;

import java.util.ArrayList;

public class User {
	
	private long id;
	private String Pseudo;
	private String skin;
	private ArrayList<GameHistory> gamesHistory;
	
	public void setPseudo(String Pseudo) {
		this.Pseudo = Pseudo;
	}
	public String getSkin() {
		return skin;
	}
	public void setSkin(String skin) {
		this.skin = skin;
	}
	public String getPseudo() {
		return Pseudo;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getId() {
		return id;
	}
	public ArrayList<GameHistory> getGamesHistory() {
		return gamesHistory;
	}
	public void setGamesHistory(ArrayList<GameHistory> gamesHistory) {
		this.gamesHistory = gamesHistory;
	}
}
