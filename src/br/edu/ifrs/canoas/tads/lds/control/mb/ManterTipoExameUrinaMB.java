package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifrs.canoas.tads.lds.bean.TipoExameUrina;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterTipoUrina;

@Named
@SessionScoped
public class ManterTipoExameUrinaMB implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5360775543616677292L;
	
	@Inject
	private GerenciarLoginMB gerenciarLoginMB;
	
	@EJB
	private ManterTipoUrina tipoUrinaService;
	
	private TipoExameUrina tipoExameUrina;
	private List<TipoExameUrina> tipos;
	private String criterioTipoExameUrina;
	
	public TipoExameUrina getTipoExameUrina() {
		return tipoExameUrina;
	}
	
	public void setTipoExameUrina(TipoExameUrina tipoExameUrina) {
		this.tipoExameUrina = tipoExameUrina;
	}
	
	/*public void salvaTipoExame(){
		tipoExameUrina.setUsuario(gerenciarLoginMB.getUsuario());
		tipoUrinaService.salvaUsario(tipoExameUrina);
		this.init();
	}
	
	@PostConstruct
	public void init(){
		tipoExameUrina = new TipoExameUrina();
		criterioTipoExameUrina = "";
		tipos = new ArrayList<>();
	}*/
	
}
