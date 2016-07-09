package com.romajs.demojsfchat.jsf.view;

import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

import org.apache.commons.codec.digest.DigestUtils;

import com.romajs.demojsfchat.domain.User;
import com.romajs.demojsfchat.filter.AuthenticationFilter;
import com.romajs.demojsfchat.service.UserService;
import com.romajs.demojsfchat.util.FacesUtils;

@Named("sessionManagedBean")
@SessionScoped
public class SessionManagedBean {

//	@ManagedProperty(value = "#{facesUtils}")
//	private FacesUtils facesUtils;

	private String username;

	private String password;

	private User user;

	public boolean isLoggedIn() {
		return false ;// facesUtils.getExternalContext().getSessionMap()
//				.get(AuthenticationFilter.AUTH_KEY) != null;
	}

	private String encryptedPassword(String password) {
		return DigestUtils.md5Hex(password);
	}

	public void login() {
		UserService service = new UserService();
		user = service.getUser(username, encryptedPassword(password));
		if (user != null) {
//			facesUtils.getSessionMap().put(AuthenticationFilter.AUTH_KEY,
//					user.getUsername());
//			facesUtils.info("Login Successfully",
//					"User \"" + user.getUsername() + "\" is now logged in");
//			facesUtils.redirect(AuthenticationFilter.HOME_URL);
			return;
		} else {
//			facesUtils.warn("Login failed", "Invalid username e/or password");
		}
	}

	public void logout() {
		user = null;
		username = null;
		password = null;
//		facesUtils.getExternalContext().getSessionMap()
//				.remove(AuthenticationFilter.AUTH_KEY);
//		facesUtils.redirect(AuthenticationFilter.LOGIN_URL);
	}

//	public FacesUtils getFacesUtils() {
//		return facesUtils;
//	}
//
//	public void setFacesUtils(FacesUtils facesUtils) {
//		this.facesUtils = facesUtils;
//	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}