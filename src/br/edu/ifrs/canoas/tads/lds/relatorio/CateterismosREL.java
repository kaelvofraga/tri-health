package br.edu.ifrs.canoas.tads.lds.relatorio;

import java.util.List;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.edu.ifrs.canoas.tads.lds.bean.Cateterismo;
import br.edu.ifrs.canoas.tads.lds.bean.CateterismoReportBean;

public class CateterismosREL {

	//caminho base
	private String path;
	
	//Caminho para o package onde estão armazenados os relatórios Jasper
	private String pathToReportPackage;
	
	//Recupera os caminhos para que a classe possa encontrar os relatórios
		public CateterismosREL() {
			this.path = this.getClass().getClassLoader().getResource("").getPath();
			this.pathToReportPackage = this.path + "br/edu/ifrs/canoas/tads/lds/jasper/";
			System.out.println(path);
		}
		
		
		//Imprime/gera uma lista de Clientes
		public void imprimir(List<CateterismoReportBean> exames) throws Exception	
		{
			JasperReport report = JasperCompileManager.compileReport(this.getPathToReportPackage() + "Cateterismos.jrxml");
			
			JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(exames));
	 
			JasperExportManager.exportReportToPdfFile(print, "/Users/lucianoacosta/Documents/Cateterismos_Rel.pdf");		
		}
	 
		public String getPathToReportPackage() {
			return this.pathToReportPackage;
		}
		
		public String getPath() {
			return this.path;
		}
	}