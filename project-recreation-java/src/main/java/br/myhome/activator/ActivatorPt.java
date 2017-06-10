package br.myhome.activator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.List;

import br.myhome.dao.ClassDao;
import br.myhome.pojos.Product;
import br.myhome.screen.PanelBasePt;
import br.myhome.tablemodel.ProductModel;

public class ActivatorPt extends PanelBasePt {

	private Product productSelected;
	private ProductModel model;
	private ClassDao dao;

	public ActivatorPt() {
		super();
		clearFields();
		configureButtons();
		configureTable();
	}

	private void configureTable() {
		ClassDao dao = new ClassDao();
		List<Product> list = dao.getTodosP();
		
		this.model = new ProductModel(list);
		super.table.setModel(model);
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
		Product p = this.model.getProduct(idx);
		this.productSelected = p;
		this.lblAlerta.setText(CARREGADO_PARA_ALTERACAO);
		super.txtId.setText(String.valueOf(p.getId()));
		super.txtNome.setText(p.getNome());
		super.txtValor.setText(String.valueOf(p.getValor()));
		
		super.btnDelete.setEnabled(true);
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
		this.model.remove(this.productSelected);
		
		dao = new ClassDao();
		dao.excluiP(productSelected.getId());
		
		clearFields();
	}

	protected void save() {
		dao = new ClassDao();
		if(productSelected == null){
			Product p = new Product();
			String strId = txtId.getText().trim();
			String strNome = txtNome.getText().trim();
			String strValor = txtValor.getText().trim();
			
			int intId = Integer.parseInt(strId);
			BigDecimal big = new BigDecimal(strValor);
			
			p.setId(intId);
			p.setNome(strNome);
			p.setValor(big);
			
			this.model.addProduct(p);
			
			dao.insereP(p);
			clearFields();
		} else {
			String strId = txtId.getText().trim();
			String strNome = txtNome.getText().trim();
			String strValor = txtValor.getText().trim();
			
			int intId = Integer.parseInt(strId);
			BigDecimal big = new BigDecimal(strValor);
			
			this.productSelected.setId(intId);
			this.productSelected.setNome(strNome);
			this.productSelected.setValor(big);
			
			dao.atualizaP(intId, productSelected);
			
			clearFields();
			this.model.fireTableDataChanged();
		}
	}

	protected void newProduct() {
		this.productSelected = null;
		clearFields();
	}

	private void clearFields() {
		super.lblAlerta.setText("");
		super.txtId.setText("");
		super.txtNome.setText("");
		super.txtValor.setText("");

		super.btnDelete.setEnabled(false);
	}

}
