package projectProgramacion;

import java.util.ArrayList;

public class Carrito {

	private ArrayList<Articulo> carrito;
	private float totalPagar;



	/* Constructores */

	Carrito() {
		this.carrito = new ArrayList<Articulo>();
		this.setTotalPagar(0);
	}



	/* Metodos */

	public void addArticle(Articulo article) {

		if(article.getStock() != 0) {

			article.downStock();

			this.carrito.add(article);

			this.calculateCost();

			System.out.println("Articulo agregado al carrito.");		

		} else {
			System.out.println("No hay stock disponible.");	
		}

	}



	private void calculateCost() {

		this.setTotalPagar(0);

		for(int i = 0; i < this.carrito.size(); i++) {
			this.totalPagar += this.carrito.get(i).getPrice();
		}

	}



	public void showCart() {

		if(this.carrito.size() != 0) {

			System.out.println("\n- Tu Carrito -\n");
			for(int i = 0; i < this.carrito.size(); i++) {
				System.out.println("Articulo -> " + this.carrito.get(i).getName());
				System.out.println("Precio -> $" + this.carrito.get(i).getPrice() + "\n----------------------");
			}

			System.out.println("Total a pagar: $" + this.getTotalPagar());

		} else {
			System.out.println("Tu carrito esta vacio!");
		}


	}



	public float getTotalPagar() { return totalPagar; }
	private void setTotalPagar(float totalPagar) { this.totalPagar = totalPagar; }





}
