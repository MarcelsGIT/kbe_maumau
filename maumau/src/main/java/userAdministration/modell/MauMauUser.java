package userAdministration.modell;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import cards.modell.Card;

// TODO: Auto-generated Javadoc
/**
 * The Class MauMauUser.
 */
@Entity

@Table(name="Users")
//@DiscriminatorValue("A320")

@Component
public class MauMauUser extends CardGameUser {
	
	@Id
	@GeneratedValue
	@Column(name="id", columnDefinition="INT")
	private Integer id;
	
	/**
	 * Instantiates a new mau mau user.
	 */
	public MauMauUser() {
		
	}

	/**
	 * Instantiates a new mau mau user.
	 *
	 * @param username the username
	 * @param hand the hand
	 * @param wins the wins
	 * @param isActive the is active
	 * @param skipRound the skip round
	 * @param mau the mau
	 * @param maumau the maumau
	 * @param virtualUser the virtual user
	 */
	public MauMauUser(String username, List<Card> hand, int wins,boolean isActive,boolean skipRound, boolean mau, boolean maumau, boolean virtualUser) {
		super(username, hand, wins);
		this.isActive = isActive;
		this.skipRound = skipRound;
		this.mau = mau;
		this.maumau = maumau;
		this.virtualUser = virtualUser;
		
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Checks if is virtual user.
	 *
	 * @return true, if is virtual user
	 */
	public boolean isVirtualUser() {
		return virtualUser;
	}

	/**
	 * Sets the virtual user.
	 *
	 * @param virtualUser the new virtual user
	 */
	public void setVirtualUser(boolean virtualUser) {
		this.virtualUser = virtualUser;
	}

	/**
	 * Instantiates a new mau mau user.
	 *
	 * @param username the username
	 * @param hand the hand
	 * @param wins the wins
	 */
	public MauMauUser(String username, List<Card> hand, int wins) {
		super(username, hand, wins);
	}
	
	private boolean isActive;
	private boolean skipRound;
	private boolean mau;
	private boolean maumau;
	private boolean virtualUser;
	
	/**
	 * Checks if is active.
	 *
	 * @return true, if is active
	 */
	public boolean isActive() {
		return isActive;
	}
	
	/**
	 * Sets the active.
	 *
	 * @param isActive the new active
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	/**
	 * Checks if is skip round.
	 *
	 * @return true, if is skip round
	 */
	public boolean isSkipRound() {
		return skipRound;
	}
	
	/**
	 * Sets the skip round.
	 *
	 * @param skipRound the new skip round
	 */
	public void setSkipRound(boolean skipRound) {
		this.skipRound = skipRound;
	}
	
	/**
	 * Checks if is mau.
	 *
	 * @return true, if is mau
	 */
	public boolean isMau() {
		return mau;
	}
	
	/**
	 * Sets the mau.
	 *
	 * @param mau the new mau
	 */
	public void setMau(boolean mau) {
		this.mau = mau;
	}
	
	/**
	 * Checks if is maumau.
	 *
	 * @return true, if is maumau
	 */
	public boolean isMaumau() {
		return maumau;
	}
	
	/**
	 * Sets the maumau.
	 *
	 * @param maumau the new maumau
	 */
	public void setMaumau(boolean maumau) {
		this.maumau = maumau;
	}
	
}
