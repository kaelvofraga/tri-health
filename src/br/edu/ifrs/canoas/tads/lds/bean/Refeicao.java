package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
public class Refeicao extends BaseEntity<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4676157611979535684L;

	public Refeicao() {
		super();
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "USUARIO_ID")
	private Usuario usuario;

	@NotNull(message = "A data da refeição é obrigatória.")
	@Temporal(TemporalType.TIMESTAMP)
	private Date refeicaoData;

	@Length(max = 500, message = "A Observação deve ter no máximo 500 caractéres.")
	private String observacao;

	@Transient
	private List<RefeicaoAlimento> refeicaoAlimentos = new ArrayList<RefeicaoAlimento>();
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getRefeicaoData() {
		return refeicaoData;
	}

	public void setRefeicaoData(Date refeicaoData) {
		this.refeicaoData = refeicaoData;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public List<RefeicaoAlimento> getRefeicaoAlimentos() {
		return refeicaoAlimentos;
	}

	public void setRefeicaoAlimentos(List<RefeicaoAlimento> refeicaoAlimentos) {
		this.refeicaoAlimentos = refeicaoAlimentos;
	}
	
	public String getCalorias(){
		String retorno = "0 Calorias";
		
		if(this.getRefeicaoAlimentos() != null && this.getRefeicaoAlimentos().size() > 0){
			double calorias = 0;
			for (RefeicaoAlimento refeicaoAlimento : this.getRefeicaoAlimentos()) {
				if(refeicaoAlimento.getAlimento() != null){
					double divisor = refeicaoAlimento.getPesoEmGramasOuMl() / 100;
					calorias = refeicaoAlimento.getAlimento().getCaloriasPorCemGrOuMl() * divisor;
				}
			}				
			retorno = calorias + " Calorias";
		}
		
		return retorno;
	}

}
