package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: MedicamentoUsuario
 *
 */
@Entity
public class AlergiaUsuario extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = -6449844417929624834L;

	@NotNull @OneToMany 
	@JoinColumn(name="ID")
	private  Collection<MedicamentoUsuario> medicamentoUsuarios;
	
	@NotNull @ManyToOne
	@JoinColumn(name="USUARIO_ID")
	private Usuario usuario;
	
	@NotNull 
	private String reacao;
	
	@NotNull @ManyToOne
	private TipoAlergia tipoAlergia;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataPrimeiraOcorrencia;
	
	public AlergiaUsuario() {
		super();
	}

	
	public Collection<MedicamentoUsuario> getMedicamentoUsuarios() {
		return medicamentoUsuarios;
	}


	public void setMedicamentoUsuarios(
			Collection<MedicamentoUsuario> medicamentoUsuarios) {
		this.medicamentoUsuarios = medicamentoUsuarios;
	}


	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getReacao() {
		return reacao;
	}

	public void setReacao(String reacao) {
		this.reacao = reacao;
	}

	public TipoAlergia getTipoAlergia() {
		return tipoAlergia;
	}

	public void setTipoAlergia(TipoAlergia tipoAlergia) {
		this.tipoAlergia = tipoAlergia;
	}

	public Date getDataPrimeiraOcorrencia() {
		return dataPrimeiraOcorrencia;
	}

	public void setDataPrimeiraOcorrencia(Date dataPrimeiraOcorrencia) {
		this.dataPrimeiraOcorrencia = dataPrimeiraOcorrencia;
	}
	
	
}
