package beans;

import java.util.HashMap;

public class ShopBean {
	
	private HashMap<String, Integer> skins;
	
	public ShopBean() {
		skins = new HashMap<String, Integer>();
		skins.put("green", 50);
		skins.put("red", 100);
		skins.put("pacman", 150);
		skins.put("skull", 300);
		skins.put("fortnite", 1000);
	}

	public HashMap<String, Integer> getSkins() {
		return skins;
	}
}
