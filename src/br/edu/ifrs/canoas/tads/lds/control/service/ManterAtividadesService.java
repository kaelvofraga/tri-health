package br.edu.ifrs.canoas.tads.lds.control.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.Atividade;
import br.edu.ifrs.canoas.tads.lds.bean.AtividadeUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.TipoAtividade;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;
import br.edu.ifrs.canoas.tads.lds.model.dao.AtividadeDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.AtividadeUsuarioDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.TipoAtividadeDAO;
import br.edu.ifrs.canoas.tads.lds.util.MathUtil;
import br.edu.ifrs.canoas.tads.lds.util.Mensagens;
import br.edu.ifrs.canoas.tads.lds.util.StrUtil;

/** Service da US Manter Atividades F�sicas
* @author Kael Fraga
* @since 07/05/2015
* */
@Stateless
public class ManterAtividadesService {

	@Inject
	private AtividadeUsuarioDAO atividadeUsuarioDAO;

	@Inject
	private TipoAtividadeDAO tipoAtividadeDAO;

	@Inject
	private AtividadeDAO atividadeDAO;
		
	/** 
	 * @brief Monta mensagem a ser compartilhada no Facebook.	  	 		  
	 * @param AtividadeUsuario atividadeUsuario: atividade relacionada a um usu�rio
	 * @return mensagem que ser� compartilhada no Facebook
	 * */
	public String montaFacebookMensagem(AtividadeUsuario atividadeUsuario) {
		
		if (atividadeUsuario == null || 
			atividadeUsuario.getAtividade() == null || 
			atividadeUsuario.getUsuario() == null){
			
			return "";
		}
			
		return Mensagens.getBundleMessage("Facebook.compartilhar.notificao", 
										  new SimpleDateFormat("dd/MM/yyyy").format(atividadeUsuario.getDataFim()),
										  atividadeUsuario.getDuracao(),
										  atividadeUsuario.getAtividade().getDescricao()); 
	}
		
	/** 
	 * @brief Calcula dura��o da pr�tica de uma atividade atrav�s da data inicial e data final.  	 		  
	 * @param AtividadeUsuario atividadeUsuario: atividade relacionada a um usu�rio
	 * @return long: dura��o da atividade convertida para minutos
	 * */
	public long calculaDuracao(AtividadeUsuario atividadeUsuario) {
		long timeDifMilli = 0L;
		long timeDifMinutes = 0L;

		Date dataIni = atividadeUsuario.getDataInicio();
		Date dataFim = atividadeUsuario.getDataFim();
		timeDifMilli = dataFim.getTime() - dataIni.getTime();
		timeDifMinutes = TimeUnit.MILLISECONDS.toMinutes(timeDifMilli);

		return timeDifMinutes;
	}
	
	/** 
	 * @brief Valida se a data Final � maior que a Inicial.	  	 		  
	 * @param AtividadeUsuario atividadeUsuario: atividade relacionada a um usu�rio
	 * @return true se for maior, false se n�o
	 * */
	public boolean validaDatas(AtividadeUsuario atividadeUsuario) {
		return calculaDuracao(atividadeUsuario) > 0L;
	}	
	
	/** 
	 * @brief Calcula calorias queimadas durante execu��o de uma atividade. 	 		  
	 * @param AtividadeUsuario atividadeUsuario: atividade relacionada a um usu�rio
	 * @return double: valor de calorias gastas arredondado para 2 casas decimais
	 * */
	public double calculaCaloriasQueimadas(AtividadeUsuario atividadeUsuario) {
		double massaCorporal = 1.0;
		
		//TODO implementar relacionamento com peso do usu�rio na pr�xima Sprint
		
		if (atividadeUsuario == null) {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"Atividade.cadastro.erro.calculaCaloria");
			return 0.0;
		}
	
