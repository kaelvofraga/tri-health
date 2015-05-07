package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * Entity implementation class for Entity: ValorMedidaUsuario
 * @author JuarezMonteiro
 * @brief Classe de relacionamento entre Medida a usuário. 
 * @since 07/05/2015
 * 
 * Atributos:
 * udm (Udm): unidade de medida relacionada.
 * tipoMedida (TipoMedida): tipo de medida relacionado.
 * usuario (Usuario): usuario relacionado.
 * observacao (String): observações e detalhes, máximo 200 caracteres.
 * dataMedida (Date): data em que ocorreu a mensuração.
 * medidaValor (double): Valor da mensuração. Valor com máscara de duas casas decimais e 8 caracteres no total. Ex.: 123456,78.
 * 
 */

@Entity
public class ValorMedidaUsuario extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = 568800589620367270L;

	@NotNull @OneToOne
	@JoinColumn(name="UDM_ID")
	private Udm udm;
	
	@NotNull @OneToOne
	@JoinColumn(name="TIPOMEDIDA_ID")
	private TipoMedida tipoMedida;
	
	@NotNull @ManyToOne
	@JoinColumn(name="USUARIO_ID")
	private Usuario usuario;
	
	@NotNull @Column(precision=8, scale=2)
	private double medidaValor;
	
	@NotNull @Temporal(TemporalType.TIMESTAMP)
	private Date dataMedida;
	
	@Length(max=200, message="As observacoes devem possuir no máximo 100 caracteres!")
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
	
	public double getMedidaValor() {
		return medidaValor;
	}
	
	public void setMedidaValor(double medidaValor) {
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
}
