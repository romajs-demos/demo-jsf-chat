package com.romajs.demojsfchat.service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.romajs.demojsfchat.domain.User;
import com.romajs.demojsfchat.util.JPAUtils;

public class UserService {

	private EntityManager entityManager;

	{
		entityManager = JPAUtils.getEntityManager();
	}

	public User getUser(String username, String password) {
		User user;
		TypedQuery<User> query = entityManager.createQuery("from User u "
				+ " where u.username = :username and u.password = :password ",
				User.class);
		query.setParameter("username", username);
		query.setParameter("password", password);
		try {
			user = query.getSingleResult();
		} catch (NoResultException ex) {
			user = null;
		}
		return user;
	}
}
