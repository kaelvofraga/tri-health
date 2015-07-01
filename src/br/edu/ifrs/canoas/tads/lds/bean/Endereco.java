package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * 
 * @author: Miromar J. Lima
 * Proposito da Classe: Compo o endereço do usuário 
 *
 * @author: Juarez Monteiro
 * Proposito da Alteração: Ajuste do bean para se adequar ao caso de uso Listar Hospitais
 */

@Entity
public class Endereco extends BaseEntity<Long> implements Serializable {
	
	
	private static final long serialVersionUID = 1445737913096083057L;
	/**
	 * Estabelecer relacionamento com a tabela de cidade
	 */
	
	
	@OneToOne(cascade=CascadeType.ALL)    
	@JoinColumn(name="ID_CIDADE")
	private Cidade cidade = new Cidade();
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ID_TELEFONE", nullable = true)
	private Telefone telefone;
	
	private String cep  = "";
	private String logradouro = "";
	private Integer numero = 0;
	private String complemento = "";
	private String latitude = "";
	private String longitude = "";
	
	
	public Telefone getTelefone() {
		return telefone;
	}
	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

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
		
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 
	@Override
	public String toString() {
		return "Endereco [cep=" + cep + ", logradouro=" + logradouro
				+ ", numero=" + numero + ", complemento=" + complemento + "]";
	}
	*/
	
	@Override
	public String toString() {
		return "Endereco [cidade=" + cidade + ", telefone=" + telefone
				+ ", cep=" + cep + ", logradouro=" + logradouro + ", numero="
				+ numero + ", complemento=" + complemento + ", latitude="
				+ latitude + ", longitude=" + longitude + "]";
	}
}
