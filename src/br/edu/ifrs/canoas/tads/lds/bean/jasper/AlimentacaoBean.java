package br.edu.ifrs.canoas.tads.lds.bean.jasper;

import java.util.Date;

public class AlimentacaoBean {

	private String nome;
	
	private String dataEmissao = new Date().toString();
	
	private String tipoAlimento;
	
	private String alimento;
	
	private String quantidade;
	
	private String calorias;
	
	private String totalCalorias;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipoAlimento() {
		return tipoAlimento;
	}

	public void setTipoAlimento(String tipoAlimento) {
		this.tipoAlimento = tipoAlimento;
	}

	public String getAlimento() {
		return alimento;
	}

	public void setAlimento(String alimento) {
		this.alimento = alimento;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public String getCalorias() {
		return calorias;
	}

	public void setCalorias(String calorias) {
		this.calorias = calorias;
	}

	public String getTotalCalorias() {
		return totalCalorias;
	}

	public void setTotalCalorias(String totalCalorias) {
		this.totalCalorias = totalCalorias;
	}

}
