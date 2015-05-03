package br.edu.ifrs.canoas.tads.lds.model.dao;


import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifrs.canoas.tads.lds.bean.Medicamento;
import br.edu.ifrs.canoas.tads.lds.bean.MedicamentoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.TipoMedida;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;
import br.edu.ifrs.canoas.tads.lds.bean.ValorMedidaUsuario;

@Stateless
public class TipoMedidaDAO extends BaseDAO< TipoMedida, Long>{

	private static final long serialVersionUID = -4135102423252874690L;

	
	
	/*
	public List<TipoMedida> buscaDescricaoTipoMedida(Usuario usuario) {
		
		return em.createQuery(
		         "SELECT descricao " 
		         + "FROM TipoMedida "
		         + "WHERE TipoMedida.usuario.id = :usuario"
		         + " ORDER BY descricao")
		         .setParameter("usuario", usuario.getId())
		         .getResultList();
	}
*/
	
}
