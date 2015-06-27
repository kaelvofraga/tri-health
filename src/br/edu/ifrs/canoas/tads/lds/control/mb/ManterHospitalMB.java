package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Juarez Monteiro
 */

@Named
@SessionScoped
public class ManterHospitalMB implements Serializable {

	private static final long serialVersionUID = -6152898785356095230L;
	
	private static final String URL_LISTAR_HOSPITAIS = "/private/pages/mapaHospital.jsf";

	@Inject
	private GerenciarLoginMB gerenciarLoginMB;

	public String initListar() {
		return URL_LISTAR_HOSPITAIS;
	}
	
}