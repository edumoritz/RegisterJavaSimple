package br.home.recreation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.List;

public class PanelActiveP extends PanelBaseP{
	
	private Produto produtoSelecionado;
	private ProdutoModel modelo;
	private ClassDao cd;
	
	public PanelActiveP() {
		super();
		clearFields();
		configureButtons();
		configureTable();
	}

	private void clearFields() {
		super.lblAlerta.setText("");
		super.txtId.setText("");
		super.txtNome.setText("");
		super.txtValor.setText("");

		super.btnDelete.setEnabled(false);
		
	}

	private void configureButtons() {
		super.btnNew.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				newProduct();
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
		this.modelo.remove(this.produtoSelecionado);
		
		cd = new ClassDao();
		cd.excluiP(produtoSelecionado.getId());
		
		clearFields();
	}

	protected void save() {
		cd = new ClassDao();
		if(produtoSelecionado == null){
			Produto p = new Produto();
			String strId = txtId.getText().trim();
			String strNome = txtNome.getText().trim();
			String strValor = txtValor.getText().trim();
			
			int intId = Integer.parseInt(strId);
			BigDecimal BigValor = new BigDecimal(strValor);
			
			p.setId(intId);
			p.setNome(strNome);
			p.setValor(BigValor);
			
			this.modelo.addProduct(p);
			
			cd.insereP(p);
			clearFields();
		} else {
			String strId = txtId.getText().trim();
			String strNome = txtNome.getText().trim();
			String strValor = txtValor.getText().trim();
			
			int intId = Integer.parseInt(strId);
			BigDecimal BigValor = new BigDecimal(strValor);
			
			this.produtoSelecionado.setId(intId);
			this.produtoSelecionado.setNome(strNome);
			this.produtoSelecionado.setValor(BigValor);
			
			cd.atualizaP(intId, produtoSelecionado);
			
			clearFields();
			this.modelo.fireTableDataChanged();
		}
		
	}

	protected void newProduct() {
		this.produtoSelecionado = null;
		clearFields();
		
	}

	private void configureTable() {
		ClassDao dao = new ClassDao();
		List<Produto> list = dao.getTodosP();
		
		this.modelo = new ProdutoModel(list);
		super.table.setModel(modelo);
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
		Produto p = this.modelo.getProduto(idx);
		this.produtoSelecionado = p;
		this.lblAlerta.setText(CARREGADO_PARA_ALTERACAO);
		
		super.txtId.setText(String.valueOf(p.getId()));
		super.txtNome.setText(p.getNome());
		super.txtValor.setText(String.valueOf(p.getValor()));
		
		super.btnDelete.setEnabled(true);
	}

}
