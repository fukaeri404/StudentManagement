package controller;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;

//import com.jfoenix.controls.JFXButton.ButtonType;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;

import javafx.animation.PauseTransition;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import modules.DataSource;
import modules.Student;
import javafx.scene.input.KeyEvent;

public class StudentController implements Initializable {
	@FXML
	private Button btnNew;

	@FXML
	private Button btnEdit;

	@FXML
	private Button btnDelete;

	@FXML
	private Button btnFinish;

	@FXML
	private Button btnUpdate;

	@FXML
	private JFXComboBox<String> cbbMajor;

	@FXML
	private ToggleGroup tg_gender;

	@FXML
	private HBox hboxRegister;

	@FXML
	private HBox hboxSearch;

	@FXML
	private ImageView imgGender;

	@FXML
	private Label lblError;

	@FXML
	private Label lblSorry;

	@FXML
	private Label lblForm;

	@FXML
	private JFXRadioButton rbAlien;

	@FXML
	private JFXRadioButton rbBoy;

	@FXML
	private JFXRadioButton rbGirl;

	@FXML
	private TextField tfEmail;

	@FXML
	private TextField tfSearch;

	@FXML
	private TextField tfId;

	@FXML
	private TextField tfName;

	@FXML
	private TextField tfPhone;

	@FXML
	private TableView<Student> tvStudents;

	@FXML
	private TableColumn<Student, String> email;

	@FXML
	private TableColumn<Student, String> gender;

	@FXML
	private TableColumn<Student, String> major;

	@FXML
	private TableColumn<Student, String> name;

	@FXML
	private TableColumn<Student, Integer> order;

	@FXML
	private TableColumn<Student, String> phone;

	@FXML
	private TableColumn<Student, Integer> id;

	@FXML
	private Slider slider;

	@FXML
	private TextField tfSlider;

	private final DataSource dataSource = DataSource.getDatasourceInstance();
	private final ObservableList<Student> studentsList = dataSource.getStudentList();
	protected static int instance_id = 1001;
	protected static int instance_order = 1;

	@FXML
	void processDelete(ActionEvent event) {
		Student selectedStudent = tvStudents.getSelectionModel().getSelectedItem();
		tvStudents.getItems().remove(selectedStudent);
		refresh(selectedStudent);
	}

	@FXML
	void processCancel(ActionEvent event) {
		if (studentsList.size() == 0) {
			if (!lblSorry.isVisible())
				lblSorry.setVisible(true);
			if (tvStudents.isVisible())
				tvStudents.setVisible(false);
		} else {
			if (lblSorry.isVisible())
				lblSorry.setVisible(false);
			if (!tvStudents.isVisible())
				tvStudents.setVisible(true);
		}
		hboxRegister.setVisible(false);
		btnNew.setDisable(false);
	}

	@FXML
	void processFinish(ActionEvent e) {
		if (tfEmail.getText().isEmpty() || tfName.getText().isEmpty() || tfPhone.getText().isEmpty()
				|| !(rbAlien.isSelected() || rbBoy.isSelected() || rbGirl.isSelected())
				|| cbbMajor.getSelectionModel().isEmpty()) {
			lblError.setVisible(true);
			PauseTransition visiblePause = new PauseTransition(Duration.seconds(3));
			visiblePause.setOnFinished(event -> lblError.setVisible(false));
			visiblePause.play();

		} else {
			Student newStudent = new Student();
			newStudent.setOrder(instance_order);
			newStudent.setId(instance_id);
			newStudent.setEmail(tfEmail.getText());
			newStudent.setGender(tg_gender.getSelectedToggle().getUserData().toString());
			newStudent.setMajor(cbbMajor.getSelectionModel().getSelectedItem());
			newStudent.setPhone(tfPhone.getText());
			newStudent.setName(tfName.getText());
			studentsList.add(newStudent);
			instance_order++;
			instance_id++;

			hboxRegister.setVisible(false);
			hboxSearch.setVisible(true);
			tvStudents.setVisible(true);
			btnNew.setDisable(false);
		}
	}

	@FXML
	void processGuess(ActionEvent event) {
		int guessValue = (int) slider.getValue();

		if (studentsList.size() == guessValue) {
			ButtonType type = new ButtonType("CRY", ButtonData.OK_DONE);
			Alert alert = new Alert(Alert.AlertType.NONE);
			alert.setTitle("Right Answer");
			alert.setContentText("You Guessed It Right.Now pray for scholarships!LOL!!");
			alert.getDialogPane().getButtonTypes().add(type);
			alert.show();
		} else {
			ButtonType type = new ButtonType("CRY", ButtonData.OK_DONE);
			Alert alert = new Alert(Alert.AlertType.NONE);
			alert.setTitle("Wrong Answer");
			alert.setContentText("You can't even guess this right and stilll want scholar? Pathetic!!!");
			alert.getDialogPane().getButtonTypes().add(type);
			alert.show();
		}
	}

	@FXML
	void processEdit(ActionEvent event) {
		btnFinish.setVisible(false);
		Student selectedStudent = tvStudents.getSelectionModel().getSelectedItem();
		lblForm.setText("Edit Form");
		tfId.setText(String.valueOf(selectedStudent.getId()));
		tfName.setText(selectedStudent.getName());
		if (selectedStudent.getGender().equals("Boy"))
			rbBoy.setSelected(true);
		if (selectedStudent.getGender().equals("Girl"))
			rbGirl.setSelected(true);
		if (selectedStudent.getGender().equals("Boy"))
			rbAlien.setSelected(true);
		cbbMajor.setValue(selectedStudent.getMajor());
		tfEmail.setText(selectedStudent.getEmail());
		tfPhone.setText(selectedStudent.getPhone());

		if (!hboxRegister.isVisible())
			hboxRegister.setVisible(true);
		if (tvStudents.isVisible())
			tvStudents.setVisible(false);

	}

