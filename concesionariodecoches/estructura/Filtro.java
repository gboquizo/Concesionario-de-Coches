package concesionariodecoches.estructura;

import java.io.File;

import javax.swing.filechooser.FileFilter;


/**
 * Clase que filtra el tipo de archivo por extension
 * @author Guillermo Boquizo Sánchez
 * @version 1.0
 *
 */
public class Filtro extends FileFilter {

	
	/**
	 * Variable que almacena la extension
	 */
	private String extension;
	
	/**
	 * Variable que guarda la descripcion de la extension
	 */
	private String descripcion;
	
	
	
	/**
	 * Constructor
	 * @param extension extension especificada
	 * @param descripcion descripcion de la extension
	 */
	public Filtro(String extension, String descripcion) {
		this.extension = extension;
		this.descripcion = descripcion;
	}

	
	/**
	 *Metodo que comprueba si los archivos son validos
	 *@param file archivo seleccionado
	 *@return devuelve true si la extension es correcta, si no la lleva la añade
	 */
	@Override
	public boolean accept(File file) {
		if(file.isDirectory()) {
		return true;
		}
		return file.getName().endsWith(extension);
	}

	
	/**
	 * Metodo que devuelve la descripcion
	 * @return devuelve la descripcion respecto a una extension
	 */
	@Override
	public String getDescription() {
		return descripcion + String.format(" (*%s)", extension);
	}

	/**
	 * Metodo que devuelve la extension
	 * @return la extension
	 */
	public String getExtension() {
		return extension;
	}

}
