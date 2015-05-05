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
 * Entity implementation class for Entity: MedicamentoUsuario
 *
 */
@Entity
public class MedicamentoUsuario extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = 1754848302536341079L;

	@NotNull @OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="MEDICAMENTO_ID")
	private Medicamento medicamento;
	
	@NotNull @ManyToOne
	@JoinColumn(name="USUARIO_ID")
	private Usuario usuario;
	
	private String dosagem;
	
	private String frequencia;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInicioTratamento;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFimTratamento;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date dataConsulta;
	
	private String situacao;
	
	private String descricao;
	
	private String nota;
	
	
	public MedicamentoUsuario() {
		super();
	}

	
	
	
	/*GETTERS & SETTERS*/

	public Medicamento getMedicamento() {
		return medicamento;
	}


	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public String getDosagem() {
		return dosagem;
	}


	public void setDosagem(String dosagem) {
		this.dosagem = dosagem;
	}


	public String getFrequencia() {
		return frequencia;
	}


	public void setFrequencia(String frequencia) {
		this.frequencia = frequencia;
	}


	public Date getDataInicioTratamento() {
		return dataInicioTratamento;
	}


	public void setDataInicioTratamento(Date dataInicioTratamento) {
		this.dataInicioTratamento = dataInicioTratamento;
	}


	public Date getDataFimTratamento() {
		return dataFimTratamento;
	}


	public void setDataFimTratamento(Date dataFimTratamento) {
		this.dataFimTratamento = dataFimTratamento;
	}


	public String getSituacao() {
		return situacao;
	}


	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getNota() {
		return nota;
	}


	public void setNota(String nota) {
		this.nota = nota;
	}


	public Date getDataConsulta() {
		return dataConsulta;
	}


	public void setDataConsulta(Date dataConsulta) {
		this.dataConsulta = dataConsulta;
	}
}
