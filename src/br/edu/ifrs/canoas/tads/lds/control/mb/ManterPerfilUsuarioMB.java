package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifrs.canoas.tads.lds.bean.Cidade;
import br.edu.ifrs.canoas.tads.lds.bean.Pais;
import br.edu.ifrs.canoas.tads.lds.bean.UnidadeFederativa;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterCidadeService;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterPaisesService;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterUfService;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterUsuarioService;

@Named
@SessionScoped
public class ManterPerfilUsuarioMB implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6343391229889538808L;

	@Inject
	private GerenciarLoginMB gerenciarLoginMB;
		
	@Inject
	private Usuario usuario;
	
	@EJB
	private ManterPaisesService manterPaisService;
	
	@EJB
	private ManterUsuarioService usuarioService;
	
	@EJB
	private ManterUfService manterUfService;
	
	@EJB
	private ManterCidadeService manterCidadeService;
	
	
	private List<Pais> paises;
	
	private List<UnidadeFederativa> ufs;
	
	private List<Cidade> cidades;	

	
	public ManterPerfilUsuarioMB() {
	}

	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public GerenciarLoginMB getGerenciarLoginMB() {
		return gerenciarLoginMB;
	}

	public void setGerenciarLoginMB(GerenciarLoginMB gerenciarLoginMB) {
		this.gerenciarLoginMB = gerenciarLoginMB;
	}

	/**
	 * @return the paises
	 */
	public List<Pais> getPaises() {
		if (paises == null)
			paises = manterPaisService.buscaPaises();

		return paises;
	}

	/**
	 * @param paises the paises to set
	 */
	public void setPaises(List<Pais> paises) {
		this.paises = paises;
	}

	
	/**
	 * @return the manterPaisService
	 */
	public ManterPaisesService getManterPaisService() {
		return manterPaisService;
	}

	/**
	 * @param manterPaisService the manterPaisService to set
	 */
	public void setManterPaisService(ManterPaisesService manterPaisService) {
		this.manterPaisService = manterPaisService;
	}

	/**
	 * @return the ufs
	 */
	public List<UnidadeFederativa> getUfs() {
		if (ufs == null)
			ufs = manterUfService.buscaUfs();
		
		return ufs;	
	}
	
	
	/**
	 * @param ufs the ufs to set
	 */
	public void setUfs(List<UnidadeFederativa> ufs) {
		this.ufs = ufs;
	}

	/**
	 * @return the cidades
	 */
	public List<Cidade> getCidades() {
		if (cidades == null)
			cidades = manterCidadeService.buscaCidades();
		return cidades;
	}

	/**
	 * @param cidades the cidades to set
	 */
	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	/**
	 * @return the usuarioService
	 */
	public ManterUsuarioService getUsuarioService() {
		return usuarioService;
	}

	/**
	 * @param usuarioService the usuarioService to set
	 */
	public void setUsuarioService(ManterUsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public void salva() {
		usuarioService.salvaUsarioEdicao(usuario);
	}

	
	public String atualizaUsuarioSecao(){
		this.usuario =  gerenciarLoginMB.getUsuario();
		return "/private/pages/manterPerfilUsuario.jsf";
	}

}
