package br.edu.ifrs.canoas.tads.lds.bean;

import br.edu.ifrs.canoas.tads.lds.bean.BaseEntity;
import br.edu.ifrs.canoas.tads.lds.bean.Exame;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
public class ExameCampos extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = -1867964589580907055L;
	
	@NotNull
	private String nomeCampo;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "EXAME_ID")
	private Exame exame;
	
	public ExameCampos() {
		super();
	}   
	public String getNomeCampo() {
		return this.nomeCampo;
	}

	public void setNomeCampo(String nomeCampo) {
		this.nomeCampo = nomeCampo;
	}   
	public Exame getExame() {
		return this.exame;
	}

	public void setExame(Exame exame) {
		this.exame = exame;
	}    
}
