package concesionariodecoches.GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ListIterator;

import javax.swing.JOptionPane;
import concesionariodecoches.estructura.Coche;
import concesionariodecoches.estructura.Color;
import concesionariodecoches.estructura.Fichero;

/**
 * Clase que permite la búsqueda por color
 * 
 * @author Guillermo Boquizo Sánchez
 * @version 2.0
 *
 */
public class BuscarPorColor extends CochesGUI {

	private static final long serialVersionUID = 1L;
	private ListIterator<Coche> it;
	private Coche coche;

	/**
	 * Create the dialog.
	 */
	public BuscarPorColor() {
		actualizarTextos();
		comprobarBotones();
		actualizarPane();
		actualizarEventos();
	}
	/**
	 * Método que permite seleccionar un color entre las opciones dadas en la enumeración
	 * @return
	 */
	private Color seleccionarColor() {
		if (rdbtnRed.isSelected()) {
			return concesionariodecoches.estructura.Color.ROJO;
		} else if (rdbtnSilver.isSelected()) {
			return concesionariodecoches.estructura.Color.PLATA;
		} else if (rdbtnBlue.isSelected()) {
			return concesionariodecoches.estructura.Color.AZUL;
		} else {
			return null;
		}
	}
	/**
	 * Método que permite mostrar un coche
	 * 
	 */
	private void mostrarCoche() {
		textField_Matricula.setText(coche.getMatricula());
		combobox_marca.setSelectedItem(coche.getModelo().getMarca());
		comboBox_modelo.setSelectedItem(coche.getModelo());
		textField_Matricula.setForeground(java.awt.Color.BLACK);
		seleccionarColor(coche.getColor());
		comprobarBtnDesplazamiento();
	}

	/**
	 * Método que permite comprobar el estado de habilitación/inhabilitación de
	 * los botones principales
	 */
	private void comprobarBotones() {
		btnDcha.setEnabled(false);
		btnIzda.setEnabled(false);
		btnOK.setVisible(false);
		comboBox_modelo.setEnabled(false);
		combobox_marca.setEnabled(false);
		textField_Matricula.setEditable(false);
	}

	/**
	 * Método que comprueba el estado de habilitación/inhabilitación de los
	 * botones de desplazamiento
	 */
	private void comprobarBtnDesplazamiento() {
		if (!it.hasNext()) {
			btnDcha.setEnabled(false);
			coche = it.previous();
		} else {
			btnDcha.setEnabled(true);
		}
		if (!it.hasPrevious()) {
			btnIzda.setEnabled(false);
			coche = it.next();
		} else {
			btnIzda.setEnabled(true);
		}
	}
	
	/**
	 * Método que permite iterar hacia adelante 
	 */
	private void cocheAdelante() {
		if (it.hasNext()) {
			coche = it.next();
		}
		mostrarCoche();
	}

	/**
	 * Método que permite iterar hacia atrás
	 */
	private void cocheAtras() {
		if (it.hasPrevious()) {
			coche = it.previous();
		}
		mostrarCoche();
	}

	/**
	 * Método que permite actualizar dimensiones y layout del panel
	 */
	private void actualizarPane() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
	}

	/**
	 * Método que permite actualizar los textos de la ventana
	 */
	private void actualizarTextos() {
		setTitle("Mostrar por color");
		btnCancel.setText("Cerrar");
	}

	/**
	 * Método que actualiza los eventos asociados a los botones principales de
	 * la ventana
	 */
	private void actualizarEventos() {
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					it = Fichero.concesionario.getCochesColor(seleccionarColor()).listIterator();
					coche = it.next();
					mostrarCoche();
					btnIzda.setEnabled(false);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "No hay coches del color seleccionado!", "Atención",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		/**
		 * Método que gestiona el comportamiento del botón derecho
		 */
		btnDcha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cocheAdelante();
			}
		});

		/**
		 * Método que gestiona el comportamiento del botón izquierdo
		 *
		 */
		btnIzda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cocheAtras();
			}
		});
	}
}
