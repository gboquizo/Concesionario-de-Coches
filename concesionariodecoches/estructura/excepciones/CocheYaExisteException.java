package concesionariodecoches.estructura.excepciones;

public class CocheYaExisteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CocheYaExisteException(String string) {
		super(string);
	}

}