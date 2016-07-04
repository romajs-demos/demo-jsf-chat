package com.romajs.demojsfchat.jsf.component.tab;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Tab<E> implements Serializable {

	protected String title;

	protected boolean closable = true;

	protected E model;

	public Tab() {
	}

	public Tab(String title) {
		this.title = title;
	}

	public Tab(String title, boolean closable) {
		this.title = title;
		this.closable = closable;
	}

	public Tab(String title, E model) {
		this.title = title;
		this.model = model;
	}

	public Tab(String title, E model, boolean closable) {
		this.title = title;
		this.closable = closable;
		this.model = model;
	}

	public void focus() {
		// TODO: code goes here
		System.out.println("@focus: \"" + title + "\"");
	}

	public void blurry() {
		// TODO: code goes here
		System.out.println("@blurry: \"" + title + "\"");
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isClosable() {
		return closable;
	}

	public void setClosable(boolean closable) {
		this.closable = closable;
	}

	public E getModel() {
		return model;
	}

	public void setModel(E model) {
		this.model = model;
	}

}
