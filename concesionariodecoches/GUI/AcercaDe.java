package concesionariodecoches.GUI;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.BorderLayout;
import javax.swing.JTextPane;

/**
 * Clase para la ventana GUI AcercaDe
 * 
 * @author Guillermo Boquizo Sánchez
 * @version 1.0
 *
 */
public class AcercaDe extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public AcercaDe() {
		setTitle("Acerca de...\r\n");
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AcercaDe.class.getResource("/resources/favicon.png")));
		setResizable(false);
		setBounds(100, 100, 450, 342);
		getContentPane().setLayout(null);
		getContentPane().add(contentPanel);
		contentPanel.setBounds(0, 0, 0, 0);
		contentPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 434, 275);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		getContentPane().add(scrollPane);

		JTextPane txtpnConcesionarioDeCoches = new JTextPane();
		txtpnConcesionarioDeCoches.setEditable(false);
		txtpnConcesionarioDeCoches.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txtpnConcesionarioDeCoches.setText(
				"\r\nI.E.S Gran Capit\u00E1n, 2017\t\r\n\r\nConcesionario de coches.\t\r\n\r\n\t\r\n\r\nDesarrollado por:\r\n\r\nGuillermo Boquizo S\u00E1nchez\r\n\r\nVersi\u00F3n: 1.0\r\n\r\nAbril - Mayo 2017\r\n\r\n\r\n\r\n\r\n\r\n");
		scrollPane.setViewportView(txtpnConcesionarioDeCoches);

		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(269, 271, 140, 42);
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		buttonPane.setLayout(null);
		btnAceptar.setBounds(10, 11, 104, 23);
		buttonPane.add(btnAceptar);
	}
}
