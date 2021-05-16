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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

public class ClientDAO extends Client implements IClientDAO {
	

	private final static String GETBYID = "SELECT idCliente, nombre, dni, telefono, gasto FROM cliente where idClient = ?";
	private final static String GETALL = "SELECT * FROM Cliente";


	
	private final static String SELECTCLIENTBYNAMEARTWORK="SELECT cliente.nombre,cliente.dni,cliente.teledono, cliente.gasto FROM cliente,obra WHERE cliente.idCliente=obra.idObra AND obra.nombre=?";


	
	private final static String UPDATECLIENTE = "INSERT INTO Cliente (idCliente, nombre, dni, telefono, gasto) VALUES (?,?,?,?,?) ON DUPLICATE KEY UPDATE nombre=?, dni =?, telefono=?, gasto=? ";
	
	private final static String ACTUALIZARCLIENTE = "UPDATE cliente SET nombre = ?, dni = ?, telefono = ?, gasto = ? WHERE idCliente=?";
	
		public ClientDAO (int idCliente, String nombre, String dni, int telefono, double gasto, ArrayList<Artwork> miCompras) {	
			super(idCliente, nombre, dni, telefono, gasto, miCompras);
			
		}
			
		public ClientDAO (String nombre, String dni, int telefono, double gasto) {
			super(nombre, dni, telefono, gasto);
		}
		
		public ClientDAO (Client c) {
			this.idClient = c.idClient;
			this.nombre = c.nombre;
			this.dni = c.dni;
			this.telefono = c.telefono;
			this.gasto = c.gasto;
			this.misObras= c.misObras;
		}
		


		public ClientDAO(String cliente) {
			// TODO Auto-generated constructor stub
		}
		
		public ClientDAO() {
			
		}


