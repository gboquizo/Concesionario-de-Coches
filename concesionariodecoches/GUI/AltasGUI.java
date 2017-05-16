package concesionariodecoches.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.Toolkit;
import javax.swing.border.EmptyBorder;

import concesionariodecoches.estructura.Color;
import concesionariodecoches.estructura.Fichero;
import concesionariodecoches.estructura.Modelo;
import concesionariodecoches.estructura.excepciones.CocheYaExisteException;
import concesionariodecoches.estructura.excepciones.ColorNoValidoException;
import concesionariodecoches.estructura.excepciones.MatriculaNoValidaException;
import concesionariodecoches.estructura.excepciones.ModeloNoValidoException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase que gestiona la GUI de altas del concesionario
 * 
 * @author Guillermo Boquizo Sánchez
 * @version 2.0
 *
 */
public class AltasGUI extends CochesGUI {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();


	/**
	 * Create the dialog.
	 */
	public AltasGUI() {

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AltasGUI.class.getResource("/resources/favicon.png")));
		setModal(true);
		setResizable(false);
		setTitle("Altas de coches\r\n");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		btnCancel.setText("Cerrar");
		btnBuscar.setText("A\u00F1adir");
		btnIzda.setVisible(false);
		btnDcha.setVisible(false);
		btnOK.setVisible(false);
		contentPanel.setBackground(java.awt.Color.WHITE);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		annadir();
	}

	
	/**
	 * Método que permite seleccionar el color, sobrescrito de la ventana padre
	 * CochesGUI
	 */
	@Override
	protected Color getSelectedColor() {
		return super.getSelectedColor();
	}

	/**
	 * Método encargado de gestionar el comportamiento de los eventos vinculados al botón que añade al concesionario
	 */
	private void annadir() {
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Fichero.concesionario.annadir(textField_Matricula.getText(), getSelectedColor(),
							(Modelo) comboBox_modelo.getSelectedItem());
					textField_Matricula.setText("");
					Fichero.concesionario.setModificado(true);
				} catch (ColorNoValidoException | ModeloNoValidoException e) {
					JOptionPane.showMessageDialog(contentPanel, e.getMessage(), "¡¡ERROR!!", JOptionPane.ERROR_MESSAGE);
				} catch (MatriculaNoValidaException e) {
					JOptionPane.showMessageDialog(contentPanel, e.getMessage(), "¡¡ERROR!!", JOptionPane.ERROR_MESSAGE);
				} catch (CocheYaExisteException e) {
					JOptionPane.showMessageDialog(contentPanel, e.getMessage(), "¡¡ERROR!!", JOptionPane.ERROR_MESSAGE);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}
}
