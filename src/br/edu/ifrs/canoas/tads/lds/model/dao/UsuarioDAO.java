package br.edu.ifrs.canoas.tads.lds.model.dao;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.edu.ifrs.canoas.tads.lds.bean.Usuario;
import br.edu.ifrs.canoas.tads.lds.util.StrUtil;

@Stateless
public class UsuarioDAO extends BaseDAO<Usuario, Long>{

	private static final long serialVersionUID = -6896321074436211850L;
	
	
	@SuppressWarnings("unchecked")
	public List<Usuario> buscaPorEmail(String email){
		return em.createQuery(
		         "SELECT u "
		         + "FROM Usuario u "
		         + "WHERE lower(u.email) = :email ")
		         .setParameter("email", email)
		         .getResultList();
	}
		

	
	@SuppressWarnings("unchecked")
	public List<Usuario> busca(Usuario usuario){
				
		if (usuario == null)
			return new ArrayList<Usuario>();
		
		 Criteria criteria = super.createCriteria();

		 
		 if (StrUtil.isNotBlank(usuario.getNome()))
			 criteria.add(Restrictions.eq("nome", usuario.getNome().trim().toLowerCase()));
		
		 if (StrUtil.isNotBlank(usuario.getSobrenome()))
			 criteria.add(Restrictions.eq("sobrenome", usuario.getSobrenome().trim().toLowerCase()));
		 
		 if (StrUtil.isNotBlank(usuario.getEmail()))
			 criteria.add(Restrictions.eq("email", usuario.getEmail().trim().toLowerCase()));
		
		 if (StrUtil.isNotBlank(usuario.getSenha()))
			 criteria.add(Restrictions.eq("senha", usuario.getSenha().trim().toLowerCase()));

		 return criteria.list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> buscaPorCriterio(String criterio){
		return em.createQuery(
		         "SELECT u "
		         + "FROM Usuario u "
		         + "WHERE lower(u.email) = :email "
		         + " or lower (u.nome) = :nome "
		         + " or lower (u.sobrenome) =  :sobrenome ")
		         .setParameter("email", criterio.trim().toLowerCase())
		         .setParameter("nome", criterio.trim().toLowerCase())
		         .setParameter("sobrenome", criterio.trim().toLowerCase())
		         .getResultList();
	}

}
