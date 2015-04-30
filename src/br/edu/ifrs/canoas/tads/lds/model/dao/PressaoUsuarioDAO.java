package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifrs.canoas.tads.lds.bean.PressaoArterial;
import br.edu.ifrs.canoas.tads.lds.bean.PressaoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;

@Stateless
public class PressaoUsuarioDAO extends BaseDAO< PressaoUsuario, Long>{
	private static final long serialVersionUID = -3230082070333146918L;
	
	@SuppressWarnings("unchecked")
	public List<PressaoUsuario> buscaPressaoPorUsuario(Usuario usuario){
		return em.createQuery(
		         "SELECT pu.pressaoArterial " 
		         + "FROM PressaoUsuario pu "
		         + "WHERE pu.usuario.id = :usuario ")
		         .setParameter("usuario", usuario.getId())
		         .getResultList();
	}
}
