package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class UsuarioExame extends BaseEntity<Long> implements Serializable{

	/**
	 * @author André ficht
	 * Classe intermediária de Usuário e Item de Exame Sangue
	 */
	private static final long serialVersionUID = 6965798775639734052L;
	
	@NotNull 
	@ManyToOne
    @JoinColumn(name="ID_USUARIO") 
	private Usuario usuario;
	
	@NotNull
	@OneToMany (mappedBy="usuarioExame",fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private Collection<ItemExameSangue> itensExame;
		
	@Temporal(TemporalType.TIMESTAMP)//(TemporalType.DATE)
	private Date data;
	
	private String observacao;
	
//construtores	
	public UsuarioExame(Usuario usuario,
			Collection<ItemExameSangue> itensExame, Date data, String observacao) {
		super();
		this.usuario = usuario;
		this.itensExame = itensExame;
		this.data = data;
		this.observacao = observacao;
	}

	public UsuarioExame() {
		super();
	}
	
//getters e setters
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Collection<ItemExameSangue> getItensExame() {
		return itensExame;
	}

	public void setItensExame(Collection<ItemExameSangue> itensExame) {
		this.itensExame = itensExame;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	

	
	

}
