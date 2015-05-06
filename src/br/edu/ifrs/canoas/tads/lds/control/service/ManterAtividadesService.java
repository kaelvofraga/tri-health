package br.edu.ifrs.canoas.tads.lds.control.service;
import java.math.BigDecimal;
import java.math.RoundingMode;
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

	public long calculaDuracao(AtividadeUsuario atividadeUsuario) {
		long timeDifMilli = 0L;
		long timeDifMinutes = 0L;

		Date dataIni = atividadeUsuario.getDataInicio();
		Date dataFim = atividadeUsuario.getDataFim();
		timeDifMilli = dataFim.getTime() - dataIni.getTime();
		timeDifMinutes = TimeUnit.MILLISECONDS.toMinutes(timeDifMilli);

		return timeDifMinutes;
	}
	
	public double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	public double calculaCaloriasQueimadas(AtividadeUsuario atividadeUsuario) {
		double massaCorporal = 1.0; // TODO Pegar peso do usuário da classe Peso Usuario
				
		return this.round((atividadeUsuario.getAtividade().getMET() * massaCorporal *  (atividadeUsuario.getDuracao()/60.0)), 2);
	}
	
	public boolean validaDatas(AtividadeUsuario atividadeUsuario) {
		return calculaDuracao(atividadeUsuario) > 0L;
	}

	public boolean salvaAtividadeUsuario(AtividadeUsuario atividadeUsuario) {
		if (atividadeUsuario == null || 
				atividadeUsuario.getAtividade() == null || 
				atividadeUsuario.getUsuario() == null) {

			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"Atividade.cadastro.erro");
			return false;
		}

		if(validaDatas(atividadeUsuario) == false){
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"Atividade.cadastro.erro.dataFinalMenor");
			return false;
		}			

		atividadeUsuarioDAO.insere(atividadeUsuario);

		Mensagens.define(FacesMessage.SEVERITY_INFO,
				"Atividade.cadastro.sucesso");

		return true;
	}

	public AtividadeUsuario buscaAtividadeUsuarioPorID(
			AtividadeUsuario atividadeUsuario) {
		AtividadeUsuario atv = atividadeUsuarioDAO.busca(atividadeUsuario
				.getId());

		if (atividadeUsuario != null)
			atividadeUsuario = atv;

		return atividadeUsuario;
	}

	public List<AtividadeUsuario> buscaAtividadesDoUsuario(Usuario usuario) {

		if (usuario != null && usuario.getId() != null)
			return atividadeUsuarioDAO.buscaAtividadesDoUsuario(usuario);

		return new ArrayList<AtividadeUsuario>();
	}

	public List<AtividadeUsuario> buscaGeral(String criterioAtividadeUsuario,
			Usuario usuario) {
		
		List<AtividadeUsuario> auList = null;
		
		if (usuario == null)
			return null;

		if (StrUtil.isNotBlank(criterioAtividadeUsuario) && criterioAtividadeUsuario != null) {
			auList = atividadeUsuarioDAO.buscaPorCriterio(criterioAtividadeUsuario,
					usuario);
		} else {
			auList = atividadeUsuarioDAO.buscaAtividadesDoUsuario(usuario);			
		}
		
		if(auList != null){
			for(AtividadeUsuario au: auList){
				au.setDuracao(calculaDuracao(au));
				au.setCalorias(calculaCaloriasQueimadas(au));
			}
		}
		
		return auList;
	}

	@SuppressWarnings("unchecked")
	public List<TipoAtividade> buscaNomeTipoAtividades() {
		return tipoAtividadeDAO.buscaTodos();
	}

	@SuppressWarnings("unchecked")
	public List<Atividade> buscaDescricoesAtividades() {
		return atividadeDAO.buscaTodos();
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
