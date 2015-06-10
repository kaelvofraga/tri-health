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

/**
 * Entity implementation class for Entity: PesoUsuario
 * Possui identificacao do usuario, udm, data, nota e valor
 * @author Luana
 * @version 06/05/2015
 * 
 * @author Alisson Lorscheiter
 * @version 10/06/2015
 * *Adição do Peso como Join.
 * 	 
 */
@Entity
public class PesoUsuario extends BaseEntity<Long> implements Serializable{	
		
	private static final long serialVersionUID = -1508218881376423095L;
		
	@NotNull @ManyToOne
	@JoinColumn(name="USUARIO_ID")
	private Usuario usuario;
	
	@NotNull @OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="PESO_ID") // JOIN pesoUsuario com peso.
	private Peso peso;
	
    @OneToOne
	@JoinColumn(name="UDM_ID")
	private Udm udm;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	@NotNull 
	private String nota;
	
	
	public PesoUsuario(){
		super();
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

	public Udm getUdm() {
		return udm;
	}

	public void setUdm(Udm udm) {
		this.udm = udm;
	}

	public Peso getPeso() {
		return peso;
	}

	public void setPeso(Peso peso) {
		this.peso = peso;
	}
		
}
