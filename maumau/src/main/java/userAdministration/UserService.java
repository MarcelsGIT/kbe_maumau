package userAdministration;

import java.util.List;

import cards.modell.Card;
import userAdministration.modell.MauMauUser;


public interface UserService {

	
	/**
	 * Gives the card that user wants to play
	 * @param index: index of card to be played in user's cardList
	 * @return Card: The card the user played
	 */
	Card playCard(int index, MauMauUser mauMauUser);
	
	/**
	 * Adds a card to user's hand
	 * @param cardFromDeck: card to be added to user's card
	 * @param user: the user that takes a card
	 * @return MauMauUser: user with one more card in hand
	 */
	MauMauUser takeCard(Card cardFromDeck, MauMauUser mauMauUser);
	
	/**
	 * Creates users from userNames
	 * @param userNames Names of users to be created
	 * @return List with MauMauUsers
	 */
	List<MauMauUser> createUsers(List<String> userNames);
	
	/**
	 * Lets user shout mau
	 * @param mauMauUser:
	 * @return string: maumau
	 */
	String shoutMau(MauMauUser mauMauUser);
	
	/** 
	 * Lets user shout maumau
	 * @param mauMauUser:
	 * @return string: maumau
	 * 
	 */
	String shoutMauMau(MauMauUser mauMauUser);
	

	
	
}
