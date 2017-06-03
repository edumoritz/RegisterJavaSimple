package br.home.recreation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import br.home.Contato;
import br.home.ContatoModel;

public class PanelActiveC extends PanelBaseC{
	
	private Contato contatoSelecionado;
	private ContatoModel modelo;
	private ClassDao cd;
	
	public PanelActiveC() {
		super();
		clearFields();
		configureButtons();
		configureTable();
		//configureMenus();
	}

	private void configureTable() {
		ClassDao dao = new ClassDao();
		List<Contato> lista = dao.getTodosC();
		
		this.modelo = new ContatoModel(lista);
		super.table.setModel(modelo);
		super.table.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2){
					int idx = table.getSelectedRow();
					if(idx < 0){
						System.out.println("Não a linha selecionada");
					} else {
						loadRow(idx);
					}
				}
			}
		});
		
	}

	protected void loadRow(int idx) {
		Contato c = this.modelo.getContato(idx);
		this.contatoSelecionado = c;
		this.lblAlerta.setText(CARREGADO_PARA_ALTERACAO);
		this.txtId.setText(String.valueOf(c.getId()));
		this.txtNome.setText(c.getNome());
		this.txtTelefone.setText(c.getTelefone());
		
		super.btnDelete.setEnabled(true);
	}

	private void clearFields() {
		super.lblAlerta.setText("");
		super.txtId.setText("");
		super.txtNome.setText("");
		super.txtTelefone.setText("");

		super.btnDelete.setEnabled(false);

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
		this.modelo.remover(this.contatoSelecionado);
		
		cd = new ClassDao();
		cd.excluiC(contatoSelecionado.getId());
		
		clearFields();
	}

	protected void save() {
		cd = new ClassDao();
		if(contatoSelecionado == null){
			Contato c = new Contato();
			String strId = txtId.getText().trim();
			String strNome = txtNome.getText().trim();
			String strTelefone = txtTelefone.getText().trim();

			int intId = Integer.parseInt(strId);

			c.setId(intId);
			c.setNome(strNome);
			c.setTelefone(strTelefone);
			
			this.modelo.adicionar(c);
			
			cd.insereC(c);
			clearFields();
		} else {
			String strId = txtId.getText().trim();
			String strNome = txtNome.getText().trim();
			String strTelefone = txtTelefone.getText().trim();

			int intId = Integer.parseInt(strId);

			this.contatoSelecionado.setId(intId);
			this.contatoSelecionado.setNome(strNome);
			this.contatoSelecionado.setTelefone(strTelefone);
			
			cd.atualizaC(intId, contatoSelecionado);
			
			clearFields();
			this.modelo.fireTableDataChanged();//??
		}
	}

	protected void newContact() {
		this.contatoSelecionado = null;

		clearFields();		
	}

}
