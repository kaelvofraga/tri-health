package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifrs.canoas.tads.lds.bean.ComposicaoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;

/**
 * 
 * @author Pablo Diehl
 * @version 24/06/2015
 * @brief Classe responsável por enviar para o Banco comandos SQL relacionadas a US de Manter Composições Corporais 
 *
 */
@Stateless
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
								+ "WHERE co.usuario.id = :usuario ORDER BY co.data DESC")
				.setParameter("usuario", usuario.getId()).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<ComposicaoUsuario> buscaPorCriterio(long criterio, Usuario usuario){
		try {	
			String str = "SELECT co FROM ComposicaoUsuario co WHERE data BETWEEN DATE'"; 
			str+=criterio; 
			str+="' AND DATE'2999-10-30' AND co.usuario.id = :usuario ORDER BY co.data";
						
			return em.createQuery(str).setParameter("usuario", usuario.getId()).getResultList();
			
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
}
