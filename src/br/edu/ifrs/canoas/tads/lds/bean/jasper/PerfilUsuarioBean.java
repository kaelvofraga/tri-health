package br.edu.ifrs.canoas.tads.lds.bean.jasper;

import java.util.Date;

import br.edu.ifrs.canoas.tads.lds.bean.Idioma;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;

public class PerfilUsuarioBean {

	private String nome;
	
	private String dataEmissao = new Date().toString();

	private String nascimento;
	
	private String nacionalidade;
	
	private String genero;
	
	private String tipoSanguineo;
	
	private String telefone;
	
	private String celular;
	
	private String email;
	
	private String idiomas;
		
	private String endereco;
	
	private String cidade;
	
	private String estado;
	
	private String pais;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNascimento() {
		return nascimento;
	}

	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getTipoSanguineo() {
		return tipoSanguineo;
	}

	public void setTipoSanguineo(String tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdiomas() {
		return idiomas;
	}

	public void setIdiomas(String idiomas) {
		this.idiomas = idiomas;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
	
	public PerfilUsuarioBean conversor(Usuario usuario){
		
		PerfilUsuarioBean pub = new PerfilUsuarioBean();
		
		pub.setNome(usuario.getNome()+" "+usuario.getSobrenome()+" ("+usuario.getApelido()+")");
		pub.setNascimento(usuario.getDataNasc().toString());
		pub.setCelular(usuario.getCelular());
		pub.setEmail(usuario.getEmail());
		pub.setCidade(usuario.getEndereco().getCidade().getNome());
		pub.setEndereco(usuario.getEndereco().getLogradouro()+","+usuario.getEndereco().getCep());
		pub.setGenero(usuario.getGenero());
		pub.setTelefone(usuario.getTelefone());
		pub.setEstado(usuario.getEndereco().getCidade().getUf().getNome());
		pub.setPais(usuario.getEndereco().getCidade().getUf().getPais().getNome());
		pub.setNacionalidade(usuario.getEndereco().getCidade().getUf().getPais().getNacionalidade());
		String idiomas = "";
		for(Idioma idioma : usuario.getIdiomas()){
			idiomas += idioma.getNome()+" ";
		}
		pub.setIdiomas(idiomas);

		return pub;
	}
	
}
