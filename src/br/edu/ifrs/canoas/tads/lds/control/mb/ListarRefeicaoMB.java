package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.edu.ifrs.canoas.tads.lds.bean.Alimento;
import br.edu.ifrs.canoas.tads.lds.bean.Refeicao;
import br.edu.ifrs.canoas.tads.lds.bean.TipoAlimento;
import br.edu.ifrs.canoas.tads.lds.control.service.ListarRefeicaoService;
import br.edu.ifrs.canoas.tads.lds.util.DateUtil;

@Named
@SessionScoped
public class ListarRefeicaoMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7757050323140036846L;
	
	@Inject
	private GerenciarLoginMB gerenciarLoginMB;
	
	@Inject
	private ManterRefeicoesMB manterRefeicoesMB;

	@EJB
	private ListarRefeicaoService listarRefeicaoService;

	//Parâmetros
	private Date dataDe;
	private Date dataAte;
	
	// Beans
	private TipoAlimento tipoAlimento;
	private Alimento alimento;
	private Refeicao refeicao;

	// Listas
	private List<TipoAlimento> tipoAlimentos;
	private List<Alimento> alimentos;
	private List<Refeicao> refeicoes;
	
	public ListarRefeicaoMB() {	
	}

	@PostConstruct
	public void init() {
		this.clear();
	}
	
	public void onTipoAlimentoChange(){
		alimentos.clear();
		
		if (tipoAlimento != null && tipoAlimento.getId() > 0){
			alimentos = listarRefeicaoService.buscaAlimentos(tipoAlimento);						
		}
	}
	
	public void onRefeicaoSelect(SelectEvent event) throws IOException {
		manterRefeicoesMB.clear();
		manterRefeicoesMB.setRefeicao(getRefeicao());
        FacesContext.getCurrentInstance().getExternalContext().redirect("manterRefeicoes.jsf");
    }
	
	public void novaRefeicao() throws IOException{
		manterRefeicoesMB.clear();
        FacesContext.getCurrentInstance().getExternalContext().redirect("manterRefeicoes.jsf");
	}
	
	public void atualizarDataTable(){
		this.setRefeicoes(listarRefeicaoService.buscaRefeicoes(this.getDataDe(),this.getDataAte(),this.getAlimento(), gerenciarLoginMB.getUsuario()));
	}
	
	public void clear(){
		tipoAlimento = null;
		alimento = null;
		refeicao = null;
		dataDe = DateUtil.getDataAtual();
		dataAte = DateUtil.getDataAtualIncrementa(1);
		alimentos = listarRefeicaoService.buscaAlimentos(null);

		/** Listas **/
		if (tipoAlimentos == null)
			tipoAlimentos = listarRefeicaoService.buscaTodosTiposAlimentos();		
		if (refeicoes == null)
			refeicoes = new ArrayList<>();
	}

	// Getters e Setters
	public TipoAlimento getTipoAlimento() {
		return tipoAlimento;
	}

	public void setTipoAlimento(TipoAlimento tipoAlimento) {
		this.tipoAlimento = tipoAlimento;
	}

	public List<TipoAlimento> getTipoAlimentos() {
		return tipoAlimentos;
	}

	public void setTipoAlimentos(List<TipoAlimento> tipoAlimentos) {
		this.tipoAlimentos = tipoAlimentos;
	}

	public Alimento getAlimento() {
		return alimento;
	}

	public void setAlimento(Alimento alimento) {
		this.alimento = alimento;
	}

	public List<Alimento> getAlimentos() {
		return alimentos;
	}

	public void setAlimentos(List<Alimento> alimentos) {
		this.alimentos = alimentos;
	}

	public List<Refeicao> getRefeicoes() {
		return refeicoes;
	}

	public void setRefeicoes(List<Refeicao> refeicoes) {
		this.refeicoes = refeicoes;
	}

	public Refeicao getRefeicao() {
		return refeicao;
	}

	public void setRefeicao(Refeicao refeicao) {
		this.refeicao = refeicao;
	}

	public Date getDataAte() {
		return dataAte;
	}

	public void setDataAte(Date dataAte) {
		this.dataAte = dataAte;
	}

	public Date getDataDe() {
		return dataDe;
	}

	public void setDataDe(Date dataDe) {
		this.dataDe = dataDe;
	}
}
