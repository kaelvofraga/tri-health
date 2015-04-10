package br.edu.ifrs.canoas.tads.lds.bean; 

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.edu.ifrs.canoas.tads.lds.bean.Atividade;
import br.edu.ifrs.canoas.tads.lds.bean.BaseEntity;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;

/**
 * Entity implementation class for Entity: AtividadeUsuario
 *
 */
@Entity
public class AtividadeUsuario extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = -7000579623219744087L;

	@NotNull @ManyToOne 
	@JoinColumn(name="ATIVIDADE_ID")
	private  Atividade atividade;
	
	@NotNull @ManyToOne
	@JoinColumn(name="USUARIO_ID")
	private Usuario usuario;
	
	@NotNull 
	private String notas;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInicio;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFim;
	
	@NotNull 
	private double distancia;
	
	public AtividadeUsuario() {
		super();
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public double getDistancia() {
		return distancia;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}
	
}