package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.List;

import javax.inject.Inject;
import br.edu.ifrs.canoas.tads.lds.bean.Consulta;
import br.edu.ifrs.canoas.tads.lds.model.dao.ConsultaDAO;

public class ManterConsultasService {
	
	@Inject
	private ConsultaDAO consultaDAO;

	@SuppressWarnings("unchecked")
	public List<Consulta> buscaTodos() {
		return consultaDAO.buscaTodos();
	}	
	
	public boolean salva(Consulta consulta) {
		try{
			consultaDAO.insere(consulta);
			System.out.println("Salvo com sucesso");
		}catch(Exception e){
			System.out.println("Exception ao Salvar");
			return false;
		}
		return true;
	}
}