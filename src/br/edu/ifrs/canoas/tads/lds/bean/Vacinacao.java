package br.edu.ifrs.canoas.tads.lds.bean;

import br.edu.ifrs.canoas.tads.lds.bean.BaseEntity;

import java.io.Serializable;
import java.lang.Double;
import java.lang.String;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

/**
 * Entity implementation class for Entity: Vacinacao
 *
 */
@Entity

public class Vacinacao extends BaseEntity<Long> implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5273291259253392195L;
	
	
	@Length(min = 0, message = "Campo Obrigatório.")
	private String efeitoColateral;
	
	@Length(min = 0, message = "Campo Obrigatório.")
	private String descricao;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataVacinacao;
	
	@Range(min = 0)
	private Double doseVacinacao;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "USUARIO_ID")
	private Usuario usuario;
	
	public Vacinacao() {
		super();
	}   
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}   
	public Date getDataVacinacao() {
		return this.dataVacinacao;
	}

	public void setDataVacinacao(Date dataVacinacao) {
		this.dataVacinacao = dataVacinacao;
	}   
	public Double getDoseVacinacao() {
		return this.doseVacinacao;
	}

	public void setDoseVacinacao(Double doseVacinacao) {
		this.doseVacinacao = doseVacinacao;
	}   
	public String getEfeitoColateral() {
		return this.efeitoColateral;
	}

	public void setEfeitoColateral(String efeitoColateral) {
		this.efeitoColateral = efeitoColateral;
	}
	
	public String getDataVacinacaoStr(){
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = formatter.format(this.getDataVacinacao());
		return dataFormatada;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
   
}
