package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifrs.canoas.tads.lds.bean.PesoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.TipoExameUrina;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;

@Stateless
public class PesoUsuarioDAO extends BaseDAO<PesoUsuario, Long>{

	/** 
	 * Classe que busca dados no banco
	 * @author Luana
	 * @version 06/05/2015
	 */
	private static final long serialVersionUID = -2732933055922684415L;

	@SuppressWarnings("unchecked")
	public List<PesoUsuario> buscaPesoUsuario(Usuario usuario) {
		
		return em.createQuery("SELECT pu " 
		                      + "FROM PesoUsuario pu "
		                      + "WHERE pu.usuario.id = :usuario ")
		                      .setParameter("usuario", usuario.getId()).getResultList();
		
	}
	
	public List<PesoUsuario> listaTodos() {
		return em.createQuery("SELECT ps FROM PesoUsuario ps").getResultList();
	}
	
}