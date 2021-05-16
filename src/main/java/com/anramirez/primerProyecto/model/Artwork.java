package com.anramirez.primerProyecto.model;

import java.time.LocalDate;

import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class Artwork {
	
	protected int idObra;
	protected String nombre;
	protected String autor;
	protected double precio;
	protected Client miComprador;
	//protected Image foto;
	//protected byte [] fotoBytes;
	//private Image foto;
	
	public Artwork(int id, String nombre, String autor, double precio, 
			 Client miComprador/**, Image foto, byte[] fotoBytes*/) {
		super();
		this.idObra = id;
		this.nombre = nombre;
		this.autor = autor;
		this.precio = precio;
		this.miComprador = miComprador;
		//this.foto = foto;
		//this.fotoBytes = fotoBytes;
	}
	
	
	
	protected Artwork(int idObra, String nombre, String autor, double precio,
			 Client miComprador, Image foto) {
		super();
		this.idObra = idObra;
		this.nombre = nombre;
		this.autor = autor;
		this.precio = precio;

		this.miComprador = miComprador;
		//this.foto = foto;
	}

	protected Artwork(int id, String nombre, String autor, double precio,
			 Client miComprador, byte[] fotoBytes) {
		super();
		this.idObra = id;
		this.nombre = nombre;
		this.autor = autor;
		this.precio = precio;
		this.miComprador = miComprador;

	}
	
	public Artwork(String nombre, String autor, double precio) {
		super();
		this.nombre = nombre;
		this.autor = autor;
		this.precio = precio;
		//this.fotoBytes = fotoBytes;
	}
	
	public Artwork(String nombre, String autor, double precio, Client miComprador) {
		super();
		this.nombre = nombre;
		this.autor = autor;
		this.precio = precio;
		this.miComprador = miComprador;
	}



	public Artwork() {

	}



	public Artwork(int idObra, String nombre, String autor, double precio) {
		// TODO Auto-generated constructor stub
		this.idObra=idObra;
		this.nombre=nombre;
		this.autor=autor;
		this.precio=precio;
		
	}



	public int getId() {
		return idObra;
	}

	public void setId(int idObra) {
		this.idObra = idObra;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}


	public Client getMiComprador() {
		return miComprador;
	}

	public void setMiComprador(Client miComprador) {
		this.miComprador = miComprador;
	}
	
	

	public int getIdObra() {
		return idObra;
	}



	public void setIdObra(int idObra) {
		this.idObra = idObra;
	}


	/*
	 * @Override public String toString() { return "ID: "+getId()+"\n"+
	 * "Nombre de la Obra: "+getNombre()+"\n"+ "Autor: "+getAutor()+"\n"+
	 * "Precio: "+getPrecio()+"\n"+ "Propietario: "+getMiComprador(); }
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autor == null) ? 0 : autor.hashCode());
		result = prime * result + idObra;
		result = prime * result + ((miComprador == null) ? 0 : miComprador.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		long temp;
		temp = Double.doubleToLongBits(precio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Artwork other = (Artwork) obj;
		if (autor == null) {
			if (other.autor != null)
				return false;
		} else if (!autor.equals(other.autor))
			return false;
		if (idObra != other.idObra)
			return false;
		if (miComprador == null) {
			if (other.miComprador != null)
				return false;
		} else if (!miComprador.equals(other.miComprador))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (Double.doubleToLongBits(precio) != Double.doubleToLongBits(other.precio))
			return false;
		return true;
	}



	public ObservableList<ArtworkDAO> obtenerObraByIdCliente() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "ID: "+getId()+"\n"+
				   "Nombre de la Obra: "+getNombre()+"\n"+
				   "Autor: "+getAutor()+"\n"+
				   "Precio: "+getPrecio();
	}
	

}
