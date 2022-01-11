package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class loginFormController {
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassWord;
    public AnchorPane root;

    public void btnSignInOnAction(ActionEvent actionEvent) throws IOException {
        if (txtUserName.getText().equals("cash") && txtPassWord.getText().equals("2021")) {
            Parent parent= FXMLLoader.load(this.getClass().getResource("../view/dashBoardForm.fxml"));
            Scene scene=new Scene(parent);

            Stage primaryStage=(Stage)this.root.getScene().getWindow();

            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
        } else {
            new Alert(Alert.AlertType.WARNING, "Please Check Your Username Or Password !", ButtonType.CLOSE).show();
        }
    }
}
