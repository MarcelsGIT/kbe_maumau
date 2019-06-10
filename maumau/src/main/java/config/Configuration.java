package config;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import view.GameUI;


public class Configuration {
	//immer das "oberste" package angeben! niemals z.B. cardGame.modell, stattdessen z.B. cardGame
	private static ConfigurableApplicationContext container = new AnnotationConfigApplicationContext("controller", "cardGame", "cards", "rules", "userAdministration", "view");

	public static void main(String[] args) {
		
		container.getBean(GameUI.class).run();

	}

}
