package br.myhome.tablemodel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import br.myhome.pojos.Contact;

public class ContactModel extends AbstractTableModel{
	
	private List<Contact> lista;
	
	void fillsResult(List<Contact> result){
		this.lista = result;
		
		fireTableDataChanged();
	}
	public ContactModel(){
		this((List<Contact>)null);
	}
	
	public ContactModel(List<Contact> list) {
		if(list == null){
			this.lista = new ArrayList<>();
		} else {
			this.lista = list;
		}
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Contact ct = this.lista.get(rowIndex);
		
		switch (columnIndex) {
		case 0:
			return ct.getId();
		case 1:
			return ct.getNome();
		case 2:
			return ct.getTelefone();
		}
		return null;
	}
	
	public void addContact(Contact c){
		this.lista.add(c);
		super.fireTableDataChanged();
	}
	public Contact getContact(int idx){
		return lista.get(idx);
	}
	public void remove(Contact contatoSelecionado){
		this.lista.remove(contatoSelecionado);
		super.fireTableDataChanged();
	}
	public Contact getContactAt(int idx){
		if (idx >= this.lista.size()) {
			return null;
		}
		return this.lista.get(idx);
	}
	

}
