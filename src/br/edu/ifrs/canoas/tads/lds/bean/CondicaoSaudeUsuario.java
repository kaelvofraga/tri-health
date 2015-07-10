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

import org.hibernate.validator.constraints.Length;

/**
 * Entity implementation class for Entity: CondicaoSaudeUsuario
 *
 * @brief Classe de relacionamento
 * @author Luana Gomes
 * @since 06/07/2015
 * 
 * Atributos:
 * - usuario (Usuario): usuario relacionado.
 * - status (StatusSaude): status da condicao de saude que pode ser: atual, intermitente ou passado.
 * - dataInicio (Date): data de início de determinada condicao.
 * - dataFim (Date): data de término da condicao.
 * - descricao (String): indica a condicao de saude
 * - solucao (String): indica a solucao do usuario para a condicao de saude
 * **/

@Entity
public class CondicaoSaudeUsuario extends BaseEntity<Long> implements Serializable {
		
	private static final long serialVersionUID = 113670808978537917L;
	
	@NotNull @ManyToOne
	@JoinColumn(name="USUARIO_ID")
	private Usuario usuario;
	
	@OneToOne
	@JoinColumn(name="STATUS_ID")
	private StatusSaude status;
	
	@NotNull @Temporal(TemporalType.TIMESTAMP)
	private Date dataInicio;
		
	@NotNull @Temporal(TemporalType.TIMESTAMP)
	private Date dataFim;
	 
	private String descricao;
	 
	private String solucao;
	
	public CondicaoSaudeUsuario() {
		super();
	}
	
	/*GETTERS E SETTERS*/

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public StatusSaude getStatus() {
		return status;
	}

	public void setStatus(StatusSaude status) {
		this.status = status;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSolucao() {
		return solucao;
	}

	public void setSolucao(String solucao) {
		this.solucao = solucao;
	}
	
}