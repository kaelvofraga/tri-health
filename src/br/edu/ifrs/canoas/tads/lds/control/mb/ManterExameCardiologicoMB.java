package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.UploadedFile;

import br.edu.ifrs.canoas.tads.lds.bean.ExameCardiologico;
import br.edu.ifrs.canoas.tads.lds.bean.Medico;
import br.edu.ifrs.canoas.tads.lds.bean.TipoExame;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;
import br.edu.ifrs.canoas.tads.lds.control.service.ExameCardidologicoService;

/**
 * @author: Miromar Jos� de Lima 
 * 
 * Classe para representar a View manterExameCardiologico.jsf.
 * 
 *          Data:25/06/2015
 */

@Named
@SessionScoped
public class ManterExameCardiologicoMB implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String URL_LISTAR_EXAMES = "/private/pages/listarExameCardiologico.jsf";
	private static final String URL_MANTER_EXAMES = "/private/pages/manterExameCardiologico.jsf";

	@Inject
	private GerenciarLoginMB gerenciarLoginMB;
	
	@Inject
	private Usuario usuario;

	@EJB
	private ExameCardidologicoService exameCardidologicoService;

	@Inject
	private ExameCardiologico exameCardi;

	private String criterio = "";
	
	private UploadedFile file;

	private List<ExameCardiologico> listExameCardiologico;
	private List<TipoExame> listTipoExame;
	private List<Medico> listMedico;


	@PostConstruct
	public String initListar() {		
		listExameCardiologico = new ArrayList<ExameCardiologico>();
		listTipoExame = new ArrayList<TipoExame>();
		listMedico = new ArrayList<Medico>();
		return URL_LISTAR_EXAMES;
	}

	public String initManter() {
		exameCardi = new ExameCardiologico();
		return URL_MANTER_EXAMES;
	}

	public void busca() {
		listExameCardiologico = exameCardidologicoService.busca(this.criterio);
	}

	public void clearTable() {
		exameCardi = new ExameCardiologico();
		this.criterio = "";
		this.busca();
	}

	public String novo() {
		exameCardi = new ExameCardiologico();
		return URL_MANTER_EXAMES;
	}

	public String voltaPraLista() {
		return URL_LISTAR_EXAMES;
	}

	public String excluiExame() {
		exameCardidologicoService.excluiExame(exameCardi);
		return URL_LISTAR_EXAMES;
	}

	public void alteraExame() {
		exameCardidologicoService.alteraExame(exameCardi);
	}

	public void salvaExame() {
		if(file != null) {
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
		
		exameCardi.setUsuario(gerenciarLoginMB.getUsuario());
		exameCardidologicoService.salvaExameCard(exameCardi);
		initListar();
	}
	
	
	public boolean isEmEdicao() {
		return exameCardi != null && exameCardi.getId() != null;
	}

	public List<ExameCardiologico> getListExameCardiologico() {
		this.busca();
		return listExameCardiologico;
	}

	public void setListExameCardiologico(List<ExameCardiologico> listExameCardiologico) {
		this.listExameCardiologico = listExameCardiologico;
	}
	
	
	public ExameCardiologico getExame() {
		return exameCardi;
	}


	public void setExame(ExameCardiologico exame) {
		this.exameCardi = exame;
	}

	public String getCriterio() {
		return criterio;
	}

	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}

	
	public boolean isAtualizacao() {
		return exameCardi != null && exameCardi.getId() != null;
	}

	public void onRowSelect(SelectEvent event) throws IOException {
		this.exameCardi = (ExameCardiologico) event.getObject();
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("manterExameCardiologico.jsf");
	}

	public void onRowUnselect(UnselectEvent event) {
		FacesMessage msg = new FacesMessage("Exame Desmarcado");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public List<Medico> getListMedico() {
		listMedico = exameCardidologicoService.buscaMedicos("");
		return listMedico;
	}

	public void setListMedico(List<Medico> listMedico) {
		this.listMedico = listMedico;
	}
	
	public List<TipoExame> getListTipoExame() {
		listTipoExame = exameCardidologicoService.buscaTipoExames("");
		return listTipoExame;
	}

	public void setListTipoExame(List<TipoExame> listTipoExame) {
		this.listTipoExame = listTipoExame;
	}
	public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
	
	
	 /* 
	  * public void setExameCardiologico(ExameCardiologico exame) {
			this.exame = exame;
		}
	 * */
	
	public Usuario getUsuario() {
		return this.usuario = gerenciarLoginMB.getUsuario();
	}
}