/*package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifrs.canoas.tads.lds.bean.Peso;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;

	/**
	 * * Classe que busca dados do banco
	 * /** 
	 * @author Luana
	 * @version 06/05/2015
	 */
	 /*
@Stateless
public class PesoDAO extends BaseDAO<Peso, Long> implements Serializable {
	
	private static final long serialVersionUID = -7932771645088001580L;

	@SuppressWarnings("unchecked")
	public List<Peso> buscaPesoUsuario(Usuario usuario) {	
		return em.createQuery("SELECT p " 
					         + " FROM Peso p "
					         + "WHERE p.id in "
					         + "          (select pu.peso.id "
					         + "             from PesoUsuario pu "
					         + "            where au.usuario.id = :usuario) ").setParameter("usuario", usuario.getId()).getResultList();
	}
}

*/