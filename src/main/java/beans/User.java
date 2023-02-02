package beans;

public class User {
	
	private long id;
	private String Pseudo;
	private String skin;
	
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
}
