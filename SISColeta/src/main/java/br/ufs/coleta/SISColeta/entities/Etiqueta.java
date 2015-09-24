package br.ufs.coleta.SISColeta.entities;

import java.io.Serializable;

public class Etiqueta implements Serializable{

	private String codColecao;
	private String especie;
	private String Rio;
	private String familia;
	private Integer quantidade;
	private String localidade;
	private String coordenada;
	private String codColeta;
	private String coletor;
	private String determinador;
	
	public Etiqueta(){
	}

	public Etiqueta(String codColeta) {
		super();
		this.codColecao = codColeta;
	}

	public String getCodColecao() {
		return codColecao;
	}

	public void setCodColecao(String codColecao) {
		this.codColecao = codColecao;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getRio() {
		return Rio;
	}

	public void setRio(String rio) {
		Rio = rio;
	}

	public String getFamilia() {
		return familia;
	}

	public void setFamilia(String familia) {
		this.familia = familia;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getCoordenada() {
		return coordenada;
	}

	public void setCoordenada(String coordenada) {
		this.coordenada = coordenada;
	}

	public String getCodColeta() {
		return codColeta;
	}

	public void setCodColeta(String codColeta) {
		this.codColeta = codColeta;
	}

	public String getDeterminador() {
		return determinador;
	}

	public void setDeterminador(String determinador) {
		this.determinador = determinador;
	}

	public String getColetor() {
		return coletor;
	}

	public void setColetor(String coletor) {
		this.coletor = coletor;
	}
	
	
	
}