	@FXML
	void processUpdate(ActionEvent e) {
		if (tfEmail.getText().isEmpty() || tfName.getText().isEmpty() || tfPhone.getText().isEmpty()
				|| !(rbAlien.isSelected() || rbBoy.isSelected() || rbGirl.isSelected())
				|| cbbMajor.getSelectionModel().isEmpty()) {
			lblError.setVisible(true);
			PauseTransition visiblePause = new PauseTransition(Duration.seconds(3));
			visiblePause.setOnFinished(event -> lblError.setVisible(false));
			visiblePause.play();
		} else {
			Student selectedStudent = tvStudents.getSelectionModel().getSelectedItem();
			int selectedIndex = tvStudents.getSelectionModel().getSelectedIndex();
			for (Student s : studentsList) {
				if (s.getOrder() == selectedStudent.getOrder()) {
					s.setEmail(tfEmail.getText());
					s.setGender(tg_gender.getSelectedToggle().getUserData().toString());
					s.setMajor(cbbMajor.getSelectionModel().getSelectedItem());
					s.setPhone(tfPhone.getText());
					s.setName(tfName.getText());
					tvStudents.getItems().set(selectedIndex,s);
					studentsList.set(s.getOrder() - 1, s);
				
				}
			}

			hboxRegister.setVisible(false);
			tvStudents.setVisible(true);
			btnNew.setDisable(false);
		}
	}

	@FXML
	public void kr_search(KeyEvent event) {
		btnEdit.setDisable(true);
		btnDelete.setDisable(true);
		String text = tfSearch.getText();
		ObservableList<Student> list = FXCollections.observableArrayList();
		if (!text.isEmpty() && studentsList.size() != 0) {
			for (Student student : studentsList) {
				if (text.equalsIgnoreCase(String.valueOf(student.getId())) || text.equalsIgnoreCase(student.getName())
						|| text.equalsIgnoreCase(student.getEmail()) || text.equalsIgnoreCase(student.getPhone()))
					list.add(student);
			}
			tvStudents.setItems(list);
		}
		if (text.isEmpty())
			tvStudents.setItems(studentsList);
	}

	@FXML
	void processNew(ActionEvent event) {
		tfId.setText(String.valueOf(instance_id));

		btnFinish.setVisible(true);
		btnNew.setDisable(true);
		hboxRegister.setVisible(true);
		tvStudents.setVisible(false);
		lblSorry.setVisible(false);
		cbbMajor.setValue(null);

		tfEmail.setText("");
		tfName.setText("");
		tfPhone.setText("");

		rbAlien.setSelected(false);
		rbBoy.setSelected(false);
		rbGirl.setSelected(false);
		imgGender.setImage(new Image(getClass().getResourceAsStream("/images/question-mark.png")));
	}

	@FXML
	void mc_tvStudent(MouseEvent event) {
		Student selectedStudent = tvStudents.getSelectionModel().getSelectedItem();
		if (selectedStudent != null) {
			btnDelete.setDisable(false);
			btnEdit.setDisable(false);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		hboxRegister.setVisible(false);
		hboxSearch.setVisible(false);
		tvStudents.setVisible(false);
		lblSorry.setVisible(true);
		lblError.setVisible(false);
		btnDelete.setDisable(true);
		btnEdit.setDisable(true);

		rbBoy.setUserData("boy");
		rbGirl.setUserData("girl");
		rbAlien.setUserData("alien");

		tg_gender.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				if (tg_gender.getSelectedToggle() != null) {
					final Image image = new Image(getClass().getResourceAsStream(
							"/images/" + tg_gender.getSelectedToggle().getUserData().toString() + ".png"));
					imgGender.setImage(image);
				}
			}
		});
		slider.setValue(0);
		// tfSlider.textProperty().bindBidirectional(slider.valueProperty(),
		// NumberFormat.getInstance());
		tfSlider.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue.equals("")) {
					tfSlider.setText("0");
					slider.valueProperty().setValue(0);
				} else {
					slider.valueProperty().setValue(Integer.parseInt(tfSlider.getText()));
				}

			}
		});

		slider.valueProperty().addListener(new InvalidationListener() {
			@Override
			public void invalidated(Observable observable) {
				tfSlider.setText(String.valueOf((int) slider.getValue()));
			}
		});

		order.setCellValueFactory(new PropertyValueFactory<>("order"));
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
		major.setCellValueFactory(new PropertyValueFactory<>("major"));
		email.setCellValueFactory(new PropertyValueFactory<>("email"));
		phone.setCellValueFactory(new PropertyValueFactory<>("phone"));

		tvStudents.setItems(this.studentsList);

		tfId.setText(String.valueOf(instance_id));

		ObservableList<String> majorList = FXCollections.observableArrayList("Civil Engineering", "Networking",
				"Programming", "AI");
		cbbMajor.setItems(majorList);
	}

	void refresh(Student student) {
		instance_order = 1;
		studentsList.remove(student);
		for (Student s : studentsList) {
			s.setOrder(instance_order);
			instance_order++;
		}
		if (tvStudents.getItems().size() == 0) {
			btnDelete.setDisable(true);
			btnEdit.setDisable(true);
		}
		if(studentsList.size()==0) {
			hboxSearch.setVisible(false);
			tvStudents.setVisible(false);
			lblSorry.setVisible(true);
		}
	}

}
