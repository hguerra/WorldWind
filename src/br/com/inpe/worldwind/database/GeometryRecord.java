package br.com.inpe.worldwind.database;

import java.io.Serializable;

public class GeometryRecord implements Serializable {
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

	public GeometryRecord() {

	}

	public boolean matchesMunicipalityName(String parameter) {
		if (parameter.equals(getMunicipalityName()))
			return true;
		return false;
	}

	public boolean matchesMunicipalityArea(long parameter) {
		if (parameter == getMunicipalityArea())
			return true;
		return false;
	}
	
	public boolean matchesBiggestMunicipalityArea(long parameter) {
		if (parameter < getMunicipalityArea())
			return true;
		return false;
	}
	
	public boolean matchesSmallestMunicipalityArea(long parameter) {
		if (parameter > getMunicipalityArea())
			return true;
		return false;
	}

	public boolean matchesGeometry(String parameter) {
		if (parameter.equals(getGeometry()))
			return true;
		return false;
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