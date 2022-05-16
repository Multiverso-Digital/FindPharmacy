package com.abreuretto.findpharmacy;

import java.io.Serializable;

public class ApplicationTips implements Serializable {

	private static final long serialVersionUID = 5L;
	
	
	    private String tips;
		private String imguser;
	    private String nome;
	    private String data;
	    private String tipo;
	    private String refe;
	    private String id;
	    
	    
	    
	    
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getRefe() {
			return refe;
		}
		public void setRefe(String refe) {
			this.refe = refe;
		}
		public String getTipo() {
			return tipo;
		}
		public void setTipo(String tipo) {
			this.tipo = tipo;
		}
		public String getTips() {
			return tips;
		}
		public void setTips(String tips) {
			this.tips = tips;
		}
		public String getImguser() {
			return imguser;
		}
		public void setImguser(String imguser) {
			this.imguser = imguser;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public String getData() {
			return data;
		}
		public void setData(String data) {
			this.data = data;
		}
	    

}
