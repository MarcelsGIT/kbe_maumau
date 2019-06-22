package persistence;

import java.util.List;

import org.springframework.stereotype.Component;

import cardGame.MauMauService;
import cardGame.modell.MauMau;
import persistence.modell.JPAHandler;

@Component
public interface PersistenceService {
	
	public void persistGame(MauMau maumau, JPAHandler handler);
	public JPAHandler establishConnection(String instanceName, JPAHandler handler);
	public List<MauMau> loadGames(JPAHandler handler, List<String> userNames);
}
