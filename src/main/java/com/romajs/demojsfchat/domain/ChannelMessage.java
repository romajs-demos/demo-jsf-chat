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
public class ChannelMessage implements Serializable {

	@Id
	@SequenceGenerator(name = "CHANNELMESSAGE_ID", sequenceName = "CHANNELMESSAGE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "CHANNELMESSAGE_ID")
	private Long id;

	@ManyToOne
	private Channel channel;

	@ManyToOne
	private User sender;

	// TODO: blob
	private String text;

	public ChannelMessage() {
	}

	public ChannelMessage(Channel channel, User sender, String text) {
		this.channel = channel;
		this.sender = sender;
		this.text = text;
	}

	public Channel getChannel() {
		return channel;
	}

	public Long getId() {
		return id;
	}

	public User getSender() {
		return sender;
	}

	public String getText() {
		return text;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public void setText(String text) {
		this.text = text;
	}

}
