package awto.registrologs.exeption;

/**
 * Excepción que se lanza cuando un recurso solicitado no está disponible.
 * 
 * @author Rodrigo Sepúlveda.
 *
 */
public class NotFoundRuntime extends RuntimeException {

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
	public NotFoundRuntime(String mensaje) {

		super(mensaje);

	}

}
