package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

/**
 * 
 * @author Pablo Diehl
 * @version 22/06/2015
 *
 */
@Named
@SessionScoped
public class ManterComposicoesCorporaisMB implements Serializable {

	private static final long serialVersionUID = 6061701751807684892L;
	
	private static final String URL_LISTAR_COMPOSICOES_CORPORAIS = "/private/pages/listarComposicoesCorporais.jsf";
	private static final String URL_MANTER_COMPOSICOES_CORPORAIS = "/private/pages/manterComposicoesCorporais.jsf";
	
	public String initListar(){			
		return URL_LISTAR_COMPOSICOES_CORPORAIS;
	}
	
	public String initManter(){
		return URL_MANTER_COMPOSICOES_CORPORAIS;
	}
}
