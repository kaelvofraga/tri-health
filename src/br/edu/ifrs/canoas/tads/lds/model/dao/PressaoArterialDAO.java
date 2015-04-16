package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifrs.canoas.tads.lds.bean.PressaoArterial;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;

@Stateless
public class PressaoArterialDAO extends BaseDAO<PressaoArterial, Long> implements Serializable {
	private static final long serialVersionUID = -8779592587312664221L;
	
	@SuppressWarnings("unchecked")
	public List<PressaoArterial> buscaPressaoPorUsuario (Usuario usuario){
		
		return em.createQuery(
				"SELECT pa"
				+ "FROM PressaoArterial pa"
				+ "WHERE pa.id in"
				+ "(select pu.pressaoarterial.id "
		        + "	from PressaoUsuario pu "
		        + "	where pu.usuario.id = :usuario) ")
		        .setParameter("usuario", usuario.getId())
		        .getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<PressaoArterial> buscaPorData(Date data){
		return em.createQuery(
				"SELECT pa"
				+ "FROM PressaoArterial pa"
				+ "WHERE pa.data = :data" )
				.setParameter("data", data)
		        .getResultList();
	}
}