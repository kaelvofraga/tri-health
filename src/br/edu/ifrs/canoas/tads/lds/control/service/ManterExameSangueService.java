package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.ItemExameSangue;
import br.edu.ifrs.canoas.tads.lds.bean.UsuarioExame;
import br.edu.ifrs.canoas.tads.lds.model.dao.ItemExameSangueDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.TipoAnaliseDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.UdmDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.UsuarioExameDAO;
import br.edu.ifrs.canoas.tads.lds.util.DateUtil;
import br.edu.ifrs.canoas.tads.lds.util.Mensagens;
import br.edu.ifrs.canoas.tads.lds.util.StrUtil;

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
	
	public List<UsuarioExame> busca(Date dataDe, Date dataAte,String criterioExameSangue) {
		if (dataDe == null || dataAte == null || dataDe.compareTo(dataAte) > 0) {
			dataDe = DateUtil.getDataAtual();
			dataAte = DateUtil.getDataAtualIncrementa(1);
		}
		if (StrUtil.isNotBlank(criterioExameSangue) || dataDe != null && dataAte != null) {

			return usuarioExameDAO.buscaPorCriterio(dataDe, dataAte,criterioExameSangue);
		} else {
			return usuarioExameDAO.buscaTodos();
		}
	}

	public void alteraExameSangue(UsuarioExame usuarioExame) {
		usuarioExameDAO.atualiza(usuarioExame);
	}
	
	

}