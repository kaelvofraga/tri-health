package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * Entity implementation class for Entity: Atividade
 * 
 * @brief Classe que representa um hospital.
 * @author Juarez Monteiro
 * 
 * Atributos:
 * - endereco (Endereco): atributo que contem todos os dados relacionados ao endereco do hospital.
 * - nome (String): nome do hospital.
 * 
 * **/

@Entity
public class Hospital extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = 5586495132790418015L;

	@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="ID_ENDERECO")
	private Endereco endereco = new Endereco();
	private String nome = "";
	
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return "Hospital [endereco=" + endereco + ", nome=" + nome + "]";
	}	
}