		public ClientDAO(int idClient) {
			super();
			// TODO Auto-generated constructor stub
			Connection con = Conexion.getInstance();
			if(con!=null) {
					PreparedStatement pst;
					try {
						pst = con.prepareStatement(GETBYID);

					pst.setInt(1, idClient);
					ResultSet rs=pst.executeQuery();
					while(rs.next()) {
						this.idClient=rs.getInt("idCliente");
						this.nombre=rs.getString("nombre");
						this.dni=rs.getString("dni");
						this.telefono=rs.getInt("telefono");
						this.gasto=rs.getDouble("gasto");

					}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
			}
		}


		@Override
		public boolean registrarCliente() {
			// TODO Auto-generated method stub
			boolean registrar = false;
			
			Statement st=null;
			Connection con=null;
			
			String sql="INSERT INTO cliente (`nombre`, `dni`, `telefono`, `gasto`)  values ('"+this.getNombre()+"', "
					+ "'"+this.getDni()+"', "+this.getTelefono()+","+this.getGasto()+")";
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
		


		//Nos devuelve a todos los clientes
		@Override
		public ObservableList<ClientDAO> obtenerCliente() {
			// TODO Auto-generated method stub
			Connection con=null;
			Statement st=null;
			ResultSet rs=null;
			ObservableList<ClientDAO> listaClientes = FXCollections.observableArrayList();	
			String sql="SELECT * FROM cliente";
			con=Conexion.getInstance();
			if(con!=null) {
				try {
					st=con.createStatement();
					rs=st.executeQuery(sql);
					while (rs.next()) {
						this.idClient=rs.getInt(1);
						this.nombre=rs.getString(2);
						this.dni=rs.getString(3);
						this.telefono=rs.getInt(4);
						this.gasto=rs.getDouble(5);
						
						ClientDAO c = new ClientDAO(idClient, nombre, dni, telefono, gasto, misObras);
						listaClientes.add(c);
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setTitle("ERROR");
					alert.setContentText("No se han podido mostrar los clientes de la base de datos");
					alert.showAndWait();
				}
				
			}
					

			
			return listaClientes;
		}
		
		
		public int actualizarCliente2() {
		      int rs=0;
		      Connection con = Conexion.getInstance();
		   	
		      if (con != null) {
		         try {
		            PreparedStatement q=con.prepareStatement(ACTUALIZARCLIENTE);
		            q.setString(1, this.nombre);
		            q.setString(2, this.dni);
		            q.setInt(3, this.telefono);
		            q.setDouble(4, this.gasto);
		            q.setInt(5, this.idClient);
		            
		            rs =q.executeUpdate();
		         	
		         } catch (SQLException e) {
		         	// TODO Auto-generated catch block
		            e.printStackTrace();
		         }
		      }
		      return rs;
		}
		
		public int actualizarCliente() {

		      int rs=0;
		      Connection con = Conexion.getInstance();
		   	
		      if (con != null) {
		         try {
		            PreparedStatement q=con.prepareStatement(UPDATECLIENTE);
		            q.setInt(1, this.idClient);
		            q.setString(2, this.nombre);
		            q.setString(3, this.dni);
		            q.setInt(4, this.telefono);
		            q.setDouble(5, this.gasto);
		            q.setString(6, this.nombre);
		            q.setString(7, this.dni);
		            q.setInt(8, this.telefono);
		            q.setDouble(9, this.gasto);
		            
		            rs =q.executeUpdate();
		         	

		         } catch (SQLException e) {
		         	// TODO Auto-generated catch block
		            e.printStackTrace();
		         }
		      }
		      return rs;
		}
		
		
		public int eliminar() {
			int rs=0;
			Connection con = Conexion.getInstance();
	
			if (con != null) {
				try {
					
					PreparedStatement q=con.prepareStatement("DELETE FROM cliente WHERE IdCliente=?");
					q.setInt(1, this.idClient);
					rs =q.executeUpdate();
					this.dni="";
					this.nombre="";
					this.idClient=-1;
					this.gasto=-1;
					this.telefono=-1;
					this.misObras=null;
					
				} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
			}
				return rs;
			}


		
		   public static Client getAutorByArtwork(int idClient) {
			      Client result=new Client();
			      Connection con = Conexion.getInstance();
			      if (con != null) {
			         try {
			            PreparedStatement q=con.prepareStatement(SELECTCLIENTBYNAMEARTWORK);
			            q.setInt(1, idClient);
			            ResultSet rs=q.executeQuery();
			            while(rs.next()) {
			               result.setDni(rs.getString("nombre"));
			               result.setNombre(rs.getString("dni"));
			               result.setTelefono(rs.getInt("telefono"));
			               result.setGasto(rs.getDouble("gasto"));
			            }
			         } catch (SQLException e) {
			         	// TODO Auto-generated catch block
			            e.printStackTrace();
			         }
			      }
			   	
			      return result;
			   }
		   
		   public Client getClientes() {
			      Client result=new Client();
			      Connection con = Conexion.getInstance();
			      if (con != null) {
			         try {
			            PreparedStatement q=con.prepareStatement(GETALL);
			            q.setInt(1, idClient);
			            ResultSet rs=q.executeQuery();
			            while(rs.next()) {
			               result.setDni(rs.getString("nombre"));
			               result.setNombre(rs.getString("dni"));
			               result.setTelefono(rs.getInt("telefono"));
			               result.setGasto(rs.getDouble("gasto"));
			               
			            }
			         } catch (SQLException e) {
			         	// TODO Auto-generated catch block
			            e.printStackTrace();
			         }
			      }
			   	
			      return result;
			   }
		   
		   public static List<ClientDAO> AllClients(){
			   List<ClientDAO> clientes = new ArrayList<ClientDAO>();
			   Connection con = Conexion.getInstance();
			   if (con != null) {
				   try {
					PreparedStatement pst = con.prepareStatement(GETALL);
					ResultSet rs = pst.executeQuery();
		            while(rs.next()) {
		            	Client c = new Client();	
			               c.setDni(rs.getString("nombre"));
			               c.setNombre(rs.getString("dni"));
			               c.setTelefono(rs.getInt("telefono"));
			               c.setGasto(rs.getDouble("gasto"));
			               clientes.add((ClientDAO) c);
		            }
				   	} catch (SQLException e) {
				   		// TODO Auto-generated catch block
				   		e.printStackTrace();
				   		e.printStackTrace();
				   		Alert alert = new Alert(Alert.AlertType.ERROR);
				   		alert.setHeaderText(null);
				   		alert.setTitle("ERROR");
				   		alert.setContentText("No se han podido mostrar los clientes de la base de datos");
				   		alert.showAndWait();
				   		}
			   		}
			   		return clientes;
			  }
		   
		   
		   public Client getClientesId() {
			      Client result=new Client();
			      Connection con = Conexion.getInstance();
			      if (con != null) {
			         try {
			            PreparedStatement q=con.prepareStatement(GETALL);
			            q.setInt(1, idClient);
			            ResultSet rs=q.executeQuery();
			            while(rs.next()) {
			               result.setDni(rs.getString("nombre"));
			               result.setNombre(rs.getString("dni"));
			               result.setTelefono(rs.getInt("telefono"));
			               result.setGasto(rs.getDouble("gasto"));
			               
			            }
			         } catch (SQLException e) {
			         	// TODO Auto-generated catch block
			            e.printStackTrace();
			         }
			      }
			   	
			      return result;
			   }


		@Override
		public boolean eliminarClientePorId() {
			// TODO Auto-generated method stub
			return false;
		}


		@Override
		public ArrayList<Artwork> getMisobras() {
			// TODO Auto-generated method stub
			return null;
		}
		

		
		
}
