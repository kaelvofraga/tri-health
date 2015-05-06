package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifrs.canoas.tads.lds.bean.Alimento;
import br.edu.ifrs.canoas.tads.lds.bean.Refeicao;
import br.edu.ifrs.canoas.tads.lds.bean.TipoAlimento;
import br.edu.ifrs.canoas.tads.lds.control.service.ListarRefeicaoService;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterRefeicaoService;

@Named
@SessionScoped
public class ManterRefeicoesMB implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3001851211765166179L;

	@Inject
	private GerenciarLoginMB gerenciarLoginMB;

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
		tipoAlimento = null;
	}

	@PostConstruct
	public void init() {
		if(getRefeicao() == null){
			setRefeicao(new Refeicao());
		}	
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
	
	public void onAlimentoChange(){
		if(this.getAlimento() == null){
			
		}
	}
	
	public Refeicao getRefeicao() {
		return refeicao;
	}

	public void setRefeicao(Refeicao refeicao) {
		this.refeicao = refeicao;
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
}
