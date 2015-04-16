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
public class TipoMedida extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = -2886415432327494685L;
	
	@NotNull 
	private String tipoMedida;

	public String getTipoMedida() {
		return tipoMedida;
	}

	public void setTipoMedida(String tipoMedida) {
		this.tipoMedida = tipoMedida;
	}	
	
}
