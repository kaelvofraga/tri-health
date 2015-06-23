package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.edu.ifrs.canoas.tads.lds.bean.Composicao;
import br.edu.ifrs.canoas.tads.lds.bean.BaseEntity;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;

/***
 * 
 * @author Pablo Diehl
 * @version 23/06/2015
 * 
 * @brief Classe de relacionamento entre Composicao e Usuario
 * #Atributos
 *  - Data: Representa a data do registro de Composição Corporal.
 *  - Notas: String contendo observações sobre o registro.
 *  - Valor: Representação percentual de uma determinada composição corporal.
 *	
 */
public class ComposicaoUsuario extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = -3342715828920539423L;
	
	@NotNull @ManyToMany
	@JoinColumn(name="COMPOSICAO_ID")
	private Composicao composicao = new Composicao();
	
	@NotNull @ManyToMany
	@JoinColumn(name="USUARIO_ID")
	private Usuario usuario = new Usuario();
	
	@NotNull
	private Date data;
	
	@NotNull
	private String notas;
	
	@NotNull
	private double valor;

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Composicao getComposicao() {
		return composicao;
	}

	public void setComposicao(Composicao composicao) {
		this.composicao = composicao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
