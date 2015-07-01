package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifrs.canoas.tads.lds.bean.Resultado;
import br.edu.ifrs.canoas.tads.lds.bean.ResultadoCampos;
import br.edu.ifrs.canoas.tads.lds.control.service.ListarResultadoService;

@Named
@SessionScoped
public class ListarResultadoMB  implements Serializable {

	private static final String URL_LISTAR_RESULTADOS = "/private/pages/listarResultado.jsf";
	private static final String URL_MANTER_RESULTADOS = "/private/pages/manterResultado.jsf";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7171528304429784985L;

	private List<Resultado> resultados = new ArrayList<Resultado>();
	
	@EJB
	private ListarResultadoService listarResultadoService;

	@Inject
	private GerenciarLoginMB gerenciarLoginMB;
	
	private Resultado resultado;
	
	public ListarResultadoMB() {	
		
	}
	
	public String initListar() {
		this.clear();
		this.setResultados(this.listarResultadoService
				.buscarTodosResultados(gerenciarLoginMB.getUsuario()));
		
		if(this.getResultados().size() > 0){
			this.setResultado(this.getResultados().get(0));
		}
		
		return URL_LISTAR_RESULTADOS;
	}
	
	private void clear(){
		this.setResultados(new ArrayList<Resultado>());
		this.setResultado(null);
	}

	public List<Resultado> getResultados() {
		return resultados;
	}

	public void setResultados(List<Resultado> resultados) {
		this.resultados = resultados;
	}

	public Resultado getResultado() {
		return resultado;
	}

	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}
	
	public List<ResultadoCampos> getResultadoCamposList() {
		if (this.getResultado() == null)
			return new ArrayList<ResultadoCampos>();

		return this.getResultado().getResultadoCamposList();
	}
}
