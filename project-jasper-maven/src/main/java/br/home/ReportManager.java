package br.home;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import java.util.Locale;

import com.ibm.icu.text.SimpleDateFormat;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class ReportManager {

	private static final String JASPER_CONTATO = "C:\\Users\\Eduardo\\JaspersoftWorkspace\\MyReports\\HelloJasper.jasper";
	
	public void imprimir(){
		JasperPrint jasperPrintPDF = getPrint();
		Locale locale = Locale.getDefault();
		JasperViewer.viewReport(jasperPrintPDF, false, locale);
	}
	
	public void exportar(){
		JasperPrint jasperPrintPDF = getPrint();
		
		SimpleDateFormat sdf = new SimpleDateFormat("(yyyy-MM-dd)_HH-mm-ss");
		String data = sdf.format(new Date());
		
		String nomePdf = "relatorio_contatos_"+data+".pdf";
		
		try {
			JasperExportManager.exportReportToPdfFile(jasperPrintPDF, nomePdf);
			Desktop.getDesktop().open(new File(nomePdf));
		} catch (JRException | IOException e) {
			e.printStackTrace();
		}
		
	}
	private JasperPrint getPrint() {
		Connection con = ConexaoDB.getInstance().getConnection();
		try {
			return JasperFillManager.fillReport(JASPER_CONTATO, null, con);
		} catch (JRException e) {
			throw new RuntimeException(e);
		}
	}

}
