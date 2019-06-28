package rules.modell;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * The Class MauMauRules.
 */
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
	
	/**
	 * Instantiates a new mau mau rules.
	 */
	public MauMauRules() {
		
	}

	/**
	 * Checks if is jack on jack.
	 *
	 * @return true, if is jack on jack
	 */
	public boolean isJackOnJack() {
		return jackOnJack;
	}

	/**
	 * Sets the jack on jack.
	 *
	 * @param jackOnJack the new jack on jack
	 */
	public void setJackOnJack(boolean jackOnJack) {
		this.jackOnJack = jackOnJack;
	}

	/**
	 * Checks if is jack on everything.
	 *
	 * @return true, if is jack on everything
	 */
	public boolean isJackOnEverything() {
		return jackOnEverything;
	}

	/**
	 * Sets the jack on everything.
	 *
	 * @param jackOnEverything the new jack on everything
	 */
	public void setJackOnEverything(boolean jackOnEverything) {
		this.jackOnEverything = jackOnEverything;
	}
	
	
	
	

}
