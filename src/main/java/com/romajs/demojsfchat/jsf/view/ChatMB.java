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

import com.romajs.demojsfchat.domain.Channel;
import com.romajs.demojsfchat.domain.User;
import com.romajs.demojsfchat.jsf.component.tab.Tab;
import com.romajs.demojsfchat.jsf.component.tab.TabManager;
import com.romajs.demojsfchat.service.ChannelService;

@Named("chat")
@SessionScoped
@SuppressWarnings("serial")
public class ChatMB implements Serializable {

	@Inject
	private ChannelService channelService;

	final AtomicInteger tabCount = new AtomicInteger();

	private TabManager<ChannelTabModel> tabManager = new TabManager<>();

	private Channel activeChannel; // TODO

	private User activeUser; // TODO

	@PostConstruct
	public void init() {
		String title = "General";
		channelService.create(title, activeUser);
		tabManager.add(title, new ChannelTabModel(), false);
	}

	public void tabAddActionListener() {
		String title = "Title " + (tabCount.addAndGet(1));
		tabManager.add(title, new ChannelTabModel(), true);
	}

	public void channelMessageActionListener(ActionEvent event) {
		Map<String, Object> attrs = event.getComponent().getAttributes();
		ChannelTabModel model = (ChannelTabModel) attrs.get("model");
		String text = (String) attrs.get("text");
		model.addMessage(text);
		channelService.sendMessage(activeChannel, activeUser, text);
	}

	public void tabChangeEventListener(TabChangeEvent event) {
		// TODO: probably your custom action goes here...
		Map<String, Object> attrs = event.getComponent().getAttributes();
		activeChannel = (Channel) attrs.get("channel");
		tabManager.change();
	}

	@SuppressWarnings("unchecked")
	public void tabCloseEventListener(TabCloseEvent event) {
		Tab<ChannelTabModel> tab = (Tab<ChannelTabModel>) event.getData();
		channelService.ununscribe(activeChannel, activeUser);
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