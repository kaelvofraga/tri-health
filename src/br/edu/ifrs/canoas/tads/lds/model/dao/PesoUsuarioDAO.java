package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifrs.canoas.tads.lds.bean.Peso;
import br.edu.ifrs.canoas.tads.lds.bean.PesoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;

@Stateless
public class PesoUsuarioDAO extends BaseDAO< PesoUsuario, Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2732933055922684415L;

	@SuppressWarnings("unchecked")
	public List<Peso> buscaPesoUsuario(Usuario usuario) {
		
		return em.createQuery("SELECT pu " 
		                      + "FROM PesoUsuario pu "
		                      + "WHERE pu.usuario.id = :usuario ")
		                      .setParameter("usuario", usuario.getId()).getResultList();
		
	}
	
}