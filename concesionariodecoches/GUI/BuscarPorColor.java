package concesionariodecoches.GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import concesionariodecoches.estructura.Coche;
import concesionariodecoches.estructura.Fichero;

public class BuscarPorColor extends CochesGUI {

	private static final long serialVersionUID = 1L;
	private ArrayList<Coche> copia = new ArrayList<Coche>();
	private int indice = 0;

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
	 * Método que permite mostrar un coche por índice del coche
	 * 
	 * @param indiceCoche
	 *            índice del coche
	 */
	private void mostrarCoche(int indiceCoche) {
		textField_Matricula.setText(copia.get(indiceCoche).getMatricula());
		combobox_marca.setSelectedItem(copia.get(indiceCoche).getModelo().getMarca());
		comboBox_modelo.setSelectedItem(copia.get(indiceCoche).getModelo());
		textField_Matricula.setForeground(java.awt.Color.BLACK);
		seleccionarColor(copia.get(indiceCoche).getColor());
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
	 * Método que comprueba por índice el estado de habilitación/inhabilitación
	 * de los botones de desplazamiento
	 */
	private void comprobarBtnDesplazamiento() {
		if (indice + 1 >= copia.size()) {
			btnDcha.setEnabled(false);
		} else {
			btnDcha.setEnabled(true);
		}
		if (indice - 1 == -1) {
			btnIzda.setEnabled(false);
		} else {
			btnIzda.setEnabled(true);
		}
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
					indice = 0;
					copia = Fichero.concesionario.getCochesColor(getSelectedColor());
					mostrarCoche(indice);
					comprobarBtnDesplazamiento();
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
				mostrarCoche(++indice);
				comprobarBtnDesplazamiento();
			}
		});

		/**
		 * Método que gestiona el comportamiento del botón izquierdo
		 *
		 */
		btnIzda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarCoche(--indice);
				comprobarBtnDesplazamiento();
			}
		});
	}
}
