package concesionariodecoches.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ListIterator;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import concesionariodecoches.estructura.Coche;
import concesionariodecoches.estructura.Fichero;

/**
 * Clase que gestiona la GUI que muestra el concesionario
 * 
 * @author Guillermo Boquizo Sánchez
 * @version 2.0
 *
 */
public class MostrarConcesionario extends CochesGUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private ListIterator<Coche> it;
	private Coche coche;

	private void mostrarCoche() {
		textField_Matricula.setText(coche.getMatricula());
		combobox_marca.setSelectedItem(coche.getModelo().getMarca());
		comboBox_modelo.setSelectedItem(coche.getModelo());
		seleccionarColor(coche.getColor());
		comprobarBotones();
	}

	private void cocheAdelante() {
		if (it.hasNext()) {
			coche = it.next();
		}
		mostrarCoche();
	}

	private void cocheAtras() {
		if (it.hasPrevious()) {
			coche = it.previous();
		}
		mostrarCoche();
	}

	private void comprobarBotones() {
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
	 * Create the dialog.
	 */
	public MostrarConcesionario() {
		setTitle("Mostrar concesionario");
		comboBox_modelo.setEnabled(false);
		combobox_marca.setEnabled(false);
		rdbtnBlue.setEnabled(false);
		rdbtnRed.setEnabled(false);
		rdbtnSilver.setEnabled(false);
		textField_Matricula.setEditable(false);
		btnOK.setVisible(false);
		btnBuscar.setVisible(false);
		btnDcha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cocheAdelante();
			}
		});

		btnIzda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cocheAtras();
			}
		});

		btnCancel.setText("Cerrar");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		it = Fichero.concesionario.listIterator();
		coche = it.next();
		mostrarCoche();
		btnIzda.setEnabled(false);

	}
}
