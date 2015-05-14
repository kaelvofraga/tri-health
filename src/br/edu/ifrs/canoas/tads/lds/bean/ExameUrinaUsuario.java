package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**@author: Andr� Ficht
 * Proposito da Classe: Classe que representa a entidade do Exame de Urina referenciando ao 
 * usuario e aos tipos de exames presentes na urina.
 * @author: Alisson Lorscheiter
 * 
 * 
 */

@Entity
public class ExameUrinaUsuario extends BaseEntity<Long> implements Serializable{
	
	private static final long serialVersionUID = -8539097274270004334L;

	@NotNull 
	@ManyToOne
    @JoinColumn(name="ID_USUARIO") 
	private Usuario usuario;
	
	@NotNull
	private ItemExameUrina itemExame;
		
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	public ExameUrinaUsuario() {
		super();
	}
	
	/*GETTERS & SETTERS*/

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
}
