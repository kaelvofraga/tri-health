package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import br.edu.ifrs.canoas.tads.lds.bean.Alimento;
import br.edu.ifrs.canoas.tads.lds.bean.Medicamento;
import br.edu.ifrs.canoas.tads.lds.bean.TipoAlimento;
import br.edu.ifrs.canoas.tads.lds.control.service.ListarRefeicaoService;

@Named
@SessionScoped
public class ListarRefeicaoMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7757050323140036846L;

	@EJB
	private ListarRefeicaoService listarRefeicaoService;
	
	//Beans
	private TipoAlimento tipoAlimento;	
	private Alimento alimento;	
	
	//Lista Atividades do Usuario
	private List<TipoAlimento> tipoAlimentos;	
	private List<Alimento> alimentos;	
	
	public ListarRefeicaoMB() {
		
	}
	
	@PostConstruct
	public void init(){
		
		/** Listas **/
		if(tipoAlimentos == null)
			tipoAlimentos = listarRefeicaoService.buscaTodosTiposAlimentos();
		if(alimentos == null)
			alimentos = new ArrayList<>();
	}
	
	public List<TipoAlimento> completeTipoAlimento(String query){
		if(this.getTipoAlimentos() == null){
			this.setTipoAlimentos(listarRefeicaoService.buscaTodosTiposAlimentos());
		}
		
		
		List<TipoAlimento> tipoAlimentoFiltrados = new ArrayList<TipoAlimento>();
         
		 for (int i = 0; i < getTipoAlimentos().size(); i++) {
	            TipoAlimento tipo = getTipoAlimentos().get(i);
	            if(tipo.getNome().trim().toLowerCase().startsWith(query)) {
	            	tipoAlimentoFiltrados.add(tipo);
	            }
	        }
		
		/*tipoAlimentoFiltrados = this.getTipoAlimentos().stream()
				.filter(x-> x.getNome().trim().toLowerCase().startsWith(query))
				.collect(Collectors.toList());*/
        
        return tipoAlimentoFiltrados;
	}
	
	public List<Alimento> completeAlimento(String query){        
        return new ArrayList<Alimento>();
	}
	
	//Getters e Setters
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
}
