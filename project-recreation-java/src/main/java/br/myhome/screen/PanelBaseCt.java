package br.myhome.screen;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.function.Consumer;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

import br.myhome.activator.ActivatorCt;
import br.myhome.pojos.Contact;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTable;

public class PanelBaseCt extends JPanel {
	
	protected static final String CARREGADO_PARA_ALTERACAO = "Carregando para alteração";
	protected JTextField txtId;
	protected JTextField txtNome;
	protected JTextField txtTelefone;
	protected JTable table;
	protected JButton btnNew;
	protected JButton btnSave;
	protected JButton btnDelete;
	protected JLabel lblCarregandoParaAlterao;
	protected JButton btnSearch;

	public PanelBaseCt() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel PanelMarginTop = new JPanel();
		GridBagConstraints gbc_PanelMarginTop = new GridBagConstraints();
		gbc_PanelMarginTop.insets = new Insets(0, 0, 5, 0);
		gbc_PanelMarginTop.fill = GridBagConstraints.BOTH;
		gbc_PanelMarginTop.gridx = 0;
		gbc_PanelMarginTop.gridy = 0;
		add(PanelMarginTop, gbc_PanelMarginTop);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.SOUTH;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblId = new JLabel("Id(F2):");
		GridBagConstraints gbc_lblId = new GridBagConstraints();
		gbc_lblId.anchor = GridBagConstraints.EAST;
		gbc_lblId.insets = new Insets(0, 0, 5, 5);
		gbc_lblId.gridx = 0;
		gbc_lblId.gridy = 0;
		panel.add(lblId, gbc_lblId);
		
		txtId = new JTextField();
		txtId.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_F2){
					openSearch();
				}
			}
		});
		GridBagConstraints gbc_txtId = new GridBagConstraints();
		gbc_txtId.anchor = GridBagConstraints.WEST;
		gbc_txtId.insets = new Insets(0, 0, 5, 5);
		gbc_txtId.gridx = 1;
		gbc_txtId.gridy = 0;
		panel.add(txtId, gbc_txtId);
		txtId.setColumns(5);
		
		lblCarregandoParaAlterao = new JLabel(CARREGADO_PARA_ALTERACAO);
		lblCarregandoParaAlterao.setForeground(Color.RED);
		GridBagConstraints gbc_lblCarregandoParaAlterao = new GridBagConstraints();
		gbc_lblCarregandoParaAlterao.anchor = GridBagConstraints.WEST;
		gbc_lblCarregandoParaAlterao.insets = new Insets(0, 0, 5, 0);
		gbc_lblCarregandoParaAlterao.gridx = 2;
		gbc_lblCarregandoParaAlterao.gridy = 0;
		panel.add(lblCarregandoParaAlterao, gbc_lblCarregandoParaAlterao);
		
		JLabel lblNome = new JLabel("Nome:");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.anchor = GridBagConstraints.EAST;
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 1;
		panel.add(lblNome, gbc_lblNome);
		
		txtNome = new JTextField();
		GridBagConstraints gbc_txtNome = new GridBagConstraints();
		gbc_txtNome.gridwidth = 2;
		gbc_txtNome.insets = new Insets(0, 0, 5, 5);
		gbc_txtNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNome.gridx = 1;
		gbc_txtNome.gridy = 1;
		panel.add(txtNome, gbc_txtNome);
		txtNome.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		GridBagConstraints gbc_lblTelefone = new GridBagConstraints();
		gbc_lblTelefone.anchor = GridBagConstraints.EAST;
		gbc_lblTelefone.insets = new Insets(0, 0, 0, 5);
		gbc_lblTelefone.gridx = 0;
		gbc_lblTelefone.gridy = 2;
		panel.add(lblTelefone, gbc_lblTelefone);
		
		txtTelefone = new JTextField();
		GridBagConstraints gbc_txtTelefone = new GridBagConstraints();
		gbc_txtTelefone.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTelefone.insets = new Insets(0, 0, 0, 5);
		gbc_txtTelefone.gridx = 1;
		gbc_txtTelefone.gridy = 2;
		panel.add(txtTelefone, gbc_txtTelefone);
		txtTelefone.setColumns(15);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 2;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openSearch();
			}
		});
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.insets = new Insets(0, 0, 0, 5);
		gbc_btnSearch.gridx = 0;
		gbc_btnSearch.gridy = 0;
		panel_1.add(btnSearch, gbc_btnSearch);
		
		btnNew = new JButton("New");
		GridBagConstraints gbc_btnNew = new GridBagConstraints();
		gbc_btnNew.anchor = GridBagConstraints.EAST;
		gbc_btnNew.insets = new Insets(0, 0, 0, 5);
		gbc_btnNew.gridx = 1;
		gbc_btnNew.gridy = 0;
		panel_1.add(btnNew, gbc_btnNew);
		
		btnSave = new JButton("Save");
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.anchor = GridBagConstraints.EAST;
		gbc_btnSave.insets = new Insets(0, 0, 0, 5);
		gbc_btnSave.gridx = 2;
		gbc_btnSave.gridy = 0;
		panel_1.add(btnSave, gbc_btnSave);
		
		btnDelete = new JButton("Delete");
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.anchor = GridBagConstraints.EAST;
		gbc_btnDelete.gridx = 3;
		gbc_btnDelete.gridy = 0;
		panel_1.add(btnDelete, gbc_btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 3;
		add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);

	}

	protected void openSearch() {
		PanelSearch panel = new PanelSearch();
		
		panel.setOnOk(new Consumer<Contact>() {
			
			@Override
			public void accept(Contact t) {
				//getGlassPane().setVisible(false);
				fills(t);
			}
		});
		panel.setOnCancel(new Runnable() {
			
			@Override
			public void run() {
				clear();
				//getGlassPane().setVisible(false);				
			}
		});
		//setGlassPane(panel);
		panel.setVisible(true);
	}
	protected void clear() {
		txtId.setText("");
		txtNome.setText("");
		txtTelefone.setText("");
	}
	protected void fills(Contact ct){
		txtId.setText(String.valueOf(ct.getId()));
		txtNome.setText(ct.getNome());
		txtTelefone.setText(ct.getTelefone());
	}

}
