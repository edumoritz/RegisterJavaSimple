package br.home.recreation;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import br.home.ReportManager;

public class ScreenMain extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScreenMain frame = new ScreenMain();
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
	public ScreenMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));// Alterado
																	// manualmente
																	// para
																	// Leading

		/*--------------------------------------Ação Botão Contato--------------------------------------*/
		JButton btnContato = new JButton("Contato");
		btnContato.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				adicionarAbaContato();
			}
		});
		panel.add(btnContato);
		/*-----------------------------------------------------------------------------------------------*/

		/*--------------------------------------Ação Botão Produto--------------------------------------*/
		JButton btnProduto = new JButton("Produto");
		btnProduto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				adicionarAbaProduto();
			}
		});
		panel.add(btnProduto);
		/*-----------------------------------------------------------------------------------------------*/

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane);
	}

	protected void adicionarAbaProduto() {
		JPanel panelContact = new PanelActiveP();

		PanelWrepper wrapper = new PanelWrepper();

		wrapper.setConteudo(panelContact);
		wrapper.setTitulo("Cadastro de Produtos");

		wrapper.setActionClose(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.remove(wrapper);

			}
		});
		wrapper.setActionExport(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ExportReport er = new ExportReport();
				er.exportProduct();
			}
		});
		tabbedPane.add("Produto", wrapper);
	}

	protected void adicionarAbaContato() {
		JPanel painelContato = new PanelActiveC();

		PanelWrepper wrapper = new PanelWrepper();

		wrapper.setConteudo(painelContato);
		wrapper.setTitulo("Cadastro de Contatos");

		wrapper.setActionClose(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.remove(wrapper);
			}
		});

		wrapper.setActionExport(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ExportReport er = new ExportReport();
				er.exportContact();
			}
		});

		tabbedPane.add("Contato", wrapper);
	}

}
