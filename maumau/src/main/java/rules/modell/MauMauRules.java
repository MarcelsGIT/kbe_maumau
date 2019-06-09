package rules.modell;

import org.springframework.stereotype.Component;

@Component
public class MauMauRules {

	//@Autowired
	private boolean jackOnJack;
	
	//@Autowired
	private boolean jackOnEverything;
	
	public MauMauRules() {
		
	}

	public boolean isJackOnJack() {
		return jackOnJack;
	}

	public void setJackOnJack(boolean jackOnJack) {
		this.jackOnJack = jackOnJack;
	}

	public boolean isJackOnEverything() {
		return jackOnEverything;
	}

	public void setJackOnEverything(boolean jackOnEverything) {
		this.jackOnEverything = jackOnEverything;
	}
	
	
	
	

}
