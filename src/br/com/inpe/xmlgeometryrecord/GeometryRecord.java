package br.com.inpe.xmlgeometryrecord;
import java.io.Serializable;

public class GeometryRecord implements Serializable{
	private String municipalityName;
	private long municipalityArea;
	private String geometry;


	public GeometryRecord(String municipalityName, long municipalityArea,
			String geometry) {
		super();
		this.municipalityName = municipalityName;
		this.municipalityArea = municipalityArea;
		this.geometry = geometry;
	}
	public GeometryRecord(){
		
	}


	public String getMunicipalityName() {
		return municipalityName;
	}

	public void setMunicipalityName(String municipalityName) {
		this.municipalityName = municipalityName;
	}

	public long getMunicipalityArea() {
		return municipalityArea;
	}

	public void setMunicipalityArea(long municipalityArea) {
		this.municipalityArea = municipalityArea;
	}

	public String getGeometry() {
		return geometry;
	}

	public void setGeometry(String geometry) {
		this.geometry = geometry;
	}
}