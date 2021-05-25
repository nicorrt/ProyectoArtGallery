package com.anramirez.primerProyecto;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.anramirez.primerProyecto.SecondMenuController.clientConverter;
import com.anramirez.primerProyecto.model.Artwork;
import com.anramirez.primerProyecto.model.ArtworkDAO;
import com.anramirez.primerProyecto.model.Client;
import com.anramirez.primerProyecto.model.ClientDAO;
import com.anramirez.primerProyecto.utils.IconCell;
import com.anramirez.primerProyecto.utils.Validadores;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class WindowArtworkController implements Initializable{
	
	

		    @FXML
		    private Button botGuardar;

		    @FXML
		    private Button botVolver;

		    @FXML
		    private TextField txtNombre;

		    @FXML
		    private TextField txtAutor;

		    @FXML
		    private TextField txtPrecio;

		    @FXML
		    private TextField txtFoto;
		    
		    private ObservableList<ArtworkDAO> artworks;
		    
		    private final ClientDAO modeloCliente = new ClientDAO();
		    
		    private ArtworkDAO artwork;
		    
		    @FXML
		    private ComboBox<Client> comboArtwork;
		    
		    
		    private int idCliente; //id del cliente seleccionado en el combo
		    
		    //Llama según el proceso de creación o modificación a una ventana u cierra la actual
		    @FXML
		    void handleBack(ActionEvent event) {
				Stage stage = (Stage) this.botVolver.getScene().getWindow();
				try {
					App.setRoot("ViewObra");
					stage.show();
					if (this.artwork != null && this.artwork.getIdObra()>0) {
						stage.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		    }	
		    
		    
		    @FXML
		    public void insertarArtwork(ActionEvent event) {
		    	
		    	String nombre = this.txtNombre.getText();
		    	String autor = this.txtAutor.getText();
		    	double precio = Validadores.checkNumericoDou(this.txtPrecio.getText()+"");

		    	if(this.txtAutor.getText().length()>2&&this.txtNombre.getText().length()>0
		    			&&this.txtPrecio.getText().length()>=0) {	
		    		Artwork a;
		    		
		    		Client clienteTmp = new Client();
	    			clienteTmp.setIdClient(idCliente);
	    	
	    			
		    				
    	    		//update la obra con su id
    	    		if (this.artwork != null && this.artwork.getIdObra() > 0) {
    	    			a = new Artwork(this.artwork.getIdObra(), nombre, autor, precio, clienteTmp);
    	    		} else { // nueva obra a insertar en la base de datos
    	    			a = new Artwork(nombre, autor, precio, clienteTmp);
    	    		}
		    	    		
    			
		    		ArtworkDAO ad = new ArtworkDAO(a);
		    		
		    		
		    		if(this.artwork != null && this.artwork.getIdObra() > 0) {

		    			this.artwork.setNombre(nombre);
		    			this.artwork.setAutor(autor);
		    			this.artwork.setPrecio(precio);
		    			this.artwork.setMiComprador(clienteTmp);
		    			

		
		    		
		    			ad.actualizarObra();
		                Alert alert = new Alert(Alert.AlertType.INFORMATION);
		                alert.setHeaderText(null);
		                alert.setTitle("Info");
		                alert.setContentText("Se ha modificado conrrectamente");
		                alert.showAndWait();
		    		}else {
		    			this.artwork = ad;
		    			//artworks.add(ad);
		    			ad.registrarObra();
		    		}
		    		
		    	}else {
					Alert alert2 = new Alert(Alert.AlertType.ERROR);
					alert2.setHeaderText(null);
					alert2.setTitle("ERROR");
					alert2.setContentText("Debe completar todos los campos");
					alert2.showAndWait();
		    	}	 
		    	
		    }
	
	

		    
		    public void initAtrMod(ObservableList<ArtworkDAO> artworks, ArtworkDAO a) {
		    	this.artworks= artworks;
		    	this.artwork = a;
		    	this.txtNombre.setText(a.getNombre());
		    	this.txtAutor.setText(a.getAutor());
		    	this.txtPrecio.setText(a.getPrecio()+"");


		    }
		    public void initAtr(ObservableList<ArtworkDAO> artworks, ArtworkDAO a) {
		    	this.artworks= artworks;
		    	this.artwork = a;
		    	this.txtNombre.setText(a.getNombre());
		    	this.txtAutor.setText(a.getAutor());
		    	this.txtPrecio.setText(a.getPrecio()+"");

		    }
		    
		    public Artwork getClient() {
		    	return artwork;
		    }

		    
			@FXML
			private void comboBoxEventsClient(ActionEvent e) {
				Object evt = e.getSource();
				
				idCliente = comboArtwork.getSelectionModel().getSelectedItem().getIdClient();
				System.out.println("Cliente seleccionado en el combo: " + idCliente);
				

			}
			
			@Override
			public void initialize(URL location, ResourceBundle resources) {
				// TODO Auto-generated method stub
				ObservableList<ClientDAO> lista2 = (ObservableList<ClientDAO>) modeloCliente.obtenerCliente();	
				
				comboArtwork.getItems().addAll(lista2);
				comboArtwork.setConverter(new clientConverter());
					
				
				}
			
			public class clientConverter extends StringConverter<Client>{

				@Override
				public String toString(Client client) {
					// TODO Auto-generated method stub
					return client == null ? null :client.getNombre()+" "+client.getDni();
				}

				@Override
				public Client fromString(String string) {
					// TODO Auto-generated method stub
					return null;
				}
				
			}

}
