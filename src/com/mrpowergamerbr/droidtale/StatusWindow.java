package com.mrpowergamerbr.droidtale;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;
import javax.swing.JScrollPane;

public class StatusWindow {
	public JTextArea textPane;
	private JScrollPane scrollPane;
	/**
	 * @wbp.parser.entryPoint
	 */
	public void createAndShowGUI() {
		// Create and set up the window.
		JFrame frmAlphysStatusWindow = new JFrame("Droidtale Automagical Builder");
		frmAlphysStatusWindow.setTitle("Alphys' Status Window");
		frmAlphysStatusWindow.getContentPane().setLayout(null);
		
		frmAlphysStatusWindow.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("com/mrpowergamerbr/droidtale/icon.png")));
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 364, 231);
		frmAlphysStatusWindow.getContentPane().add(scrollPane);
		
		textPane = new JTextArea();
		scrollPane.setViewportView(textPane);
		textPane.setEditable(false);
		DefaultCaret caret = (DefaultCaret)textPane.getCaret();
		caret.setUpdatePolicy(DefaultCaret.OUT_BOTTOM);

		frmAlphysStatusWindow.setSize(400, 292);
		frmAlphysStatusWindow.setVisible(true);
	}
}
