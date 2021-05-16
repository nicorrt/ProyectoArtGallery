package com.anramirez.primerProyecto.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.control.Alert;

public class Validadores {
	
    public static int checkNumericoInt(String numero){
        int resultado = 0; //Valor predeterminado 0
        try{
            if(numero != null){
                resultado = Integer.parseInt(numero);
            }
        }catch(NumberFormatException nfe){

        }
        return resultado;
    }
    public static double checkNumericoDou(String numero){
        double resultado = 0; //Valor predeterminado 0
        try{
            if(numero != null){
                resultado = Integer.parseInt(numero);
            }
        }catch(NumberFormatException nfe){

        }
        return resultado;
    }
    
 
    public static String validarDNI(String dni) {
    	
          Pattern pat = Pattern.compile("[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKET]");
          Matcher mat = pat.matcher(dni);
    	if(mat.matches()) {
    		return dni;
           }else {        	   
        	   dni="";
				Alert alert2 = new Alert(Alert.AlertType.ERROR);
				alert2.setHeaderText(null);
				alert2.setTitle("ERROR");
				alert2.setContentText("Debe completar con un dni real");
				alert2.showAndWait();
           }
    	return dni;
    }

}
