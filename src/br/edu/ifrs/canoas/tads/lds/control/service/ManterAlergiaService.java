package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import br.edu.ifrs.canoas.tads.lds.bean.AlergiaUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.Medicamento;
import br.edu.ifrs.canoas.tads.lds.bean.TipoAlergia;
import br.edu.ifrs.canoas.tads.lds.model.dao.AlergiaUsuarioDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.MedicamentoUsuarioDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.TipoAlergiaDAO;
import br.edu.ifrs.canoas.tads.lds.util.Mensagens;
import br.edu.ifrs.canoas.tads.lds.util.StrUtil;

/** 
 * @author RodrigoNoll 		  
 * Service Implementations por AlergiaUsuario
 * @author Alisson Lorscheiter
 * @version 28/06/2015
 * Foi alterado o metodo de salvar e alterar.
 * Foi adicionado o metodo de valida��o da Data.
 * Foi alterado o metodo de busca dos tiposAlergia.
 * Foi adicionado coment�rios aos metodos. * 
 * */

@Stateless
public class ManterAlergiaService {

	@Inject
	private AlergiaUsuarioDAO alergiaUsuarioDAO;
		
	@Inject
	private TipoAlergiaDAO tipoAlergiaDAO;
	
	@Inject
	private MedicamentoUsuarioDAO medicamentoUsuarioDAO;
	
	@Inject
	private ManterUsoMedicamentoService manterUsoMedicamentoService;
	
