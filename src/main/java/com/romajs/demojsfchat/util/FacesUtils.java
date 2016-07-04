package com.romajs.demojsfchat.util;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

@ManagedBean
@ApplicationScoped
public class FacesUtils implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 581045127881038966L;

	private static final Logger log = Logger.getRootLogger();

	public FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	public ExternalContext getExternalContext() {
		return getFacesContext().getExternalContext();
	}

	public Map<String, Object> getSessionMap() {
		return getExternalContext().getSessionMap();
	}

	public HttpServletRequest getRequest() {
		return (HttpServletRequest) getExternalContext().getRequest();
	}

	public void forward(String url) {
		ExternalContext ctx = getFacesContext().getExternalContext();
		ServletRequest req = (ServletRequest) ctx.getRequest();
		ServletResponse resp = (ServletResponse) ctx.getResponse();
		RequestDispatcher dispatcher = req.getRequestDispatcher(url);
		try {
			dispatcher.forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getFacesContext().responseComplete();
	}

	public void redirect(String url) {
		try {
			getExternalContext().redirect(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Limpa os dados dos componentes de edição e de seus filhos,
	 * recursivamente. Checa se o componente é instância de EditableValueHolder
	 * e 'reseta' suas propriedades.
	 * <p>
	 * Quando este método, por algum motivo, não funcionar, parta para
	 * ignorância e limpe o componente assim:
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * component.getChildren().clear()
	 * </pre>
	 * 
	 * </blockquote> :-)
	 */
	public void cleanSubmittedValues(UIComponent component) {
		if (component instanceof EditableValueHolder) {
			EditableValueHolder evh = (EditableValueHolder) component;
			evh.setSubmittedValue(null);
			evh.setValue(null);
			evh.setLocalValueSet(false);
			evh.setValid(true);
		}
		if (component.getChildCount() > 0) {
			for (UIComponent child : component.getChildren()) {
				cleanSubmittedValues(child);
			}
		}
	}

	public void cleanSubmittedValues(String componentName) {
		UIComponent component = getFacesContext().getViewRoot().findComponent(
				componentName);
		if (component != null)
			cleanSubmittedValues(component);
	}

	/**
	 * Message
	 */

	public void addMessage(Severity severity, String summary, String detail) {
		addMessage(severity, summary, detail, null);
	}

	public void addMessage(Severity severity, String summary, String detail,
			String componentId) {

		try {
			log.warn(summary + ": " + detail);
		} catch (NullPointerException e) {
			summary = summary != null ? summary : "null";
			detail = detail != null ? detail : "null";
		}
		FacesMessage facesMessage = new FacesMessage(severity, summary, detail);
		getFacesContext().addMessage(componentId, facesMessage);
	}

	/**
	 * Message [Info]
	 */

	public void info(String summary) {
		info(summary, "");
	}

	public void info(String summary, String detail) {
		info(summary, detail, null);
	}

	public void info(String summary, String detail, String componentId) {
		addMessage(FacesMessage.SEVERITY_INFO, summary, detail, componentId);
	}

	/**
	 * Message [Warn]
	 */

	public void warn(String summary) {
		warn(summary, "");
	}

	public void warn(String summary, String detail) {
		warn(summary, detail, null);
	}

	public void warn(String summary, String detail, String componentId) {
		addMessage(FacesMessage.SEVERITY_WARN, summary, detail, componentId);
	}

	/**
	 * Message [Error]
	 */

	public void error(String summary) {
		error(summary, "");
	}

	public void error(String summary, String detail) {
		error(summary, detail, null);
	}

	public void error(String summary, String detail, String componentId) {
		addMessage(FacesMessage.SEVERITY_ERROR, summary, detail, componentId);
	}

	/**
	 * Message [Fatal]
	 */

	public void fatal(String summary) {
		fatal(summary, "");
	}

	public void fatal(String summary, String detail) {
		fatal(summary, detail, null);
	}

	public void fatal(String summary, String detail, String componentId) {
		addMessage(FacesMessage.SEVERITY_FATAL, summary, detail, componentId);
	}

}