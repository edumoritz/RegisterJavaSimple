package br.home.recreation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ProdutoModel extends AbstractTableModel{

	private List<Produto> lista;
	
	public ProdutoModel() {
		this((List<Produto>) null);
		
		for(int i = 0; i < 10; i++){
			BigDecimal num = new BigDecimal(20.00 + 1);
			Produto p = new Produto();
			p.setId(i + 1);
			p.setNome("Produto "+(i + 1));
			p.setValor(num);
			this.lista.add(p);
		}
	}
	
	public ProdutoModel(List<Produto> list) {
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
	public String getColumnName(int column) {
		
		switch(column){
		case 0:
			return "Id";
		case 1:
			return "Nome";
		case 2:
			return "Valor";
		}
		return null;
	}

	@Override
	public Object getValueAt(int row, int column) {
		
		Produto p = this.lista.get(row);
		
		switch(column){
		case 0:
			return p.getId();
		case 1:
			return p.getNome();
		case 2:
			return p.getValor();
		}
		throw new RuntimeException("Coluna " + column + " solicitada, não existe.");
	}
	
	public void addProduct(Produto p){
		this.lista.add(p);
		super.fireTableDataChanged();
	}
	public Produto getProduto(int idx){
		return lista.get(idx);
	}
	public void remove(Produto produtoSelecionado){
		this.lista.remove(produtoSelecionado);
		super.fireTableDataChanged();
	}

}
