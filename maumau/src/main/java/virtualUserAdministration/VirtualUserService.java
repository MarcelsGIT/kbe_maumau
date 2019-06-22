package virtualUserAdministration;

import cardGame.modell.MauMau;
import cards.modell.Card;
import userAdministration.modell.MauMauUser;

public interface VirtualUserService {
	
	
	Card playNextPossibleCardFromHand(MauMauUser mauMauUser, MauMau mauMau, Card lastPlayedCard);
	
	MauMauUser setMauIfPossible(MauMauUser mauMauUser, MauMau mauMau);
	
	MauMauUser setMauMauIfPossible(MauMauUser mauMauUser, MauMau mauMau);
	
	
	
	

}
