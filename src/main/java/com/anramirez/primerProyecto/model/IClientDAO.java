package com.anramirez.primerProyecto.model;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;

public interface IClientDAO {
	
	public boolean registrarCliente ();
	public ObservableList<ClientDAO> obtenerCliente();
	int eliminar();
	boolean eliminarClientePorId();
	public int actualizarCliente();
	ArrayList<Artwork> getMisobras();

	
}
