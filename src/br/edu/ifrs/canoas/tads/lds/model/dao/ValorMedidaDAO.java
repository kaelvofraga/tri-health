package br.edu.ifrs.canoas.tads.lds.model.dao;


import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifrs.canoas.tads.lds.bean.Medicamento;
import br.edu.ifrs.canoas.tads.lds.bean.MedicamentoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;
import br.edu.ifrs.canoas.tads.lds.bean.ValorMedida;

@Stateless
public class ValorMedidaDAO extends BaseDAO< ValorMedida, Long>{

	private static final long serialVersionUID = 2039209283467379697L;

	/*
	public List<Medicamento> buscaNomeMedicamentoPorUsuario(Usuario usuario) {
		
		return em.createQuery(
		         "SELECT mu.medicamento " 
		         + "FROM MedicamentoUsuario mu "
		         + "WHERE mu.usuario.id = :usuario ")
		         .setParameter("usuario", usuario.getId())
		         .getResultList();
		
	}
*/
	
}
