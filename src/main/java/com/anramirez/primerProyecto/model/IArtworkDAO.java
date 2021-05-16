package com.anramirez.primerProyecto.model;

import java.util.ArrayList;

import javafx.collections.ObservableList;

public interface IArtworkDAO {
	

	public ObservableList<ArtworkDAO> obtenerObraByIdObra();
	int eliminarObra();
	Client getMiautor();
	ObservableList<ArtworkDAO> obtenerObra();
	public ArrayList<Artwork> obtenerObraByIdCliente(int idCliente);
	boolean registrarObra();
	
	
}
