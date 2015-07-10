package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifrs.canoas.tads.lds.bean.AtividadeUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;
import br.edu.ifrs.canoas.tads.lds.bean.Vacinacao;

@Stateless
public class VacinacaoDAO extends BaseDAO<Vacinacao, Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4487850835189198959L;
	
	@SuppressWarnings("unchecked")
	public List<Vacinacao> buscaVacinacoesDoUsuario(Usuario usuario) {
		return em
				.createQuery(
						"SELECT vac FROM Vacinacao vac "
								+ "WHERE vac.usuario.id = :usuario ORDER BY vac.dataVacinacao")
				.setParameter("usuario", usuario.getId()).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Vacinacao> buscaPorCriterio(String criterio, Usuario usuario) {
		try {	
			String str = "SELECT vac FROM Vacinacao vac WHERE lower(vac.descricao) like '%"; 
			str+= criterio.trim().toLowerCase(); 
			str+="%' OR lower(vac.efeitoColateral) like '%" ;
			str+=criterio.trim().toLowerCase();
			str+="%' AND vac.usuario.id = :usuario ORDER BY vac.dataVacinacao";
						
			return em.createQuery(str).setParameter("usuario", usuario.getId()).getResultList();
			
		} catch (IllegalArgumentException e) {
			return new ArrayList<Vacinacao>();
		}
	}

}