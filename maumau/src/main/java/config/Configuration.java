package config;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import view.GameUI;


// TODO: Auto-generated Javadoc
/**
 * The Class Configuration.
 */
public class Configuration {
	//immer das "oberste" package angeben! niemals z.B. cardGame.modell, stattdessen z.B. cardGame
	private static ConfigurableApplicationContext container = new AnnotationConfigApplicationContext("controller", "cardGame", "cards", "rules", "userAdministration", "virtualUserAdministration", "view", "persistence");

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		container.getBean(GameUI.class).run();

	}

}
