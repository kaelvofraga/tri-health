package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.Endereco;
import br.edu.ifrs.canoas.tads.lds.model.dao.EnderecoDAO;

/**
 * 
 * @author: Miromar J. Lima
 * Proposito da Classe: Intermediar a camada de interface com o usuário e o banco de dados
 * 
 * Editado por:
 * @author: Juarez Monteiro   
 * Acrescentando chamada ao método buscaTodos() e criando/injetando enderecoDAO
 */
@Stateless
public class ManterEnderecoService {

	@Inject
	private EnderecoDAO enderecoDAO;
	
	public List<Endereco> buscaTodosEnderecos() {
		return enderecoDAO.buscaTodos();
	}
}
