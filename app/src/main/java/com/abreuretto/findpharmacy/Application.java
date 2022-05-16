package com.abreuretto.findpharmacy;

import java.io.Serializable;
import java.util.List;

public class Application implements Serializable {

	private static final long serialVersionUID = 2L;
	
	
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
    private int especial; 
    private double ratyelp;
    private int review;
    
    public int getReview() {
		return review;
	}
	public void setReview(int review) {
		this.review = review;
	}
	public double getRatyelp() {
		return ratyelp;
	}
	public void setRatyelp(double ratyelp) {
		this.ratyelp = ratyelp;
	}
	public int getEspecial() {
		return especial;
	}
	public void setEspecial(int especial) {
		this.especial = especial;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
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
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
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
    
    
     

}
