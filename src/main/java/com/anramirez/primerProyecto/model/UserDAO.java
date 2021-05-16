package com.anramirez.primerProyecto.model;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.anramirez.primerProyecto.utils.Conexion;

import javafx.scene.control.Alert;

public class UserDAO extends User{
	
	public int login (String usuario, String pass) {
		
		Connection conn=null;
		PreparedStatement ps;
		ResultSet rs;
		int state=-1;
		
		try {
		
			conn = Conexion.getInstance();
		
		if(conn!=null) {
			String sql = "Select * from user where binary usuario=? and pass=?";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, usuario);
			ps.setString(2, pass);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				state= 1;
			}else {
				state= 0;
			}
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("ERROR");
			alert.setContentText("Hubo un error al conectar con la base de datos");
			alert.showAndWait();
			}
		}catch(HeadlessException | SQLException ex) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("ERROR");
			alert.setContentText("Hubo un error en la ejecución aquí");
			alert.showAndWait();
		}finally {
			/*try {
				if(conn!=null) {
					Conexion.getInstance().close();
				}
				
			}catch (SQLException ex) {
				System.err.println(ex.getMessage());
			}*/
		}
		
	return state;
	}
	
	
}

