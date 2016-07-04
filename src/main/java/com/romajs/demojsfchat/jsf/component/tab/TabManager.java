package com.romajs.demojsfchat.jsf.component.tab;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SuppressWarnings("serial")
public class TabManager<E> implements Serializable, Iterable<Tab<E>> {

	private int activeIndex = -1;

	private int previousIndex = -1;

	private List<Tab<E>> tabs = new ArrayList<Tab<E>>();

	/*
	 * public enum TabType {
	 * AT_START, CURRENT, AT_END, SHIFT_RIGHT, SHIFT_LEFT; }
	 */

	// TODO: protected TabConfig config = new TabConfig();

	public TabManager() {
	}

	public void loadTabConfig() {
		// config.load(this);
	}

	/*
	 * actions
	 */

	public void add(Tab<E> tab) {

		int pos = tabInsertPosition(); // get insert position

		if (tabs.isEmpty()) { // if no tabs
			activeIndex = pos; // then focus first tab
		}

		tabs.add(pos, tab); // add new tab

		if (previousIndex != activeIndex) { // change detected
			change();
		}

		// if (config.isFocusLastTabExternalAdded()) {
		// activeIndex = lastTabIndex();
		// }
	}

	public Tab<E> find(int tigerredIndex) {
		return tabs.get(tigerredIndex);
	}

	public Tab<E> find(String title) {
		for (Tab<E> tab : tabs) {
			if (tab.getTitle().equals(title))
				return tab;
		}
		return null;
	}

	public void remove(int trigerredIndex) {

		System.out.println("@remove:");
		System.out.println("trigerredIndex: " + trigerredIndex + " \""
				+ tabs.get(trigerredIndex).getTitle() + "\"");
		System.out.println("activeIndex: " + activeIndex + " \""
				+ tabs.get(activeIndex).getTitle() + "\"");

		tabs.remove(trigerredIndex);

		if (trigerredIndex < activeIndex) {
			activeIndex--; // shift left
			previousIndex--; // shift left
		} else if (trigerredIndex == activeIndex) {
			activeIndex--;
			previousIndex = -1;
		}

		if (activeIndex == -1 && tabs.size() > 0)
			activeIndex = 0;

		System.out.println(this.toString());

	}

	public void remove(Tab<E> tab) {
		int trigerredIndex = tabs.indexOf(tab);
		remove(trigerredIndex);
	}

	public void change() {
		System.out.println("@change:");
		System.out.println("activeIndex: " + activeIndex + " \""
				+ tabs.get(activeIndex).getTitle() + "\"");
		if (previousIndex != -1) {
			tabs.get(previousIndex).blurry();
		}
		if (activeIndex != -1) {
			tabs.get(activeIndex).focus();
		}
		System.out.println(this.toString());
		previousIndex = activeIndex;
	}

	public void clear() {
		Iterator<Tab<E>> it = tabs.iterator();
		while (it.hasNext()) {
			Tab<E> t = it.next();
			if (t.isClosable()) {
				it.remove();
			}
		}
		activeIndex = -1;
		previousIndex = -1;
	}

	/*
	 * utils
	 */

	private int tabInsertPosition() {
		// insert at end
		return Math.max(0, tabs.size());
	}

	// private int lastTabIndex() {
	// return /* config.isAdderTabSupported() ? tabs.size() - 2 : */tabs
	// .size() - 1;
	// }
	//
	// private boolean isLastTab(int index) {
	// return index == lastTabIndex();
	// }
	//
	// private int adderTabIndex() {
	// return tabs.size() - 1;
	// }
	//
	// private boolean isAdderTab(int index) {
	// return index == adderTabIndex();
	// }

	/*
	 * getter's & setter's
	 */

	public int getActiveIndex() {
		return activeIndex;
	}

	public void setActiveIndex(int activeIndex) {
		this.activeIndex = activeIndex;
	}

	public List<Tab<E>> getTabs() {
		return tabs;
	}

	public void setTabs(List<Tab<E>> tabs) {
		this.tabs = tabs;
	}

	/*
	 * public TabConfig getConfig() { return config; }
	 * public void setConfig(TabConfig config) { this.config = config; }
	 */

	@Override
	public String toString() {
		StringBuffer string = new StringBuffer();
		// string.append("TabBrowser: " + this.getClass() + "\n");
		string.append(" > size: " + (tabs != null ? tabs.size() : -1) + "\n");
		string.append(" > activeIndex: " + activeIndex + " \"");
		string.append(activeIndex != -1 ? tabs.get(activeIndex).getTitle()
				: null);
		string.append("\"\n");
		string.append(" > previousIndex: " + previousIndex + " \"");
		string.append(previousIndex != -1 ? tabs.get(previousIndex).getTitle()
				: null);
		string.append("\"\n");
		return string.toString();
	}

	@Override
	public Iterator<Tab<E>> iterator() {
		return tabs.iterator();
	}
}