package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
/**
 * @author: Miromar Jose de Lima 
 * 
 * Classe que contem os atributos e metodos de ExameCardiologico.
 * 
 *          Data:01/07/2015
 */
@Entity
public class ExameCardiologico extends BaseEntity<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@OneToOne
	@JoinColumn(name="USUARIO_ID")
	private Usuario usuario;


	@Temporal(TemporalType.TIMESTAMP)
	private Date dataExame;

	@Lob
	private byte[] arquivoLaudo;

	private String nota;

	@OneToOne
	@JoinColumn(name = "ID_TIPOEXAME")
	private TipoExame tipoExame;

	
	@NotNull
	@OneToOne
	@JoinColumn(name = "ID_MEDICO")
	private Medico medico;

	
	

	public TipoExame getTipoExame() {
		return tipoExame;
	}

	public void setTipoExame(TipoExame tipoExame) {
		this.tipoExame = tipoExame;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Date getDataExame() {
		return dataExame;
	}

	public byte[] getArquivoLaudo() {
		return arquivoLaudo;
	}

	public String getNota() {
		return nota;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setDataExame(Date dataExame) {
		this.dataExame = dataExame;
	}

	public void setArquivoLaudo(byte[] arquivoLaudo) {
		this.arquivoLaudo = arquivoLaudo;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

}