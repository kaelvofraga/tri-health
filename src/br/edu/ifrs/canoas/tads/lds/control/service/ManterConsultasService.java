package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.AtividadeUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.Consulta;
import br.edu.ifrs.canoas.tads.lds.bean.Medicamento;
import br.edu.ifrs.canoas.tads.lds.bean.MedicamentoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.Medico;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;
import br.edu.ifrs.canoas.tads.lds.model.dao.ConsultaDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.MedicoDAO;
import br.edu.ifrs.canoas.tads.lds.util.Mensagens;
import br.edu.ifrs.canoas.tads.lds.util.StrUtil;

@Stateless
public class ManterConsultasService {

	@Inject
	private ConsultaDAO consultaDAO;
	@Inject
	private MedicoDAO medicoDAO;

	@SuppressWarnings("unchecked")
	public List<Consulta> buscaTodos() {
		return consultaDAO.buscaTodos();
	}

	public boolean salva(Consulta consulta) {
		try {
			consultaDAO.atualiza(consulta);
			Mensagens.define(FacesMessage.SEVERITY_INFO,
					"Consulta salva com sucesso");
		} catch (Exception e) {
			System.out.println("Exception ao Salvar: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/*
	 * Metodo para fazer alteracao na Consulta do Usuario que testa se o
	 * Consulta ja esta na tabela Consulta caso necessario ele sera criado
	 */
	public void alteraConsulta(Consulta consulta) {
		try {
			consulta.setMedico(buscaOuCriaMedicoPorNome(consulta.getMedico()));
			Medico m = new Medico();
			m = medicoDAO.atualiza(consulta.getMedico());
			consulta.setMedico(m);
			consultaDAO.atualiza(consulta);
			Mensagens.define(FacesMessage.SEVERITY_INFO,
					"manterConsulta.altera.sucesso");
		} catch (IllegalArgumentException e) {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"manterConsulta.altera.excecao.erro");
		}
	}

	/*
	 * Chama o metodo buscaOuCriaMedicoPorNome para ver se o Medico existe e
	 * depois insere na tabela consulta
	 */
	public boolean salvaConsulta(Consulta consulta) {
		if (consulta == null || consulta.getMedico() == null
				|| consulta.getUsuario() == null) {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"manterConsulta.exclui.nulo.erro");
			return false;
		}
		try {
			consulta.setMedico(buscaOuCriaMedicoPorNome(consulta.getMedico()));
			consultaDAO.insere(consulta);
		} catch (Exception e) {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"manterConsulta.cadastro.erro");
			return false;
		}
		Mensagens.define(FacesMessage.SEVERITY_INFO,
				"manterConsulta.cadastro.sucesso");
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

	public boolean insere(Consulta consulta, Usuario usuario) {
		try {
			consultaDAO.insere(consulta);
			consulta.setUsuario(usuario);
			consultaDAO.atualiza(consulta);
			Mensagens.define(FacesMessage.SEVERITY_INFO,
					"Consulta Inserida com sucesso");
		} catch (Exception e) {
			System.out.println("Exception ao Inserir: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean exclui(Consulta consulta) {
		if (consulta != null && consulta.getId() != null) {
			consultaDAO.exclui(consulta.getId());
			consulta = null;
			/*
			 * Mensagens.define(FacesMessage.SEVERITY_INFO,
			 * "Consulta excluida com sucesso");
			 */
			return true;
		} else {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"Consulta não excluida");
			return false;
		}
	}

	/*
	 * Metodo busca do Listar que realiza busca por criterio informado ou
	 * retorna todos elementos cadastrados.
	 */
	@SuppressWarnings("unchecked")
	public List<Consulta> busca(String criterioConsulta) {
		if (StrUtil.isNotBlank(criterioConsulta) && criterioConsulta != null) {
			if (!consultaDAO.buscaPorCriterio(criterioConsulta).isEmpty()) {
				return consultaDAO.buscaPorCriterio(criterioConsulta);
			} else {
				Mensagens.define(FacesMessage.SEVERITY_INFO,
						"listarMedicamento.busca.vazio");
				return new ArrayList<Consulta>();
			}
		} else
			return consultaDAO.buscaTodos();
	}

	/*
	 * Busca Medicos que estao cadastrados uma string para buscar
	 */
	public List<Medico> buscaMedico(String query) {
		if (query != null)
			return consultaDAO.buscaNomeMedico();
		return new ArrayList<Medico>();
	}

}