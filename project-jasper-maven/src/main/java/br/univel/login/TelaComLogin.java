package br.univel.login;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Insets;
import javax.swing.JTree;

public class TelaComLogin extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaComLogin frame = new TelaComLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaComLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JButton btnBloquear = new JButton("Bloquear");
		btnBloquear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bloquear();
			}
		});
		GridBagConstraints gbc_btnBloquear = new GridBagConstraints();
		gbc_btnBloquear.insets = new Insets(0, 0, 5, 0);
		gbc_btnBloquear.gridx = 0;
		gbc_btnBloquear.gridy = 0;
		contentPane.add(btnBloquear, gbc_btnBloquear);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		JTree tree = new JTree();
		scrollPane.setViewportView(tree);
	}

	protected void bloquear() {
		BlockPanel blockPane = new BlockPanel();
		
//		Runnable r = new Runnable(){
//			@Override
//			public void run() {
//				System.out.println("Run");
//			}
//		};
		
		blockPane.noFechar(() -> System.out.println("Run!!"));
		
		super.setGlassPane(blockPane);//painel de vidro
		blockPane.setVisible(true);
	}

}
