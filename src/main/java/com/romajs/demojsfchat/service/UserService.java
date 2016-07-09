package com.romajs.demojsfchat.service;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import com.romajs.demojsfchat.domain.User;

@Stateful
public class UserService {

	@PersistenceContext(unitName = "demo-jsf-chat-pu", type = PersistenceContextType.EXTENDED)
	private EntityManager em;

	public User getUser(String username, String password) {
		StringBuilder sql = new StringBuilder("from User u ");
		sql.append(" where u.username = :username and u.password = :password ");
		TypedQuery<User> query = em.createQuery(sql.toString(), User.class);
		query.setParameter("username", username);
		query.setParameter("password", password);
		try {
			return query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
}
