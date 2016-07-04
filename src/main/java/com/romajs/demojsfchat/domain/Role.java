package com.romajs.demojsfchat.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

@Entity
@SuppressWarnings("serial")
public class Role implements Serializable {

	@Id
	@SequenceGenerator(name = "ROLE_ID", sequenceName = "ROLE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "ROLE_ID")
	private Long id;
	
	@Column(nullable = false)
	private String description;
	
	@ManyToMany(mappedBy = "roles")
	private List<User> users;

	public Role() {
		super();
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