		if(this.validaDatas(atividadeUsuario) == false){
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"Atividade.cadastro.erro.dataFinalMenor");
			return 0.0;
		}
		
		atividadeUsuario.setDuracao(this.calculaDuracao(atividadeUsuario));

		return MathUtil.round((atividadeUsuario.getAtividade().getMET() * massaCorporal * (atividadeUsuario.getDuracao()/60.0)), 2);	
	}
	
	/** 
	 * @brief Salva atividade relacionada a um usu�rio no BD.	  	 		  
	 * @param AtividadeUsuario atividadeUsuario: atividade relacionada a um usu�rio
	 * @return true se salva com sucesso, false se um erro ocorreu
	 * */
	public boolean salvaAtividadeUsuario(AtividadeUsuario atividadeUsuario) {
		if (atividadeUsuario == null || 
				atividadeUsuario.getAtividade() == null || 
				atividadeUsuario.getUsuario() == null) {

			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"Atividade.cadastro.erro");
			return false;
		}

		if(validaDatas(atividadeUsuario) == false){
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"Atividade.cadastro.erro.dataFinalMenor");
			return false;
		}			

		atividadeUsuarioDAO.insere(atividadeUsuario);

		Mensagens.define(FacesMessage.SEVERITY_INFO,
				"Atividade.cadastro.sucesso");

		return true;
	}
	
	/** 
	 * @brief Busca uma atividade espec�fica relacionada a um usu�rio.	  	 		  
	 * @param AtividadeUsuario atividadeUsuario: atividade relacionada a um usu�rio
	 * @return AtividadeUsuario: atividade encontrada ou null se um erro ocorrer
	 * */
	public AtividadeUsuario buscaAtividadeUsuarioPorID(
			AtividadeUsuario atividadeUsuario) {
		AtividadeUsuario atv = atividadeUsuarioDAO.busca(atividadeUsuario
				.getId());

		if (atividadeUsuario != null)
			atividadeUsuario = atv;

		return atividadeUsuario;
	}
	
	/** 
	 * @brief Busca todas as atividades relacionadas a um usu�rio.  	 		  
	 * @param Usuario usuario: usu�rio relacionado as atividades
	 * @return ArrayList<AtividadeUsuario>: lista de atividades do usuario ou null se um erro ocorrer
	 * */
	public List<AtividadeUsuario> buscaAtividadesDoUsuario(Usuario usuario) {

		if (usuario != null && usuario.getId() != null)
			return atividadeUsuarioDAO.buscaAtividadesDoUsuario(usuario);

		return new ArrayList<AtividadeUsuario>();
	}
	
	/** 
	 * @brief Busca atividades relacionadas a um usu�rio e a um crit�rio de pesquisa.	  	 		  
	 * @param String criterioAtividadeUsuario: crit�rio de pesquisa
	 * @param Usuario usuario: usu�rio relacionado as atividades
	 * @return List<AtividadeUsuario>: lista de atividades do usuario ou null se um erro ocorrer
	 * */
	public List<AtividadeUsuario> buscaGeral(String criterioAtividadeUsuario,
			Usuario usuario) {
		
		List<AtividadeUsuario> auList = null;
		
		if (usuario == null)
			return null;

		if (StrUtil.isNotBlank(criterioAtividadeUsuario) && criterioAtividadeUsuario != null) {
			auList = atividadeUsuarioDAO.buscaPorCriterio(criterioAtividadeUsuario,
					usuario);
		} else {
			auList = atividadeUsuarioDAO.buscaAtividadesDoUsuario(usuario);			
		}
		
		if(auList != null){
			for(AtividadeUsuario au: auList){
				au.setDuracao(calculaDuracao(au));
				au.setCalorias(calculaCaloriasQueimadas(au));
			}
		}
		
		return auList;
	}
	
	/** 
	 * @brief Busca tipos de atividades.	  	 		  
	 * @param void
	 * @return List<TipoAtividade>: lista de tipos de atividades ou null se um erro ocorrer
	 * */
	public List<TipoAtividade> buscaNomeTipoAtividades() {
		return tipoAtividadeDAO.buscaTodos();
	}
	
	/** 
	 * @brief Busca atividades.	  	 		  
	 * @param void
	 * @return List<Atividade>: lista de atividades ou null se um erro ocorrer
	 * */
	public List<Atividade> buscaDescricoesAtividades() {
		return atividadeDAO.buscaTodos();
	}
	
	/** 
	 * @brief Atualiza valores da atividade no BD.	  	 		  
	 * @param AtividadeUsuario atividadeUsuario: atividade relacionada a um usu�rio
	 * @return void
	 * */
	public void alteraAtividadeUsario(AtividadeUsuario atividadeUsuario) {
		if (atividadeUsuario != null && atividadeUsuario.getId() != null) {

			if(validaDatas(atividadeUsuario) == false){
				Mensagens.define(FacesMessage.SEVERITY_ERROR,
						"Atividade.cadastro.erro.dataFinalMenor");
				return;
			}			
			
			atividadeUsuarioDAO.atualiza(atividadeUsuario);
			Mensagens.define(FacesMessage.SEVERITY_INFO,
					"AtividadeUsuario.alterar.sucesso");
		} else {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"AtividadeUsuario.alterar.erro");
		}
	}

	/** 
	 * @brief Exclui atividade no BD.	  	 		  
	 * @param AtividadeUsuario atividadeUsuario: atividade relacionada a um usu�rio
	 * @return void
	 * */
	public void excluiAtividadeUsuario(AtividadeUsuario atividadeUsuario) {
		if (atividadeUsuario != null && atividadeUsuario.getId() != null) {
			atividadeUsuarioDAO.exclui(atividadeUsuario.getId());
			Mensagens.define(FacesMessage.SEVERITY_INFO,
					"AtividadeUsuario.excluir.sucesso");
		} else {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"AtividadeUsuario.excluir.erro");
		}
	}
}
