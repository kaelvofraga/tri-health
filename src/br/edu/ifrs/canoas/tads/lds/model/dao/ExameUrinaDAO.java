package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TemporalType;
import br.edu.ifrs.canoas.tads.lds.bean.ExameUrinaUsuario;
import br.edu.ifrs.canoas.tads.lds.util.StrUtil;

@Stateless
public class ExameUrinaDAO extends BaseDAO<ExameUrinaUsuario, Long> {

	private static final long serialVersionUID = 2520975190400364647L;

	@SuppressWarnings("unchecked")
	public List<ExameUrinaUsuario> buscaPorCriterio2(Date dataDe, Date dataAte,
			String criterioExameUrina) {
		try {
			String query = "SELECT eu FROM ExameUrinaUsuario eu WHERE lower (eu.observacao) like :criterio OR eu.data between :dataDe and :dataAte ORDER BY eu.data";
			return em.createQuery(query)
					.setParameter("criterio", criterioExameUrina.toLowerCase())
					.setParameter("dataDe", dataDe, TemporalType.TIMESTAMP)
					.setParameter("dataAte", dataAte, TemporalType.TIMESTAMP)
					.getResultList();
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<ExameUrinaUsuario> buscaPorCriterio(Date dataDe, Date dataAte,
			String criterioExameUrina) {
		String criterio = "";

		if(StrUtil.isNotBlank(criterioExameUrina)){
		criterio = " lower(eu.observacao) like '%" + criterioExameUrina.toLowerCase()+ "%' or ";
		System.out.println("criterio"+criterio.toString());
		}

		if (dataDe!=null && dataAte!=null) {
			criterio += "eu.data between :dataDe and :dataAte ";
			System.out.println("criterio"+criterio.toString());
		}
		try {
			return em
					.createQuery(
							"SELECT eu FROM ExameUrinaUsuario eu " + "WHERE "
									+ criterio + "ORDER BY eu.data")
					.setParameter("dataDe", dataDe, TemporalType.TIMESTAMP)
					.setParameter("dataAte", dataAte, TemporalType.TIMESTAMP)
					.getResultList();
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
}