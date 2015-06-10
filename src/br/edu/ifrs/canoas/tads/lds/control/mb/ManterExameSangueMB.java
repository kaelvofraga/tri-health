package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.ItemExameSangue;
import br.edu.ifrs.canoas.tads.lds.bean.TipoAnalise;
import br.edu.ifrs.canoas.tads.lds.bean.UsuarioExame;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterExameSangueService;

public class ManterExameSangueMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7429065292403103386L;
	private static final String URL_LISTAR_EXAMESANGUE = "/private/pages/listarExameSangue.jsf";
	private static final String URL_MANTER_EXAMESANGUE = "/private/pages/manterExameSangue.jsf";
	
	@Inject
	private GerenciarLoginMB gerenciarLoginMB;
	
	@Inject
	private UsuarioExame usuarioExame;
	
	@Inject
	private ItemExameSangue itemExameSangue;
	
	@EJB
	private ManterExameSangueService exameSangueService;
	
	//@EJB
	//private ManterTipoAnaliseService tipoAnaliseService;
	
	private String criterioExameSangue;
	
	
//listas exames e tipos de análises
	private List<UsuarioExame> listaExames;	
	private List<TipoAnalise> tiposAnalise;
	
//getters and setters
	public GerenciarLoginMB getGerenciarLoginMB() {
		return gerenciarLoginMB;
	}
	public void setGerenciarLoginMB(GerenciarLoginMB gerenciarLoginMB) {
		this.gerenciarLoginMB = gerenciarLoginMB;
	}
	public UsuarioExame getUsuarioExame() {
		return usuarioExame;
	}
	public void setUsuarioSangue(UsuarioExame usuarioExame) {
		this.usuarioExame = usuarioExame;
	}
	public ItemExameSangue getItemExameSangue() {
		return itemExameSangue;
	}
	public void setItemExameSangue(ItemExameSangue itemExameSangue) {
		this.itemExameSangue = itemExameSangue;
	}
	public ManterExameSangueService getExameSangueService() {
		return exameSangueService;
	}
	public void setExameSangueService(ManterExameSangueService exameSangueService) {
		this.exameSangueService = exameSangueService;
	}
	/*public ManterTipoAnaliseService getTipoAnaliseService() {
		return tipoAnaliseService;
	}
	public void setTipoAnaliseService(ManterTipoAnaliseService tipoAnaliseService) {
		this.tipoAnaliseService = tipoAnaliseService;
	}*/
	public String getCriterioExameSangue() {
		return criterioExameSangue;
	}
	public void setCriterioExameSangue(String criterioExameSangue) {
		this.criterioExameSangue = criterioExameSangue;
	}
	public List<UsuarioExame> getListaExames() {
		return listaExames;
	}
	public void setListaExames(List<UsuarioExame> listaExames) {
		this.listaExames = listaExames;
	}
	public List<TipoAnalise> getTiposAnalise() {
		return tiposAnalise;
	}
	public void setTiposAnalise(List<TipoAnalise> tiposAnalise) {
		this.tiposAnalise = tiposAnalise;
	}
	
//métodos
	
	
	

}
