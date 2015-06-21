package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;

/**
 * @author Juarez Monteiro
 */

@Named
@SessionScoped
public class ListarHospitaisMB implements Serializable {

	private static final long serialVersionUID = 7254357189634350077L;
	private static final String URL_LISTAR_HOSPITAIS = "/private/pages/listarHospitais.jsf";

	@Inject
	private GerenciarLoginMB gerenciarLoginMB;

	public String initListar() {
		return URL_LISTAR_HOSPITAIS;
	}
	
}