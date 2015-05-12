package br.com.inpe.xmlgeometryrecord;

import java.util.LinkedList;
import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

public class DataLoaderScience {
	private List<GeometryRecord> geometryRecords;
	private ObjectContainer dataBase;
	// Singleton
	private static DataLoaderScience uniqueInstance;

	private DataLoaderScience() {
		geometryRecords = new LinkedList<GeometryRecord>();
		dataBase = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),
				"../WorldWind/database/data.db4o");
	}

	// Singleton
	public static DataLoaderScience getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new DataLoaderScience();
		}
		return uniqueInstance;
	}

	public void addData(GeometryRecord geometryRecord) {
		dataBase.store(geometryRecord);
	}

	public ObjectSet SearchData(String parameter) {
		Query query = dataBase.query();
		ObjectSet result;

		if (!numberCheck(parameter)) {
			query.descend("municipalityName").constrain(parameter)
					.or(query.descend("geometry").constrain(parameter));
			result = query.execute();
			return result;
		}
		query.descend("municipalityArea").constrain(parameter);
		result = query.execute();
		return result;
	}

	public void remevoData(String parameter) {
		ObjectSet result = SearchData(parameter);
		if (!result.isEmpty()) {
			GeometryRecord data = (GeometryRecord) result.next();
			dataBase.delete(data);
		}

	}

	public boolean numberCheck(String parameter) {
		try {
			Integer.parseInt(parameter);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
