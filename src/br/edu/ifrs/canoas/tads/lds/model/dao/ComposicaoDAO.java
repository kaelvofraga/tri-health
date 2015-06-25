package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifrs.canoas.tads.lds.bean.Composicao;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;

/***
 * 
 * @author Pablo Diehl
 * @version 24/06/2015
 * 
 * @brief: Classe responsável por enviar para o Banco comandos SQL relacionadas a US de Manter Composições Corporais 
 *
 */
@Stateless
public class ComposicaoDAO  extends BaseDAO<Composicao, Long> implements Serializable {

	private static final long serialVersionUID = -7143256281813213009L;

	/**
	 * @brief Busca composições corporais cadastradas por determinado usuario
	 * @param usuario - Usuário para o qual serão buscadas composições
	 * @return lista de composições
	 */
	@SuppressWarnings("unchecked")
	public List<Composicao> buscaComposicaoPorUsuario(Usuario usuario) {	
		return em.createQuery(
				"SELECT c " 
		         + "FROM Composicao c "
		         + "WHERE c.id in "
		         + "(select co.Composicao.id "
		         + "	from ComposicaoUsuario co "
		         + "	where co.usuario.id = :usuario) ")
		         .setParameter("usuario", usuario.getId())
		         .getResultList();
	}
}
