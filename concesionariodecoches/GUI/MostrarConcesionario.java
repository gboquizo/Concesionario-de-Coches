package concesionariodecoches.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import concesionariodecoches.estructura.Fichero;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MostrarConcesionario extends CochesGUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private int indice = 0;

	private void mostrarCoche(int indiceCoche) {
		textField_Matricula.setText(Fichero.concesionario.get(indiceCoche).getMatricula());
		combobox_marca.setSelectedItem(Fichero.concesionario.get(indiceCoche).getModelo().getMarca());
		comboBox_modelo.setSelectedItem(Fichero.concesionario.get(indiceCoche).getModelo());
		seleccionarColor(Fichero.concesionario.get(indiceCoche).getColor());
	}

	

	private void comprobarBotones() {
		if (indice + 1 >= Fichero.concesionario.size()) {
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
				mostrarCoche(++indice);
				comprobarBotones();
			}
		});
		
		btnIzda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarCoche(--indice);
				comprobarBotones();
			}
		});
		
		btnCancel.setText("Cerrar");
		mostrarCoche(indice);
		comprobarBotones();
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
	}
}
