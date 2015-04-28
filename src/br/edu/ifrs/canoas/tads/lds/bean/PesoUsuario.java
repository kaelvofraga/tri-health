package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: PesoUsuario
 */
@Entity
public class PesoUsuario extends BaseEntity<Long> implements Serializable{	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1508218881376423095L;

	@NotNull 
	@JoinColumn(name="PESO_ID")
	private Peso peso;
	
	@NotNull 
	@JoinColumn(name="USUARIO_ID")
	private Usuario usuario;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	@NotNull 
	private String nota;
	
	public PesoUsuario(){
		super();
	}

	public Peso getPeso() {
		return peso;
	}

	public void setPeso(Peso peso) {
		this.peso = peso;
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

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
