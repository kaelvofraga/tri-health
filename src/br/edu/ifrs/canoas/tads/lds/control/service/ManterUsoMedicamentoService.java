package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.Medicamento;
import br.edu.ifrs.canoas.tads.lds.bean.MedicamentoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;
import br.edu.ifrs.canoas.tads.lds.model.dao.MedicamentoDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.MedicamentoUsuarioDAO;
import br.edu.ifrs.canoas.tads.lds.util.Mensagens;
import br.edu.ifrs.canoas.tads.lds.util.StrUtil;

@Stateless
public class ManterUsoMedicamentoService {

	@Inject
	private MedicamentoUsuarioDAO medicamentoUsuarioDAO;

	@Inject
	private MedicamentoDAO medicamentoDAO;
	
	
	

	public Medicamento buscaOuCriaMedicamentoPorNome(Medicamento medicamento) {
		List<Medicamento> medicamentos = medicamentoDAO.buscaPorNome(medicamento.getNome());
		
		if (medicamentos.size() == 1)
			medicamento = medicamentos.get(0);
		else{
			medicamentoDAO.insere(medicamento);
		}
		return medicamento;
	}
 
	

	public List<Medicamento> buscaMedicamentoUsuario(String query, Usuario usuario) {
		if (usuario != null && usuario.getId() != null)
			
			return medicamentoDAO.buscaNomeMedicamentoPorUsuario(usuario);
		return new ArrayList<Medicamento>();
	}
	
	
	


	
	public boolean salvaMedicamentoUsuario(MedicamentoUsuario medicamentoUsuario) {	
		if (medicamentoUsuario == null || medicamentoUsuario.getMedicamento() == null || medicamentoUsuario.getUsuario() == null) {
		//Mensagens.define(FacesMessage.SEVERITY_ERROR,"Medicamento.cadastro.erro");
		return false;
		}
		try{
			medicamentoUsuarioDAO.insere(medicamentoUsuario);
		}catch(Exception e){
		//Mensagens.define(FacesMessage.SEVERITY_ERROR,"Medicamento.cadastro.erro");			
			return false;
		}
		
		Mensagens.define(FacesMessage.SEVERITY_INFO, "manterMedicamento.cadastro.sucesso");
		return true;
	}
	
	/* Método da opção Buscar do Listar que realiza busca por critério informado ou retorna todos elementos cadastrados.*/
	@SuppressWarnings("unchecked")
	public List<MedicamentoUsuario> busca(String criterioMedicamento) {
		if (StrUtil.isNotBlank(criterioMedicamento))
			return medicamentoUsuarioDAO.buscaPorCriterio(criterioMedicamento);
		else
			return medicamentoUsuarioDAO.buscaTodos();
	}
	
	/*Metodo para fazer alteração no Medicamento do Usuario*/
	public void alteraMedicamentoUsario(MedicamentoUsuario medicamentoUsuario) {
		try{
		medicamentoUsuarioDAO.atualiza(medicamentoUsuario);
		Mensagens.define(FacesMessage.SEVERITY_INFO, "manterMedicamento.altera.sucesso");
		}
		catch (IllegalArgumentException e) {
			   Mensagens.define(FacesMessage.SEVERITY_INFO, "manterMedicamento.altera.excecao.erro");
	   }
	}

	/*Metodo para fazer exclusão do Medicamento Usuario*/ 
	public void excluiMedicamento(MedicamentoUsuario medicamentoUsuario) {
		List<Integer> lista = new ArrayList<Integer>();
		lista=medicamentoUsuarioDAO.buscaIdMedicamentoAlergiaUsuario(medicamentoUsuario);
		for(int i=0;i<lista.size();i++){
			System.out.println("lista:"+lista.get(i));
		}
		
		if(lista.isEmpty()){
			System.out.println("entrou no empty");
			System.out.println("if empty"+ lista.isEmpty());
			medicamentoUsuarioDAO.exclui(medicamentoUsuario.getId());
			Mensagens.define(FacesMessage.SEVERITY_INFO, "manterMedicamento.exclui.sucesso");
			   
		}
		//Mensagens.define(FacesMessage.SEVERITY_INFO, "manterMedicamento.exclui.excecao.erro");
		else{
			System.out.println("entrou no else empty");
			Mensagens.define(FacesMessage.SEVERITY_INFO, "manterMedicamento.exclui.alergia.usuario.erro");	
			}
		//	Mensagens.define(FacesMessage.SEVERITY_INFO, "manterMedicamento.exclui.medicamentousuario.nulo.erro");
		}
}
