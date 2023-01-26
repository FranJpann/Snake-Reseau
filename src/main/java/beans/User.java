package beans;

public class User {
	
	private long id;
	private String Pseudo;
	
	public void setPseudo(String Pseudo) {
		this.Pseudo = Pseudo;
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
