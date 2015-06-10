package br.edu.ifrs.canoas.tads.lds.control.service;

import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.model.dao.ItemExameSangueDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.TipoAnaliseDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.UdmDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.UsuarioExameDAO;

public class ManterExameSangueService {
	
	@Inject
	private UsuarioExameDAO usuarioExame;
	
	@Inject
	private ItemExameSangueDAO itemExameSangue;
	
	@Inject
	private TipoAnaliseDAO tipoAnalise;
	
	@Inject
	private UdmDAO udm;
	
//getters and setters

	public UsuarioExameDAO getUsuarioExame() {
		return usuarioExame;
	}

	public void setUsuarioExame(UsuarioExameDAO usuarioExame) {
		this.usuarioExame = usuarioExame;
	}

	public ItemExameSangueDAO getItemExameSangue() {
		return itemExameSangue;
	}

	public void setItemExameSangue(ItemExameSangueDAO itemExameSangue) {
		this.itemExameSangue = itemExameSangue;
	}

	public TipoAnaliseDAO getTipoAnalise() {
		return tipoAnalise;
	}

	public void setTipoAnalise(TipoAnaliseDAO tipoAnalise) {
		this.tipoAnalise = tipoAnalise;
	}

	public UdmDAO getUdm() {
		return udm;
	}

	public void setUdm(UdmDAO udm) {
		this.udm = udm;
	}
	
	

}
