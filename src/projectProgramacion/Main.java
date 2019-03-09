package projectProgramacion;

import java.util.List;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.LinkedList;


public class Main {

	//Maximo de articulos recientes a mostrar.
	static final int maxRecentArticles = 5;

	//Guarda los ultimos articulos publicados.
	static LinkedList<Articulo> articulosRecientes = new LinkedList<Articulo>();


	
	
	
	public static void main(String[] args) {

		//Array con los usuarios.
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

		//Lista con los articulos
		List<Articulo> articulos = new ArrayList<Articulo>();
		
		
		//Test user
		Usuario lautaro = new Usuario("Lautaro", "Cepeda", 23);
		usuarios.add(lautaro);
		lautaro.createAccount();





		//Inicia el sistema.
		menuPrincipal(usuarios, articulos);
	}


	


	public static void mostrarArticulosRecientes(LinkedList<Articulo> cola) {
		int i = 0;

		int colaLength = cola.size();

		if(cola.size() != 0) {
			System.out.println("\n- " + (Main.maxRecentArticles - 1) + " Articulos mas recientes -");

			while(i != Main.maxRecentArticles && colaLength != 0){

				System.out.println( cola.get(i).getName() + " -> $" + cola.get(i).getPrice());
				i++;
				colaLength--;
			}			
		} else {
			System.out.println("Aun no se encuentran articulos recientes...");
		}

	}



	public final static void clearConsole() {
		try {
			for (int i = 0; i < 50; ++i) System.out.println();
		} catch (Exception e) {
			System.out.println("Error -> " + e);
		}
	}



	public static void menuPrincipal(ArrayList<Usuario> usuarios, List<Articulo> articulos) {

		int opcion;
		Scanner scanner = new Scanner(System.in);
		System.out.println("\n=== MENU ===");
		System.out.println("1) Iniciar Sesion.");
		System.out.println("2) Registrarse.");
		System.out.println("3) Exit.\n");
		System.out.print("Opcion -> ");	
		opcion = scanner.nextInt();


		switch(opcion) {

		case 1: 

			/* Iniciar Sesion */

			boolean flag = false;
			int intentos = 0;

			//Verifica si hay usuarios.
			if(usuarios.size() != 0) {

				do {

					System.out.print("Usuario: ");	
					String username = scanner.next();

					System.out.println("Contraseña: ");
					String password = scanner.next();

					for(int i = 0; i < usuarios.size(); i++) {

						if( usuarios.get(i).verifiedAccount(username, password) ) {
							clearConsole();
							System.out.println("Sesionado Correctamente.");
							System.out.println("Bienvenido " + username + "!\n");
							flag = true;
							menuTienda(usuarios, usuarios.get(i), articulos);
						} else {
							System.out.println("Datos Incorrectos.");


							//Maximo intentos de inicio de sesion. Da opcion para regresar al Menu Principal.
							if(intentos == 3) {
								System.out.println("Desea volver al Menu Principal? S/n");
								String backMenu = scanner.nextLine();

								if(backMenu.equals("S") || backMenu.equals("s")) {
									clearConsole();
									menuPrincipal(usuarios, articulos);
								} else {
									intentos--;
								}
							}
						}
					}

					intentos++;

				} 	while(!flag);

			} else {
				System.out.println("No se encuentran usuarios registrados. Registrate!");
				menuPrincipal(usuarios, articulos);
			}

			break;


		case 2:

			/* Registrarse */

			Usuario user = new Usuario();
			user.createAccount();

			usuarios.add(user);

			menuPrincipal(usuarios, articulos);

			break;


		case 3:

			/* Salir */

			System.out.println("Bye :D");	
			System.exit(0);
			break;

		default:
			break;


		}

	}


	
	public static void menuTienda(ArrayList<Usuario> usuarios, Usuario user, List<Articulo> articulos) {


		//Verifica si el usuario inicio sesion.
		if( user.isLogIn() ) {

			int opcion;
			Scanner scanner = new Scanner(System.in);
			System.out.println("\n=== MENU ===");
			System.out.println("1) Agregar un Articulo.");
			System.out.println("2) Ir a la Tienda.");
			System.out.println("3) Ver mi carrito.");
			System.out.println("4) Ver articulos recientes.");
			System.out.println("5) Cerrar Sesion.\n");
			System.out.print("Opcion -> ");	
			opcion = scanner.nextInt();


			switch(opcion) {

			case 1: 

				/* Agregar Articulo */

				System.out.println("Nombre del Articulo: ");
				String nombre = scanner.next();

				System.out.println("Precio: ");
				float precio = scanner.nextFloat();

				System.out.println("Stock: ");
				int stock = scanner.nextInt();

				Articulo art = new Articulo(nombre, precio, stock, user.getUsername());


				//Agrega el articulo a los articulos globales.
				articulos.add(art);

				//Agrega el articulo a los mas recientes.
				addArticuloReciente(art);


				System.out.println("Articulo agregado correctamente.");

				menuTienda(usuarios, user, articulos);


				break;


			case 2:

				/* Mostrar Tienda */

				clearConsole();

				if(articulos.size() != 0) {
					for(int i = 0; i < articulos.size(); i++) {

						System.out.println("\nID -> " + i);
						System.out.println("Articulo -> " + articulos.get(i).getName());
						System.out.println("Precio -> $" + articulos.get(i).getPrice());
						System.out.println("Stock -> " + articulos.get(i).getStock());
						System.out.println("Autor -> " + articulos.get(i).getAutor() + "\n");

					}

				} else {
					System.out.println("No hay articulos en la tienda! :(");
				}

				/* Agregar articulo al carrito */
				int tiendaOpcion;
				int idArticuloComprar;
				do {
					System.out.println("\n=== MENU ===");
					System.out.println("1) Comprar");
					System.out.println("2) Volver");
					System.out.print("Opcion -> ");	
					tiendaOpcion = scanner.nextInt();

				} while(tiendaOpcion != 1 && tiendaOpcion != 2);

				if(tiendaOpcion == 1) {

					System.out.println("Ingresa el ID del articulo: ");
					idArticuloComprar = scanner.nextInt();

					try{
						user.carrito.addArticle(articulos.get(idArticuloComprar));
					}catch(Exception e) {
						System.out.println("No existe ningun articulo con ese ID.");
					}


				}

				menuTienda(usuarios, user, articulos);
				break;

			case 3:

				/* Mostrar Carrito */
				user.carrito.showCart();

				menuTienda(usuarios, user, articulos);


				break;

			case 4:
				/* Mostrar articulos mas recientes */
				Main.mostrarArticulosRecientes(Main.articulosRecientes);

				menuTienda(usuarios, user, articulos);
				break;


			case 5:


				/* Deslogearse */

				user.logOut();

				clearConsole();

				menuPrincipal(usuarios, articulos);

				break;

			default:
				break;

			}
		}
	}



	private static void addArticuloReciente(Articulo art) {
		Main.articulosRecientes.add(art);

		if(Main.articulosRecientes.size() == Main.maxRecentArticles) {
			Main.articulosRecientes.poll();
		}
	}

	
	
}
