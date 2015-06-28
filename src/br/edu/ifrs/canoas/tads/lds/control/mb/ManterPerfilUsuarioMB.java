package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIOutput;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifrs.canoas.tads.lds.bean.Cidade;
import br.edu.ifrs.canoas.tads.lds.bean.Idioma;
import br.edu.ifrs.canoas.tads.lds.bean.Pais;
import br.edu.ifrs.canoas.tads.lds.bean.UnidadeFederativa;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;
import br.edu.ifrs.canoas.tads.lds.bean.jasper.PerfilUsuarioBean;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterCidadeService;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterIdiomaService;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterPaisesService;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterUfService;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterUsuarioService;
import br.edu.ifrs.canoas.tads.lds.relatorio.PerfilUsuarioREL;


/**
 * 
 * @author: Miromar J. Lima
 * Prop�sito da Classe: Receber dados da classe manterPerfilUsu�rio.xhtm e 
 * invocar as classes Service que por sua vez invocar�o as classes Dao para 
 * fazer busca e armazenamento no banco de dados 
 * Data: 13/05/2015         
 */


@Named
@SessionScoped
public class ManterPerfilUsuarioMB implements Serializable {

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

	@EJB
	private ManterIdiomaService manterIdiomaService;

	private List<Pais> paises;

	private List<UnidadeFederativa> ufs;

	private List<Cidade> cidades;

	private List<Long> idiomasSelecionado;

	private List<Idioma> idiomas;

	public ManterPerfilUsuarioMB() {
	}

	public void adicionarIdioma(Idioma idioma) {
		this.idiomas.add(idioma);
	}

	/**
	 * 
	 * @author: Miromar J. Lima
	 * Prop�sito do m�todo: capturar usu�rio logado e direcionar para pagina manterPerfilUsuario.jsf
	 * Data: 13/05/2015         
	 */

	public String atualizaUsuarioSecao() {
		this.usuario = gerenciarLoginMB.getUsuario();
		return "/private/pages/manterPerfilUsuario.jsf";
	}

	/**
	 * @return the cidades
	 * Verfifica se est� nulo para busca as cidades filtrando pela UF
	 */
	public List<Cidade> getCidades() {
		if (cidades == null && this.usuario != null
				&& this.usuario.getEndereco() != null && this.usuario.getEndereco().getCidade() != null
				&& this.usuario.getEndereco().getCidade().getUf() != null) {
			cidades = manterCidadeService.buscaCidadesPorUf(this.usuario.getEndereco().getCidade().getUf());
		}
		return cidades;
	}

	public GerenciarLoginMB getGerenciarLoginMB() {
		return gerenciarLoginMB;
	}

	/**
	 * @return the idiomas
	 */
	public List<Idioma> getIdiomas() {
		if (idiomas == null)
			idiomas = manterIdiomaService.buscaIdiomas();

		return idiomas;
	}

	

	/**
	 * 
	 * @author: Miromar J. Lima
	 * Prop�sito do m�todo: carregar lista de idiomas para o usu�rio pelo id
	 * Data: 13/05/2015         
	 */
	public List<Long> getIdiomasSelecionado() {
		if (idiomasSelecionado == null) {
			idiomasSelecionado = new ArrayList<>();
			for (Idioma idioma : getUsuario().getIdiomas()) {
				idiomasSelecionado.add(idioma.getId());
			}
		}
		
		return idiomasSelecionado;
	}

	/**
	 * @return the manterCidadeService
	 */
	public ManterCidadeService getManterCidadeService() {
		return manterCidadeService;
	}

	/**
	 * @return the manterIdiomaService
	 */
	public ManterIdiomaService getManterIdiomaService() {
		return manterIdiomaService;
	}

	/**
	 * @return the manterPaisService
	 */
	public ManterPaisesService getManterPaisService() {
		return manterPaisService;
	}

