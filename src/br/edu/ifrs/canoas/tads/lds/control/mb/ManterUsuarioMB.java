package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import br.edu.ifrs.canoas.tads.lds.bean.Usuario;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterUsuarioService;

@Named
@RequestScoped
public class ManterUsuarioMB implements Serializable {

	private static final long serialVersionUID = 7918766405702133530L;

	@Inject
	private Usuario usuario;
		
	@Inject
	private GerenciarLoginMB gerenciarLoginMB;

	
	@EJB
	private ManterUsuarioService usuarioService;

	private String criterio = "";
	private List<Usuario> usuarios = new ArrayList<Usuario>();

	public void busca() {
		usuarios = usuarioService.busca(criterio);
		System.out.println(usuarios.size());
	}

	public String salva() {
		if (usuarioService.salvaUsario(usuario)){
			gerenciarLoginMB.setUsuario(usuario);
			usuario = new Usuario();
			return "login";
		}
		else return "manterUsuario";
	}
	
	public boolean isEmEdicao(){
		return usuario != null && usuario.getId() != null;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getCriterio() {
		return criterio;
	}

	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	
	public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Usuario Editado", ((Usuario) event.getObject()).getId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Usuario Excluído", ((Usuario) event.getObject()).getId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
}