	/** 
	 * @brief Metodo que salva no banco de dados o alergiaUsuario ap�s a valida��o.
	 * Retorna V ou F se conseguiu ou n�o salvar no banco.	 		  
	 * @param alergiaUsuario(AlergiaUsuario)
	 * @return boolean
	 * Foi inserido a valida��o da data, do tipo de alergia informada,valida��o do medicamento e 
	 * tamb�m foi adicionada mensagens.
	 * */
	public boolean salvaAlergiaUsuario(AlergiaUsuario alergiaUsuario) {
		if(alergiaUsuario == null || alergiaUsuario.getUsuario() == null){			
			Mensagens.define(FacesMessage.SEVERITY_INFO, "manterPerfilEmergencia.cadastro.erro");
			return false;
		}
		
		if (validaData(alergiaUsuario) == false) {
			Mensagens.define(FacesMessage.SEVERITY_INFO, "manterPerfilEmergencia.cadastro.data.erro");
			return false;
		}
		
		try {
			if(alergiaUsuario.getTipoAlergia().getId()==4){
			alergiaUsuario.getMedicamentoUsuario().setDataConsulta(alergiaUsuario.getDataPrimeiraOcorrencia());
			alergiaUsuario.getMedicamentoUsuario().setUsuario(alergiaUsuario.getUsuario());
			alergiaUsuario.getMedicamentoUsuario().setMedicamento(buscaOuCriaMedicamentoPorNome(alergiaUsuario.getMedicamentoUsuario().getMedicamento()));
			medicamentoUsuarioDAO.insere(alergiaUsuario.getMedicamentoUsuario());
			alergiaUsuarioDAO.insere(alergiaUsuario);
			Mensagens.define(FacesMessage.SEVERITY_INFO,"manterPerfilEmergencia.cadastro.sucesso");
			return true;
			}
			else{
				Mensagens.define(FacesMessage.SEVERITY_INFO,"manterPerfilEmergencia.tpAlergia.erro");
				return false;
			}
		} catch (Exception e) {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,"manterPerfilEmergencia.cadastro.erro");
			return false;
		}	
	}
	
	/** 
	 * @author Alisson Lorscheiter
	 * @brief Metodo que valida a data informada verificando se ela � menor que a data atual..
	 * Retorna V ou F se validou ou n�o.	 		  
	 * @param alergiaUsuario(AlergiaUsuario)
	 * @return boolean
	 * M�todo criado para validar a Data informada.
	 * */
	public boolean validaData(AlergiaUsuario alergiaUsuario){
		long timeSysDate = new Date().getTime();
		long timeData = alergiaUsuario.getDataPrimeiraOcorrencia().getTime();
		if (timeData > timeSysDate) {
			return false;
		}
		return true;
	}
	
	/** 
	 * @brief Metodo que busca medicamento ou cria se n�o existir.	 		  
	 * @param medicamento(Medicamento)
	 * @return Medicamento
	 * */
	public Medicamento buscaOuCriaMedicamentoPorNome(Medicamento medicamento){
		return manterUsoMedicamentoService.buscaOuCriaMedicamentoPorNome(medicamento);
	}
	
	/** 
	 * @brief Metodo que realiza busca no banco de dados das alergias do usu�rio
	 * atrav�s de crit�rio informado.
	 * Retorna lista de AlergiaUsuario.	 		  
	 * @param criterioAlergia(String)
	 * @return List<AlergiaUsuario>
	 * */
	public List<AlergiaUsuario> busca(String criterioAlergia) {
		if (StrUtil.isNotBlank(criterioAlergia))
			return alergiaUsuarioDAO.buscaPorCriterio(criterioAlergia);
		else
			return alergiaUsuarioDAO.buscaTodos();
	}

	/** 
	 * @brief Metodo que realiza busca no banco de dados dos tipos de alergias
	 * Retorna lista de TipoAlergia.	 		  
	 * @return List<TipoAlergia>
	 * */
	public List<TipoAlergia> buscaDescricoesTipoAlergias() {
		return  tipoAlergiaDAO.buscaTodos();
	}
	

	/** 
	 * @brief Metodo que valida os campos e altera no banco de dados informa��es do AlergiaUsuario.
	 * Retorna V ou F se conseguiu ou n�o alterar no banco.	 		  
	 * @param alergiaUsuario(AlergiaUsuario)
	 * @return boolean
	 * Foi inserido a valida��o da data, do tipo de alergia informada,valida��o do medicamento e 
	 * tamb�m foi adicionada mensagens.
	 * */
	public boolean alteraAlergiaUsuario(AlergiaUsuario alergiaUsuario) {
		try {
			if(validaData(alergiaUsuario)==false){
				Mensagens.define(FacesMessage.SEVERITY_INFO,
						"manterPerfilEmergencia.cadastro.data.erro");
				return false;
			}
			if(alergiaUsuario.getTipoAlergia().getId()==4){
			alergiaUsuario.getMedicamentoUsuario().setDataConsulta(alergiaUsuario.getDataPrimeiraOcorrencia());
			manterUsoMedicamentoService.alteraMedicamentoUsario(alergiaUsuario.getMedicamentoUsuario());	
			alergiaUsuarioDAO.atualiza(alergiaUsuario);
			Mensagens.define(FacesMessage.SEVERITY_INFO,
					"manterPerfilEmergencia.altera.sucesso");
			return true;
			}
			else{
				Mensagens.define(FacesMessage.SEVERITY_INFO,
						"manterPerfilEmergencia.tpAlergia.erro");	
				return false;
			}
		} catch (IllegalArgumentException e) {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"manterPerfilEmergencia.altera.excecao.erro");
			return false;
		}
	}

	/** 
	 * @brief Metodo que realiza a exclusao do banco de dados da alergia do usu�rio
	 * Retorna V ou F se conseguiu excluir.	 		  
	 * @param alergiaUsuario(AlergiaUsuario)
	 * @return boolean
	 * */
	public boolean excluiAlergiaUsuario(AlergiaUsuario alergiaUsuario) {
		if (alergiaUsuario.getId() != null && alergiaUsuario != null) {
		alergiaUsuarioDAO.exclui(alergiaUsuario.getId());
		Mensagens.define(FacesMessage.SEVERITY_INFO,
				"manterPerfilEmergencia.exclui.sucesso");
		return true;
		}
		else{
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"manterPerfilEmergencia.exclui.erro");
			return false;
		}
	}
}