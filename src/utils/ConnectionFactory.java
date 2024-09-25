package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



public class ConnectionFactory {
	
	
	private static ConnectionFactory instancia = new ConnectionFactory();
	
	private EntityManagerFactory emf;
	
	private ConnectionFactory() {
		
	}
	
	public static ConnectionFactory getInstance() {
		return instancia;
	}
	
	public EntityManager connect(MotorDB type) {
		disconnect();
		this.emf = Persistence.createEntityManagerFactory(type.name());
		return emf.createEntityManager();
	}

	public EntityManager connection() {
		return emf.createEntityManager();
	}

	public void disconnect() {
		if (emf != null)
			this.emf.close();
	}


}