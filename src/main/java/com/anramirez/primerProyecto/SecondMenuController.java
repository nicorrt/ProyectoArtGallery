package com.anramirez.primerProyecto;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.anramirez.primerProyecto.model.Artwork;
import com.anramirez.primerProyecto.model.ArtworkDAO;
import com.anramirez.primerProyecto.model.Client;
import com.anramirez.primerProyecto.model.ClientDAO;
import com.anramirez.primerProyecto.model.IClientDAO;
import com.anramirez.primerProyecto.utils.Conexion;
import com.anramirez.primerProyecto.utils.IconCell;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class SecondMenuController implements Initializable{
	
	@FXML
	private ComboBox<Client> comboBoxClient;
	@FXML
	private TextField textSearchClient;
	@FXML
	private Button botSearchClient;
	@FXML
	private TextArea textAreaClient;
	@FXML
	private Button toMenuClient;
	
	@FXML
	private ComboBox<Artwork> comboBoxArtwork;
	@FXML
	private TextField textSearchArtwork;
	@FXML
	private Button botSearchArtwork;
	@FXML
	private TextArea textAreaArtwork;
	@FXML
	private Button toMenuArtwork;
	
	private final ArtworkDAO modeloObra = new ArtworkDAO();
	private final ClientDAO modeloCliente = new ClientDAO();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		ObservableList<ClientDAO> lista2 = (ObservableList<ClientDAO>) modeloCliente.obtenerCliente();	
		ObservableList<ArtworkDAO> lista = (ObservableList<ArtworkDAO>) modeloObra.obtenerObraByIdObra();
		comboBoxClient.getItems().addAll(lista2);
		comboBoxClient.setConverter(new clientConverter());
		
		comboBoxArtwork.getItems().addAll(lista);
		comboBoxArtwork.setConverter(new artworkConverter());
			
		//Código que será util para una próxima actualización
		/*if(lista.size()>0) {
			comboBoxArtwork.getItems().addAll(lista);
			comboBoxArtwork.setCellFactory(new Callback<ListView<Artwork>, ListCell<Artwork>>(){

				@Override
				public ListCell<Artwork> call(ListView<Artwork> param) {
					// TODO Auto-generated method stub
					return new IconCell();
				}
				
			});
		}
		/**if(lista2.size()>0) {
			comboBoxClient.getItems().addAll(lista2);

		}else {
			System.out.println("no veo base");
		}*/
		
	}
	
	@FXML
	private void comboBoxEventsClient(ActionEvent e) {
		Object evt = e.getSource();
		if(evt.equals(comboBoxClient)) {
			//original
			textAreaClient.setText(comboBoxClient.getSelectionModel().getSelectedItem().toString());
			
			Client cliente = comboBoxClient.getSelectionModel().getSelectedItem();
			ArrayList<Artwork> listaObras = modeloObra.obtenerObraByIdCliente(cliente.getIdClient());
			
			
			cliente.setMiCompras(listaObras);
			
			textAreaClient.setText(cliente.toString());
			
			
		}else if(evt.equals(botSearchClient)) {
			String idClient = textSearchClient.getText();
			try {
				int idSearch = Integer.parseInt(idClient);
				int index = getIdSelectedClient(idSearch, comboBoxClient);
				if(index != -1) {
					comboBoxClient.getSelectionModel().select(index);
				}else {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setTitle("ERROR");
					alert.setContentText("El id que está buscando no existe");
					alert.showAndWait();
				}
				
			}catch (Exception ex) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("ERROR");
				alert.setContentText("El formato no es el adecuado");
				alert.showAndWait();
			}
		}
	}
	@FXML
	private void comboBoxEventsArtwork(ActionEvent e) {
		Object evt = e.getSource();
		if(evt.equals(comboBoxArtwork)) {
			textAreaArtwork.setText(comboBoxArtwork.getSelectionModel().getSelectedItem().toString());
		}else if(evt.equals(botSearchArtwork)) {
			String idArtwork = textSearchArtwork.getText();
			try {
				int idSearch = Integer.parseInt(idArtwork);
				int index = getIdSelectedArtwork(idSearch, comboBoxArtwork);
				if(index != -1) {
					comboBoxArtwork.getSelectionModel().select(index);
				}else {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setTitle("ERROR");
					alert.setContentText("El id que está buscando no existe");
					alert.showAndWait();
				}
				
			}catch (Exception ex) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("ERROR");
				alert.setContentText("El formato no es el adecuado");
				alert.showAndWait();
			}
		}
	}
	
	//Selecciona a los clientes del Combobox
	private int getIdSelectedClient (int idClient, ComboBox<Client> comboBox) {
		ObservableList<Client> clients = comboBox.getItems();
		boolean state = true;
		int i = 0;
		int index = -1;
		
		while(state && i<clients.size()) {
			if(clients.get(i).getIdClient()== idClient) {
				state=false;
				index=i;
			}else {
				i++;
			}
		}
		return index;
	}
	
	private int getIdSelectedArtwork (int idArtwork, ComboBox<Artwork> comboBox) {
		ObservableList<Artwork> artworks = comboBox.getItems();
		boolean state = true;
		int i = 0;
		int index = -1;
		
		while(state && i<artworks.size()) {
			if(artworks.get(i).getId()== idArtwork) {
				state=false;
				index=i;
			}else {
				i++;
			}
		}
		return index;
	}
	//Llama a una nueva vista
	@FXML
	public void handleClients(ActionEvent event) {
		Stage stage = (Stage) this.toMenuClient.getScene().getWindow();
		try {
			App.setRoot("ViewCliente");
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	//Llama a una nueva vista
	@FXML
	public void handleArtworks(ActionEvent event) {
		Stage stage = (Stage) this.toMenuArtwork.getScene().getWindow();
		try {
			App.setRoot("ViewObra");
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
		//Se utiliza para devolver el nombre y el dni del cliente en la vista del ComboBox del cliente y la funcion de abajo de la obra.
	public class clientConverter extends StringConverter<Client>{

		@Override
		public String toString(Client client) {
			// TODO Auto-generated method stub
			return client == null ? null :client.getNombre()+" "+client.getDni()+" "+client.getIdClient();
		}

		@Override
		public Client fromString(String string) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	public class artworkConverter extends StringConverter<Artwork>{

		@Override
		public String toString(Artwork artwork) {
			// TODO Auto-generated method stub
			return artwork == null ? null :artwork.getNombre()+" "+artwork.getAutor()+" "+artwork.getIdObra();

		}

		@Override
		public Artwork fromString(String string) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}	

}
