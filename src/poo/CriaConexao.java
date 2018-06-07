package poo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CriaConexao {
	private static final SessionFactory sessionFactory =
		    new Configuration().configure().buildSessionFactory();
	public SessionFactory factory() {
		return sessionFactory;
		
	}
	
}
