package br.univel.reports.custom;

import java.util.Iterator;
import java.util.List;
import br.home.Contato;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class CustomContatoReports implements JRDataSource{//FORMA SEMELHANTE AO GETVALUEAT

	private int contador = 0;
	private Iterator<Contato> it;//percorrer na lista
	private Contato selecionado;
	
	public CustomContatoReports(List<Contato> lista) {
		this.it = lista.iterator();
	}

	@Override
	public Object getFieldValue(JRField field) throws JRException {//executa todas vez que o metodo next for true
		
		if("Id".equals(field.getName())){
			return selecionado.getId();
		}
		if("Nome".equals(field.getName())){
			return selecionado.getNome();
		}
		if("Telefone".equals(field.getName())){
			return selecionado.getTelefone();
		}
		return "Mah oeee!";//valor de todas as colunas
	}

	@Override
	public boolean next() throws JRException {//retorna true até terminar a condição
		//return contador++ < 100;
		if(this.it.hasNext()){
			this.selecionado = it.next();
			return true;
		}
		return false;
	}

}
