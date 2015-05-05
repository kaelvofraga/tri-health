package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@NamedQueries({
	@NamedQuery(name="todasConsultas", query = "SELECT c FROM Consulta c")
})
@Entity
public class Consulta extends BaseEntity<Long> implements Serializable  {
	
	private static final long serialVersionUID = -6833487765093285536L;

	@NotNull 
	private String sintomas;
	@NotNull 
	private String diagnostico;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	/* Aplicação dos Medicamentos (Exemplo de bidirecional)
	 * @OneToMany(fetch = FetchType.EAGER, mappedBy="usuario", cascade = CascadeType.ALL)
    private List<Consulta> consultas;*/
    
	
	public Consulta() {
		super();
	}

	public String getSintomas() {
		return sintomas;
	}

	public void setSintomas(String sintomas) {
		this.sintomas = sintomas;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}
	
}
