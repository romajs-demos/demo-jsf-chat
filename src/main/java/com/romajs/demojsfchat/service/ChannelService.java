package com.romajs.demojsfchat.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.romajs.demojsfchat.domain.Channel;
import com.romajs.demojsfchat.domain.ChannelInscribed;
import com.romajs.demojsfchat.domain.User;

@ApplicationScoped
public class ChannelService implements Serializable {

	@PersistenceContext // FIXME
//	EntityManager em;

	public void create(String name, User owner, User... users) {
		Channel channel = new Channel(name, owner);
//		em.persist(channel);
		for (User user : users) {
			ChannelInscribed inscribed = new ChannelInscribed(channel, user);
//			em.persist(inscribed);
		}
	}

	public void inscribe(Channel channel, User user) {
		ChannelInscribed inscribed = new ChannelInscribed(channel, user);
//		em.persist(inscribed);
	}

	public List<Channel> listChannels() {
		StringBuilder sql = new StringBuilder("select c from Channel c");
//		TypedQuery<Channel> query = em.createQuery(sql.toString(), Channel.class);
		return null; //query.getResultList();
	}

	public List<User> listChannelUsers(Channel channel) {
		StringBuilder sql = new StringBuilder("select c.u from ChannelInscribed ci where ci.channel = :c");
//		TypedQuery<User> query = em.createQuery(sql.toString(), User.class);
//		query.setParameter("c", channel);
		return null; //query.getResultList();
	}

	public void ununscribe(Channel channel, User user) {
		StringBuilder sql = new StringBuilder("select ci from ChannelInscribed ci");
		sql.append("where ci.channel = :c and c.user = :u");
//		TypedQuery<ChannelInscribed> query = em.createQuery(sql.toString(), ChannelInscribed.class);
//		query.setParameter("c", channel);
//		query.setParameter("u", user);
//		ChannelInscribed inscribed = query.getSingleResult();
//		em.remove(inscribed);
	}
}
