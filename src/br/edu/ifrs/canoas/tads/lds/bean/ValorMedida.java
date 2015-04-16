package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: MedicamentoUsuario
 *
 */

public class ValorMedida extends BaseEntity<Long> implements Serializable {

private static final long serialVersionUID = 3616123664819931225L;

@NotNull @OneToOne
@JoinColumn(name="UDM_ID")
private Udm udm;

@NotNull @OneToOne
@JoinColumn(name="TIPOMEDIDA_ID")
private TipoMedida tipoMedida;

@NotNull @ManyToOne
@JoinColumn(name="USUARIO_ID")
private Usuario usuario;

@NotNull 
private float valorMedida;

@NotNull @Temporal(TemporalType.TIMESTAMP)
private Date dataMedida;

private String observacao;
}
