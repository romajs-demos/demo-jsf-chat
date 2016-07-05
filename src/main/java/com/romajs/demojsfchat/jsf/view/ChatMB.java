package com.romajs.demojsfchat.jsf.view;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;

import com.romajs.demojsfchat.jsf.component.tab.Tab;
import com.romajs.demojsfchat.jsf.component.tab.TabManager;
import com.romajs.demojsfchat.service.ChannelService;

@Named("chat")
@SessionScoped
@SuppressWarnings("serial")
public class ChatMB implements Serializable {
	
	@Inject
	ChannelService channelService;

	final AtomicInteger tabCount = new AtomicInteger();

	private TabManager<ChannelTabModel> tabManager = new TabManager<>();

	@PostConstruct
	public void init() {
		String title = "General";
		channelService.create(title, null);
		tabManager.add(new Tab<ChannelTabModel>(title, new ChannelTabModel(), false));
	}

	public void tabAddActionListener() {
		String title = "Title " + (tabCount.addAndGet(1));
		tabManager.add(new Tab<ChannelTabModel>(title, new ChannelTabModel(), true));
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