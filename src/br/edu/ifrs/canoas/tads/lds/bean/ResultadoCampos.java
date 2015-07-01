package br.edu.ifrs.canoas.tads.lds.bean;

import br.edu.ifrs.canoas.tads.lds.bean.BaseEntity;
import br.edu.ifrs.canoas.tads.lds.bean.ExameCampos;
import br.edu.ifrs.canoas.tads.lds.bean.Resultado;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: ResultadoCampos
 *
 */
@Entity

public class ResultadoCampos extends BaseEntity<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 879174447762189602L;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "RESULTADO_ID")
	private Resultado resultado;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "EXAMECAMPOS_ID")
	private ExameCampos exameCampos;

	private String resultadoValor;
	
	public ResultadoCampos() {
		super();
	}   
	public Resultado getResultado() {
		return this.resultado;
	}

	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}   
	public ExameCampos getExameCampos() {
		return this.exameCampos;
	}

	public void setExameCampos(ExameCampos exameCampos) {
		this.exameCampos = exameCampos;
	}
	public String getResultadoValor() {
		return resultadoValor;
	}
	public void setResultadoValor(String resultadoValor) {
		this.resultadoValor = resultadoValor;
	}   
}
