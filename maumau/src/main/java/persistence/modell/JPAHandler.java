package persistence.modell;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * The Class JPAHandler.
 */
@Component
public class JPAHandler {
	
	EntityManager em;
	
	/**
	 * Instantiates a new JPA handler.
	 */
	public JPAHandler() {
		
	}
	
	/**
	 * Instantiates a new JPA handler.
	 *
	 * @param emf the emf
	 */
	public JPAHandler(EntityManagerFactory emf) {
		this.em = emf.createEntityManager();
	}

	/**
	 * Gets the em.
	 *
	 * @return the em
	 */
	public EntityManager getEm() {
		return em;
	}

	/**
	 * Sets the em.
	 *
	 * @param em the new em
	 */
	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	
	
	
}
