package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import br.edu.ifrs.canoas.tads.lds.bean.Hospital;
import br.edu.ifrs.canoas.tads.lds.model.dao.HospitalDAO;
import br.edu.ifrs.canoas.tads.lds.util.StrUtil;

/** 
* Service Relacionado a US localizar hospital em mapa
* @author Juarez Monteiro
* */

@Stateless
public class ManterHospitalService {
	
	@Inject
	HospitalDAO hospitalDAO;
	
	/**
	 * @param nomeHospital Se refere a um critério de busca (nome do hospital) do tipo String;
	 * @return retorna todos os objetos os quais possuem o nome em critério ou caso não seja informado esse critério
	 *  irá retornar todos todos os hospitais.
	 */
	public List<Hospital> busca(String nomeHospital) {
		if (StrUtil.isNotBlank(nomeHospital))
			return hospitalDAO.buscaHospitalPorNome(nomeHospital);
		else
			return hospitalDAO.buscaTodos();
	}
	
	public List<Hospital> buscaTodosHospitais() {
		return  hospitalDAO.buscaTodos();
	}
	
}
