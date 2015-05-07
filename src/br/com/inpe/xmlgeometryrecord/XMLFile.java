package br.com.inpe.xmlgeometryrecord;


import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class XMLFile {
	public static void write(GeometryRecord object, String filename) throws Exception {
		XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(
				new FileOutputStream(filename)));
		encoder.writeObject(object);
		encoder.flush();
		encoder.close();
	}
	public static void writeList(ArrayList<GeometryRecord> object, String filename) throws Exception {
		XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(
				new FileOutputStream(filename)));
		encoder.writeObject(object);
		encoder.flush();
		encoder.close();
	}

	public static GeometryRecord read(String filename) throws Exception {
		XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(
				new FileInputStream(filename)));
		GeometryRecord o = (GeometryRecord) decoder.readObject();
		decoder.close();
		return o;
	}

}
