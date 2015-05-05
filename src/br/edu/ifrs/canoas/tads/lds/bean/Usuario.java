package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;

/**
 * Entity implementation class for Entity: Usuario
 *
 */
@Entity
public class Usuario extends BaseEntity<Long> implements Serializable {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8888460954413155909L;

	@NotNull @Email 
	private String email;
	
	@NotNull
	private String senha;
	
	@NotNull
	private String nome;
	
	@NotNull 
	private String sobrenome;
	
	private String apelido;
	
	private String telefone;
		
	private String celular;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataNasc;
	
	private String genero;
	
	private String tipoSanguineo;
	
	@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="ID_ENDERECO")
	private Endereco endereco = new Endereco();

	//@ManyToMany(cascade=CascadeType.ALL)
    //@JoinTable(name="IDIOMA_USUARIO", 
     //          joinColumns=@JoinColumn(name="ID_USUARIO"),
     //          inverseJoinColumns=
     //                    @JoinColumn(name="ID_IDIOMA")
     //          )
    private DualListModel<String> idiomas;
	
    /*@OneToMany(mappedBy="usuario")
    private List<Consulta> consultas;*/
    
	public String getTipoSanguineo() {
		return tipoSanguineo;
	}

	public void setTipoSanguineo(String tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public String getApelido() {
		return apelido;
	}


	public void setApelido(String apelido) {
		this.apelido = apelido;
	}


	
	public Date getDataNasc() {
		return dataNasc;
	}


	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}


	public Endereco getEndereco() {
		return endereco;
	}


	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}


	public Usuario() {
		super();
	}
	
	
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}   
	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
/*
	public List<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}
*/
	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public String getCelular() {
		return celular;
	}


	public void setCelular(String celular) {
		this.celular = celular;
	}

	/**
	 * @return the idiomas
	 */
	//public Collection<Idioma> getIdiomas() {
	//	return idiomas;
	//}

	/**
	 * @param idiomas the idiomas to set
	 */
	//public void setIdiomas(Collection<Idioma> idiomas) {
	//	this.idiomas = idiomas;
	//}
	@PostConstruct
    public void init() {
        //idiomas
        List<String> listaDeIdiomas = new ArrayList<String>();
        List<String> alvoIdioma = new ArrayList<String>();
         
        listaDeIdiomas.add("Portugês");
        listaDeIdiomas.add("Inglês");
        listaDeIdiomas.add("Francês");
        listaDeIdiomas.add("Estanhól");
        listaDeIdiomas.add("Alemanaha");
        listaDeIdiomas.add("Italiano");
        listaDeIdiomas.add("Japonês");
         
        idiomas = new DualListModel<String>(listaDeIdiomas, alvoIdioma);
         
        
    }
 
    public DualListModel<String> getIdiomas() {
        return idiomas;
    }
 
    public void setIdioma(DualListModel<String> idiomas) {
        this.idiomas = idiomas;
    }
 
 
    public void onSelect(SelectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selecionado", event.getObject().toString()));
    }
     
    public void onUnselect(UnselectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item não Selecionado", event.getObject().toString()));
    }
     
    public void onReorder() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "List Reordered", null));
    } 
}


