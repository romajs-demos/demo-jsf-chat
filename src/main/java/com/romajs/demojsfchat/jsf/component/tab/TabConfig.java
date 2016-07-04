package com.romajs.demojsfchat.jsf.component.tab;

public class TabConfig<E> {

	private boolean adderTabSupported = true;

	private boolean focusLastTabExternalAdded = true;

	public boolean isAdderTabSupported() {
		return adderTabSupported;
	}

	public void setAdderTabSupported(boolean addertabSupported) {
		this.adderTabSupported = addertabSupported;
	}

	public boolean isFocusLastTabExternalAdded() {
		return focusLastTabExternalAdded;
	}

	public void setFocusLastTabExternalAdded(boolean focusLastTabExternalAdded) {
		this.focusLastTabExternalAdded = focusLastTabExternalAdded;
	}

	@SuppressWarnings("unchecked")
	public void load(TabManager<E> tabBrowser) {
		if (adderTabSupported) {
			tabBrowser.add(new TabAdder());
		}
	}

}
