package br.edu.ifrs.canoas.tads.lds.bean.jasper;

import java.util.Date;

public class AtividadesBean {
	
	private String nome;
	
	private String dataEmissao = new Date().toString();
	
	private String atividade;
	
	private String tipo;
	
	private String distancia;
	
	private String data;
	
	private String minutos;
	
	private String caloriasQueimadas;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAtividade() {
		return atividade;
	}

	public void setAtividade(String atividade) {
		this.atividade = atividade;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDistancia() {
		return distancia;
	}

	public void setDistancia(String distancia) {
		this.distancia = distancia;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getMinutos() {
		return minutos;
	}

	public void setMinutos(String minutos) {
		this.minutos = minutos;
	}

	public String getCaloriasQueimadas() {
		return caloriasQueimadas;
	}

	public void setCaloriasQueimadas(String caloriasQueimadas) {
		this.caloriasQueimadas = caloriasQueimadas;
	}
}
