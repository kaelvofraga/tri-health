package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.edu.ifrs.canoas.tads.lds.bean.PressaoArterial;
import br.edu.ifrs.canoas.tads.lds.bean.BaseEntity;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;

/**
 * Entity implementation class for Entity: PressaoUsuario
 */
@Entity
public class PressaoUsuario extends BaseEntity<Long> implements Serializable{
	private static final long serialVersionUID = 9138218268279147003L;
	
	@NotNull @ManyToOne
	@JoinColumn(name="PRESSAOARTERIAL_ID")
	private PressaoArterial pressaoArterial = new PressaoArterial();
	
	@NotNull @ManyToOne 
	@JoinColumn(name="USUARIO_ID")
	private Usuario usuario;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	private String notas;
	
	public PressaoUsuario(){
		super();
	}

	public PressaoArterial getPressaoArterial() {
		return pressaoArterial;
	}

	public void setPressaoArterial(PressaoArterial pressaoArterial) {
		this.pressaoArterial = pressaoArterial;
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

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
