package projectProgramacion;

public abstract class Persona {

	protected String nombre;
	protected String apellido;
	protected int edad;



	/* Constructores */

	Persona(String nombre, String apellido, int edad) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
	}


	Persona() {
		this.createPerson();
	}



	/* Metodos Abstractos */

	public abstract void createPerson();
	public abstract void createAccount();


}
