package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.Alimento;
import br.edu.ifrs.canoas.tads.lds.bean.Atividade;
import br.edu.ifrs.canoas.tads.lds.bean.AtividadeUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.TipoAlimento;
import br.edu.ifrs.canoas.tads.lds.bean.TipoAtividade;
import br.edu.ifrs.canoas.tads.lds.model.dao.AtividadeDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.TipoAtividadeDAO;
import br.edu.ifrs.canoas.tads.lds.util.Mensagens;

/** 
 * Service da US Manter Categoria
 * @author Luana
 * @version 21/06/2015

 */

@Stateless
public class ManterCategoriaService {
	
	@Inject
	private AtividadeDAO atividadeDAO;
	
	@Inject
	private TipoAtividadeDAO tipoAtividadeDAO;
		
	/** 
	 * @brief Salva atividade relacionada a um usuário no BD.	  	 		  
	 * @param AtividadeUsuario atividadeUsuario: atividade relacionada a um usuário
	 * @return true se salva com sucesso, false se um erro ocorreu
	 * */
	public Boolean salvaCategoria(Atividade atividade, TipoAtividade tipoAtividade) {
		if(tipoAtividade == null || tipoAtividade.getNome() == null ){	
			Mensagens.define(FacesMessage.SEVERITY_ERROR, "Atividade.cadastro.erro");
			return false;	
		}
		if(atividade == null || atividade.getDescricao() == null){	
		
			Mensagens.define(FacesMessage.SEVERITY_ERROR, "Atividade.cadastro.erro");
			return false;					
		}				
		if (tipoAtividadeDAO.buscaPorNome(tipoAtividade).size() == 0)
			tipoAtividadeDAO.insere(tipoAtividade);
	
		atividade.setTipoAtividade(tipoAtividade);
		atividadeDAO.insere(atividade);		
		Mensagens.define(FacesMessage.SEVERITY_INFO, "Atividade.cadastro.sucesso");
		
		return true;	
	}	
	

	/** 
	 * @brief Busca todos tipos de atividades	  	 		  
	 * */
	public List<TipoAtividade> buscaTipoAtividades() {
		return tipoAtividadeDAO.buscaTodos();
	}
	
	/** 
	 * @brief Busca todas descricoes de atividades	  	 		  
	 * */
	public List<Atividade> buscaDescricaoAtividades(){
		return atividadeDAO.buscaTodos();
	}
			
	/** 
	 * @brief Busca todas descricoes de atividades de acordo com o tipo de atividade 	 		  
	 * */
	public List<Atividade> buscaAtividades(TipoAtividade tipoAtividade){
		List<Atividade> retorno = atividadeDAO.buscaAtividadePorTipoAtividade(tipoAtividade);
		return retorno;
	}
	
	/** 
	 * @brief Atualiza valores da categoria no BD.	  	 		  
	 * @return void
	 * */
	public void alteraAtividade(Atividade atividade) {
		if (atividade != null && atividade.getId() != null) {			
			atividadeDAO.atualiza(atividade);
			Mensagens.define(FacesMessage.SEVERITY_INFO,
					"AtividadeUsuario.alterar.sucesso");
		} else {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"AtividadeUsuario.alterar.erro");
		}
	}

	/** 
	 * @brief Exclui valores da categoria no BD.	  	 		  
	 * @return void
	 * */
	public void excluiAtividade(Atividade atividade) {
			
		if (atividade != null && atividade.getId() != null) {
			atividadeDAO.exclui(atividade.getId());
			Mensagens.define(FacesMessage.SEVERITY_INFO,
					"AtividadeUsuario.excluir.sucesso");
		} else {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"AtividadeUsuario.excluir.erro");
		}
			
	}
}