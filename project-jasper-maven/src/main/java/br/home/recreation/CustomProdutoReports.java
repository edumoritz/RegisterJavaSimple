package br.home.recreation;

import java.util.Iterator;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class CustomProdutoReports implements JRDataSource {

	private int contador = 0;
	private Iterator<Produto> it;
	private Produto selecionado;
	
	public CustomProdutoReports(List<Produto> list) {
		this.it = list.iterator();
	}
	@Override
	public boolean next() throws JRException {
		if(this.it.hasNext()){
			this.selecionado = it.next();
			return true;
		}
		return false;
	}

	@Override
	public Object getFieldValue(JRField field) throws JRException {
		if("Id".equals(field.getName())){
			return selecionado.getId();
		}
		if("Nome".equals(field.getName())){
			return selecionado.getNome();
		}
		if("Telefone".equals(field.getName())){
			return selecionado.getValor();
		}
		return "Produto";
	}

}
