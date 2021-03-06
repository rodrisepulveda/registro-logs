package awto.registrologs.exeption;

/**
 * Excepcion que se lanza cuando la petición es incorrecta.
 * 
 * @author Rodrigo Sepúlveda.
 *
 */
public class BadRequestRuntime extends RuntimeException {

	/**
	 * Serie.
	 */
	private static final long serialVersionUID = -7866402565873972749L;

	/**
	 * Método costructor de la clase.
	 * 
	 * @param mensaje
	 *            mensaje asociado a la excepción.
	 */
	public BadRequestRuntime(String mensaje) {

		super(mensaje);

	}

}
