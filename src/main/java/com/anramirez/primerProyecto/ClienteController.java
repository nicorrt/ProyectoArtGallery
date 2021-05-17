package com.anramirez.primerProyecto;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.anramirez.primerProyecto.model.Artwork;
import com.anramirez.primerProyecto.model.Client;
import com.anramirez.primerProyecto.model.ClientDAO;
import com.anramirez.primerProyecto.utils.Validadores;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ClienteController implements Initializable{
	


	@FXML
	private Button botVolver;

    @FXML
    private TableView<ClientDAO> TablaClientes;

    @FXML
    private TableColumn<ClientDAO, String> ColID;

    @FXML
    private TableColumn<ClientDAO, String> ColNombre;

    @FXML
    private TableColumn<ClientDAO, String> ColDni;

    @FXML
    private TableColumn<ClientDAO, String> ColTlf;

    @FXML
    private TableColumn<ClientDAO, String> ColGasto;

    @FXML
    private TableColumn<ClientDAO, String> ColObras;

    @FXML
    private Button botAgregar;
    


    @FXML
    private Button botModificar;

    @FXML
    private Button botEliminar;

    @FXML
    private TextField barraBuscar;

	
    private ObservableList<ClientDAO> clientes;
    
    
    private ObservableList<ClientDAO> filtroclientes;
    
    private final ClientDAO modeloCliente = new ClientDAO();
    
    private ClientDAO cliente;
    
    
    


	
	public void initialize(URL url, ResourceBundle rb) {
		clientes = FXCollections.observableArrayList();
		filtroclientes = FXCollections.observableArrayList();
	
		ObservableList<ClientDAO> client = modeloCliente.obtenerCliente();

		this.ColID.setCellValueFactory(new PropertyValueFactory("IdClient"));
		this.ColNombre.setCellValueFactory(new PropertyValueFactory("Nombre"));
		this.ColDni.setCellValueFactory(new PropertyValueFactory("Dni"));
		this.ColTlf.setCellValueFactory(new PropertyValueFactory("Telefono"));
		this.ColGasto.setCellValueFactory(new PropertyValueFactory("Gasto"));
		
		clientes = client;
		this.TablaClientes.setItems(clientes);
	}
	
	

	//Boton para llamar a la escena donde se creara el nuevo Cliente
    @FXML
    void agregarCliente(ActionEvent event) {
		Stage stage = (Stage) this.botAgregar.getScene().getWindow();
		try {
			App.setRoot("windowClient");
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    
    //Método para filtrar los nombre a traves de un campo de texto
    @FXML
    private void buscarCliente(KeyEvent event) {


    	String filtroNombre = this.barraBuscar.getText();
    	
    	if(filtroNombre.isEmpty()) {
    		this.TablaClientes.setItems(clientes);
    	}else {
    		this.filtroclientes.clear();
    		
    		for(ClientDAO c:this.clientes) {
    			if(c.getNombre().toLowerCase().contains(filtroNombre.toLowerCase())) {
    				this.filtroclientes.add(c);
    			}
    		}
    		this.TablaClientes.setItems(filtroclientes);
    	}
    }
    
    
    //Sirve para eliminar un Cliente de una base de datos, llamando al método para eliminar clientes de ClientDAO
    @FXML
    private void eliminarCliente(ActionEvent event) {
    	
    	Client c = this.TablaClientes.getSelectionModel().getSelectedItem();
    	
    	if (c==null) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setHeaderText(null);
			alert.setTitle("ERROR");
			alert.setContentText("Debes seleccionar un cliente");
			alert.showAndWait();
    	}else {
    		ClientDAO cd = (ClientDAO) c;
    		this.clientes.remove(cd);
    		cd.eliminar();
    		this.TablaClientes.refresh();
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
    		alert.setHeaderText(null);
			alert.setTitle("INFORMACION");
			alert.setContentText("Se ha borrado el cliente");
			alert.showAndWait();

    	}

    }
    
    //Inicializa en la siguiente ventana los atributos del Client
    public void initAtr(ObservableList<ClientDAO> clientes, ClientDAO c) {
    	this.clientes= clientes;
    	this.cliente = c;

    }
    
    public Client getClient() {
    	return cliente;
    }
    

  //Sirve para modificar un Cliente de una base de datos, llamando al método para eliminar clientes de ClientDAO y a una nueva ventana de Javafx.
   public void modificarCliente(ActionEvent event) {
    	
    	Client c = this.TablaClientes.getSelectionModel().getSelectedItem();
    	
        if (c == null) {
        	
        	
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debe seleccionar un usuario");
            alert.showAndWait();
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("windowClient.fxml"));
                Parent root = loader.load();
                WindowClientController controlador = loader.getController();
                controlador.initAtrMod(clientes, (ClientDAO) c);

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();

                Client aux = controlador.getClient();
                if (aux != null) {
                    this.TablaClientes.refresh();
                }

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    	
    }
   		
   	
    @FXML
    void seleccionarCliente(ActionEvent event) {
    	
    	Client c = (Client) this.TablaClientes.getSelectionModel().getSelectedItem();
    	
    	if (c==null) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setHeaderText(null);
			alert.setTitle("ERROR");
			alert.setContentText("Debes seleccionar un cliente");
			alert.showAndWait();
    	}
    }
	@FXML
	public void handleBack(ActionEvent event) {
		Stage stage = (Stage) this.botVolver.getScene().getWindow();
		try {
			App.setRoot("secondMenu");
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	   @FXML
	   private void editNombre(TableColumn.CellEditEvent<Client, String> event) {
	      ClientDAO c = new ClientDAO(event.getRowValue());
	      c.setNombre(event.getNewValue());
	   }
	
}
