package rules.modell;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="Rules")
@Component
public class MauMauRules {
	@Id
	@GeneratedValue
	@Column(name="id", columnDefinition="INT")
	private Integer id;
	
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
