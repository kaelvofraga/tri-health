package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.ExameUrinaUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.ItemExameUrina;
import br.edu.ifrs.canoas.tads.lds.bean.MedicamentoUsuario;
import br.edu.ifrs.canoas.tads.lds.model.dao.ExameUrinaDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.ItemExameUrinaDAO;
import br.edu.ifrs.canoas.tads.lds.util.Mensagens;
import br.edu.ifrs.canoas.tads.lds.util.StrUtil;

@Stateless
public class ManterExameUrinaService {

	@Inject
	private ExameUrinaDAO exameUrinaDAO;

	@Inject
	private ItemExameUrinaDAO itemExameUrinaDAO;

	/**
	 * @brief Metodo que salva no banco de dados o ExameUrinaUsuario ap�s a
	 *        valida��o. Retorna V ou F se conseguiu ou n�o salvar no banco.
	 * @param exameUrina
	 *            (ExameUrinaUsuario)
	 * @return boolean
	 * */
	public boolean salvaExameUrinaUsuario(ExameUrinaUsuario exameUrina) {
		if (exameUrina.getItensExame() == null
				|| exameUrina.getItensExame().isEmpty()
				|| exameUrina.getUsuario() == null) {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"manterExameUrina.cadastro.erro.nulo");
			return false;
		} else {
			try {
				exameUrinaDAO.insere(exameUrina);
				for (ItemExameUrina itemExame : exameUrina.getItensExame()) {
					itemExame.setExameUsuario(exameUrina);
					itemExameUrinaDAO.insere(itemExame);
				}

			} catch (Exception e) {
				Mensagens.define(FacesMessage.SEVERITY_ERROR,
						"manterExameUrina.cadastro.erro");
				return false;
			}

			Mensagens.define(FacesMessage.SEVERITY_INFO,
					"manterExameUrina.cadastro.sucesso");
			return true;
		}
	}

	/**
	 * @brief Metodo que realiza busca no banco de dados os exames de urina do
	 *        usu�rio atrav�s de crit�rio informado. Retorna lista de
	 *        ExameUrinaUsuario.
	 * @param criterioExameUrina
	 *            (String),dataDe(Date),dataAte(Date)
	 * @return List<ExameUrinaUsuario>
	 * */
	public List<ExameUrinaUsuario> busca(Date dataDe, Date dataAte,String criterioExameUrina) {
			if (StrUtil.isNotBlank(criterioExameUrina) || dataDe != null && dataAte != null) {
				if(!exameUrinaDAO.buscaPorCriterio(dataDe, dataAte,criterioExameUrina).isEmpty()){
					return exameUrinaDAO.buscaPorCriterio(dataDe, dataAte,criterioExameUrina);
				}
				else {
					Mensagens.define(FacesMessage.SEVERITY_INFO,
							"listarExameUrina.busca.vazio");
					return new ArrayList<ExameUrinaUsuario>();
				}
			}else{
			return exameUrinaDAO.buscaTodos();
			}
	}

	/**
	 * @author Alisson Lorscheiter
	 * @brief Metodo que valida a data informada verificando se ela � menor que
	 *        a data atual.. Retorna V ou F se validou ou n�o.
	 * @param exameUrina
	 *            (ExameUrinaUsuario)
	 * @return boolean M�todo criado para validar a Data informada.
	 * */
	public boolean validaData(ExameUrinaUsuario exameUrina) {
		long timeSysDate = new Date().getTime();
		long timeData = exameUrina.getData().getTime();
		if (timeData > timeSysDate) {
			return false;
		}
		return true;
	}

	/**
	 * @brief Metodo que valida os campos e altera no banco de dados informa��es
	 *        do exameUrinaUsuario. Retorna V ou F se conseguiu ou n�o alterar
	 *        no banco.
	 * @param exameUrina
	 *            (ExameUrinaUsuario)
	 * @return boolean
	 * */
	public boolean alteraExameUrinaUsuario(ExameUrinaUsuario exameUrina) {
		try {
			if (validaData(exameUrina) == false) {

				Mensagens.define(FacesMessage.SEVERITY_INFO,
						"manterExameUrina.cadastro.data.erro");
				return false;
			}
			exameUrinaDAO.atualiza(exameUrina);
			Mensagens.define(FacesMessage.SEVERITY_INFO,
					"manterExameUrina.altera.sucesso");
			return true;
		} catch (IllegalArgumentException e) {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"manterExameUrina.altera.excecao.erro");
			return false;
		}
	}

	/**
	 * @brief Metodo que exclui o exameUrinaUsuario do banco de dados. Retorna V
	 *        ou F se conseguiu ou n�o excluir do banco.
	 * @param exameUrina
	 *            (ExameUrinaUsuario)
	 * @return boolean
	 * */
	public boolean excluiExameUrinaUsuario(ExameUrinaUsuario exameUrina) {
		try {
			if (exameUrina.getId() != null && exameUrina != null) {
				exameUrinaDAO.exclui(exameUrina.getId());
				Mensagens.define(FacesMessage.SEVERITY_INFO,
						"manterExameUrina.exclui.sucesso");
				return true;
			} else {
				Mensagens.define(FacesMessage.SEVERITY_INFO,
						"manterExameUrina.exclui.erro");
				return false;
			}
		} catch (IllegalArgumentException e) {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"manterExameUrina.exclui.excecao.erro");
			return false;
		}
	}
}