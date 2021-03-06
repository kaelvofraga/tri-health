package br.edu.ifrs.canoas.tads.lds.bean;
import java.io.Serializable;
import javax.persistence.Entity;

/**
 * Entity implementation class for Entity: Atividade
 * 
 * @brief Classe que representa um telefone.
 * @author Juarez Monteiro
 * 
 * Atributos:
 * - ddd (String): ddd (c�digo de �rea) do telefone.
 * - numero (String): n�mero do telefone.
 * 
 * **/

@Entity
public class Telefone extends BaseEntity<Long> implements Serializable {
	
	private static final long serialVersionUID = 1125008604069590639L;
	
	private String ddd = "";
	private String numero = "";
	
	
	public String getDdd() {
		return ddd;
	}
	
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	@Override
	public String toString() {
		return "Telefone [ddd=" + ddd + ", numero=" + numero + "]";
	}
}
