package com.anramirez.primerProyecto.utils;

import com.anramirez.primerProyecto.model.Artwork;

import javafx.scene.control.ListCell;

public class IconCell extends ListCell<Artwork> {
	
	/**@Override
	public void updateItem(Artwork foto, boolean empty) {
		
		super.updateItem(foto, empty);
		
		if(empty) {
			setText(null);
			setGraphic(null);
		}else {
			setGraphic(ConverImagenes.generateHBox(foto));
		}
	}*/
}
