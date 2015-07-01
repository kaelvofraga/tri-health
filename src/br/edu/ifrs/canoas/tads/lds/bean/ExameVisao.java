package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class ExameVisao extends BaseEntity<Long> implements Serializable   {
	private static final long serialVersionUID = -6833487765093285522L;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	@ManyToOne
	@JoinColumn(name="USUARIO_ID")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name="MEDICO_ID")
	private Medico medico;
	
	@OneToMany
	@JoinColumn
	private List<Grau> graus;
    	
	public ExameVisao() {
		super();
	}
	
	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Grau> getGraus() {
		return graus;
	}

	public void setGraus(List<Grau> graus) {
		this.graus = graus;
	}
}