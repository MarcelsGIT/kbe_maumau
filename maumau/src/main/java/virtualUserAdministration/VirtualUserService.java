package virtualUserAdministration;

import cardGame.modell.MauMau;
import cards.modell.Card;
import userAdministration.modell.MauMauUser;

public interface VirtualUserService {
	
	
	Card playNextPossibleCardFromHand(MauMauUser virtualMauMauUser, MauMau mauMau, Card lastPlayedCard);
	
	MauMauUser setMauIfPossible(MauMauUser virtualMauMauUser, MauMau mauMau);
	
	MauMauUser setMauMauIfPossible(MauMauUser virtualMauMauUser, MauMau mauMau);
	
	
	
	

}
