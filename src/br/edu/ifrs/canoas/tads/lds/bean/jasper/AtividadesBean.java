package br.edu.ifrs.canoas.tads.lds.bean.jasper;

import java.util.Date;

import br.edu.ifrs.canoas.tads.lds.bean.AtividadeUsuario;

/**
 * Classe para representar o relatório de Atividades, conténdo as informações necessarias para gerar o Report em PDF

 * @author Luciano Acosta

 */

public class AtividadesBean {
	
	private String nome;
		
	private String atividade;
	
	private String tipo;
	
	private String distancia;
	
	private String data;
	
	private String minutos;
	
	private String calorias;

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

	public String getCalorias() {
		return calorias;
	}

	public void setCalorias(String calorias) {
		this.calorias = calorias;
	}
	
	
	/**
	 * Método que converte o tipo AtividadeUsuario em AtividadesBean para envio de um List<AtividadesBean>
	 * como datasource pro jasper
	 * */
	public AtividadesBean conversor(AtividadeUsuario au){
		AtividadesBean atividade = new AtividadesBean();
		atividade.setNome(au.getUsuario().getNome());
		atividade.setAtividade(au.getAtividade().getDescricao());
		atividade.setTipo(au.getAtividade().getTipoAtividade().getNome());
		atividade.setDistancia(au.getDistancia()+"");
		atividade.setData(au.getDataFim().toString());
		atividade.setMinutos(au.getDuracao()+"");
		atividade.setCalorias(au.getCalorias()+"");
		return atividade;
		
	}
}
