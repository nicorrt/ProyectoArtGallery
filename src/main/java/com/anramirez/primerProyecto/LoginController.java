package com.anramirez.primerProyecto;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.anramirez.primerProyecto.model.User;
import com.anramirez.primerProyecto.model.UserDAO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginController {

	@FXML
	private TextField txtUsuario;
	@FXML
	private PasswordField txtPass;
	@FXML
	private Button botEntrar;
	@FXML 
	private Button botSalir;
	
	@FXML
	private UserDAO modelo = new UserDAO();
	
	@FXML
	private void eventKey(KeyEvent event) {
		Object e= event.getSource();
		if(e.equals(txtUsuario)) {
			if(event.getCharacter().equals(" ")) {
				event.consume();
			}
			
		}else if(e.equals(txtPass)){
			if(event.getCharacter().equals(" ")) {
				event.consume();
			}
		}
		
	}
	
	@FXML 
	private void eventAction (ActionEvent event) {
		Object evento=event.getSource();
		
		if(evento.equals(botEntrar)) {
			if(!txtUsuario.getText().isEmpty()&&!txtPass.getText().isEmpty()) {
				String user = txtUsuario.getText();
				String pass = txtPass.getText();
				
				int state = modelo.login(user, pass);
				
				if(state!=-1) {
					if(state==1) {
						Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setHeaderText(null);
						alert.setTitle("CORRECTO");
						alert.setContentText("El usuario y la contraseña son correctos");
						alert.showAndWait();
						try {
							App.setRoot("secondMenu");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//OpenSecondMenu();
					}else {
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setHeaderText(null);
						alert.setTitle("ERROR");
						alert.setContentText("El usuario o la contraseña no son correctos");
						this.txtUsuario.clear();
						this.txtPass.clear();
						alert.showAndWait();
					}
						
				}
				
			}else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("ERROR");
				alert.setContentText("El usuario o la contraseña no son correctos");
				this.txtUsuario.clear();
				this.txtPass.clear();
				alert.showAndWait();
			}
		}
	}
	
	//Abre la segunda pantalla
	@FXML
	public void OpenSecondMenu() {

		try {
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("secondMenu.fxml"));
			Parent modal;
			modal = fxmlLoader.load();
			
			Stage modalStage= new Stage();
			modalStage.initModality(Modality.APPLICATION_MODAL);
			modalStage.initOwner(App.roostage);
			Scene modalScene = new Scene(modal);
			modalStage.setScene(modalScene);
			modalStage.showAndWait();

				}catch (IOException ex){
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setTitle("ERROR");
					alert.setContentText("No ha sido posible cargar la segunda pantalla");
					alert.showAndWait();
				}
	}
	
	//Botón para cerrar el Sistema
	@FXML
	public void handleClose(ActionEvent event) {
		Stage stage = (Stage) this.botSalir.getScene().getWindow();
		stage.close();
		System.exit(0);
	}

}
