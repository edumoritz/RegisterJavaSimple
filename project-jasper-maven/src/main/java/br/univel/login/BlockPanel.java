package br.univel.login;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.JPasswordField;
import java.awt.Insets;

public class BlockPanel extends JPanel {
	
	private Runnable executaNoFechar;
	
	public BlockPanel() {
		this.setOpaque(false);
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				e.consume();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				e.consume();
			}
		});
		this.setBackground(Color.BLUE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setPreferredSize(new Dimension(320, 240));
		panel.setMaximumSize(new Dimension(320, 240));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fechar();
			}
		});
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 0;
		gbc_passwordField.gridy = 0;
		panel.add(passwordField, gbc_passwordField);
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.gridx = 0;
		gbc_btnOk.gridy = 1;
		panel.add(btnOk, gbc_btnOk);
	}
	
	protected void fechar() {
		super.setVisible(false);
		
		char[] arrChar = this.passwordField.getPassword();
		String senha = new String(arrChar);
		
		if(this.executaNoFechar != null){
			this.executaNoFechar.run();
		}
	}

	//opacidade da tela de bloqueio
	private AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f);
	private JPasswordField passwordField;
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();

		int h = super.getHeight();
		int w = super.getWidth();
		
		//Insere um quadrado com a cor de fundo da opacidade;
		g2.setColor(getBackground());
		g2.setComposite(alcom);
		g2.fillRect(0, 0, getWidth()-1, getHeight()-1);

//		for (int i = 0; i < h; i += 5) {
//			g.drawLine(0, i, w, i);
//		}
		
		g2.dispose();
		super.paintComponent(g);
	}

	public void noFechar(Runnable runnable) {
		this.executaNoFechar = runnable;
	}

}
