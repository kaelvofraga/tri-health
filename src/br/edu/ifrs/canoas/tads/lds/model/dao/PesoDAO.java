package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifrs.canoas.tads.lds.bean.Peso;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;


@Stateless
public class PesoDAO extends BaseDAO<Peso, Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7932771645088001580L;

	@SuppressWarnings("unchecked")
	public List<Peso> buscaPesoUser(Usuario u) {	
		return em.createQuery(
				"SELECT p " 
		         + "FROM Peso p "
		         + "WHERE p.id in "
		         + "(select pu.peso.id "
		         + "   from PesoUsuario pu "
		         + "  where au.usuario.id = :u) ")
		         .setParameter("usuario", u.getId()).getResultList();
	}
}	

