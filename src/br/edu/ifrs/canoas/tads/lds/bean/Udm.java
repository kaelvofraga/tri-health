package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: ValorMedida
 */

@Entity
public class Udm extends BaseEntity<Long> implements Serializable {

	
	private static final long serialVersionUID = 1392486240999537103L;

	@NotNull 
	private String Descricao;
	

	public String getTipoUdm() {
		return Descricao;
	}

	public void setTipoUdm(String tipoUdm) {
		this.Descricao = tipoUdm;
	}
	
	
	
}
