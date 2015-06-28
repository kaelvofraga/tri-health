package br.edu.ifrs.canoas.tads.lds.util;
/**/
import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class Mensagens {

	private static ResourceBundle resBundle = ResourceBundle
			.getBundle("ValidationMessages");

	public static void define(Severity severity, String msg) {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(severity, getBundleMessage(msg),
						getBundleMessage(msg)));
	}
	

	public static void define(Severity severity, String msg, Object...args) {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(severity, getBundleMessage(msg, args),
						getBundleMessage(msg)));
	}

	public static String getBundleMessage(final String key, final Object... args) {
		return MessageFormat.format(resBundle.getString(key), args);
	}
	
	public static String getBundleMessage(final String key) {
		return resBundle.getString(key);
	}

}
