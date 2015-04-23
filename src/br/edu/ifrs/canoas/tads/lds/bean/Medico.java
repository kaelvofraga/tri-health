package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Medico extends BaseEntity<Long> implements Serializable{
	

	private static final long serialVersionUID = -6493053493498926375L;

	@NotNull
	private String nome;
	
	@NotNull
	private String crm;

	@OneToMany
	@JoinColumn(name="ID_MEDICO")
	private Collection<EspecialidadeMedica> especialidades = new ArrayList<EspecialidadeMedica>();
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public Collection<EspecialidadeMedica> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(Collection<EspecialidadeMedica> especialidades) {
		this.especialidades = especialidades;
	}
	
	
}
