package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.edu.ifrs.canoas.tads.lds.bean.TipoAlimento;
import br.edu.ifrs.canoas.tads.lds.control.service.ListarRefeicaoService;

@Named
@SessionScoped
public class ListarRefeicaoMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1260194592160756614L;
	
	@EJB
	private ListarRefeicaoService listarRefeicaoService;
	
	private List<TipoAlimento> tipoAlimentos;	
	
	public ListarRefeicaoMB() {
		if(true){
			
		}
	}
	
	@PostConstruct
	public void init(){		
		setTipoAlimentos(new ArrayList<>());
	}
	
	public List<TipoAlimento> completeTipoAlimento(String query){
		List<TipoAlimento> tipoAlimentoFiltrados = new ArrayList<TipoAlimento>();
         
//		tipoAlimentoFiltrados = tipoAlimentos.stream()
//				.filter(x-> x.getNome().trim().toLowerCase().startsWith(query))
//				.collect(Collectors.toList());
        
        return tipoAlimentoFiltrados;
	}

	public List<TipoAlimento> getTipoAlimentos() {
		return tipoAlimentos;
	}

	public void setTipoAlimentos(List<TipoAlimento> tipoAlimentos) {
		this.tipoAlimentos = tipoAlimentos;
	}
}
