package com.abreuretto.findpharmacy;

import java.io.Serializable;

public class FotoFS implements Serializable {

	private static final long serialVersionUID = 3L;
	

	    private String img_user;
	    private String img_place;
	    private String name_foto;
	    private String name_user;
	    private String data;
	    private int total; 
	    private String fotogrande;

		public String getFotogrande() {
			return fotogrande;
		}
		public void setFotogrande(String fotogrande) {
			this.fotogrande = fotogrande;
		}
		public int getTotal() {
			return total;
		}
		public void setTotal(int total) {
			this.total = total;
		}
		public String getImg_user() {
			return img_user;
		}
		public void setImg_user(String img_user) {
			this.img_user = img_user;
		}
		public String getImg_place() {
			return img_place;
		}
		public void setImg_place(String img_place) {
			this.img_place = img_place;
		}
		public String getName_foto() {
			return name_foto;
		}
		public void setName_foto(String name_foto) {
			this.name_foto = name_foto;
		}
		public String getName_user() {
			return name_user;
		}
		public void setName_user(String name_user) {
			this.name_user = name_user;
		}
		public String getData() {
			return data;
		}
		public void setData(String data) {
			this.data = data;
		}
	    
	    

	
	
}
