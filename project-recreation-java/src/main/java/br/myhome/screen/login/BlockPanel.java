package br.myhome.screen.login;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class BlockPanel extends JPanel {
	private JPasswordField passwordField;
	private Runnable executaNoFechar;

	/**
	 * Create the panel.
	 */
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
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblSenha = new JLabel("Senha:");
		GridBagConstraints gbc_lblSenha = new GridBagConstraints();
		gbc_lblSenha.insets = new Insets(0, 0, 5, 5);
		gbc_lblSenha.anchor = GridBagConstraints.EAST;
		gbc_lblSenha.gridx = 0;
		gbc_lblSenha.gridy = 0;
		panel.add(lblSenha, gbc_lblSenha);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.fill = GridBagConstraints.BOTH;
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 0;
		panel.add(passwordField, gbc_passwordField);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.gridwidth = 2;
		gbc_btnOk.gridx = 0;
		gbc_btnOk.gridy = 1;
		panel.add(btnOk, gbc_btnOk);

	}

	public int fechar() {
		char[] arrChar = this.passwordField.getPassword();
		String senha = new String(arrChar);
		System.out.println(senha);
		if(isPasswordCorrect(arrChar)){
			super.setVisible(false);
			
			if(this.executaNoFechar != null){
				this.executaNoFechar.run();
			}
			return 1;
		} else {
			JOptionPane.showMessageDialog(null,
	                "Invalid password. Try again.",
	                "Error Message",
	                JOptionPane.ERROR_MESSAGE);
			System.out.println("Password Inválido");
			return 0;
		}
	}
	
	private AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f);
	private JPasswordField jPasswordField;
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		
		g2.setColor(getBackground());
		g2.setComposite(alcom);
		g2.fillRect(0, 0, getWidth()-1, getHeight()-1);
		
		super.paintComponent(g);
	}
	
	public void noFechar(Runnable runnable){
		this.executaNoFechar = runnable;
	}
	
	private static boolean isPasswordCorrect(char[] input) {
	    boolean isCorrect = true;
	    char[] correctPassword = { '1', '2', '3' };

	    if (input.length != correctPassword.length) {
	        isCorrect = false;
	    } else {
	        isCorrect = Arrays.equals (input, correctPassword);
	    }

	    //Zero out the password.
	    Arrays.fill(correctPassword,'0');

	    return isCorrect;
	}
}
