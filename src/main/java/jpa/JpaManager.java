package jpa;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaManager {
	private static final EntityManagerFactory instance = Persistence.createEntityManagerFactory("pwa-oracle");
	
	public static EntityManagerFactory getInstance() { return instance; }
	private JpaManager() {}
}
