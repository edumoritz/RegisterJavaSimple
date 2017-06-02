package br.univel.caddois;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JButton;

import java.awt.Insets;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.EmptyBorder;

import br.home.ReportManager;

import java.awt.Font;

public class PainelWrepper extends JPanel {

	private JButton btnXis;
	private JLabel labelTitulo;
	private JButton btnPDF;
	/**
	 * Create the panel.
	 */
	public PainelWrepper() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.setBackground(Color.LIGHT_GRAY);
		add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 340, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		labelTitulo = new JLabel("T�tulo");
		labelTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel.add(labelTitulo, gbc_lblNewLabel);
		
		btnPDF = new JButton("Exportar");
		btnPDF.setMargin(new Insets(2, 5, 2, 5));
		btnPDF.setActionCommand("ExportarPDF");
		GridBagConstraints gbc_btnPDF = new GridBagConstraints();
		gbc_btnPDF.anchor = GridBagConstraints.EAST;
		gbc_btnPDF.insets = new Insets(0, 0, 0, 5);
		gbc_btnPDF.gridx = 1;
		gbc_btnPDF.gridy = 0;
		panel.add(btnPDF, gbc_btnPDF);
		
		btnXis = new JButton("X");
		GridBagConstraints gbc_btnXis = new GridBagConstraints();
		gbc_btnXis.anchor = GridBagConstraints.EAST;
		gbc_btnXis.gridx = 2;
		gbc_btnXis.gridy = 0;
		panel.add(btnXis, gbc_btnXis);
		
		

	}
	
	public void setConteudo(JPanel painel){
		add(painel, BorderLayout.CENTER);
	}
	public void setAcaoFechar(ActionListener action) {
		btnXis.addActionListener(action);
	}
	public void setTitulo(String titulo){
		labelTitulo.setText(titulo);
	}
	public void setAcaoExportar(ActionListener action){
		btnPDF.addActionListener(action);		
	}

}
