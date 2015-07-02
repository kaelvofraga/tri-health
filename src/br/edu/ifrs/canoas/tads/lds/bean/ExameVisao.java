package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class ExameVisao extends BaseEntity<Long> implements Serializable   {
	private static final long serialVersionUID = -6833487765093285522L;

	@NotNull 
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	/*
	 * Ao se adicionar NotNull às dependencias, o cadastro gera um erro.
	*/ 
	@ManyToOne
	@JoinColumn(name="USUARIO_ID")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name="MEDICO_ID")
	private Medico medico;
	
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="GRAUS_ID")
	private List<Grau> graus = new ArrayList<>();
		
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

	/*
	 * info usada na listagem de Exame Visao
	 */
	public String getDisfuncaoString() {
		String out = "";
		if(graus != null && !graus.isEmpty()){
			for (Grau grau : graus) {
				out += grau.getTipoGrau().getDescricao() + "... ";
			}			
		}
		return out;
	}
	
	/*
	 * Grau Esquerdo usada na listagem de Exame Visao
	 */
	public String getEsquerdoString() {
		String out = "";
		if(graus != null && !graus.isEmpty()){
			for (Grau grau : graus) {
				out += grau.getEsquerdo() + "... ";
			}			
		}
		return out;
	}
	
	/*
	 * Grau direito usada na listagem de Exame Visao
	 */
	public String getDireitoString() {
		String out = "";
		if(graus != null && !graus.isEmpty()){
			for (Grau grau : graus) {
				out += grau.getDireito() + "... ";
			}			
		}
		return out;
	}

	public void setGraus(List<Grau> graus) {
		this.graus = graus;
	}
}