package com.anramirez.primerProyecto.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


import com.anramirez.primerProyecto.model.Artwork;
import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class ConverImagenes {
	
	private File loadFile (String url) {
		return new File(url);
	}
	
	private byte [] toBytes(String url) {
		File file = loadFile(url);
		byte [] imagenBytes = null;
		
		if (file!=null) {
			try {
				imagenBytes = Files.readAllBytes(file.toPath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return imagenBytes;
	}
	
	public static Image getImage (String fileName) {
		return new Image(ConverImagenes.class.getResource("/imagenes"+fileName+".png").toString());
	}
	
	/**public static HBox generateHBox(Artwork artwork) {
		Label label = new Label(artwork.getNombre());
		label.setFont(new Font(16));
		label.setTextFill(Color.BLACK);
		HBox hbox = new HBox(generateImageView(artwork.getFoto(),64),label);
		hbox.setSpacing(10);
		hbox.setAlignment(Pos.CENTER_LEFT);
		return hbox;
	}*/
	
	public static ImageView generateImageView(Image image, int width) {
		ImageView imagen = new ImageView(image);	
		imagen.setPreserveRatio(true);
		imagen.setFitWidth(width);

		return imagen;
	}
	
	public static Image converJavaFxtoImage(byte[] raw, int width, int height) {
		WritableImage imagen = new WritableImage(width, height);
		try {
		ByteArrayInputStream bis = new ByteArrayInputStream(raw);
		BufferedImage read = ImageIO.read(bis);
		imagen = SwingFXUtils.toFXImage(read, null);
		}catch (IOException ex) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("ERROR");
			alert.setContentText("No se ha podido convertir la foto.");
			alert.showAndWait();
		}
		return imagen;
		
	}

}
