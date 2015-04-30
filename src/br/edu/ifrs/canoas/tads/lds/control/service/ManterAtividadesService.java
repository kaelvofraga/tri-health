package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.Atividade;
import br.edu.ifrs.canoas.tads.lds.bean.AtividadeUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.TipoAtividade;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;
import br.edu.ifrs.canoas.tads.lds.model.dao.AtividadeDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.AtividadeUsuarioDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.TipoAtividadeDAO;
import br.edu.ifrs.canoas.tads.lds.util.Mensagens;
import br.edu.ifrs.canoas.tads.lds.util.StrUtil;

@Stateless
public class ManterAtividadesService {

	@Inject
	private AtividadeUsuarioDAO atividadeUsuarioDAO;

	@Inject
	private TipoAtividadeDAO tipoAtividadeDAO;

	@Inject
	private AtividadeDAO atividadeDAO;

	private boolean executaCalculosNaLista(
			List<AtividadeUsuario> atividadesUsuario) {

		if (atividadesUsuario == null)
			return false;

		for (AtividadeUsuario au : atividadesUsuario) {
			au.setDuracao(calculaDuracao(au));
			au.setCalorias(calculaCaloriasQueimadas(au));
		}

		return true;
	}

	private long calculaDuracao(AtividadeUsuario atividadeUsuario) {
		long timeDifMilli = 0L;
		long timeDifMinutes = 0L;
		if (atividadeUsuario != null && atividadeUsuario.getId() != null) {
			Date dataIni = atividadeUsuario.getDataInicio();
			Date dataFim = atividadeUsuario.getDataFim();
			timeDifMilli = dataFim.getTime() - dataIni.getTime();
			timeDifMinutes = TimeUnit.MILLISECONDS.toMinutes(timeDifMilli);
		}
		return timeDifMinutes;
	}

	private double calculaCaloriasQueimadas(AtividadeUsuario atividadeUsuario) {
		double calorias = 0.0;
		long duracao = 0L;
		double massaCorporal = 0.0; //TODO Pegar peso do usuário da classe Peso Usuario
		if (atividadeUsuario != null && atividadeUsuario.getId() != null) {
			duracao = calculaDuracao(atividadeUsuario);
			calorias = atividadeUsuario.getAtividade().getMET() * massaCorporal
					* duracao;
		}
		return calorias;
	}

	public boolean salvaAtividadeUsuario(AtividadeUsuario atividadeUsuario) {
		if (atividadeUsuario != null && atividadeUsuario.getId() != null) {
			atividadeUsuarioDAO.insere(atividadeUsuario);
			Mensagens.define(FacesMessage.SEVERITY_INFO,
					"Atividade.cadastro.sucesso");
		} else {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"Atividade.cadastro.erro");
		}
		return true;
	}

	public AtividadeUsuario buscaAtividadeUsuarioPorID(
			AtividadeUsuario atividadeUsuario) {
		AtividadeUsuario atv = atividadeUsuarioDAO.busca(atividadeUsuario
				.getId());

		if (atividadeUsuario != null)
			atividadeUsuario = atv;

		atividadeUsuario.setDuracao(calculaDuracao(atividadeUsuario));

		atividadeUsuario
				.setCalorias(calculaCaloriasQueimadas(atividadeUsuario));

		return atividadeUsuario;
	}

	public List<AtividadeUsuario> buscaAtividadesDoUsuario(Usuario usuario) {
		if (usuario != null && usuario.getId() != null){			
			List<AtividadeUsuario> auList = atividadeUsuarioDAO.buscaAtividadesDoUsuario(usuario);
			
			executaCalculosNaLista(auList);
			
			return auList;			
		}
		
		return new ArrayList<AtividadeUsuario>();
	}

	public List<AtividadeUsuario> buscaGeral(String criterioAlergia,
			Usuario usuario) {
		if (usuario == null)
			return null;

		if (StrUtil.isNotBlank(criterioAlergia)){

			List<AtividadeUsuario> auList = atividadeUsuarioDAO.buscaPorCriterio(criterioAlergia,
					usuario);

			executaCalculosNaLista(auList);

			return auList;	
			
		}else{
			return atividadeUsuarioDAO.buscaAtividadesDoUsuario(usuario);
		}
	}

	@SuppressWarnings("unchecked")
	public List<TipoAtividade> buscaNomeTipoAtividades() {
		return tipoAtividadeDAO.buscaTodos();
	}

	public List<Atividade> buscaDescricoesAtividades(String descricao) {
		return atividadeDAO.buscaPorDescricao(descricao);
	}

	public void alteraAtividadeUsario(AtividadeUsuario atividadeUsuario) {
		if (atividadeUsuario != null && atividadeUsuario.getId() != null) {
			atividadeUsuarioDAO.atualiza(atividadeUsuario);
			Mensagens.define(FacesMessage.SEVERITY_INFO,
					"AtividadeUsuario.alterar.sucesso");
		} else {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"AtividadeUsuario.alterar.erro");
		}
	}

	public void excluiAtividadeUsuario(AtividadeUsuario atividadeUsuario) {
		if (atividadeUsuario != null && atividadeUsuario.getId() != null) {
			atividadeUsuarioDAO.exclui(atividadeUsuario.getId());
			Mensagens.define(FacesMessage.SEVERITY_INFO,
					"AtividadeUsuario.excluir.sucesso");
		} else {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"AtividadeUsuario.excluir.erro");
		}
	}
}
