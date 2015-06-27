package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.Hospital;
import br.edu.ifrs.canoas.tads.lds.model.dao.HospitalDAO;
import br.edu.ifrs.canoas.tads.lds.util.StrUtil;

@Stateless
public class ManterHospitalService {
	
	@Inject
	HospitalDAO hospitalDAO;
	
	/**
	 * @param criterioMedida Se refere a um critério de busca (Unidade de Medida ou Tipo da Medida) do tipo String;
	 * @return retorna todos os objetos os quais possuem (Aproximado ou igual) em seus atributos o criterioMedida;
	 */
	@SuppressWarnings("unchecked")
	public List<Hospital> busca(String nomeHospital) {
		if (StrUtil.isNotBlank(nomeHospital))
			return hospitalDAO.buscaHospitalPorNome(nomeHospital);
		else
			return hospitalDAO.buscaTodos();
	}
	
}
