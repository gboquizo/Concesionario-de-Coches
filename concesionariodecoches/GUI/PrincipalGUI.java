package concesionariodecoches.GUI;

import java.awt.EventQueue;
import java.awt.HeadlessException;
import concesionariodecoches.estructura.*;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.NoSuchElementException;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 
 * <h1>Concesionario de coches con Ficheros y GUI</h1>
 * <p>
 * Sobre la versión anterior del Concesionario de coches con excepciones del
 * siguiente repositorio,<br />
 * crea otra versión añadiéndole tanto Ficheros como el entorno gráfico para la
 * comunicación con el usuario.<br />
 * Nos fijaremos en el Notepad para hacerlo lo más parecido posible.
 * </p>
 * <h3>Con respecto a los ficheros:</h3>
 * <ul>
 * <ol>
 * <li>Añade un menú Archivo Tendrá las opciones típicas: nuevo, abrir, guardar,
 * guardar como...</li>
 * <li>El concesionario podrá guardarse en un fichero (guardar y guardar
 * como...)</li>
 * <li>El concesionario podrá leerse de un fichero (abrir)</li>
 * <li>Podrá crearse un concesionario nuevo (nuevo)
 * <li>En caso de que se pueda perder información del concesionario, se le
 * preguntará al usuario (nuevo, abrir, guardar como...)</li>
 * <li>Se le añadirá la extensión ".obj". Deberás utilizar la clase File, que es
 * una representación abstracta de los nombres de los ficheros y
 * directorios.</li>
 * <li>Podrás usar los métodos: File file = new File(String pathname)
 * file.getPath(); file.exists();</li>
 * <li>PD: Utiliza el ARM para evitar el tener que utilizar el close() de los
 * flujos PPD: Procura colocar la creación del flujo en la misma línea, evitando
 * el uso de variables innecesarias</li>
 * </ol>
 * </ul>
 * 
 * <h3>Con respecto al GUI:</h3>
 * <ul>
 * <ol>
 * <li>Paquetes. Deberás olvidarte del paquete utiles. Necesitas un paquete sólo
 * para el GUI, donde tendrás la clase Principal.java que abrirá la ventana
 * principal.</li>
 * <li>Recuerda que las excepciones han de estar en el paquete que las
 * crean.</li>
 * <li>La página principal tendrá como título el fichero abierto ("Sin título"
 * en caso de no tener asociado ningún fichero). Será al estilo del
 * Notepad.</li>
 * <li>Una barra de menú (JMenuBar) con los siguientes menús (JMenu):</li>
 * <ol>
 * <li>Ficheros</li>
 * <li>Coches</li>
 * <li>Ayuda</li>
 * <li>Tendrá las teclas de acceso rápido asociadas a cada elemento del menú,
 * así como mnemotécnicos.</li>
 * <li>Añade también separadores entre elementos del menú.</li>
 * <li>Será al estilo del Notepad. (como en el recurso de los alumnos Juan
 * Antonio Romero y Elisa Navarro)</li>
 * </ol>
 * <li>Un diálogo para trabajar con ficheros (JFileChooser). Ha de usar un
 * filtro para visualizar los ficheros con extensión ".obj"</li>
 * <li>Un diálogo para trabajar con Coches (JDialog).</li>
 * <li>El diálogo contendrá:</li>
 * <ol>
 * <li>Una caja de texto (JTextField) debidamente etiquetada(JLabel) para las
 * matrículas Al perder el foco cambiará el color si la matrícula no es válida.
 * Al editar, la matrícula siempre lo hará en negro.</li>
 * <li>Una lista (JList) para el modelo y otra para la marca. Las listas han de
 * estar sincronizadas</li>.
 * <li>Tres radio buttons con los tres colores (tres JRadioButton y un
 * ButtonGroup</li>
 * <li>Selecciona los tres, menú contextual > set ButtonGroup con un borde
 * etiquetado Color Selecciona los tres, menú contextual > Surround with >
 * javax.swing.JPanel border) y Botones (JButton) para Salir o realizar la
 * acción (crear, eliminar, mostrar...).</li>
 * <li>Los mensajes de error (JOptionPane) han de ser lo más conciso
 * posible.</li>
 * <li>Quizás deban basarse en el atributo message de las excepciones.</li>
 * <li>El menú ayuda dispondrá de un Ver ayuda y Acerca de...</li>
 * </ol>
 * </ol>
 * </ul>
 * 
 * @author Guillermo Boquizo Sánchez
 * @version 2.0
 *
 */
public class PrincipalGUI extends JFrame implements Serializable {

