package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifrs.canoas.tads.lds.bean.AtividadeUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;

@Stateless
public class AtividadeUsuarioDAO extends BaseDAO<AtividadeUsuario, Long>
		implements Serializable {

	private static final long serialVersionUID = -3684765988161245661L;

	@SuppressWarnings("unchecked")
	public List<AtividadeUsuario> buscaAtividadesDoUsuario(Usuario usuario) {
		return em
				.createQuery(
						"SELECT au FROM AtividadeUsuario au "
								+ "WHERE au.usuario.id = :usuario ORDER BY au.dataInicio")
				.setParameter("usuario", usuario.getId()).getResultList();

	}

	@SuppressWarnings("unchecked")
	public List<AtividadeUsuario> buscaPorCriterio(String criterio, Usuario usuario) {
		try {	
			String str = "SELECT au FROM AtividadeUsuario au WHERE lower(au.notas) like '%"; 
			str+= criterio.trim().toLowerCase(); 
			str+="%' OR lower(au.atividade.descricao) like '%" ;
			str+=criterio.trim().toLowerCase();
			str+="%' OR lower(au.atividade.tipoAtividade.nome) like '%"; 
			str+=criterio.trim().toLowerCase();
			str+="%' AND au.usuario.id = :usuario ORDER BY au.dataInicio";
						
			return em.createQuery(str).setParameter("usuario", usuario.getId()).getResultList();
			
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
}
