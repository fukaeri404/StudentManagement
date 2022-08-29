package modules;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataSource {
	private static final DataSource DATASOURCE_INSTANCE = new DataSource();
	private static final ObservableList<Student> STUDENT_LIST = FXCollections.observableArrayList();

	public static DataSource getDatasourceInstance() {
		return DATASOURCE_INSTANCE;
	}

	public static ObservableList<Student> getStudentList() {
		return STUDENT_LIST;
	}
	
	

}
