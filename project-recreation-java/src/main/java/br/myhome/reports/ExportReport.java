package br.myhome.reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import br.myhome.dao.ConectionBD;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class ExportReport {
	private static final String JASPER_CONTACT = "C:\\Users\\Eduardo\\JaspersoftWorkspace\\MyReports\\ContactsReport.jasper";
	private static final String JASPER_PRODUCT = "C:\\Users\\Eduardo\\JaspersoftWorkspace\\MyReports\\ProductsReport.jasper";

	public void exportContact() {
		JasperPrint jasperPrintPDF = getPrint();

		SimpleDateFormat sdf = new SimpleDateFormat("(yyyy-MM-dd)_HH-mm-ss");
		String data = sdf.format(new Date());

		String nomePdf = "relatorio_contatos_" + data + ".pdf";

		try {
			JasperExportManager.exportReportToPdfFile(jasperPrintPDF, nomePdf);
			Desktop.getDesktop().open(new File(nomePdf));
		} catch (JRException | IOException e) {
			e.printStackTrace();
		}
	}

	private JasperPrint getPrint() {
		Connection con = ConectionBD.getInstance().getConnection();

		try {
			return JasperFillManager.fillReport(JASPER_CONTACT, null, con);
		} catch (JRException e) {
			System.out.println("JasperNumDeu");
			throw new RuntimeException(e);
		}
	}

	public void exportProduct() {
		// ClassDao dao = new ClassDao();
		// List<Produto> list = dao.getTodosP();
		//
		// JRDataSource customDs = new CustomProdutoReports(list);
		//
		// JasperPrint jp;
		//
		// try {
		// jp = JasperFillManager.fillReport(JASPER_PRODUCT, null, customDs);
		// Locale locale = Locale.getDefault();
		// JasperViewer.viewReport(jp, false, locale);
		// } catch (JRException e) {
		// e.printStackTrace();
		// }
		JasperPrint jasperPrintPDF = getPrintP();

		SimpleDateFormat sdf = new SimpleDateFormat("(yyyy-MM-dd)_HH-mm-ss");
		String data = sdf.format(new Date());

		String nomePdf = "relatorio_produtos_" + data + ".pdf";

		try {
			JasperExportManager.exportReportToPdfFile(jasperPrintPDF, nomePdf);
			Desktop.getDesktop().open(new File(nomePdf));
		} catch (JRException | IOException e) {
			e.printStackTrace();
		}

	}

	private JasperPrint getPrintP() {
		Connection con = ConectionBD.getInstance().getConnection();

		try {
			return JasperFillManager.fillReport(JASPER_PRODUCT, null, con);
		} catch (JRException e) {
			System.out.println("JasperNumDeu");
			throw new RuntimeException(e);
		}
	}
}
