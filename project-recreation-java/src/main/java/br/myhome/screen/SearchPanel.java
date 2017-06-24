package br.myhome.screen;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;

import br.myhome.dao.ClassDao;
import br.myhome.pojos.Contact;
import br.myhome.tablemodel.ContactModel;

import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SearchPanel extends JPanel {
	private JTextField textField;
	private JTable table;
	private Consumer<Contact> consumerOnOk;
	private Runnable runnableOnCancel;

	public SearchPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		
		JLabel lblNome = new JLabel("Nome:");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.anchor = GridBagConstraints.EAST;
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 1;
		add(lblNome, gbc_lblNome);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		add(textField, gbc_textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
		// $hide>>$
		configureTable();
		// $hide<<$

	}

	private void configureTable() {
		ContactModel model = new ContactModel();
		table.setModel(model);
		
		textField.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					busca(textField.getText().trim());
				}
				if(e.getKeyCode() == KeyEvent.VK_DOWN){
					table.getSelectionModel().addSelectionInterval(0, 0);
					textField.transferFocus();
				}
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
					if(SearchPanel.this.runnableOnCancel != null){
						SearchPanel.this.runnableOnCancel.run();
					}
				}
			}
		});
		
		table.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_UP){
					table.transferFocusBackward();
				}
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					e.consume();
					
					int idx = table.getSelectedRow();
					if(idx != -1){
						Contact ct = ((ContactModel)table.getModel()).getContactAt(idx);
						if(ct == null){
							return;
						}
						SearchPanel.this.consumerOnOk.accept(ct);
					}
				}
				
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
					if(SearchPanel.this.runnableOnCancel != null){
						SearchPanel.this.runnableOnCancel.run();
					}
				}
				
			}
		});
		
	}

	protected void busca(String trim) {
		ClassDao dao = new ClassDao();
		//verificar nova sql
		List<Contact> lista = dao.getTodosC();
		
		//table.getModel().
		
	}

}
