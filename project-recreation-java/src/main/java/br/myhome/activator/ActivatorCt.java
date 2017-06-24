package br.myhome.activator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import br.myhome.dao.ClassDao;
import br.myhome.pojos.Contact;
import br.myhome.screen.PanelBaseCt;
import br.myhome.tablemodel.ContactModel;

public class ActivatorCt extends PanelBaseCt {

	private Contact contactSelected;
	private ContactModel tableModel;
	private ClassDao dao;

	public ActivatorCt() {
		super();
		clearFields();
		configureButtons();
		configureTable();
	}
	
	private void configureTable() {
		ClassDao dao = new ClassDao();
		List<Contact> list = dao.getTodosC();

		this.tableModel = new ContactModel(list);
		super.table.setModel(tableModel);
		super.table.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2){
					int idx = table.getSelectedRow();
					if(idx < 0){
						System.out.println("Não há linha selecionada");
					} else {
						loadRow(idx);
					}
				}
			}
			
		});
	}

	protected void loadRow(int idx) {
		Contact ct = this.tableModel.getContact(idx);
		this.contactSelected = ct;
		this.lblCarregandoParaAlterao.setText(CARREGADO_PARA_ALTERACAO);
		super.txtId.setText(String.valueOf(ct.getId()));
		super.txtNome.setText(ct.getNome());
		super.txtTelefone.setText(ct.getTelefone());
		
		super.btnDelete.setEnabled(true);
	}

	private void configureButtons() {
		
		super.btnNew.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				newContact();
			}
		});
		super.btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		super.btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});

	}

	protected void delete() {
		this.tableModel.remove(contactSelected);
		dao = new ClassDao();
		dao.excluiC(contactSelected.getId());
		clearFields();
	}

	protected void save() {
		dao = new ClassDao();
		if(contactSelected == null){
			Contact ct = new Contact();
			String strId = txtId.getText().trim();
			String strNome = txtNome.getText().trim();
			String strTelefone = txtTelefone.getText().trim();
			
			int intId = Integer.parseInt(strId);
			
			ct.setId(intId);
			ct.setNome(strNome);
			ct.setTelefone(strTelefone);
			
			this.tableModel.addContact(ct);
			
			dao.insereC(ct);
			clearFields();
		} else {
			String strId = txtId.getText().trim();
			String strNome = txtNome.getText().trim();
			String strTelefone = txtTelefone.getText().trim();
			
			int intId = Integer.parseInt(strId);
			
			this.contactSelected.setId(intId);
			this.contactSelected.setNome(strNome);
			this.contactSelected.setTelefone(strTelefone);
			
			dao.atualizaC(intId, contactSelected);
			clearFields();
			
			this.tableModel.fireTableDataChanged();
		}
	}

	protected void newContact() {
		this.contactSelected = null;
		clearFields();
	}

	private void clearFields() {
		super.lblCarregandoParaAlterao.setText("");
		super.txtId.setText("");
		super.txtNome.setText("");
		super.txtTelefone.setText("");

		super.btnDelete.setEnabled(false);
	}
	protected void fills(Contact ct){
		txtId.setText(String.valueOf(ct.getId()));
		txtNome.setText(ct.getNome());
		txtTelefone.setText(ct.getTelefone());
	}

}
