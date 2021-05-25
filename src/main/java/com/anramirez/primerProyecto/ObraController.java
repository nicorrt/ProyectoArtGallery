package com.anramirez.primerProyecto;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.anramirez.primerProyecto.model.Artwork;
import com.anramirez.primerProyecto.model.ArtworkDAO;
import com.anramirez.primerProyecto.model.Client;
import com.anramirez.primerProyecto.model.ClientDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ObraController implements Initializable {

	@FXML
	private Button botAgregar;
	
	@FXML
	private Button botActualizar;
	
	@FXML 
	private Button botEliminar;
	
	@FXML
	private TableView<ArtworkDAO> tablaObras;
	

	
	@FXML
	private TableColumn<ArtworkDAO, String> ColID;
	
	@FXML
	private TableColumn<ArtworkDAO, String> ColNombre;
	
	@FXML
	private TableColumn<ArtworkDAO, String> ColArtista;
	
	@FXML
	private TableColumn<ArtworkDAO, String> ColPrecio;

	@FXML
	private TableColumn<ArtworkDAO, String> ColPropietario;
	
    @FXML
    private TextField barraBuscar;
	

	
	@FXML
	private Button botVolver;
	
	private ObservableList<ArtworkDAO> artworks;
    private ObservableList<ArtworkDAO> filtroartworks;
    
    private final ArtworkDAO modeloArtwork = new ArtworkDAO();
    
    private ArtworkDAO artwork;
	
	

	public void initialize(URL location, ResourceBundle rb) {
		// TODO Auto-generated method stub
		//Se crea el observaList
		artworks = FXCollections.observableArrayList();

		filtroartworks = FXCollections.observableArrayList();
		/**ClientDAO c = new ClientDAO();
		ObservableList<ClientDAO> variables = c.obtenerCliente();*/
		
		ObservableList<ArtworkDAO> artwork = (ObservableList<ArtworkDAO>) modeloArtwork.obtenerObraByIdObra();
		//Se asignan las columnas a los atributos del modelo
		
		this.ColID.setCellValueFactory(new PropertyValueFactory("idObra"));
		this.ColNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
		this.ColArtista.setCellValueFactory(new PropertyValueFactory("autor"));
		this.ColPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
		this.ColPropietario.setCellValueFactory(new PropertyValueFactory("miComprador"));
		//this.colImagen.setCellValueFactory(new PropertyValueFactory("Imagen"));
		artworks = artwork;
		this.tablaObras.setItems(artworks);
		
		
	}
	
	
	//Llamada a la escena donde crar la obra
    @FXML
    void agregarArtwork(ActionEvent event) {
		Stage stage = (Stage) this.botAgregar.getScene().getWindow();
		try {
			App.setRoot("windowArtwork");
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    //Método para filtrar los nombre a traves de un campo de texto
    @FXML
    private void buscarArtwork(KeyEvent event) {


    	String filtroNombre = this.barraBuscar.getText();
    	
    	if(filtroNombre.isEmpty()) {
    		this.tablaObras.setItems(artworks);
    	}else {
    		this.filtroartworks.clear();
    		
    		for(ArtworkDAO c:this.artworks) {
    			if(c.getNombre().toLowerCase().contains(filtroNombre.toLowerCase())) {
    				this.filtroartworks.add(c);
    			}
    		}
    		this.tablaObras.setItems(filtroartworks);
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
    private void eliminarArtwork(ActionEvent event) {
    	
    	Artwork c = this.tablaObras.getSelectionModel().getSelectedItem();
    	
    	if (c==null) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setHeaderText(null);
			alert.setTitle("ERROR");
			alert.setContentText("Debes seleccionar una obra");
			alert.showAndWait();
    	}else {
    		ArtworkDAO cd = (ArtworkDAO) c;
    		this.artworks.remove(cd);
    		cd.eliminarObra();
    		this.tablaObras.refresh();
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
    		alert.setHeaderText(null);
			alert.setTitle("INFORMACION");
			alert.setContentText("Se ha borrado la obra");
			alert.showAndWait();

    	}

    }
    
    public void initAtr(ObservableList<ArtworkDAO> artworks, ArtworkDAO a) {
    	this.artworks= artworks;
    	this.artwork = a;

    }
    
    public Artwork getArtork() {
    	return artwork;
    }
    
    @FXML
    public void modificarArtwork(ActionEvent event) {
     	
     	Artwork a = this.tablaObras.getSelectionModel().getSelectedItem();
     	
         if (a == null) {
         	
         	
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setHeaderText(null);
             alert.setTitle("Error");
             alert.setContentText("Debe seleccionar una obra");
             alert.showAndWait();
         } else {
             try {
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("windowArtwork.fxml"));
                 Parent root = loader.load();
                 WindowArtworkController controlador = loader.getController();
                 controlador.initAtrMod(artworks, (ArtworkDAO) a);

                 Scene scene = new Scene(root);
                 Stage stage = new Stage();
                 stage.initModality(Modality.APPLICATION_MODAL);
                 stage.setScene(scene);
                 stage.showAndWait();

                 Artwork aux = controlador.getClient();
                 if (aux != null) {
                     this.tablaObras.refresh();
                 }

             } catch (IOException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
             }

         }
     	
     }

     @FXML
     void seleccionarArtwork(ActionEvent event) {
     	
     	Artwork a = (Artwork) this.tablaObras.getSelectionModel().getSelectedItem();
     	
     	if (a==null) {
     		Alert alert = new Alert(Alert.AlertType.ERROR);
     		alert.setHeaderText(null);
 			alert.setTitle("ERROR");
 			alert.setContentText("Debes seleccionar una obra");
 			alert.showAndWait();
     	}else {
     		
     	}
     }
 	
		

}
