package br.home;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ContatoModel extends AbstractTableModel {

	private List<Contato> lista;

	/**
	 * Construtor vaziu, chama o de baixo passando nulo
	 */
	public ContatoModel() {
		// faz um cast para indicar o costrutor que ira receber null.
		this((List<Contato>) null);

		for (int i = 0; i < 10; i++) {
			Contato c = new Contato();
			c.setId(i + 1);
			c.setNome("Contato " + (i + 1));
			c.setTelefone("(45)9987-290" + i);
			this.lista.add(c);
		}
		
	}

	public ContatoModel(List<Contato> _lista) {
		if (_lista == null) {
			this.lista = new ArrayList<>();
		} else {
			this.lista = _lista;
		}
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public String getColumnName(int column) {

		switch (column) {
		case 0:
			return "Id";
		case 1:
			return "Nome";
		case 2:
			return "Telefone";
		}
		return null;
	}

	@Override
	public Object getValueAt(int row, int column) {

		Contato c = this.lista.get(row);

		switch (column) {
		case 0:
			return c.getId();
		case 1:
			return c.getNome();
		case 2:
			return c.getTelefone();
		}

		// return "Inexistente";
		throw new RuntimeException("Coluna " + column + " solicitada, não existe.");
	}

	public void adicionar(Contato c) {
		this.lista.add(c);
		// Dispara um evento para a JTable
		// atualizar sua estrutura/tela
		super.fireTableDataChanged();
	}

	public Contato getContato(int idx) {
		return lista.get(idx);
	}

	public void remover(Contato contatoSelecionado) {
		this.lista.remove(contatoSelecionado);
		super.fireTableDataChanged();//Atualiza tabela
	}

}
