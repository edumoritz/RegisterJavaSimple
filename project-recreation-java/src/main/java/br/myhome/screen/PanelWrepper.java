package br.myhome.screen;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

public class PanelWrepper extends JPanel {

	private JLabel lblTitulo;
	private JButton btnExport;
	private JButton btnX;

	/**
	 * Create the panel.
	 */
	public PanelWrepper() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.setBackground(Color.GRAY);
		add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		lblTitulo = new JLabel("Titulo:");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.insets = new Insets(0, 0, 0, 5);
		gbc_lblTitulo.gridx = 0;
		gbc_lblTitulo.gridy = 0;
		panel.add(lblTitulo, gbc_lblTitulo);
		
		btnExport = new JButton("Export");
		GridBagConstraints gbc_btnExport = new GridBagConstraints();
		gbc_btnExport.anchor = GridBagConstraints.EAST;
		gbc_btnExport.insets = new Insets(0, 0, 0, 5);
		gbc_btnExport.gridx = 1;
		gbc_btnExport.gridy = 0;
		panel.add(btnExport, gbc_btnExport);
		
		btnX = new JButton("X");
		GridBagConstraints gbc_btnX = new GridBagConstraints();
		gbc_btnX.anchor = GridBagConstraints.EAST;
		gbc_btnX.gridx = 2;
		gbc_btnX.gridy = 0;
		panel.add(btnX, gbc_btnX);

	}
	
	public void setConteudo(JPanel panel){
		add(panel, BorderLayout.CENTER);
	}
	public void setActionClose(ActionListener action){
		btnX.addActionListener(action);
	}
	public void setTitulo(String titulo){
		lblTitulo.setText(titulo);
	}
	public void setActionExport(ActionListener action){
		btnExport.addActionListener(action);
	}

}
