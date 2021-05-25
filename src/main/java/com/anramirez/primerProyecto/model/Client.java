package com.anramirez.primerProyecto.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Client {
	
	protected int idClient;
	protected String nombre;
	protected String dni;
	protected int telefono;
	protected double gasto;
	protected ArrayList<Artwork> misObras;

	
	
	public Client(int idClient, String nombre, String dni, int telefono, double gasto,
			ArrayList<Artwork> misObras) {
		super();
		this.idClient = idClient;
		this.nombre = nombre;
		this.dni = dni;
		this.telefono = telefono;
		this.misObras = misObras;
		this.gasto = gasto;
	}

	public Client(int idClient, String nombre, String dni, int telefono, double gasto) {
		super();
		this.idClient = idClient;
		this.nombre = nombre;
		this.dni = dni;
		this.telefono = telefono;
		this.gasto = gasto;

	}
	
	

	public Client(String nombre, String dni, int telefono, double gasto) {
		super();
		this.nombre = nombre;
		this.dni = dni;
		this.telefono = telefono;
		this.gasto = gasto;
	}

	public Client() {
		this (-1, "", "", 0, 0.0, null);
	}
	
	


	public Client(int telefono, double gasto) {
		this.telefono= telefono;
		this.gasto=gasto;
	}

	public Client(int telefono) {
		// TODO Auto-generated constructor stub
		
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public double getGasto() {
		return gasto;
	}

	public void setGasto(double gasto) {
		this.gasto = gasto;
	}

	public ArrayList<Artwork> getMiCompras() {
		return misObras;
	}

	public void setMiCompras(ArrayList<Artwork> misObras) {
		this.misObras = misObras;
	}

	@Override
	public String toString() {
		return "ID: "+getIdClient()+"\n"+
			   "Nombre Completo: "+getNombre()+"\n"+
			   "DNI: "+getDni()+"\n"+
			   "Teléfono: "+getTelefono()+"\n"+
			   "Gasto: "+getGasto()+"\n"+
			   "Obras Compradas: "+getMiCompras();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		result = prime * result + idClient;
		result = prime * result + ((misObras == null) ? 0 : misObras.hashCode());
		result = prime * result + telefono;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		if (idClient != other.idClient)
			return false;
		if (misObras == null) {
			if (other.misObras != null)
				return false;
		} else if (!misObras.equals(other.misObras))
			return false;
		if (telefono != other.telefono)
			return false;
		return true;
	}


	


	
	

}
