package com.romajs.demojsfchat.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class JPAUtils implements ServletContextListener {

	private static EntityManagerFactory entityManagerFactory;

	// Prepare the EntityManagerFactory & Enhance:
	public void contextInitialized(ServletContextEvent e) {
		entityManagerFactory = Persistence.createEntityManagerFactory("UserPU");
		// e.getServletContext().setAttribute("entityManagerFactory",
		// entityManagerFactory);
	}

	public static EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

	// Release the EntityManagerFactory:
	public void contextDestroyed(ServletContextEvent e) {
		// entityManagerFactory = (EntityManagerFactory) e.getServletContext()
		// .getAttribute("entityManagerFactory");
		entityManagerFactory.close();
	}
}
