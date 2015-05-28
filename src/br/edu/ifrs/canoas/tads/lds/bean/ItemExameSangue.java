package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class ItemExameSangue extends BaseEntity<Long> implements Serializable{

	/**
	 * @author André ficht
	 * Classe para os resultados dos exames
	 */
	private static final long serialVersionUID = 6438502909147720009L;
	
	
	private UsuarioSangue usuarioSangue;
	
	private TipoAnalise tipoAnalise;
	
	private String resultado;
	
	

}
