package projectProgramacion;

import java.util.ArrayList;

public class Articulo {

	private String nombre;
	private float precio;
	private int stock;

	private String autor;


	/* Constructores */

	Articulo(String nombre, float precio, int stock, String autor) {
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
		this.autor = autor;
	}


	/* Metodos */

	public void downStock() {
		this.stock--;
	}


	public String getName() { return this.nombre; }

	public float getPrice() { return this.precio; }

	public int getStock() { return this.stock; }

	public String getAutor() { return this.autor; }

}
