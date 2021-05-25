package com.anramirez.primerProyecto.model;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;

public interface IClientDAO {
	
	public boolean registrarCliente();
	public List<ClientDAO> obtenerCliente();
	int eliminar();
	boolean eliminarClientePorId();
	public int actualizarCliente2();
	ArrayList<Artwork> getMisobras();
	
}
