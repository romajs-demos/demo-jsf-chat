package com.romajs.demojsfchat.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * @author romajs
 * @see Authentication Filter (REQUESTs only)
 */
//@WebFilter(urlPatterns = "/*")
public class AuthenticationFilter implements Filter {

	private static final Logger log = Logger.getRootLogger();
	public static final String AUTH_KEY = "romajs.faces.user.key";
	public static final String LOGIN_URL = "login.jsf";
	public static final String HOME_URL = "index.jsf";
	public static final String[] RESOURCE_URLS = { "/javax.faces.resource/",
			"/static/" };

	private FilterConfig config;

	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

	public void destroy() {
		config = null;
	}

	// authentication filter implementation
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {

		StringBuffer requestURL = ((HttpServletRequest) req).getRequestURL();

		// verify if user is authenticated
		if (isAuthenticated((HttpServletRequest) req)) {

			// user may not access login url once logged in
			if (isLoginUrl(requestURL)) {
				((HttpServletResponse) resp).sendRedirect(HOME_URL);
				log.info("User \"" + getUserKey((HttpServletRequest) req)
						+ "\" already authenticated");
			} else {
				log.info("Authentication GRANTED for user: \""
						+ getUserKey((HttpServletRequest) req) + "\"");
			}
			// completes the chain
			chain.doFilter(req, resp);

		} else { // if not

			// grant access to resouce urls (temporaly)
			if (isResourceURL(requestURL)) {
				chain.doFilter(req, resp);
				log.info("Authentication TEMPORALY GRANTED for RESOURCE: \""
						+ requestURL + "\"");
				return;
			}

			// verify if url is login again
			if (isLoginUrl(requestURL)) {
				chain.doFilter(req, resp); // completes the chain
			} else { // if not
				config.getServletContext()
						.getRequestDispatcher("/" + LOGIN_URL)
						.forward(req, resp); // forward to login url
			}

			log.warn("Authentication DENIED! \"" + requestURL + "\"");
		}

	}

	// verify if user is currently authenticated
	private boolean isAuthenticated(HttpServletRequest req) {
		return getUserKey(req) != null;
	}

	// get user key session attribute
	private String getUserKey(HttpServletRequest req) {
		Object key = req.getSession().getAttribute(AUTH_KEY);
		return key != null ? key.toString() : null;
	}

	// verify if url is resource url
	private boolean isResourceURL(StringBuffer requestURL) {
		for (String str : RESOURCE_URLS) {
			if (requestURL.toString().indexOf(str) != -1) {
				return true;
			}
		}
		return false;
	}

	// verify if url is login url
	private boolean isLoginUrl(StringBuffer requestURL) {
		return (requestURL.indexOf(LOGIN_URL) != -1);
	}

}