package br.edu.ifrs.canoas.tads.lds.bean; 

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.edu.ifrs.canoas.tads.lds.bean.Atividade;
import br.edu.ifrs.canoas.tads.lds.bean.BaseEntity;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;

/**
 * Entity implementation class for Entity: AtividadeUsuario
 *
 * @brief Classe de relacionamento entre atividade a usuário.
 * @author Kael Fraga
 * @since 07/05/2015
 * 
 * Atributos:
 * - atividade (Atividade): atividade relacionada.
 * - usuario (Usuario): usuario relacionado.
 * - notas (String): observações e detalhes, máximo 500 caracteres.
 * - dataInicio (Date): data de início da atividade.
 * - dataFim (Date): data de término da atividade.
 * - distancia (double): distãncia em metros percorrida durante execução da atividade. Valor máximo permitido é 99999.9999 metros.
 * - duracao (long): duração da atividade em minutos.
 * - calorias (double): calorias queimadas durante a atividade.
 * 
 * **/
@Entity
public class AtividadeUsuario extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = -7000579623219744087L;

	@NotNull @ManyToOne 
	@JoinColumn(name="ATIVIDADE_ID")
	private  Atividade atividade = new Atividade();
	
	@NotNull @ManyToOne
	@JoinColumn(name="USUARIO_ID")
	private Usuario usuario;
	
	@NotNull @Length(max=500, message="As notas devem possuir no máximo 144 caracteres!") 
	private String notas;
	
	@NotNull @Temporal(TemporalType.TIMESTAMP)
	private Date dataInicio;
		
	@NotNull @Temporal(TemporalType.TIMESTAMP)
	private Date dataFim;
	
	@NotNull 
	@DecimalMax(value= "99999.9999", message = "Distâncias maiores que 99999.9999 não são aceitas!")
	private double distancia;
	
	@Transient
	private long duracao;
	
	@Transient 
	private double calorias;
	
	public AtividadeUsuario() {
		super();
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public double getDistancia() {
		return distancia;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}

	public long getDuracao() {
		return duracao;
	}

	public void setDuracao(long duracao) {
		this.duracao = duracao;
	}

	public double getCalorias() {
		return calorias;
	}

	public void setCalorias(double calorias) {
		this.calorias = calorias;
	}
}