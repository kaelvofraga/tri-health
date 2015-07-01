package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.ItemExameSangue;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;
import br.edu.ifrs.canoas.tads.lds.bean.UsuarioExame;
import br.edu.ifrs.canoas.tads.lds.model.dao.ItemExameSangueDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.TipoAnaliseDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.UdmDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.UsuarioExameDAO;
import br.edu.ifrs.canoas.tads.lds.util.DateUtil;
import br.edu.ifrs.canoas.tads.lds.util.Mensagens;
import br.edu.ifrs.canoas.tads.lds.util.StrUtil;
/**
 * Classe Service do Manter Exame de Sangue
 * @author André
 *
 */
@Stateless
public class ManterExameSangueService {
	
	@Inject
	private UsuarioExameDAO usuarioExameDAO;
	
	@Inject
	private ItemExameSangueDAO itemExameSangueDAO;
	
	@Inject
	private TipoAnaliseDAO tipoAnaliseDAO;
	
	@Inject
	private UdmDAO udmDAO;
	
//getters and setters

	public UsuarioExameDAO getUsuarioExameDAO() {
		return usuarioExameDAO;
	}

	public void setUsuarioExameDAO(UsuarioExameDAO usuarioExameDAO) {
		this.usuarioExameDAO = usuarioExameDAO;
	}

	public ItemExameSangueDAO getItemExameSangueDAO() {
		return itemExameSangueDAO;
	}

	public void setItemExameSangueDAO(ItemExameSangueDAO itemExameSangueDAO) {
		this.itemExameSangueDAO = itemExameSangueDAO;
	}

	public TipoAnaliseDAO getTipoAnaliseDAO() {
		return tipoAnaliseDAO;
	}

	public void setTipoAnaliseDAO(TipoAnaliseDAO tipoAnaliseDAO) {
		this.tipoAnaliseDAO = tipoAnaliseDAO;
	}

	public UdmDAO getUdmDAO() {
		return udmDAO;
	}

	public void setUdmDAO(UdmDAO udmDAO) {
		this.udmDAO = udmDAO;
	}
	/**
	 * {@code} Método para salvar o exame de sangue realizado
	 * @param usuarioExame
	 * @return boolean
	 */
	public boolean salvaExameSangueUsuario(UsuarioExame usuarioExame) {
		if (usuarioExame.getItensExame() == null
				|| usuarioExame.getItensExame().isEmpty()
				|| usuarioExame.getUsuario() == null) {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"manterExameSangue.cadastro.erro.nulo");
			return false;
		} else {
			try {
				usuarioExameDAO.insere(usuarioExame);
				for (ItemExameSangue itemExame : usuarioExame.getItensExame()) {
					itemExame.setUsuarioExame(usuarioExame);
					itemExameSangueDAO.insere(itemExame);
				}

			} catch (Exception e) {
				Mensagens.define(FacesMessage.SEVERITY_ERROR,
						"manterExameSangue.cadastro.erro");
				return false;
			}

			Mensagens.define(FacesMessage.SEVERITY_INFO,
					"manterExameSangue.cadastro.sucesso");
			return true;
		}
	}
	/**
	 * {@code} Método de busca dos exames realizados por usuário num determinado período
	 * @param dataDe
	 * @param dataAte
	 * @return Lista de exames realizados
	 */
	public List<UsuarioExame> buscaExameSangueUsuario(Date dataDe, Date dataAte) {
		if (dataDe == null || dataAte == null) {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"listarExameSangue.consulta.datasvazias");
			return usuarioExameDAO.buscaTodos();
		}
		return usuarioExameDAO.buscaTodos();
		
	}
	/**
	 * {@code} Método de busca dos exames realizados por usuário num determinado período
	 * @param dataDe
	 * @param dataAte
	 * @return Lista de exames realizados
	 */
	public List<UsuarioExame> busca(Date dataDe, Date dataAte) {
		if (dataDe != null && dataAte != null) {
			return usuarioExameDAO.buscaTodos();
		} else{
			return usuarioExameDAO.buscaTodos();
		}
	}
	/**
	 * {@code} Método para fazer alterações no exame de sangue
	 * @param usuarioExame
	 * @return boolean
	 */
	public boolean alteraExameSangue(UsuarioExame usuarioExame) {
		try{
			if (validaData(usuarioExame) == false){
				Mensagens.define(FacesMessage.SEVERITY_INFO, "manterExameSangue.cadastro.data.erro");
				return false;
			}
			usuarioExameDAO.atualiza(usuarioExame);
			Mensagens.define(FacesMessage.SEVERITY_INFO, "manterExameSangue.altera.sucesso");
			return true;
		}catch(IllegalArgumentException e){
			Mensagens.define(FacesMessage.SEVERITY_ERROR,"manterExameSangue.altera.excecao.erro");
			return false;
		}
	}
	/**
	 * {@code} Método para excluir exame de sangue
	 * @param usuarioExame
	 * @return boolean
	 */
	public boolean excluiExameSangueUsuario(UsuarioExame usuarioExame) {
		try {
			if (usuarioExame.getId() != null && usuarioExame != null) {
				usuarioExameDAO.exclui(usuarioExame.getId());
				Mensagens.define(FacesMessage.SEVERITY_INFO,"manterExameSangue.exclui.sucesso");
				return true;
			} else {
				Mensagens.define(FacesMessage.SEVERITY_INFO,"manterExameSangue.exclui.erro");
				return false;
			}
		} catch (IllegalArgumentException e) {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,"manterExameSangue.exclui.excecao.erro");
			return false;
		}
	}
	/**
	 * {@code} Método para validar a data
	 * @param usuarioExame
	 * @return boolean
	 */
	public boolean validaData(UsuarioExame usuarioExame) {
		long timeSysDate = new Date().getTime();
		long timeData = usuarioExame.getData().getTime();
		if (timeData > timeSysDate) {
			return false;
		}
		return true;
	}
}
