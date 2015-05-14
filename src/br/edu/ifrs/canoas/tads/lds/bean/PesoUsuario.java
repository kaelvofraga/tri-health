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
 */
@Entity
public class PesoUsuario extends BaseEntity<Long> implements Serializable{	
		
	private static final long serialVersionUID = -1508218881376423095L;
		
	@NotNull @ManyToOne
	@JoinColumn(name="USUARIO_ID")
	private Usuario usuario;
	
    @OneToOne
	@JoinColumn(name="UDM_ID")
	private Udm udm;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	@NotNull 
	private String nota;
	
	private double valor;
	
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

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
}
