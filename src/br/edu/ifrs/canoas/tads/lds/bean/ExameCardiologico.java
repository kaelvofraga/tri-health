package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class ExameCardiologico  extends BaseEntity<Long> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Usuario usuario; 
	
	@Temporal(TemporalType.TIMESTAMP)
	
	private Date dataExame;
		
	private byte[] arquivoLaudo;
	
	private String nota;	
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "EXAMECARDIOLOGICO_MEDICO", joinColumns = @JoinColumn(name = "ID_EXAMECARDIOLOGICO"), inverseJoinColumns = @JoinColumn(name = "ID_MEDICO"))
	private List<Medico> medicos = new ArrayList<>();
		@Override
	public String toString() {
		return "ExameCardiologico [usuario=" + usuario + ", dataExame="
				+ dataExame + ", arquivoLaudo=" + Arrays.toString(arquivoLaudo)
				+ ", nota=" + nota + ", medicos=" + medicos + ", tipoExames="
				+ tipoExames + "]";
	}

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "EXAMECARDIOLOGICO_TIPOEXAME", joinColumns = @JoinColumn(name = "ID_EXAMECARDIOLOGICO"), inverseJoinColumns = @JoinColumn(name = "ID_TIPOEXAME"))
	private List<TipoExame> tipoExames = new ArrayList<>();
		
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


	public List<Medico> getMedicos() {
		return medicos;
	}

	public List<TipoExame> getTipoExames() {
		return tipoExames;
	}

	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}

	public void setTipoExames(List<TipoExame> tipoExames) {
		this.tipoExames = tipoExames;
	}

 

}
