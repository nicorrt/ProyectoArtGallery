package com.anramirez.primerProyecto;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.anramirez.primerProyecto.model.Artwork;
import com.anramirez.primerProyecto.model.Client;
import com.anramirez.primerProyecto.model.ClientDAO;
import com.anramirez.primerProyecto.utils.Conexion;
import com.anramirez.primerProyecto.utils.Validadores;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class WindowClientController {



	    @FXML
	    private Button botGuardar;

	    @FXML
	    private Button botVolver;

	    @FXML
	    private TextField txtNombre;

	    @FXML
	    private TextField txtDni;

	    @FXML
	    private TextField txtTelefono;

	    @FXML
	    private TextField txtGasto;
	    
		
	    private ObservableList<ClientDAO> clientes;
	    
	    private ClientDAO cliente;
	    
	    @FXML
	    private Button botActualizar;


	    


	    @FXML
	    void handleBack(ActionEvent event) {
			Stage stage = (Stage) this.botVolver.getScene().getWindow();
			try {
				App.setRoot("ViewCliente");
				stage.show();
				if (this.cliente != null && this.cliente.getIdClient()>0) {
					stage.close();
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    }
	    
	   @FXML
	    void insertarCliente(ActionEvent event) {
	    	
	    	String nombre = this.txtNombre.getText();
	    	String dni = Validadores.validarDNI(this.txtDni.getText());
	    	int telefono = Validadores.checkNumericoInt((this.txtTelefono.getText()+""));
	    	double gasto = Validadores.checkNumericoDou(this.txtGasto.getText()+"");





	    	if(this.txtDni.getText().length()>2&&this.txtNombre.getText().length()>0&&this.txtTelefono.getText().length()>0
	    			&&this.txtGasto.getText().length()>=0) {	
	    		
	    		Client c;
	    		//update
	    		if (this.cliente != null && this.cliente.getIdClient() > 0) {
	    			c = new Client(this.cliente.getIdClient(), nombre, dni, telefono, gasto);
	    		} else { // nuevo
	    			c = new Client(nombre, dni, telefono, gasto);
	    		}
	    		
	    		ClientDAO cd = new ClientDAO(c);
	    		
	    		if(this.cliente != null && this.cliente.getIdClient() > 0) {

	    			this.cliente.setNombre(nombre);
	    			this.cliente.setDni(dni);
	    			this.cliente.setTelefono(telefono);
	    			this.cliente.setGasto(gasto);


	
	    		
	    			cd.actualizarCliente2();
	                Alert alert = new Alert(Alert.AlertType.INFORMATION);
	                alert.setHeaderText(null);
	                alert.setTitle("Info");
	                alert.setContentText("Se ha modificado conrrectamente");
	                alert.showAndWait();
	                
	    		}else {
	    			this.cliente = cd;
	    			//clientes.add(cd);
	    			cd.registrarCliente();
	    		}
	    		
	    	}else {
				Alert alert2 = new Alert(Alert.AlertType.ERROR);
				alert2.setHeaderText(null);
				alert2.setTitle("ERROR");
				alert2.setContentText("Debe completar todos los campos");
				alert2.showAndWait();
	    	}	 
	    	
	    }


	    
	    public void initAtrMod(ObservableList<ClientDAO> clientes, ClientDAO c) {
	    	this.clientes= clientes;
	    	this.cliente = c;
	    	this.txtNombre.setText(c.getNombre());
	    	this.txtDni.setText(c.getDni());
	    	this.txtTelefono.setText(c.getTelefono()+"");
	    	this.txtGasto.setText(c.getGasto()+"");

	    }
	    public void initAtr(ObservableList<ClientDAO> clientes, ClientDAO c) {
	    	this.clientes= clientes;
	    	this.cliente = c;
	    	this.txtNombre.setText(c.getNombre());
	    	this.txtDni.setText(c.getDni());
	    	this.txtTelefono.setText(c.getTelefono()+"");
	    	this.txtGasto.setText(c.getGasto()+"");

	    }
	    public Client getClient() {
	    	return cliente;
	    }
	    

}
