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
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifrs.canoas.tads.lds.bean.Alimento;
import br.edu.ifrs.canoas.tads.lds.bean.Refeicao;
import br.edu.ifrs.canoas.tads.lds.bean.RefeicaoAlimento;
import br.edu.ifrs.canoas.tads.lds.bean.TipoAlimento;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterRefeicaoService;

@Named
@SessionScoped
public class ManterRefeicoesMB implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3001851211765166179L;

	private static final String URL_LISTAR_REFEICOES = "/private/pages/listarRefeicao.jsf";
	
	@Inject
	private GerenciarLoginMB gerenciarLoginMB;
	
	@Inject
	private ListarRefeicaoMB listarRefeicaoMB;

	@EJB
	private ManterRefeicaoService manterRefeicaoService;
	
	private TipoAlimento tipoAlimento;
	private Alimento alimento;
	private Refeicao refeicao;
	private double quantidadeGrMl;
	
	// Listas
	private List<TipoAlimento> tipoAlimentos;
	private List<Alimento> alimentos;
	
	public ManterRefeicoesMB() {
		this.clear();
	}

	@PostConstruct
	public void init() {
		this.clear();
		
		if(this.tipoAlimentos == null)
			tipoAlimentos = manterRefeicaoService.buscaTodosTiposAlimentos();
		if (alimentos == null)
			alimentos = manterRefeicaoService.buscaAlimentos(null);
	}

	public void onTipoAlimentoChange(){
		alimentos.clear();
		
		if (tipoAlimento != null && tipoAlimento.getId() > 0){
			alimentos = manterRefeicaoService.buscaAlimentos(tipoAlimento);						
		}
	}
	
	public void salvarRefeicoes() throws IOException{
		if(refeicao == null || refeicao.getRefeicaoAlimentos().size() == 0){
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Insira ao menos um alimento.",
							"Insira ao menos um alimento."));
			return;
		}		
		
		if(this.manterRefeicaoService.salvarRefeicoes(getRefeicao(),this.gerenciarLoginMB.getUsuario()) == false){
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar os dados.",
							"Erro ao salvar os dados."));
			return;
		}
		
		this.clear();
	}
	
	public void adicionarRefeicoes(){
		if(this.getAlimento() != null && this.getRefeicao() != null){		
			
			RefeicaoAlimento refeicaoAlimento = new RefeicaoAlimento();
			refeicaoAlimento.setPesoEmGramasOuMl(this.getQuantidadeGrMl());
			refeicaoAlimento.setAlimento(this.getAlimento());
			refeicaoAlimento.setRefeicao(this.getRefeicao());
			this.getRefeicao().getRefeicaoAlimentos().add(refeicaoAlimento);
		}
	}
	
	public void clear(){
		tipoAlimento = null;
		alimento = null;
		this.alimentos = new ArrayList<>();
		setRefeicao(new Refeicao());
	}
	
	public Refeicao getRefeicao() {
		return refeicao;
	}

	public void setRefeicao(Refeicao refeicao) {
		this.refeicao = refeicao;
	}
	
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

	public List<Alimento> getAlimentos() {
		return alimentos;
	}

	public void setAlimentos(List<Alimento> alimentos) {
		this.alimentos = alimentos;
	}

	public Alimento getAlimento() {
		return alimento;
	}

	public void setAlimento(Alimento alimento) {
		this.alimento = alimento;
	}

	public double getQuantidadeGrMl() {
		return quantidadeGrMl;
	}

	public void setQuantidadeGrMl(double quantidadeGrMl) {
		this.quantidadeGrMl = quantidadeGrMl;
	}
	
	public double getTotalCalorias(){
		double retorno = 0;
		
		if(this.getRefeicao() != null && this.getRefeicao().getRefeicaoAlimentos() != null){
			
			for (RefeicaoAlimento refeicaoAlimento : this.getRefeicao().getRefeicaoAlimentos()) {
				retorno += refeicaoAlimento.getQuantidadeCaloriasValor();
			}
			
		}
		
		return retorno;
	}
}