	private JPanel contentPane = new FondoPrincipal();
	private final Filtro filter = new Filtro(".obj", "Objeto");
	private static final long serialVersionUID = 1L;
	private JFileChooser jFilechooser = new JFileChooser();
	private Ayuda ayuda = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalGUI frame = new PrincipalGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PrincipalGUI() {
		contentPane.setVisible(true);
		jFilechooser.setSelectedFile(new File(""));
		setTitle("Sin titulo");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(PrincipalGUI.class.getResource("/resources/favicon.png")));
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(100, 149, 237));
		setJMenuBar(menuBar);

		JMenu mnArchivo = new JMenu("Archivo");
		mnArchivo.setForeground(Color.BLACK);
		mnArchivo.setBackground(new Color(100, 149, 237));
		mnArchivo.setMnemonic('A');
		menuBar.add(mnArchivo);

		JMenuItem mntmNuevo = new JMenuItem("Nuevo");
		mntmNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nuevo();
			}
		});
		mntmNuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mnArchivo.add(mntmNuevo);

		JMenuItem mntmAbrir = new JMenuItem("Abrir...");
		mntmAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrir();
			}
		});
		mntmAbrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		mnArchivo.add(mntmAbrir);

		JSeparator separator = new JSeparator();
		mnArchivo.add(separator);

		JMenuItem mntmGuardar = new JMenuItem("Guardar");
		mntmGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();
			}
		});
		mntmGuardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		mnArchivo.add(mntmGuardar);

		JMenuItem mntmGuardarComo = new JMenuItem("Guardar como...");
		mntmGuardarComo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, InputEvent.CTRL_MASK));
		mntmGuardarComo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarComo();
			}
		});
		mnArchivo.add(mntmGuardarComo);

		JSeparator separator_2 = new JSeparator();
		mnArchivo.add(separator_2);

		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salir();
			}
		});
		mntmSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
		mnArchivo.add(mntmSalir);

		JMenu mnCoche = new JMenu("Coche");
		mnCoche.setBackground(new Color(100, 149, 237));
		mnCoche.setMnemonic('c');
		menuBar.add(mnCoche);

		JMenuItem mntmAlta = new JMenuItem("Alta");
		mntmAlta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_UP, InputEvent.CTRL_MASK));
		mntmAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AltasGUI altas = new AltasGUI();
				altas.setVisible(true);
			}
		});
		mnCoche.add(mntmAlta);

		JMenuItem mntmBaja = new JMenuItem("Baja");
		mntmBaja.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, InputEvent.CTRL_MASK));
		mntmBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Fichero.concesionario.size() > 0) {
					BajasGUI bajas = new BajasGUI();
					bajas.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "¡Concesionario vacío!", "?", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnCoche.add(mntmBaja);

		JSeparator separator_3 = new JSeparator();
		mnCoche.add(separator_3);

		JMenuItem mntmMostrarConcesionario = new JMenuItem("Mostrar concesionario");
		mntmMostrarConcesionario.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, InputEvent.CTRL_MASK));
		mntmMostrarConcesionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					CochesGUI mostrar = new MostrarConcesionario();
					mostrar.setVisible(true);
				} catch (IndexOutOfBoundsException | NoSuchElementException e) {
					JOptionPane.showMessageDialog(null, "¡Concesionario vacío!", "?", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnCoche.add(mntmMostrarConcesionario);

		JMenu mnBuscar = new JMenu("Buscar");
		mnBuscar.setBackground(new Color(100, 149, 237));
		mnBuscar.setMnemonic('b');
		menuBar.add(mnBuscar);

		JMenuItem mntmPorMatricula = new JMenuItem("Por matr\u00EDcula");
		mntmPorMatricula.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
		mntmPorMatricula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Fichero.concesionario.size() > 0) {
					BuscarPorMatricula mostrarMatricula = new BuscarPorMatricula();
					mostrarMatricula.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "¡Concesionario vacío!", "?", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnBuscar.add(mntmPorMatricula);

		JMenuItem mntmPorColor = new JMenuItem("Por color");
		mntmPorColor.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		mntmPorColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Fichero.concesionario.size() > 0) {
					BuscarPorColor mostrarPorColor = new BuscarPorColor();
					mostrarPorColor.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "¡Concesionario vacío!", "?", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnBuscar.add(mntmPorColor);

		JMenu mnAyuda = new JMenu("Ayuda");
		mnAyuda.setBackground(new Color(100, 149, 237));
		mnAyuda.setMnemonic('h');
		menuBar.add(mnAyuda);

		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de...");
		mntmAcercaDe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK));
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AcercaDe acercaDe = new AcercaDe();
				acercaDe.setVisible(true);
			}
		});

		JMenuItem mntmVerLaAyuda = new JMenuItem("Ver la Ayuda");
		mntmVerLaAyuda.setIcon(null);
		mntmVerLaAyuda.setSelectedIcon(new ImageIcon(PrincipalGUI.class.getResource("/resources/favicon.png")));
		mntmVerLaAyuda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mntmVerLaAyuda.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (ayuda == null)
					ayuda = new Ayuda();
				ayuda.setVisible(true);
			}
		});
		mnAyuda.add(mntmVerLaAyuda);

		JSeparator separador_1 = new JSeparator();
		mnAyuda.add(separador_1);
		mnAyuda.add(mntmAcercaDe);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		cerrar();
	}

	/**
	 * Método qe permite crear un nuevo concesionario, por defecto sin
	 * modificaciones y sin título
	 */
	private void nuevo() {
		if (Fichero.concesionario.isModificado()) {
			int respuesta = JOptionPane.showConfirmDialog(jFilechooser, "No ha guardado, ¿Desea guardar?", "Atención",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
			if (respuesta == JOptionPane.YES_OPTION) {
				guardarComo();
				Fichero.concesionario = new Concesionario();
				setTitle("Sin Titulo");
				Fichero.concesionario.setModificado(false);
			} else if (respuesta == JOptionPane.NO_OPTION) {
				Fichero.concesionario = new Concesionario();
				setTitle(jFilechooser.getSelectedFile().getName());
				Fichero.concesionario.setModificado(false);
			} else {
			}
		} else {
			Fichero.concesionario = new Concesionario();
			setTitle("Sin titulo");
			Fichero.concesionario.setModificado(false);
		}
	}

	/**
	 * Método que abre un archivo a partir del filechooser
	 * 
	 * @throws HeadlessException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void abrirArchivo() throws HeadlessException, IOException, ClassNotFoundException {
		jFilechooser.setAcceptAllFileFilterUsed(false);
		jFilechooser.addChoosableFileFilter(filter);
		if (jFilechooser.showDialog(jFilechooser, "Abrir Archivo") == JFileChooser.APPROVE_OPTION) {
			Fichero.abrir(jFilechooser.getSelectedFile());
			setTitle(jFilechooser.getSelectedFile().getName());
			Fichero.concesionario.setModificado(false);
		}
	}

	/**
	 * Método que abre un nuevo concesionario y comprueba si esta modificado
	 */
	private void abrir() {
		if (Fichero.concesionario.isModificado()) {
			int respuesta = JOptionPane.showConfirmDialog(jFilechooser, "No ha guardado, ¿Desea Guardar?", "Atención",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
			if (respuesta == JOptionPane.YES_OPTION) {
				guardarComo();
			} else if (respuesta == JOptionPane.NO_OPTION) {
				try {
					abrirArchivo();
				} catch (IOException | ClassNotFoundException e) {
					JOptionPane.showMessageDialog(null, "Error al abrir archivo", "!!", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				// No hacemos nada en caso de cancelar
			}
		} else {
			try {
				abrirArchivo();
			} catch (IOException | ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Error al abrir archivo", "!!", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Método que guarda un archivo
	 */
	private void guardar() {
		if (getTitle().equalsIgnoreCase("Sin titulo")) {
			guardarComo();
		} else {
			try {
				Fichero.guardar(Fichero.concesionario, jFilechooser.getSelectedFile());
				Fichero.concesionario.setModificado(false);
				setTitle(jFilechooser.getSelectedFile().getName());
			} catch (IOException e) {
			}
		}
	}

	/**
	 * Método que guarda un archivo y comprueba si existe
	 */
	private void guardarComo() {
		jFilechooser.setAcceptAllFileFilterUsed(false);
		jFilechooser.addChoosableFileFilter(filter);

		if (JFileChooser.APPROVE_OPTION == jFilechooser.showDialog(jFilechooser, "Guardar")) {
			jFilechooser.setAcceptAllFileFilterUsed(false);
			if (jFilechooser.getSelectedFile().exists()) {
				int respuesta = JOptionPane.showConfirmDialog(jFilechooser,
						"El archivo ya existe, ¿Desea sobreescribirlo?", "Atención", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.WARNING_MESSAGE);
				if (respuesta == JOptionPane.YES_OPTION) {
					try {
						Fichero.guardarComo(Fichero.concesionario, jFilechooser.getSelectedFile());
						JOptionPane.showMessageDialog(null, "El archivo ha sido guardado correctamente", "Aceptar",
								JOptionPane.INFORMATION_MESSAGE);
						Fichero.concesionario.setModificado(false);
						setTitle(jFilechooser.getSelectedFile().getName());
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "El archivo no se ha guardado", "Error!",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				try {
					Fichero.guardar(Fichero.concesionario, jFilechooser.getSelectedFile());
					Fichero.concesionario.setModificado(false);
					setTitle(jFilechooser.getSelectedFile().getName());
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "El archivo no se ha guardado", "Error!",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	/**
	 * Controla los cierres ordinarios de la ventana del programa (alt + F4,
	 * cierre en la cruz), comprobando cambios previamente con un JOptionPane
	 */
	private void cerrar() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				salir();
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				if (Fichero.concesionario.isModificado()) {
				}
				salir();
			}
		});
	}

	/**
	 * Sale del programa comprobando cambios
	 */
	protected void salir() {
		if (Fichero.concesionario.isModificado()) {
			int respuesta = JOptionPane.showConfirmDialog(jFilechooser, "No ha guardado, ¿Desea Guardar?", "Atención",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
			if (respuesta == JOptionPane.YES_OPTION) {
				guardarComo();
			} else if (respuesta == JOptionPane.NO_OPTION) {
				System.exit(0);
			} else {

			}
		} else {
			System.exit(0);
		}
	}

}
