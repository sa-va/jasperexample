package com.sava.jasperexample;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class PrintService {
	List<Person> persons = Arrays.asList(new Person("Иван", "Иванов"), new Person("Пётр", "Петров"),
			new Person("Сергей", "Сергеев"));

	public void createPdfReport() {
		try {
			InputStream is = PrintService.class.getClassLoader().getResourceAsStream("employeeList.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(is);
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("DATE", new Date());
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
					new JRBeanCollectionDataSource(persons));
			File outDir = new File("D:/myJasperReports");
			outDir.mkdirs();
			JasperExportManager.exportReportToPdfFile(jasperPrint, "D:/myJasperReports/employeeReport.pdf");
			System.out.println("PDF report done!");
		} catch (JRException ex) {
			ex.printStackTrace();
		}
	}

}
