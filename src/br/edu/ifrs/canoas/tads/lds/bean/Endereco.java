package br.edu.ifrs.canoas.tads.lds.bean;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Endereco extends BaseEntity<Long>{
	
	@OneToOne
    @JoinColumn(name="ID_CIDADE")
	private Cidade cidade;
	private String cep;
	private String logradouro;
	private Integer numero;
	private String complemento;
	
	

	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
}
