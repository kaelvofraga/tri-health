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

@Entity
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
private float medidaValor;

@NotNull @Temporal(TemporalType.TIMESTAMP)
private Date dataMedida;

private String observacao;

public Udm getUdm() {
	return udm;
}

public void setUdm(Udm udm) {
	this.udm = udm;
}

public TipoMedida getTipoMedida() {
	return tipoMedida;
}

public void setTipoMedida(TipoMedida tipoMedida) {
	this.tipoMedida = tipoMedida;
}

public Usuario getUsuario() {
	return usuario;
}

public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
}

public float getMedidaValor() {
	return medidaValor;
}

public void setMedidaValor(float medidaValor) {
	this.medidaValor = medidaValor;
}

public Date getDataMedida() {
	return dataMedida;
}

public void setDataMedida(Date dataMedida) {
	this.dataMedida = dataMedida;
}

public String getObservacao() {
	return observacao;
}

public void setObservacao(String observacao) {
	this.observacao = observacao;
}

public static long getSerialversionuid() {
	return serialVersionUID;
}


}
