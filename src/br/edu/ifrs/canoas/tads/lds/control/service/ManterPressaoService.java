package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import br.edu.ifrs.canoas.tads.lds.bean.PressaoArterial;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;
import br.edu.ifrs.canoas.tads.lds.model.dao.PressaoArterialDAO;
import br.edu.ifrs.canoas.tads.lds.util.Mensagens;
import br.edu.ifrs.canoas.tads.lds.util.StrUtil;

/*
 * @author Pablo Diehl da Silva
 * @version 06/05/2015
 *
 */
@Stateless
public class ManterPressaoService {
	@Inject
	private PressaoArterialDAO pressaoDAO;

	/*
	 * Respons�vel pela inser��o de novo registro de press�o arterial na base de
	 * dados
	 * 
	 * @param pressaoUsuario Objeto do tipo "PressaoUsuario" a ser cadastrado.
	 */
	public boolean salvaPressaoArterial(PressaoArterial pressaoArterial) {
		pressaoDAO.insere(pressaoArterial);
		Mensagens
				.define(FacesMessage.SEVERITY_INFO, "pressao.cadastro.sucesso");
		return true;
	}

	public List<PressaoArterial> buscaPressoes(Date query, Usuario usuario) {
		if (usuario != null && usuario.getId() != null)
			return pressaoDAO.buscaPressaoPorUsuario(usuario);
		return new ArrayList<PressaoArterial>();
	}

	public void alteraPressaoUsario(PressaoArterial pressaoArterial) {
		pressaoDAO.atualiza(pressaoArterial);
	}

	public void excluiPressao(PressaoArterial pressaoArterial) {
		pressaoDAO.exclui(pressaoArterial.getId());
	}
}