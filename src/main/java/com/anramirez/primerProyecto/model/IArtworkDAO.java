package com.anramirez.primerProyecto.model;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;

public interface IArtworkDAO {
	

	public List<ArtworkDAO> obtenerObraByIdObra();
	int eliminarObra();
	Client getMiautor();
	List<ArtworkDAO> obtenerObra();
	public ArrayList<Artwork> obtenerObraByIdCliente(int idCliente);
	boolean registrarObra();
	
	
}
