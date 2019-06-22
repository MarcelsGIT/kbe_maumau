package persistence.modell;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.springframework.stereotype.Component;

@Component
public class JPAHandler {
	
	EntityManager em;
	
	public JPAHandler() {
		
	}
	
	public JPAHandler(EntityManagerFactory emf) {
		this.em = emf.createEntityManager();
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	
	
	
}
