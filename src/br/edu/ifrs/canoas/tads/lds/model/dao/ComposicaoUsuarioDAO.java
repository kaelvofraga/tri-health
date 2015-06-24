package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.io.Serializable;
import java.util.List;

import br.edu.ifrs.canoas.tads.lds.bean.AtividadeUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.ComposicaoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;

/**
 * 
 * @author Pablo Diehl
 * @version 24/06/2015
 * @brief Classe responsável por enviar para o Banco comandos SQL relacionadas a US de Manter Composições Corporais 
 *
 */
public class ComposicaoUsuarioDAO extends BaseDAO<ComposicaoUsuario, Long> implements Serializable {

	private static final long serialVersionUID = 1481114400788565915L;
	
	/**
	 * @brief Busca registros de composições corporais relacionadas a um usuario
	 * @param usuario - Usuario do qual deseja-se buscar registros de composições
	 * @return Lista de composições corporais
	 */
	
	@SuppressWarnings("unchecked")
	public List<ComposicaoUsuario> buscaComposicoesDoUsuario(Usuario usuario) {
		return em
				.createQuery(
						"SELECT co FROM ComposicaoUsuario co "
								+ "WHERE co.usuario.id = :usuario ORDER BY co.data")
				.setParameter("usuario", usuario.getId()).getResultList();
	}

}
