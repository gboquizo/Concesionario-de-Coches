                                                                                                                 package concesionariodecoches.estructura;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Fichero implements Serializable {

	private static final long serialVersionUID = 1L;

	public static Concesionario concesionario = new Concesionario();

	/**
	 * 
	 * Permite la lectura de un fichero y cargar al programa su contenido, de
	 * tipo object
	 * 
	 * @param archivo
	 * @throws IOException
	 * @throws ClassNotFoundException
	 *             Exception que se lanza si el objeto destino pertenece a la
	 *             misma clase que el objeto del fichero
	 */
	public static Concesionario abrir(File archivo) throws IOException, ClassNotFoundException {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo))) {
			concesionario = (Concesionario) in.readObject();
			return concesionario;
		}
		
	}

	/**
	 * 
	 * Método que guarda un objeto pidiendo el nombre de archivo a crear
	 * 
	 * @param objeto
	 *            objeto a guardar
	 * 
	 * @param nombre
	 * @throws IOException
	 *             Excepción lanzada al finalizar el flujo
	 */
	public static void guardarComo(Object objeto, File nombre) throws IOException {
		try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(nombre)))) {
			out.writeObject(objeto);
		}
	}

	/**
	 * Método guardar que guarda el contenido de un archivo ya existente
	 *
	 * @param objeto
	 *            objeto que queremos guardar
	 * @throws IOException
	 *             Exception que lanza cuando el flujo acaba
	 */
	public static void guardar(Object objeto, File fichero) throws IOException {
		try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fichero)))) {
			out.writeObject(objeto);
		}
	}
}
