package com.anramirez.primerProyecto.utils;

import java.io.File;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;



@SuppressWarnings("exports")
public class Conexion {
	
	private static Connection conn = null;
	/**private final static String DB="galeria";
    private final static String URL = "jdbc:mysql://localhost:3306/"+DB+"";
    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final static String USUARIO = "root";
    private final static String PASSWORD = "";*/
	private final static String SERVER = ConexionXML.getConectionInfo("server");
	private final static String DATABASE = ConexionXML.getConectionInfo("database");
	private final static String USERNAME = ConexionXML.getConectionInfo("user");
	private final static String PASSWORD = ConexionXML.getConectionInfo("password");
    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private final static String file = "galeria.xml";

	
	@SuppressWarnings("unused")
	private static void conexion(){
	    try{
	 Class.forName(DRIVER);
	 conn=DriverManager.getConnection(SERVER+"/"+DATABASE,USERNAME,PASSWORD);
	    }
	    catch(ClassNotFoundException | SQLException e){
	 e.printStackTrace();
	    }
	}
	
	public static Connection getInstance(){
		 
	    if (conn == null){
	 conexion();
	    }
	 
	    return conn;
	}
	
	
	public static void cerrarConexion()
		{
		/**try {
			if (conn != null && !conn.isClosed())
				{
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}


	
	
}