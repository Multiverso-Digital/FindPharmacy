package com.abreuretto.findpharmacy;

import java.io.Serializable;






public class AppMapa  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	


	public AppMapa() {
		// TODO Auto-generated constructor stub
	}

	private String icon;
	private String id;
    private String name;
    private int rating;
    private String reference;
	private String vicinity;
    private double distance;
    private double aLat;
    private double aLon;
    private int tipo; //1=google, 2=fs , 3=nokia
	
	
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getVicinity() {
		return vicinity;
	}
	public void setVicinity(String vicinity) {
		this.vicinity = vicinity;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public double getaLat() {
		return aLat;
	}
	public void setaLat(double aLat) {
		this.aLat = aLat;
	}
	public double getaLon() {
		return aLon;
	}
	public void setaLon(double aLon) {
		this.aLon = aLon;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

}
