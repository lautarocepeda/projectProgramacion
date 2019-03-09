package projectProgramacion;

import java.util.Scanner;


public class Usuario extends Persona {

	private String nombreUsuario;
	private String clave;
	private boolean sesionado;
	protected Carrito carrito = new Carrito();


	/* Constructores */

	Usuario(String nombre, String apellido, int edad) {	
		super(nombre, apellido, edad);
		this.sesionado = false;
		this.nombreUsuario = (super.nombre.substring(0, 3) + super.apellido.substring(0, 3)).toUpperCase();
	}

	Usuario() {
		this.sesionado = false;
		this.nombreUsuario = (super.nombre.substring(0, 3) + super.apellido.substring(0, 3)).toUpperCase();
	}




	/* Metodos */

	public void showData() {
		System.out.println( "Nombre: " + nombre );
		System.out.println( "Apellido: " + apellido );
		System.out.println( "Edad: " + edad );

		System.out.println( "Nombre Usuario: " + this.nombreUsuario );
		System.out.println( "Clave: " + this.clave );
	}



	public void createPerson() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Ingresa tu nombre: ");
		this.nombre = scanner.nextLine();

		System.out.println("Ingresa tu apellido: ");
		this.apellido = scanner.nextLine();

		try {
			System.out.println("Ingresa tu edad: ");
			this.edad = scanner.nextInt();
		} catch(Exception error) {
			System.out.println("Error -> " + error);
		}
	}



	public void createAccount() {

		System.out.println("Nombre Usuario: " + this.getUsername());

		this.setPassword();
		this.showData();
	}



	public boolean verifiedAccount(String usuario, String clave) {

		if( usuario.equals(this.nombreUsuario) && clave.equals(this.clave) ) {
			this.sesionado = true;
			return true;
		} else {
			return false;
		}

	}



	public void setPassword() {
		Scanner scanner = new Scanner(System.in);

		String password = null;
		String rePassword = null;
		int repeat = 0;

		do {
			System.out.println("Ingresa tu clave: ");
			password = scanner.nextLine().toString();

			System.out.println("Re-ingresa tu clave: ");
			rePassword = scanner.nextLine().toString();

			try {
				this.validatePassword(password, rePassword);
			}catch(ExceptionValidatePassword e) {
				System.out.println(e.getMessage());
			}

			repeat++;

		} while(!password.equals(rePassword));

		if(password.equals(rePassword)) {
			this.clave = rePassword;
		}

	}


	
	private boolean validatePassword(String password, String rePassword) throws ExceptionValidatePassword {
		if(!password.equals(rePassword)) {
			throw new ExceptionValidatePassword("Tus claves no cohinciden. Intenta de nuevo...\n");
		} else {
			return true;
		}	
	}


	
	public void logOut() { this.sesionado = false; }
	public boolean isLogIn() { return this.sesionado; }
	public String getUsername() { return this.nombreUsuario; }
	public String getPassword() { return this.clave; }

}
