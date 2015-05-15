package br.com.inpe.xmlRecord;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import br.com.inpe.worldwind.database.GeometryRecord;

public class XMLFile {
	public static void write(GeometryRecord object, String filename)
			throws Exception {
		XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(
				new FileOutputStream(filename)));
		encoder.writeObject(object);
		encoder.flush();
		encoder.close();
	}

	public static void writeList(List<GeometryRecord> object,
			String filename) throws Exception {
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

	public static List<GeometryRecord> readList(String filename)
			throws Exception {
		XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(
				new FileInputStream(filename)));
		@SuppressWarnings("unchecked")
		List<GeometryRecord> o = (List<GeometryRecord>) decoder.readObject();
		decoder.close();
		return o;
	}
}
