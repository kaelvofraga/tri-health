package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import br.edu.ifrs.canoas.tads.lds.bean.ExameUrinaUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.ItemExameUrina;
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

	public List<ExameUrinaUsuario> busca(Date dataDe, Date dataAte,String criterioExameUrina) {
		if (StrUtil.isNotBlank(criterioExameUrina) || dataDe != null && dataAte != null) {

			return exameUrinaDAO.buscaPorCriterio(dataDe, dataAte,criterioExameUrina);
		} else {
			return exameUrinaDAO.buscaTodos();
		}
	}

	public void alteraExameUrinaUsuario(ExameUrinaUsuario exameUrina) {
		exameUrinaDAO.atualiza(exameUrina);
	}
	
	public boolean excluiExameUrinaUsuario(ExameUrinaUsuario exameUrina) {
		exameUrinaDAO.exclui(exameUrina.getId());
		return true;
	}

	/* GETTERS & SETTERS */

	public ExameUrinaDAO getExameUrinaDAO() {
		return exameUrinaDAO;
	}

	public void setExameUrinaDAO(ExameUrinaDAO exameUrinaDAO) {
		this.exameUrinaDAO = exameUrinaDAO;
	}
}