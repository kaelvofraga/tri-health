package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.Consulta;
import br.edu.ifrs.canoas.tads.lds.bean.ExameVisao;
import br.edu.ifrs.canoas.tads.lds.bean.Medico;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;
import br.edu.ifrs.canoas.tads.lds.model.dao.ConsultaDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.ExameVisaoDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.MedicoDAO;
import br.edu.ifrs.canoas.tads.lds.util.Mensagens;
import br.edu.ifrs.canoas.tads.lds.util.StrUtil;

@Stateless
public class ManterExameVisaoService {
	@Inject
	private ExameVisaoDAO exameVisaoDAO;
	@Inject
	private MedicoDAO medicoDAO;

	@SuppressWarnings("unchecked")
	public List<ExameVisao> buscaTodos() {
		return exameVisaoDAO.buscaTodos();
	}

	public boolean salva(ExameVisao exame) {
		try {
			exameVisaoDAO.atualiza(exame);
			Mensagens.define(FacesMessage.SEVERITY_INFO,
					"manterExameVisao.cadastro.sucesso");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/*
	 * Metodo para fazer alteracao no ExameVisao do Usuario que testa se o
	 * ExameVisao ja esta na tabela ExameVisao caso necessario ele sera criado
	 */
	public void alteraExameVisao(ExameVisao exame) {
		try {
			exame.setMedico(buscaOuCriaMedicoPorNome(exame.getMedico()));
			Medico m = new Medico();
			m = medicoDAO.atualiza(exame.getMedico());
			exame.setMedico(m);
			exameVisaoDAO.atualiza(exame);
			Mensagens.define(FacesMessage.SEVERITY_INFO,
					"manterExameVisao.altera.sucesso");
		} catch (IllegalArgumentException e) {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"manterExameVisao.altera.excecao.erro");
		}
	}

	/*
	 * Chama o metodo buscaOuCriaMedicoPorNome para ver se o Medico existe e
	 * depois insere na tabela ExameVisao
	 */
	public boolean salvaExameVisao(ExameVisao exame) {
		if (exame == null || exame.getMedico() == null
				|| exame.getUsuario() == null) {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"manterExameVisao.exclui.nulo.erro");
			return false;
		}
		try {
			exame.setMedico(buscaOuCriaMedicoPorNome(exame.getMedico()));
			exameVisaoDAO.insere(exame);
		} catch (Exception e) {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"manterExameVisao.cadastro.erro");
			return false;
		}
		Mensagens.define(FacesMessage.SEVERITY_INFO,
				"manterExameVisao.cadastro.sucesso");
		return true;
	}

	/*
	 * Metodo para buscar medico no banco se ele existir retorna ou entao cria
	 * ele na tabela medico e retorna.
	 */
	public Medico buscaOuCriaMedicoPorNome(Medico medico) {
		List<Medico> medicos = medicoDAO.buscaPorNome(medico.getNome());

		if (medicos.size() == 1)
			medico = medicos.get(0);
		else {
			medico.setId(null);
			medico.setCrm("0");
			medicoDAO.insere(medico);
		}
		return medico;
	}

	public boolean insere(ExameVisao exame, Usuario usuario) {
		try {
			exameVisaoDAO.insere(exame);
			exame.setUsuario(usuario);
			exameVisaoDAO.atualiza(exame);
			Mensagens.define(FacesMessage.SEVERITY_INFO,
					"manterExameVisao.cadastro.sucesso");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean exclui(ExameVisao exame) {
		if (exame != null && exame.getId() != null) {
			exameVisaoDAO.exclui(exame.getId());
			exame = null;
			Mensagens.define(FacesMessage.SEVERITY_INFO,
					"manterExameVisao.exclui.sucesso");
			return true;
		} else {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"manterExameVisao.exclui.nulo.erro");
			return false;
		}
	}

	/*
	 * Metodo busca do Listar que realiza busca por criterio informado ou
	 * retorna todos elementos cadastrados.
	 */
	@SuppressWarnings("unchecked")
	public List<ExameVisao> busca(String criterioExameVisao) {
		if (StrUtil.isNotBlank(criterioExameVisao) && criterioExameVisao != null) {
			if (!exameVisaoDAO.buscaPorCriterio(criterioExameVisao).isEmpty()) {
				return exameVisaoDAO.buscaPorCriterio(criterioExameVisao);
			} else {
				Mensagens.define(FacesMessage.SEVERITY_INFO,
						"manterExameVisao.busca.vazio");
				return new ArrayList<ExameVisao>();
			}
		} else
			return exameVisaoDAO.buscaTodos();
	}

	/*
	 * Busca Medicos que estao cadastrados uma string para buscar
	 */
	public List<Medico> buscaMedico(String query) {
		if (query != null)
			return exameVisaoDAO.buscaNomeMedico();
		return new ArrayList<Medico>();
	}
	
	/** 
	 * @author Delmar
	 * @brief Metodo que realiza busca no banco de dados dos ExameVisao do usuário
	 * Retorna lista de ExameVisao.	 		  
	 * @param usuario(Usuario)
	 * @return List<ExameVisao>
	 * */
	public List<ExameVisao> buscaExameVisaoPorUsuario(Usuario usuario) {
		if (usuario != null && usuario.getId() != null){
			return exameVisaoDAO.buscaExameVisaoPorUsuario(usuario);
		}
		return new ArrayList<ExameVisao>();
	}
}
