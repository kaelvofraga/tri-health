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

import br.edu.ifrs.canoas.tads.lds.bean.Atividade;
import br.edu.ifrs.canoas.tads.lds.bean.TipoAtividade;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterCategoriaService;

/**MB das views de Manter e Listar Categorias
 * @author Luana Gomes
 * @version 21/06/2015
 *
 */

@Named 
@SessionScoped 
public class ManterCategoriaMB implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final String URL_MANTER_CATEGORIA = "/private/pages/manterCategoria2.jsf";
	private static final String URL_LISTAR_CATEGORIA = "/private/pages/listarCategoria.jsf";

	@Inject
	private TipoAtividade tipoAtividade;
	
	@Inject
	private Atividade atividade;
	
	@EJB
	private ManterCategoriaService categoriaService;
	
	private List<TipoAtividade> listarAtividades;
	private List<Atividade> atividadeList;
	private List<Atividade> descricaoPorTipoList;;
	
	@PostConstruct
	public String initListar() {
		/*tipoAtividade = new TipoAtividade();
		atividadeList = new ArrayList<>();
		atividade = new Atividade();
		return URL_LISTAR_CATEGORIA;*/
		this.clear();
		return URL_LISTAR_CATEGORIA;
	}
	
	
	public String initManter() {
		tipoAtividade = new TipoAtividade();
		atividade = new Atividade();
		atividade.setTipoAtividade(tipoAtividade);
		//tipo
		listarAtividades = categoriaService.buscaTipoAtividades();
		//descricao
		atividadeList = categoriaService.buscaDescricaoAtividades();
		return URL_MANTER_CATEGORIA;
	}
	
	/** 
	 * @brief Salva categoria	  	 		  
	 * */
	public void salvaAtividade(){	
		
		if(categoriaService.salvaCategoria(atividade, tipoAtividade))
			this.initManter();
	}
	
	/** 
	 * @brief Busca atividades relacionadas tipo de atividade escolhida.
	 * */
	public void busca(){
		atividadeList = categoriaService.buscaAtividades(tipoAtividade);
	}
	
	/** 
	 * @brief Evento ao selecionar uma linha da tabela de descrição 
	 * */
	public void onATividadeFisica(SelectEvent event) throws IOException {
		//this.clear();
		this.setAtividade(getAtividade());
        FacesContext.getCurrentInstance().getExternalContext().redirect("manterCategoria2.jsf");
    }

	/** 
	 * @brief Verifica se a categoria atual está sendo inserida nova ou atualizada uma antiga.
	 * */
	public boolean isAtualizacao(){
		return atividade != null && atividade.getId() != null;
	}

	/** 
	 * @brief Atualiza dados no BD.
	 * */
	public String alteraAtividade(){
		categoriaService.alteraAtividade(atividade, tipoAtividade);
		this.initManter();
		return this.initListar();
	}
	
	/** 
	 * @brief Exclui categoria cadastrada do BD.
	 * */
	public String excluiAtividade(){	
		categoriaService.excluiAtividade(atividade);
		//this.busca();
		return URL_LISTAR_CATEGORIA;
	}
	
	/** 
	 * @brief 
	 * */
	private void clear() {
		tipoAtividade = new TipoAtividade();
		atividade = new Atividade();
		atividade.setTipoAtividade(tipoAtividade);
		//tipo
		if (listarAtividades == null)
			listarAtividades = categoriaService.buscaTipoAtividades();
		//descricao
		if(atividadeList == null)
			atividadeList = new ArrayList<>();		
	}
	
	/** 
	 * @brief Filtra descricao de acordo com o tipo de atividade
	 * */
	public void filtrarAtividades(){
		this.descricaoPorTipoList.clear();
		TipoAtividade ta = atividade.getTipoAtividade();
		if ( ta != null && ta.getId() != null ){			
			for (int i = 0; i < atividadeList.size(); i++) {
				Atividade a = atividadeList.get(i);
				if(a.getTipoAtividade().getId() == ta.getId()) {
					descricaoPorTipoList.add(a);
				}
			}

		}else{
			descricaoPorTipoList = new ArrayList<>();
		}
	}
			
	/*GETTERS AND SETTERS*/

	
	public ManterCategoriaService getCategoriaService() {
		return categoriaService;
	}

	public void setCategoriaService(ManterCategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}

	public TipoAtividade getTipoAtividade() {
		return tipoAtividade;
	}

	public void setTipoAtividade(TipoAtividade tipoAtividade) {
		this.tipoAtividade = tipoAtividade;
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public List<TipoAtividade> getListarAtividades() {
		if(listarAtividades == null)
			listarAtividades = categoriaService.buscaTipoAtividades();
		return listarAtividades;
	}

	public void setListarAtividades(List<TipoAtividade> listarAtividades) {
		this.listarAtividades = listarAtividades;
	}

	public List<Atividade> getAtividadeList() {
		if(atividadeList == null){
			atividadeList = categoriaService.buscaAtividades(tipoAtividade);		
		}
		return atividadeList;
	}

	public void setAtividadeList(List<Atividade> atividadeList) {		
		this.atividadeList = atividadeList;
	}

	public List<Atividade> getDescricaoPorTipoList() {
		return descricaoPorTipoList;
	}

	public void setDescricaoPorTipoList(List<Atividade> descricaoPorTipoList) {
		this.descricaoPorTipoList = descricaoPorTipoList;
	}

}