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
public class Channel implements Serializable {

	@Id
	@SequenceGenerator(name = "CHANNEL_ID", sequenceName = "CHANNEL_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "CHANNEL_ID")
	private Long id;

	private String name;

	@ManyToOne
	private User owner;

	public Channel() {
	}

	public Channel(String name, User owner) {
		this.name = name;
		this.owner = owner;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

}
