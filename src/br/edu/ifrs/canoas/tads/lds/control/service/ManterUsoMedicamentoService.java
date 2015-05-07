package br.edu.ifrs.canoas.tads.lds.control.service;


/**
 * Service Implementation for class ManterUsoMedicamentosService
 * @author Alisson Lorscheiter
 *
 */
import java.util.ArrayList;
import java.util.Date;
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
	
   /*Metodo para buscar medicamento no banco se ele existir retorna ou entao cria ele na tabela medicamento 
    * passa parametro o medicamento.*/
	public Medicamento buscaOuCriaMedicamentoPorNome(Medicamento medicamento) {
		List<Medicamento> medicamentos = medicamentoDAO.buscaPorNome(medicamento.getNome());
		
		if (medicamentos.size() == 1)
			medicamento = medicamentos.get(0);
		else{
			medicamento.setId(null);
			medicamentoDAO.insere(medicamento);
		}
		return medicamento;
	}
 
    /*Busca medicamentos que estao cadastrados pro usuario, passando o usuario e uma string para buscar*/
	public List<Medicamento> buscaMedicamentoUsuario(String query, Usuario usuario) {
		if (usuario != null && usuario.getId() != null)
			return medicamentoDAO.buscaNomeMedicamentoPorUsuario(usuario);
		return new ArrayList<Medicamento>();
	}
	
	/*Salva Medicamento chama o metodo buscaOuCriaMedicamentoPorNome para ver se o medicamento existe e depois insere na tabela medicmaentoUsuario*/
	public boolean salvaMedicamentoUsuario(MedicamentoUsuario medicamentoUsuario) {	
		if (medicamentoUsuario == null || medicamentoUsuario.getMedicamento() == null || medicamentoUsuario.getUsuario() == null) {
		Mensagens.define(FacesMessage.SEVERITY_ERROR,"manterMedicamento.cadastro.erro.nulo");
		return false;
		}
			try{
			medicamentoUsuario.setMedicamento(buscaOuCriaMedicamentoPorNome(medicamentoUsuario.getMedicamento()));
			medicamentoUsuarioDAO.insere(medicamentoUsuario);
			}catch(Exception e){
			Mensagens.define(FacesMessage.SEVERITY_ERROR,"Medicamento.cadastro.erro");			
			return false;
			}
		
		Mensagens.define(FacesMessage.SEVERITY_INFO, "manterMedicamento.cadastro.sucesso");
		return true;
	}
	
	
	/*@SuppressWarnings("unchecked")
	public List<MedicamentoUsuario> busca(String criterioMedicamento) {
		if (StrUtil.isNotBlank(criterioMedicamento) && criterioMedicamento!=null)
			return medicamentoUsuarioDAO.buscaPorCriterio(criterioMedicamento);
		else
			return medicamentoUsuarioDAO.buscaTodos();
	}*/
	
	/* Metodo busca do Listar que realiza busca por criterio informado ou retorna todos elementos cadastrados.*/
@SuppressWarnings("unchecked")
	public List<MedicamentoUsuario> busca(String criterioMedicamento) {
		if (StrUtil.isNotBlank(criterioMedicamento) && criterioMedicamento!=null){
			 if(!medicamentoUsuarioDAO.buscaPorCriterio(criterioMedicamento).isEmpty()){
			 return medicamentoUsuarioDAO.buscaPorCriterio(criterioMedicamento);
			 }
			 else{
				 Mensagens.define(FacesMessage.SEVERITY_INFO, "listarMedicamento.busca.vazio");	 
				 return new ArrayList<MedicamentoUsuario>();
			 }
		}else
			return medicamentoUsuarioDAO.buscaTodos();
	}
	 
	
	/*Metodo para fazer alteracao no Medicamento do Usuario que testa se o medicamento ja esta na tabela
	 *  medicamentos caso necessario ele sera criado passa o medicamentousuario como parametro*/
	public void alteraMedicamentoUsario(MedicamentoUsuario medicamentoUsuario) {
		try{
		medicamentoUsuario.setMedicamento(buscaOuCriaMedicamentoPorNome(medicamentoUsuario.getMedicamento()));	
		Medicamento m= new Medicamento();
		m=medicamentoDAO.atualiza(medicamentoUsuario.getMedicamento());
		medicamentoUsuario.setMedicamento(m);
		medicamentoUsuarioDAO.atualiza(medicamentoUsuario);
		Mensagens.define(FacesMessage.SEVERITY_INFO, "manterMedicamento.altera.sucesso");
		}
		catch (IllegalArgumentException e) {
			Mensagens.define(FacesMessage.SEVERITY_ERROR, "manterMedicamento.altera.excecao.erro");
	   }
	}

	/*Metodo para fazer exclusao do Medicamento Usuario passando parametro medicamentoUsuario nao
	 *  exclui se medicamento tme relação com alergia (regra de negocio)*/ 
	public boolean excluiMedicamento(MedicamentoUsuario medicamentoUsuario) {
		if (medicamentoUsuario.getId()!= null && medicamentoUsuario != null ){
		List<Integer> lista = new ArrayList<Integer>();
		lista=medicamentoUsuarioDAO.buscaIdMedicamentoAlergiaUsuario(medicamentoUsuario);
		
		if(lista.isEmpty()){
			medicamentoUsuarioDAO.exclui(medicamentoUsuario.getId());
			Mensagens.define(FacesMessage.SEVERITY_INFO, "manterMedicamento.exclui.sucesso");
			return true;	   
		}
		else{
			Mensagens.define(FacesMessage.SEVERITY_INFO, "manterMedicamento.exclui.alergia.usuario.erro");
			return false;
			}
		}
		else{
			Mensagens.define(FacesMessage.SEVERITY_ERROR, "manterMedicamento.exclui.medicamentousuario.nulo.erro");
			return false;
		}
		
		
	}
	
	public boolean validaDatas(MedicamentoUsuario medicamentoUsuario){
		
		///hgf
		Date dataCons = medicamentoUsuario.getDataConsulta();
		Date dataIni = medicamentoUsuario.getDataInicioTratamento();
		Date dataFim = medicamentoUsuario.getDataFimTratamento();
		
		if(dataCons.getTime() > System.currentTimeMillis()){
			
		}
		
		return false;
	}
}
