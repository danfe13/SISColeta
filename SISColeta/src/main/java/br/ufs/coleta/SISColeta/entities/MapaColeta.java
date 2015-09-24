package br.ufs.coleta.SISColeta.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MapaColeta implements Serializable{

	private String latitude;
	private String longitude;
	private Integer quantidade;
	private String local;
	private String data;
	
	public MapaColeta(){
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	public void setLatitude(Double grau, Double minuto, Double segundos, Character direcao){
		double lat = grau * 1 + minuto/60 + segundos/3600;
		if(direcao == 's'){
			lat *= -1; 
		}
		this.latitude = String.valueOf(lat);
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public void setLongitude(Double grau, Double minuto, Double segundos, Character direcao){
		double lon = grau * 1 + minuto/60 + segundos/3600;
		if(direcao == 'w' ){
			lon *= -1; 
		}
		this.longitude = String.valueOf(lon);
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public void setData(Date data){
		SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
		this.data = dt.format(data);
	}
	

}
