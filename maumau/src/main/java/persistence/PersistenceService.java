package persistence;

import java.util.List;

import org.springframework.stereotype.Component;

import cardGame.MauMauService;
import cardGame.modell.MauMau;
import persistence.modell.JPAHandler;

// TODO: Auto-generated Javadoc
/**
 * The Interface PersistenceService.
 */
@Component
public interface PersistenceService {

	/**
	 * Persist game.
	 *
	 * @param maumau  the maumau
	 * @param handler the handler
	 */
	public void persistGame(MauMau maumau, JPAHandler handler);

	/**
	 * Establish connection.
	 *
	 * @param instanceName the instance name
	 * @param handler      the handler
	 * @return the JPA handler
	 */
	public JPAHandler establishConnection(String instanceName, JPAHandler handler);

	/**
	 * Load games.
	 *
	 * @param handler   the handler
	 * @param userNames the user names
	 * @return the list
	 */
	public List<MauMau> loadGames(JPAHandler handler, List<String> userNames);
}
