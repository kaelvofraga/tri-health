package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.ExameUrinaUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.MedicamentoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.TipoAtividade;
import br.edu.ifrs.canoas.tads.lds.bean.TipoExameUrina;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;
import br.edu.ifrs.canoas.tads.lds.model.dao.ExameUrinaDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.TipoExameUrinaDAO;
import br.edu.ifrs.canoas.tads.lds.util.Mensagens;
import br.edu.ifrs.canoas.tads.lds.util.StrUtil;

@Stateless
public class ManterExameUrinaService {
	
	@Inject
	private ExameUrinaDAO exameUrinaDAO;
	
	@Inject
	private TipoExameUrinaDAO tipoExameUrinaDAO;

	public ExameUrinaDAO getExameUrinaDAO() {
		return exameUrinaDAO;
	}

	public void setExameUrinaDAO(ExameUrinaDAO exameUrinaDAO) {
		this.exameUrinaDAO = exameUrinaDAO;
	}

	public TipoExameUrinaDAO getTipoExameUrinaDAO() {
		return tipoExameUrinaDAO;
	}

	public void setTipoExameUrinaDAO(TipoExameUrinaDAO tipoExameUrinaDAO) {
		this.tipoExameUrinaDAO = tipoExameUrinaDAO;
	}

	public boolean salvaExameUrinaUsuario(ExameUrinaUsuario exameUrina) {
		
		exameUrinaDAO.insere(exameUrina);
		Mensagens.define(FacesMessage.SEVERITY_INFO, "manterExameUrina.cadastro.sucesso");		
		return true;		
	}
	
	public TipoExameUrina buscaOuCriaTipoExameUrinaPorNome(TipoExameUrina tipoExameUrina) {
		List<TipoExameUrina> tipos = tipoExameUrinaDAO.buscaPorTipo(tipoExameUrina.getTipo());
		
		/*if (tipos.size() == 1)
			tipoExameUrina = tipos.get(0);
		else{
			tipoExameUrinaDAO.insere(tipoExameUrina);
		}*/
		
		return tipoExameUrina;
	}
	
	public List<TipoExameUrina> buscaTiposExameUrina(String query, Usuario usuario) {
		if (usuario != null && usuario.getId() != null)
			return tipoExameUrinaDAO.buscaTipoPorUsuario(usuario);
		return new ArrayList<TipoExameUrina>();
	}
	
	@SuppressWarnings("unchecked")
	public List<ExameUrinaUsuario> busca(String criterioExameUrina) {
		if (StrUtil.isNotBlank(criterioExameUrina))
			return exameUrinaDAO.buscaPorCriterio(criterioExameUrina);
		else
			return exameUrinaDAO.buscaTodos();
	}
	
	public void alteraExameUrina(ExameUrinaUsuario exameUrina) {
		exameUrinaDAO.atualiza(exameUrina);
	}

	public boolean excluiExameUrina(ExameUrinaUsuario exameUrina) {
		List<Integer> lista = new ArrayList<Integer>();
		lista = exameUrinaDAO.buscaExameUrinaPorUsuario(exameUrina);//ver
		
		if (lista.isEmpty()){
			System.out.println("entrou no empty");
			System.out.println("if empty"+ lista.isEmpty());
			exameUrinaDAO.exclui(exameUrina.getId());
			Mensagens.define(FacesMessage.SEVERITY_INFO, "manterExameUrina.exclui.sucesso");
			return true;	   
		}
		else{
			System.out.println("entrou no else empty");
			Mensagens.define(FacesMessage.SEVERITY_INFO, "manterExameUrina.exclui.erro");
			return false;
			}
//		exameUrinaDAO.exclui(exameUrina.getId());
	}
	
	@SuppressWarnings("unchecked")
	public List<TipoExameUrina> buscaTipoExameUrina() {
		return tipoExameUrinaDAO.buscaTodos();
	}
	
	
}

/*public boolean excluiMedicamento(MedicamentoUsuario medicamentoUsuario) {
		List<Integer> lista = new ArrayList<Integer>();
		lista=medicamentoUsuarioDAO.buscaIdMedicamentoAlergiaUsuario(medicamentoUsuario);
		
		if(lista.isEmpty()){
			System.out.println("entrou no empty");
			System.out.println("if empty"+ lista.isEmpty());
			medicamentoUsuarioDAO.exclui(medicamentoUsuario.getId());
			Mensagens.define(FacesMessage.SEVERITY_INFO, "manterMedicamento.exclui.sucesso");
			return true;	   
		}
		//Mensagens.define(FacesMessage.SEVERITY_INFO, "manterMedicamento.exclui.excecao.erro");
		else{
			System.out.println("entrou no else empty");
			Mensagens.define(FacesMessage.SEVERITY_INFO, "manterMedicamento.exclui.alergia.usuario.erro");
			return false;
			}
		//	Mensagens.define(FacesMessage.SEVERITY_INFO, "manterMedicamento.exclui.medicamentousuario.nulo.erro");
		
		}*/




