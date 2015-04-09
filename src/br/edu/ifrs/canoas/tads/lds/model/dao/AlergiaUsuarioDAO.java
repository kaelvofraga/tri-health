package br.edu.ifrs.canoas.tads.lds.model.dao;


import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifrs.canoas.tads.lds.bean.AlergiaUsuario;

@Stateless
public class AlergiaUsuarioDAO extends BaseDAO<AlergiaUsuario, Long>{

	private static final long serialVersionUID = -126746156147500109L;

	public List<AlergiaUsuario> buscaPorCriterio(String criterioAlergia) {
		
		return null;
	}
	
}
