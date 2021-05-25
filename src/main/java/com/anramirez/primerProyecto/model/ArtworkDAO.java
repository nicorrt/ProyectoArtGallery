package com.anramirez.primerProyecto.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.anramirez.primerProyecto.utils.Conexion;
import com.anramirez.primerProyecto.utils.ConverImagenes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;

public class ArtworkDAO extends Artwork implements IArtworkDAO {
	

	private final static String GETBYIDCLIENTE = "SELECT * FROM obra, cliente WHERE cliente.idCliente = obra.idCliente AND obra.idCliente = ?";

	private final static String ACTUALIZAROBRA = "UPDATE obra SET nombre = ?,artista = ?, precio = ?,idCliente=? WHERE idObra=?";


	
	public ArtworkDAO (int idObra, String nombre, String artista, double precio,Client miComprador) {
		super(idObra, nombre, artista, precio, miComprador);
	}
	
	
	public ArtworkDAO() {
		super();
	}
	
	public ArtworkDAO(Artwork artwork) {
		this.idObra = artwork.idObra;
		this.nombre = artwork.nombre;
		this.autor = artwork.autor;
		this.precio = artwork.precio;
		this.miComprador = artwork.miComprador;
		//this.fotoBytes =artwork.fotoBytes;
	}

	
	@Override
	public List<ArtworkDAO> obtenerObraByIdObra() {
		// TODO Auto-generated method stub
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		String sql="SELECT * FROM obra";
		List<ArtworkDAO> listaObras = FXCollections.observableArrayList();	
				
		con=Conexion.getInstance();
		if(con!=null) {
			try {
				st=con.createStatement();
				rs=st.executeQuery(sql);
				while (rs.next()) {
					this.idObra=rs.getInt(1);
					this.nombre=rs.getString(2);
					this.autor=rs.getString(3);
					this.precio=rs.getDouble(4);
					//this.fotoBytes=rs.getByte(5);
					this.miComprador=new ClientDAO(rs.getInt("idCliente"));

					ArtworkDAO a = new ArtworkDAO(idObra, nombre, autor, precio, miComprador);
					listaObras.add(a);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("ERROR");
				alert.setContentText("No se han podido mostrar las obras de la base de datos");
				alert.showAndWait();
			}
			
		}
		
		return listaObras;
	}
	
	@Override
	public ArrayList<Artwork> obtenerObraByIdCliente(int idCliente) {
		// TODO Auto-generated method stub
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		//ArrayList<Artwork> listaObras = FXCollections.observableArrayList();	
		ArrayList<Artwork> listaObras = new ArrayList<Artwork>();		
		
		con=Conexion.getInstance();
		if(con!=null) {
			try {
				PreparedStatement q=con.prepareStatement(GETBYIDCLIENTE);
	            q.setInt(1, idCliente);
	            rs =q.executeQuery();		
				
				
				

				while (rs.next()) {
					this.idObra=rs.getInt(1);
					this.nombre=rs.getString(2);
					this.autor=rs.getString(3);
					this.precio=rs.getDouble(4);
					//this.fotoBytes=rs.getByte(5);
					this.miComprador=new ClientDAO(rs.getString(5));

					ArtworkDAO a = new ArtworkDAO(idObra, nombre, autor, precio, miComprador);
					listaObras.add(a);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("ERROR");
				alert.setContentText("No se han podido mostrar las obras de la base de datos");
				alert.showAndWait();
			}
			
		}
		
		return listaObras;
	}
	

	@Override
	public List<ArtworkDAO> obtenerObra() {
		// TODO Auto-generated method stub
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		List<ArtworkDAO> listaObras = FXCollections.observableArrayList();	
		String sql="SELECT * FROM obra";
		con=Conexion.getInstance();
		if(con!=null) {
			try {
				st=con.createStatement();
				rs=st.executeQuery(sql);
				while (rs.next()) {
					this.idObra=rs.getInt(1);
					this.nombre=rs.getString(2);
					this.autor=rs.getString(3);
					this.precio=rs.getDouble(4);
					this.miComprador=ClientDAO.getAutorByArtwork(idObra);

					ArtworkDAO c = new ArtworkDAO(idObra, nombre, autor, precio, miComprador);
					listaObras.add(c);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("ERROR");
				alert.setContentText("No se han podido mostrar las obras de la base de datos");
				alert.showAndWait();
			}
			
		}
				

		
		return listaObras;
	}
	
	public ArrayList<Artwork> obtenerObraByNameObra(String filtro) {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		if (filtro!="") {
			filtro = " where "+filtro;
		}
		String sql="SELECT * FROM obra ORDER BY nombre"+filtro+"";
		ArrayList<Artwork> listaObras = new ArrayList<Artwork>();
				
		try {
			con=Conexion.getInstance();
			pst=con.prepareStatement(sql);
			rs=pst.executeQuery();
			while (rs.next()) {
				Artwork a=new Artwork();
				//byte [] fotoObra = rs.getBytes("foto");
				//id autoincrementa
				a.setNombre(rs.getString("nombre"));
				a.setAutor(rs.getString("artista"));
				a.setPrecio(rs.getDouble("precio"));
				this.miComprador=new ClientDAO(rs.getString("idCliente"));
				/**a.setFotoBytes(fotoObra);
				a.setFoto(fotoBytes != null ? ConverImagenes.converJavaFxtoImage(fotoBytes, 128, 76): null);*/
				
				listaObras.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listaObras;
	}


	   public int actualizarObra() {
		   	// INSERT o UPDATE
		   	//INSERT -> si no existe OK
		   	//En caso de ERROR -> hago un update
		      int rs=0;
		      Connection con = Conexion.getInstance();
		      
		      if (con != null) {
		         try {
		            PreparedStatement q=con.prepareStatement(ACTUALIZAROBRA);
		            q.setString(1, this.nombre);
		            q.setString(2, this.autor);
		            q.setDouble(3, this.precio);
		            q.setInt(4, this.miComprador!=null?this.miComprador.idClient:null);
		            q.setInt(5, this.idObra);
		            rs =q.executeUpdate();		
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setTitle("INFORMACION");
					alert.setContentText("Se ha podido guardar la obra en la base de datos");
					alert.showAndWait();
		         } catch (SQLException e) {
		         	// TODO Auto-generated catch block
		 			Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setTitle("ERROR");
					alert.setContentText("No se ha podido incluir la obra en la base de datos");
					alert.showAndWait();
		            e.printStackTrace();
		         }
		      }
		      return rs;
		   }	   


		@Override
		public boolean registrarObra() {
			// TODO Auto-generated method stub
			boolean registrar = false;
			
			Statement st=null;
			Connection con=null;
			
			String sql="INSERT INTO obra (`nombre`, `artista`, `precio`, `idCliente`)  values ('"+this.getNombre()+"', "
					+ "'"+this.getAutor()+"', "+this.getPrecio()+", "+this.getMiComprador().idClient+")";
			try{

				con=Conexion.getInstance();
				st=con.createStatement();
				st.executeUpdate(sql);
				registrar=true;
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("INFORMACION");
				alert.setContentText("Se ha podido incluir el cliente de la base de datos");
				alert.showAndWait();
	
			}catch (SQLException e){
				System.out.println(e);
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("ERROR");
				alert.setContentText("No se ha podido incluir el cliente de la base de datos");
				alert.showAndWait();
				
			}
			return registrar;

	
		}


		
		public int eliminarObra() {
			int rs=0;
			Connection con = Conexion.getInstance();
	
			if (con != null) {
				try {
					
					PreparedStatement q=con.prepareStatement("DELETE FROM obra WHERE idObra=?");
					q.setInt(1, this.idObra);
					rs =q.executeUpdate();

					
				} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
			}
				return rs;
			}
	
	   public static ArrayList<Artwork> getObrasByClient(int idClient) {
		      ArrayList<Artwork> result=new ArrayList<Artwork>();
		   	
		      Connection con = Conexion.getInstance();
		      if (con != null) {
		         try {
		            PreparedStatement q=con.prepareStatement(GETBYIDCLIENTE);
		            q.setInt(1, idClient);
		            ResultSet rs =q.executeQuery();
		            while(rs.next()) {
		               Artwork a=new Artwork();
		               a.setId(rs.getInt("idObra"));
		               a.setNombre(rs.getString("nombre"));
		               a.setAutor(rs.getString("artista"));
		               a.setPrecio(rs.getDouble("precio"));
		               //a.setFotoBytes(rs.getBytes("foto"));
		               
		            	
		               Client c=new Client();
		               c.setIdClient(rs.getInt("idCliente"));
		               c.setNombre(rs.getString("nombre"));
		               c.setDni(rs.getString("dni"));		               
		               c.setTelefono(rs.getInt("telefono"));
		               c.setGasto(rs.getDouble("gasto"));

		            	
		               a.setMiComprador(c);
		               result.add(a);
		            }			
		         } catch (SQLException e) {
		         	// TODO Auto-generated catch block
		            e.printStackTrace();
		         }
		      }
		      return result;
	   }
	

	@Override
	   public Client getMiautor() {
	   	// TODO Auto-generated method stub
	      if(this.miComprador==null || this.miComprador.getDni().equals("-1")) {
	         this.miComprador=ClientDAO.getAutorByArtwork(this.idObra);
	      }
	      return super.getMiComprador();
	   }

	
	   
}
