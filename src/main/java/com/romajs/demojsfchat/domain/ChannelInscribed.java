package com.romajs.demojsfchat.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SuppressWarnings("serial")
public class ChannelInscribed implements Serializable {

	@Id
	@SequenceGenerator(name = "CHANNELINSCRIBED_ID", sequenceName = "CHANNELINSCRIBED_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "CHANNELINSCRIBED_ID")
	private Long id;

	@ManyToOne
	private Channel channel;

	@ManyToOne
	private User user;

	public ChannelInscribed() {
	}

	public ChannelInscribed(Channel channel, User user) {
		this.channel = channel;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
