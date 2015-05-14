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
import br.edu.ifrs.canoas.tads.lds.util.Mensagens;
import br.edu.ifrs.canoas.tads.lds.util.StrUtil;

@Stateless
public class ManterConsultasService {
	
	@Inject
	private ConsultaDAO consultaDAO;

	@SuppressWarnings("unchecked")
	public List<Consulta> buscaTodos() {
		return consultaDAO.buscaTodos();
	}	
	
	public boolean salva(Consulta consulta) {
		try{
			consultaDAO.atualiza(consulta);
			Mensagens.define(FacesMessage.SEVERITY_INFO, "Consulta salva com sucesso");
		}catch(Exception e){
			System.out.println("Exception ao Salvar: "+e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}	
	
	public boolean insere(Consulta consulta, Usuario usuario) {
		try{
			consultaDAO.insere(consulta);
			consulta.setUsuario(usuario);
			consultaDAO.atualiza(consulta);	
			Mensagens.define(FacesMessage.SEVERITY_INFO, "Consulta Inserida com sucesso");
		}catch(Exception e){
			System.out.println("Exception ao Inserir: "+e.getMessage());
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
			Mensagens.define(FacesMessage.SEVERITY_INFO,
					"Consulta excluida com sucesso");*/
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
		if (StrUtil.isNotBlank(criterioConsulta)
				&& criterioConsulta != null) {
			if (!consultaDAO.buscaPorCriterio(criterioConsulta)
					.isEmpty()) {
				return consultaDAO
						.buscaPorCriterio(criterioConsulta);
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