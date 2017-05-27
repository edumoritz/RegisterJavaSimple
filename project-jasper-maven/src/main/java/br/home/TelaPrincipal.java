package br.home;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

//Subclasse
public class TelaPrincipal extends TelaPrincipalBase {

	private Contato contatoSelecionado;
	private ContatoModel modelo;
	private ContatoDao cd;

	public TelaPrincipal() {
		super();// executar o construtor da super classe que é telaprincipal
		limparCampos();
		configurarBotoes();
		configuraTabela();
		configuraMenus();
	}

	private void configuraMenus() {
		
		super.mntmImprimir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ReportManager rm = new ReportManager();
				rm.imprimir();
			}
		});
		super.mntmExportarpdf.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ReportManager rm = new ReportManager();
				rm.exportar();
			}
		});
		
	}

	private void configuraTabela() {
		ContatoDao dao = new ContatoDao();
		List<Contato> lista = dao.getTodos();
		
		this.modelo = new ContatoModel(lista);
		super.table.setModel(modelo);
		super.table.addMouseListener(new MouseAdapter() {// MouseAdapter é usado
															// para não
															// implementar
															// metodos que não
															// forem usar
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int idx = table.getSelectedRow();
					if (idx < 0) {
						System.out.println("Não há linha selecionada");
					} else {
						System.out.println("Linha " + (idx + 1));
						carregarLinha(idx);
					}
				}
			}
		});
	}
	//Função que faz carregar as informações da linha nos fields
	protected void carregarLinha(int idx) {
		Contato c = this.modelo.getContato(idx);
		this.contatoSelecionado = c;
		this.labelAlerta.setText(CARREGADO_PARA_ALTERACAO);
		
		super.txfId.setText(String.valueOf(c.getId()));
		super.txfNome.setText(c.getNome());
		super.txfTelefone.setText(c.getTelefone());

		super.btnExcluir.setEnabled(true);// habilita o botao excluir
		//System.out.println("passou aqui");
	}

	private void configurarBotoes() {
		super.btnNovo.addActionListener(new ActionListener() {// classe anonima

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicou Novo");
				novo();
			}
		});
		super.btnSalvar.addActionListener(new ActionListener() {// classe
																// anonima

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicou Salvar");
				salvar();
			}
		});
		super.btnExcluir.addActionListener(new ActionListener() {// classe
																	// anonima

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicou Excluir");
				excluir();
			}
		});
	}

	protected void excluir() {// excluindo da tela
		this.modelo.remover(this.contatoSelecionado);// removendo do contato

		limparCampos();
	}

	protected void salvar() {
		cd = new ContatoDao();
		if (contatoSelecionado == null) {
			Contato c = new Contato();
			// trim = aparar{esse comando
			// remove os espaços no
			// inicio e fim da string,
			// muito util para ajustar
			// emails e logins que
			// vieram de ctrl+v}
			String strId = txfId.getText().trim();
			String strNome = txfNome.getText().trim();
			String strTelefone = txfTelefone.getText().trim();

			int intId = Integer.parseInt(strId);

			c.setId(intId);
			c.setNome(strNome);
			c.setTelefone(strTelefone);
			
			//adiciona contato na tabela
			this.modelo.adicionar(c);
			
			//insere com sql
			cd.insere(c);
			
			limparCampos();
		} else {
			// altera contato selecionado
			String strId = txfId.getText().trim();
			String strNome = txfNome.getText().trim();
			String strTelefone = txfTelefone.getText().trim();

			int intId = Integer.parseInt(strId);

			this.contatoSelecionado.setId(intId);
			this.contatoSelecionado.setNome(strNome);
			this.contatoSelecionado.setTelefone(strTelefone);

			limparCampos();
			this.modelo.fireTableDataChanged();
		}
	}

	protected void novo() {
		this.contatoSelecionado = null;

		limparCampos();
	}

	private void limparCampos() {
		super.labelAlerta.setText("");
		super.txfId.setText("");
		super.txfNome.setText("");
		super.txfTelefone.setText("");

		super.btnExcluir.setEnabled(false);// desabilita botao excluir
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Verificar com o mesmo da classe
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
