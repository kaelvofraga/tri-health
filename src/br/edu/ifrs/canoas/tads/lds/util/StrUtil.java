package br.edu.ifrs.canoas.tads.lds.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.faces.application.FacesMessage;

public class StrUtil {

	public static boolean isNotBlank(final String string) {
		return string != null && !string.isEmpty() && !string.trim().isEmpty();
	}

	public static String getMd5(String str) {
		String result = "";
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.update(str.getBytes(), 0, str.length());
			result = new BigInteger(1, m.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			Mensagens.define(FacesMessage.SEVERITY_INFO, "Usuario.senha.erro.criptografia", e.getMessage());
		}
		return result;
	}

}