	/**
	 * @return the manterUfService
	 */
	public ManterUfService getManterUfService() {
		return manterUfService;
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
	 * @return the ufs
	 * Prop�sito do m�todo: carregar lista de UFs para o usu�rio buscando pelo Pais
	 * Data: 13/05/2015
	 */
	public List<UnidadeFederativa> getUfs() {
		if (ufs == null && this.usuario != null
				&& this.usuario.getEndereco() != null && this.usuario.getEndereco().getCidade() != null
				&& this.usuario.getEndereco().getCidade().getUf() != null
				&& this.usuario.getEndereco().getCidade().getUf().getPais() != null) {
			ufs = manterUfService.buscaUfsPorPais(this.usuario.getEndereco().getCidade().getUf().getPais());
		}

		return ufs;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @return the usuarioService
	 */
	public ManterUsuarioService getUsuarioService() {
		return usuarioService;
	}

	/**
	 * Prop�sito do m�todo: Salva usu�rio editado, assim como lista de idiomas selecionado, setando pelo ID
	 * Data: 13/05/2015
	 */
	public void salva() {
		if (this.idiomasSelecionado != null) {
			List<Idioma> idiomas = new ArrayList<>();
			for (Long idiomaSelecionado : this.idiomasSelecionado) {
				Idioma idioma = new Idioma();
				idioma.setId(idiomaSelecionado);
				idiomas.add(idioma);
			}
			usuario.setIdiomas(idiomas);
		}
		
		usuarioService.salvaUsuarioEdicao(usuario);
		gerenciarLoginMB.setUsuario(usuario);
	}
	
	/**
	 * Prop�sito do m�todo: Carregar lista de paises usando evento AjaxBehaviorEvent para carregar filtrando lista de UFs e Cidades
	 * Data: 13/05/2015
	 */
	public void paisListener(AjaxBehaviorEvent event) {
		this.ufs = new ArrayList<UnidadeFederativa>();
		this.cidades = new ArrayList<Cidade>();
		Long paisSelecionado = (Long) ((UIOutput) event.getSource()).getValue();
		if (paisSelecionado != null) {
			Pais pais = new Pais();
			pais.setId(paisSelecionado);
			this.ufs = manterUfService.buscaUfsPorPais(pais);
		}
    }
	
	
	/**
	 * Prop�sito do m�todo: Carregar lista de ufs usando evento AjaxBehaviorEvent para carregar filtrando lista de Cidades
	 * Data: 13/05/2015
	 */
	public void estadoListener(AjaxBehaviorEvent event) {
		this.cidades = new ArrayList<Cidade>();
		Long ufSelecionado = (Long) ((UIOutput) event.getSource()).getValue();
		if (ufSelecionado != null) {
			UnidadeFederativa unidadeFederativa = new UnidadeFederativa();
			unidadeFederativa.setId(ufSelecionado);
			this.cidades = manterCidadeService.buscaCidadesPorUf(unidadeFederativa);
		}
    }

	/**
	 * @param cidades
	 *            the cidades to set
	 */
	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public void setGerenciarLoginMB(GerenciarLoginMB gerenciarLoginMB) {
		this.gerenciarLoginMB = gerenciarLoginMB;
	}

	/**
	 * @param idiomas
	 *            the idiomas to set
	 */
	public void setIdiomas(List<Idioma> idiomas) {
		this.idiomas = idiomas;
	}

	public void setIdiomasSelecionado(List<Long> idiomasSelecionado) {
		this.idiomasSelecionado = idiomasSelecionado;
	}

	/**
	 * @param manterCidadeService
	 *            the manterCidadeService to set
	 */
	public void setManterCidadeService(ManterCidadeService manterCidadeService) {
		this.manterCidadeService = manterCidadeService;
	}

	/**
	 * @param manterIdiomaService
	 *            the manterIdiomaService to set
	 */
	public void setManterIdiomaService(ManterIdiomaService manterIdiomaService) {
		this.manterIdiomaService = manterIdiomaService;
	}

	/**
	 * @param manterPaisService
	 *            the manterPaisService to set
	 */
	public void setManterPaisService(ManterPaisesService manterPaisService) {
		this.manterPaisService = manterPaisService;
	}

	/**
	 * @param manterUfService
	 *            the manterUfService to set
	 */
	public void setManterUfService(ManterUfService manterUfService) {
		this.manterUfService = manterUfService;
	}

	/**
	 * @param paises
	 *            the paises to set
	 */
	public void setPaises(List<Pais> paises) {
		this.paises = paises;
	}

	/**
	 * @param ufs
	 *            the ufs to set
	 */
	public void setUfs(List<UnidadeFederativa> ufs) {
		this.ufs = ufs;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @param usuarioService
	 *            the usuarioService to set
	 */
	public void setUsuarioService(ManterUsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public void gerarRelatorio(){
		PerfilUsuarioBean pub = new PerfilUsuarioBean().conversor(usuario);
		List<PerfilUsuarioBean> lista = new ArrayList<PerfilUsuarioBean>();
		lista.add(pub);
		
		try{
			PerfilUsuarioREL relatorio = new PerfilUsuarioREL();
			relatorio.imprimir(lista);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
