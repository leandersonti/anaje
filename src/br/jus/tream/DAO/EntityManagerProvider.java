package br.jus.tream.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProvider {
	private static final EntityManagerProvider INSTANCE = new EntityManagerProvider();
    // PRODUCAO: oracleprod  // HOMOLOGAÇÃO: oraclehomo mysql: mysql
    private String connDB = "mysql";
    
    private final EntityManagerFactory factory;

    private EntityManagerProvider() {
        this.factory = Persistence.createEntityManagerFactory(connDB);
    }

    public static EntityManagerProvider getInstance() {
        return INSTANCE;
    }

    public EntityManagerFactory getFactory() {
        return factory;
    }

    public EntityManager createManager() {
        return factory.createEntityManager();
    }
    
    public boolean isAmbienteProducao()  {	        	
		if(this.connDB.contains("prod")) {    		
			return true;
		} else {
			return false;
		} 	
	}
}
