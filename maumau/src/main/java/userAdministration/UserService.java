package userAdministration;

import java.util.List;

import cards.modell.Card;
import userAdministration.modell.MauMauUser;


public interface UserService {

	
	/**
	 * The card that the user plays
	 * 
	 * @return The card the user played
	 */
	Card playCard(int index, MauMauUser mauMauUser);
	
	/**
	 * Take card from card deck
	 * 
	 * @param cards The card deck
	 * @param user The user that takes from the card deck
	 * @return 
	 */
	MauMauUser takeCard(Card cardFromDeck, MauMauUser mauMauUser);
	
	/**
	 * 
	 */
	String shoutMau(MauMauUser mauMauUser);
	
	/** 
	 * 
	 */
	String shoutMauMau(MauMauUser mauMauUser);
	
	/**
	 * @param userNames Names of users to be created
	 * @return List with MauMauUsers
	 */
	List<MauMauUser> createUsers(List<String> userNames);
	
	
}
