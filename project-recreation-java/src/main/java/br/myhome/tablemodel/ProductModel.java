package br.myhome.tablemodel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import br.myhome.pojos.Product;

public class ProductModel extends AbstractTableModel{
	
	private List<Product> lista;

	public ProductModel(List<Product> list) {
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
		Product p = this.lista.get(rowIndex);
		
		switch(columnIndex){
		case 0:
			return p.getId();
		case 1:
			return p.getNome();
		case 2:
			return p.getValor();
		}
		return null;
	}

	public void addProduct(Product p){
		this.lista.add(p);
		super.fireTableDataChanged();
	}
	public Product getProduct(int idx){
		return lista.get(idx);
	}
	public void remove(Product produtoSelecionado){
		this.lista.remove(produtoSelecionado);
		super.fireTableDataChanged();
	}
}
