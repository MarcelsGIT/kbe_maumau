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

@Entity

@Table(name="Users")
//@DiscriminatorValue("A320")

@Component
public class MauMauUser extends CardGameUser {
	
	@Id
	@GeneratedValue
	@Column(name="id", columnDefinition="INT")
	private Integer id;
	
	public MauMauUser() {
		
	}

	public MauMauUser(String username, List<Card> hand, int wins,boolean isActive,boolean skipRound, boolean mau, boolean maumau) {
		super(username, hand, wins);
		this.isActive = isActive;
		this.skipRound = skipRound;
		this.mau = mau;
		this.maumau = maumau;
		
	}
	
	public MauMauUser(String username, List<Card> hand, int wins) {
		super(username, hand, wins);
	}
	
	private boolean isActive;
	private boolean skipRound;
	private boolean mau;
	private boolean maumau;
	
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public boolean isSkipRound() {
		return skipRound;
	}
	public void setSkipRound(boolean skipRound) {
		this.skipRound = skipRound;
	}
	public boolean isMau() {
		return mau;
	}
	public void setMau(boolean mau) {
		this.mau = mau;
	}
	public boolean isMaumau() {
		return maumau;
	}
	public void setMaumau(boolean maumau) {
		this.maumau = maumau;
	}
	
}
