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
	
	private int tipoCampo;
	
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
	
	//0 = String 1 = num�rico
	public int getTipoCampo() {
		return this.tipoCampo;
	}
	
	public String getTipoCampoStr(){
		return this.getTipoCampo() == 0 ? "ALFANUM�RICO" : "N�MERICO";
	}

	public void setTipoCampo(Integer tipoCampo) {
		this.tipoCampo = tipoCampo;
	}   
}
