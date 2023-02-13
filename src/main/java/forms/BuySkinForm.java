package forms;

	/* Objet métier pour faire les vérifications de l'achat d'un skin */
public class BuySkinForm {
	
	public static boolean canBuySkin(int creditFromShop, int credit) {
		if(credit < creditFromShop) return false;
		else return true;
	}
}
