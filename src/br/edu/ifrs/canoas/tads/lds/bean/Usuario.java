package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

/**
 * Entity implementation class for Entity: Usuario
 *
 */
@Entity
public class Usuario extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = 6262524988798723388L;

		
	@NotNull @Email 
	private String email;
	
	@NotNull
	private String senha;
	
	@NotNull
	private String nome;
	
	@NotNull 
	private String sobrenome;
	
	private String apelido;
	
	private Date dataNasc;
	
	
	@OneToOne
    @JoinColumn(name="ID_ENDERECO")
	private Endereco endereco;
	
	    
	
	
	public String getApelido() {
		return apelido;
	}


	public void setApelido(String apelido) {
		this.apelido = apelido;
	}


	
	public Date getDataNasc() {
		return dataNasc;
	}


	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}


	public Endereco getEndereco() {
		return endereco;
	}


	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}


	public Usuario() {
		super();
	}
	
	
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}   
	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
}
