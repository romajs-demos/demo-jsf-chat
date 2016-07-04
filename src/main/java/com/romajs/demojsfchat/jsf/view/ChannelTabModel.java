package com.romajs.demojsfchat.jsf.view;

import java.util.ArrayList;
import java.util.List;

import com.romajs.demojsfchat.domain.Channel;
import com.romajs.demojsfchat.domain.ChannelMessage;
import com.romajs.demojsfchat.jsf.component.tab.TabModel;

@SuppressWarnings("serial")
public class ChannelTabModel extends TabModel {

	private Channel channel = new Channel();

	private List<ChannelMessage> channelMessages = new ArrayList<>();

	private StringBuilder buffer = new StringBuilder();

	public ChannelTabModel() {
		channelMessages.add(new ChannelMessage(channel, null, "Welcome"));
		for (ChannelMessage channelMessage : channelMessages) {
			buffer.append(String.format("%s\n", channelMessage.getText()));
		}
	}

	public boolean addMessage(String text) {
		buffer.append(String.format("%s\n", text));
		return channelMessages.add(new ChannelMessage(channel, null, text));
	}

	public String getBuffer() {
		return buffer.toString();
	}
}
