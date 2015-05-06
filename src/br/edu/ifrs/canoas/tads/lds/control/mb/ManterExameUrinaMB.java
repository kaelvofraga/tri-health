package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.edu.ifrs.canoas.tads.lds.bean.AtividadeUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.ExameUrina;
import br.edu.ifrs.canoas.tads.lds.bean.Medicamento;
import br.edu.ifrs.canoas.tads.lds.bean.MedicamentoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.TipoAtividade;
import br.edu.ifrs.canoas.tads.lds.bean.TipoExameUrina;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterExameUrinaService;

@Named
@RequestScoped
public class ManterExameUrinaMB implements Serializable{

	/**
	 * Classe bean do Exame de Urina
	 */
//atributos
	private static final long serialVersionUID = 7113326743475818284L;
	private static final String URL_LISTAR_USO_TIPOSEXAMEURINA = "/private/pages/listarExameUrina.jsf";
	private static final String URL_MANTER_USO_EXAMEURINA = "/private/pages/manterExameUrina.jsf";
	private static final String URL_MANTER_NOVO_EXAMEURINA = "/private/pages/manterExameUrina.xhtml";
	
	@Inject
	private GerenciarLoginMB gerenciarLoginMB;
	
	@Inject
	private ExameUrina exameUrina;
	
	@EJB
	private ManterExameUrinaService exameUrinaService;
	
	private String criterioExameUrina;
	
//listas exames e tipos de exame
	private List<ExameUrina> examesLista;	
	private List<TipoExameUrina> tipos;
	
//getters and setters
	public ExameUrina getExameUrina() {
		return exameUrina;
	}

	public void setExameUrina(ExameUrina exameUrina) {
		this.exameUrina = exameUrina;
	}
	
	public List<ExameUrina> getExamesLista() {
		return examesLista;
	}

	public void setExamesLista(List<ExameUrina> examesLista) {
		this.examesLista = examesLista;
	}

	public List<TipoExameUrina> getTipos() {
		if (tipos == null)
			tipos = exameUrinaService.buscaTipoExameUrina();
		return tipos;
	}
	
	public void setTipos(List<TipoExameUrina> tipos) {
		this.tipos = tipos;
	}

	public String getCriterioExameUrina() {
		return criterioExameUrina;
	}

	public void setCriterioExameUrina(String criterioExameUrina) {
		this.criterioExameUrina = criterioExameUrina;
	}
//métodos
	
	public void inicializa() {	//inicialização do listar e manter
		exameUrina = new ExameUrina();
		exameUrina.setTipoExameUrina(new TipoExameUrina());
		examesLista = new ArrayList<>();
		tipos = new ArrayList<>();
		criterioExameUrina="";
	}
	
	public boolean isAtualizacao(){
		return exameUrina != null && exameUrina.getId() != null;
	}
	
	public void salvaExame(){
		exameUrina.setUsuario(gerenciarLoginMB.getUsuario());
		exameUrinaService.salvaExameUrinaUsuario(exameUrina);
		this.inicializa();
	}
	
	public String alteraExameUrina() {
		exameUrinaService.alteraExameUrina(exameUrina);
		return URL_LISTAR_USO_TIPOSEXAMEURINA;
	}	
	
	/*public String excluiExameUrina(){
		if (exameUrinaService.excluiExameUrina(exameUrina)){
			this.busca();
			return URL_LISTAR_USO_TIPOSEXAMEURINA;
		}
		return URL_MANTER_USO_EXAMEURINA;
	}*/
	
	public void busca(){
		examesLista = exameUrinaService.busca(criterioExameUrina);
	}
	
	public void clear(){
		exameUrina = new ExameUrina();
//		exameUrina.setTipoExameUrina(new TipoExameUrina());
		examesLista = new ArrayList<>();
	}
	
	public List<TipoExameUrina> completeTipoExameUrina(String query){
		if (tipos == null) 
			tipos = exameUrinaService.buscaTiposExameUrina(query, gerenciarLoginMB.getUsuario());

		List<TipoExameUrina> tiposBusca = new ArrayList<TipoExameUrina>();
         
        for (int i = 0; i < tipos.size(); i++) {
            TipoExameUrina tipoExameUrina = tipos.get(i);
            if(tipoExameUrina.getTipo().trim().toLowerCase().startsWith(query)) {
            	tiposBusca.add(tipoExameUrina);
            }
        }
        return tiposBusca;
	}
	
	public String novoExameUrina(){
//		this.emListagemAtvs = false;
//		this.clear();
		return URL_MANTER_NOVO_EXAMEURINA;
	}
	
	public void onRowSelect(SelectEvent event) throws IOException {
		this.exameUrina = (ExameUrina) event.getObject();
        FacesContext.getCurrentInstance().getExternalContext().redirect("manterExameUrina.jsf");
    }
	
	

}




