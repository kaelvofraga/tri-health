package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 * @author: Rodrigo Noll
 * @author: Miromar J. Lima
 * Proposito da Classe: Classe que se relacionará com as demais classes do sistema.
 *         
 */
/**Miromar contribuiu com a colocação de alguns atributos e relacionamento com a classe Endereco
 * Entity implementation class for Entity: Usuario
 * Data: 13/05/2015
 */
@Entity
public class Usuario extends BaseEntity<Long> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8888460954413155909L;

	@NotNull
	@Email
	private String email;

	@NotNull
	private String senha;

	@NotNull
	private String nome;

	@NotNull
	private String sobrenome;

	private String apelido;

	private String telefone;

	private String celular;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataNasc;

	private String genero;

	private String tipoSanguineo;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_ENDERECO")
	private Endereco endereco = new Endereco();

	/*
	 * Código foi comentado durante teste na tentativa de buscar solução para
	 * idioma de usuário
	 */

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "IDIOMA_USUARIO", joinColumns = @JoinColumn(name = "ID_USUARIO"), inverseJoinColumns = @JoinColumn(name = "ID_IDIOMA"))
	private List<Idioma> idiomas = new ArrayList<>();

	public Usuario() {
		super();
	}

	public String getApelido() {
		return apelido;
	}

	public String getCelular() {
		return celular;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public String getEmail() {
		return this.email;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public String getGenero() {
		return genero;
	}

	/**
	 * @return the idiomas
	 */
	public List<Idioma> getIdiomas() {
		return idiomas;
	}

	public String getNome() {
		return nome;
	}

	public String getSenha() {
		return this.senha;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getTipoSanguineo() {
		return tipoSanguineo;
	}

	public void onReorder() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"List Reordered", null));
	}

	public void onSelect(SelectEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Item Selecionado", event.getObject().toString()));
	}

	public void onUnselect(UnselectEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Item não Selecionado", event.getObject().toString()));
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public void setIdiomas(List<Idioma> idiomas) {
		this.idiomas = idiomas;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setTipoSanguineo(String tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}
}
