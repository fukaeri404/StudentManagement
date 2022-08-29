module Assignment10 {
	requires javafx.controls;
	requires javafx.fxml;
	requires com.jfoenix;
	
	opens application to javafx.graphics, javafx.fxml;
	opens controller to javafx.fxml;
	opens modules to javafx.base;
}
