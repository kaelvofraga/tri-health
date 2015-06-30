package br.edu.ifrs.canoas.tads.lds.bean;

import br.edu.ifrs.canoas.tads.lds.bean.BaseEntity;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
public class Exame extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = 9127288997447808922L;

	@NotNull
	private String nome;
	@Length(max = 200, message = "A descrição do exame deve ter no máximo 300 caracteres.")
	private String descricao;

	public Exame() {
		super();
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Transient
	private List<ExameCampos> examesCampos = new ArrayList<ExameCampos>();

	@Transient
	private List<ExameCampos> examesCamposRemovidos = new ArrayList<ExameCampos>();

	public void removeExameCampos(ExameCampos exameCampos) {
		if (exameCampos == null)
			return;

		if (this.getExamesCampos().contains(exameCampos)) {
			this.getExamesCampos().remove(exameCampos);
			
			if(exameCampos.getId() > 0)
				this.examesCamposRemovidos.add(exameCampos);
		}
	}

	public List<ExameCampos> getexamesCamposRemovidos() {
		return this.examesCamposRemovidos;
	}

	public List<ExameCampos> getExamesCampos() {
		return this.examesCampos;
	}

	public void setExamesCampos(List<ExameCampos> lista) {
		if (lista != null)
			this.examesCampos = lista;
	}
}
