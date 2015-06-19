package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;



/**@author: André Ficht/Alisson Lorscheiter
 * Proposito da Classe: Classe que representa a entidade do Exame de Urina referenciando ao 
 * usuario e aos tipos de exames presentes na urina.
 * 
 */

@Entity
public class ExameUrinaUsuario extends BaseEntity<Long> implements Serializable{
	
	private static final long serialVersionUID = -1792527376641748489L;

	@NotNull 
	@ManyToOne
    @JoinColumn(name="ID_USUARIO") 
	private Usuario usuario;
	
	@NotNull
	@OneToMany (mappedBy="exameUsuario",fetch = FetchType.EAGER)
	private Collection<ItemExameUrina> itensExame;
		
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	private String observacao;
	

	public ExameUrinaUsuario() {
		super();
	}
	
	/*GETTERS & SETTERS*/
	
	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Collection<ItemExameUrina> getItensExame() {
		return itensExame;
	}

	public void setItensExame(Collection<ItemExameUrina> itensExame) {
		this.itensExame = itensExame;
	}
	
	

	
	
}
