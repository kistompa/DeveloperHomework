package hu.tt.controller;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MyEntityManager{
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");

	public static EntityManagerFactory getEmf() {
		return emf;
	} 
	public static void closeEntyyManaggerFactroy()
	{
		emf.close();
	}
	
}
