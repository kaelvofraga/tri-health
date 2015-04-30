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
public class ExameUrina extends BaseEntity<Long> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8539097274270004334L;

	@NotNull @ManyToOne
    @JoinColumn(name="ID_USUARIO") 
	private Usuario usuario;
	
	@NotNull @OneToOne
    @JoinColumn(name="ID_TIPOEXAMEURINA")
	private TipoExameUrina tipoExameUrina;
	
	@NotNull
	private String resultadoExameUrina;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataExameUrina;
	private String observacao;
	
	public ExameUrina() {
		super();
	}

	public ExameUrina(Usuario usuario, TipoExameUrina tipoExameUrina, String resultadoExameUrina,
			Date dataExameUrina, String observacao) {
		super();
		this.usuario = usuario;
		this.tipoExameUrina = tipoExameUrina;
		this.resultadoExameUrina=resultadoExameUrina;
		this.dataExameUrina = dataExameUrina;
		this.observacao = observacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public TipoExameUrina getTipoExameUrina() {
		return tipoExameUrina;
	}

	public void setTipoExameUrina(TipoExameUrina tipoExameUrina) {
		this.tipoExameUrina = tipoExameUrina;
	}

	public String getResultadoExameUrina() {
		return resultadoExameUrina;
	}

	public void setResultadoExameUrina(String resultadoExameUrina) {
		this.resultadoExameUrina = resultadoExameUrina;
	}

	public Date getDataExameUrina() {
		return dataExameUrina;
	}

	public void setDataExameUrina(Date dataExameUrina) {
		this.dataExameUrina = dataExameUrina;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}
