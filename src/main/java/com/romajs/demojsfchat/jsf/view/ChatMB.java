package com.romajs.demojsfchat.jsf.view;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;

import com.romajs.demojsfchat.jsf.component.tab.Tab;
import com.romajs.demojsfchat.jsf.component.tab.TabManager;

@SessionScoped
@ManagedBean(name = "chat")
@SuppressWarnings("serial")
public class ChatMB implements Serializable {

	int count = 0;

	private TabManager<ChannelTabModel> tabManager = new TabManager<>();

	@PostConstruct
	public void init() {
		tabManager.add(new Tab<ChannelTabModel>("General", new ChannelTabModel(), false));
	}

	public void tabAddActionListener() {
		tabManager.add(new Tab<ChannelTabModel>("Title " + (++count), new ChannelTabModel(), true));
	}

	public void channelMessageActionListener(ActionEvent event) {
		Map<String, Object> attrs = event.getComponent().getAttributes();
		// Long channelId = (Long) attrs.get("channelId");
		// System.out.println("ChanneId: " + channelId);
		ChannelTabModel model = (ChannelTabModel) attrs.get("model");
		String text = (String) attrs.get("text");
		System.out.println("Model: " + model);
		System.out.println("Text: " + text);
		model.addMessage(text);
	}

	public void tabChangeEventListener(TabChangeEvent event) {
		// TODO: probably your custom action goes here...
		tabManager.change();
	}

	@SuppressWarnings("unchecked")
	public void tabCloseEventListener(TabCloseEvent event) {
		Tab<ChannelTabModel> tab = (Tab<ChannelTabModel>) event.getData();
		tabManager.remove(tab);
	}

	public void tabClearActionListener() {
		tabManager.clear();
	}

	public TabManager<ChannelTabModel> getTabManager() {
		return tabManager;
	}

	public void setTabManager(TabManager<ChannelTabModel> tabManager) {
		this.tabManager = tabManager;
	}

}