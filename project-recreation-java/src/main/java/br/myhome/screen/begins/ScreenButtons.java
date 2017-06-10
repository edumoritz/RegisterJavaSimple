package br.myhome.screen.begins;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import br.myhome.activator.ActivatorPt;
import br.myhome.reports.ExportReport;
import br.myhome.screen.PanelWrepper;

import javax.swing.JTabbedPane;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ScreenButtons extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScreenButtons frame = new ScreenButtons();
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
	public ScreenButtons() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		JButton btnContato = new JButton("Contato");
		btnContato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addContact();
			}
		});
		panel.add(btnContato);
		
		JButton btnProduto = new JButton("Produto");
		btnProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addProduct();
			}
		});
		panel.add(btnProduto);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
	}

	protected void addProduct() {
		JPanel panelProduct = new ActivatorPt();
		final PanelWrepper wrapper = new PanelWrepper();
		
		wrapper.setConteudo(panelProduct);
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

	protected void addContact() {
		
	}

}
