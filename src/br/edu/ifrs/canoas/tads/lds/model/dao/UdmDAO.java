package br.edu.ifrs.canoas.tads.lds.model.dao;


import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifrs.canoas.tads.lds.bean.Medicamento;
import br.edu.ifrs.canoas.tads.lds.bean.MedicamentoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.TipoMedida;
import br.edu.ifrs.canoas.tads.lds.bean.Udm;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;
import br.edu.ifrs.canoas.tads.lds.bean.ValorMedida;

@Stateless
public class UdmDAO extends BaseDAO< ValorMedida, Long>{

	private static final long serialVersionUID = 2039209283467379697L;

	public List<Udm> buscaDescricaoUdm(Usuario usuario) {
		
		return em.createQuery(
		         "SELECT descricao " 
		         + "FROM Udm "
		         + "WHERE Udm.usuario.id = :usuario ")
		         .setParameter("usuario", usuario.getId())
		         .getResultList();
	}
	
}
