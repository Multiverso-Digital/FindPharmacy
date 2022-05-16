package com.abreuretto.findpharmacy;

import java.io.Serializable;

public class AppDet implements Serializable {

	private static final long serialVersionUID = 4L;

	
	    private String nome;
		private String id;
	    private String fone;
	    private String rating;
	    private String ende;
	    private String cida;
	    private String pais;
	    private String lat;
	    private String lon;
	    private int fotosconta;
	    private int tipsconta;
	    private String checkin;
	    private String users;
	    private String especial;
	    private String url;
	    private String reference;
	    
	    
	    
	    
		public String getReference() {
			return reference;
		}
		public void setReference(String reference) {
			this.reference = reference;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getFone() {
			return fone;
		}
		public void setFone(String fone) {
			this.fone = fone;
		}
		public String getRating() {
			return rating;
		}
		public void setRating(String rating) {
			this.rating = rating;
		}
		public String getEnde() {
			return ende;
		}
		public void setEnde(String ende) {
			this.ende = ende;
		}
		public String getCida() {
			return cida;
		}
		public void setCida(String cida) {
			this.cida = cida;
		}
		public String getPais() {
			return pais;
		}
		public void setPais(String pais) {
			this.pais = pais;
		}
		public String getLat() {
			return lat;
		}
		public void setLat(String lat) {
			this.lat = lat;
		}
		public String getLon() {
			return lon;
		}
		public void setLon(String lon) {
			this.lon = lon;
		}
		public int getFotosconta() {
			return fotosconta;
		}
		public void setFotosconta(int fotosconta) {
			this.fotosconta = fotosconta;
		}
		public int getTipsconta() {
			return tipsconta;
		}
		public void setTipsconta(int tipsconta) {
			this.tipsconta = tipsconta;
		}
		public String getCheckin() {
			return checkin;
		}
		public void setCheckin(String checkin) {
			this.checkin = checkin;
		}
		public String getUsers() {
			return users;
		}
		public void setUsers(String users) {
			this.users = users;
		}
		public String getEspecial() {
			return especial;
		}
		public void setEspecial(String especial) {
			this.especial = especial;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		} 
	    
	
	
	
	
}
