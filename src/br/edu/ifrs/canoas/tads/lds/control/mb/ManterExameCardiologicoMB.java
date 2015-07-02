package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.UploadedFile;

import br.edu.ifrs.canoas.tads.lds.bean.ExameCardiologico;
import br.edu.ifrs.canoas.tads.lds.bean.ExameUrinaUsuario;
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
	private static final long serialVersionUID = -123416137736052462L;

	private static final String URL_LISTAR_EXAMES = "/private/pages/listarExameCardiologico.jsf";
	private static final String URL_MANTER_EXAMES = "/private/pages/manterExameCardiologico.jsf";
	
	@Inject
	private GerenciarLoginMB gerenciarLoginMB;
		
	@EJB
	private ExameCardidologicoService exameCardidologicoService;

	@Inject
	private ExameCardiologico exameCardi;
	
	@SuppressWarnings("unused")
	private Usuario usuario;

	
	private String criterio = "";
	
	private UploadedFile file;
	
	private FileOutputStream fileRecupedado;

	private List<ExameCardiologico> listExameCardiologico;
	private List<TipoExame> listTipoExame;
	private List<Medico> listMedico;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataDe;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAte;
	
	private String criterioExameCardiologico = "";
	
	@PostConstruct
	public String initListar() {		
		listExameCardiologico = new ArrayList<ExameCardiologico>();
		listTipoExame = new ArrayList<TipoExame>();
		listMedico = new ArrayList<Medico>();
		//criterioExameCardiologico = "";
		//dataDe = null;
		//dataAte = null;
		return URL_LISTAR_EXAMES;
	}

	public String initManter() {
		exameCardi = new ExameCardiologico();
		//exameCardi.setDataExame(null);
		return URL_MANTER_EXAMES;
	}

	/*
	public void buscaExameCardiologico() {		
		listExameCardiologico  = exameCardidologicoService.buscaExameCardiCriterio(this.getDataDe(),this.getDataAte(), criterioExameCardiologico);
		
	}
*/
	
	public void busca() {
		listExameCardiologico  = exameCardidologicoService.busca(this.criterio);
	}
	
	public void clearTable() {
		exameCardi = new ExameCardiologico();
		this.criterio = "";
		//this.buscaExameCardiologico();
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

	public String salvaExame() {
		//if(file != null) {
       //     FacesMessage message = new FacesMessage("Sucesso ao enviar Arquivo!", file.getFileName() + " is uploaded.");
        //    FacesContext.getCurrentInstance().addMessage(null, message);
        //}		
		exameCardi.setUsuario(gerenciarLoginMB.getUsuario());
		//exameCardi.setArquivoLaudo(file.getContents());
		exameCardidologicoService.salvaExameCard(exameCardi);
		exameCardi = new ExameCardiologico();
		return URL_MANTER_EXAMES;
	}
	
	
	public boolean isEmEdicao() {
		return exameCardi != null && exameCardi.getId() != null;
	}

	public List<ExameCardiologico> getListExameCardiologico() {
		//this.buscaExameCardiologico();
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
				.redirect("../../private/pages/manterExameCardiologico.jsf");
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
	
	
	/*
	public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }

    
    
   public FileOutputStream abreArquivo(ExameCardiologico exame){
	   fileRecupedado = ExameCardiologicoDAO
	   return  fileRecupedado;
   }
*/
	
	public Usuario getUsuario() {
		return this.usuario = gerenciarLoginMB.getUsuario();
	}
	
	public Date getDataDe() {
		return dataDe;
	}

	public void setDataDe(Date dataDe) {
		this.dataDe = dataDe;
	}

	public Date getDataAte() {
		return dataAte;
	}

	public void setDataAte(Date dataAte) {
		this.dataAte = dataAte;
	}

	public String getCriterioExameCardidologico() {
		return criterioExameCardiologico;
	}

	public void setCriterioExameCardidologico(String criterioExameCardidologico) {
		this.criterioExameCardiologico = criterioExameCardidologico;
	}

	public GerenciarLoginMB getGerenciarLoginMB() {
		return gerenciarLoginMB;
	}

	public void setGerenciarLoginMB(GerenciarLoginMB gerenciarLoginMB) {
		this.gerenciarLoginMB = gerenciarLoginMB;
	}

	public ExameCardidologicoService getExameCardidologicoService() {
		return exameCardidologicoService;
	}

	public void setExameCardidologicoService(
			ExameCardidologicoService exameCardidologicoService) {
		this.exameCardidologicoService = exameCardidologicoService;
	}

	public ExameCardiologico getExameCardi() {
		return exameCardi;
	}

	public void setExameCardi(ExameCardiologico exameCardi) {
		this.exameCardi = exameCardi;
	}

	public String getCriterioExameCardiologico() {
		return criterioExameCardiologico;
	}

	public void setCriterioExameCardiologico(String criterioExameCardiologico) {
		this.criterioExameCardiologico = criterioExameCardiologico;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}