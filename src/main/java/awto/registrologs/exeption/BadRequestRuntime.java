package awto.registrologs.exeption;

public class BadRequestRuntime extends RuntimeException {

	/**
	 * Serie.
	 */
	private static final long serialVersionUID = -7866402565873972749L;

	public BadRequestRuntime(String mensaje) {

		super(mensaje);

	}

}
