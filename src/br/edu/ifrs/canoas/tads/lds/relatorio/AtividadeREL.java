package br.edu.ifrs.canoas.tads.lds.relatorio;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.ejb.Init;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;


import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import br.edu.ifrs.canoas.tads.lds.bean.Cateterismo;
import br.edu.ifrs.canoas.tads.lds.bean.jasper.AtividadesBean;
import br.edu.ifrs.canoas.tads.lds.bean.jasper.PerfilUsuarioBean;

/**
 * Classe responsável por gerar, exportar e apresentar o Relatório de Atividades de maneira 
 * que o usuário possa salvar ou imprimir o PDF.
 * 
 * @author Luciano Acosta
 * */
public class AtividadeREL {

	//caminho base
	private String path;
	
	//Onde será armazenado o path para o package onde estão armazenados os relatórios Jasper(.jrxml)
	private String pathToReportPackage;
	
	//Constante com nome da pasta
	private static final String WEB_INF_DIR_NAME="WEB-INF";
    private static String web_inf_path;
    
    //Captura o caminho do web-inf
    public static String getWebInfPath() throws UnsupportedEncodingException {
        if (web_inf_path == null) {
            web_inf_path = URLDecoder.decode(Init.class.getProtectionDomain().getCodeSource().getLocation().getPath(), "UTF8");
            web_inf_path=web_inf_path.substring(0,web_inf_path.lastIndexOf(WEB_INF_DIR_NAME)+WEB_INF_DIR_NAME.length());
        }
        return web_inf_path;
    }
	
	//Recupera os caminhos para que a classe possa encontrar os relatórios
		public AtividadeREL() {
			this.path = this.getClass().getClassLoader().getResource("").getPath();
			this.pathToReportPackage = this.path + "br/edu/ifrs/canoas/tads/lds/jasper/";
		}
		
		
		//Imprime/gera uma lista de Clientes
		public void imprimir(List<AtividadesBean> atividades) throws Exception	
		{
		
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			facesContext.responseComplete();

			//Gera um arquivo em memória
			InputStream in = this.getClass().getClassLoader().getResourceAsStream("br/edu/ifrs/canoas/tads/lds/jasper/Atividade.jrxml");
			
			//Compila o arquivo jrxml em jasper
			JasperReport report = JasperCompileManager.compileReport(in);
			
			//Preenche o arquivo com as informações do list<>
			JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(atividades));
			


			JRPdfExporter exporter = new JRPdfExporter();

			exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);

			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

			exporter.exportReport();
			
			
			byte[] bytes = baos.toByteArray();

			if (bytes != null && bytes.length > 0) {

			//Busca contexto atual do servlet
			HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

			//Seta conteudo do arquivo a ser gerado
			response.setContentType("application/pdf");

			//Seta nome do arquivo a ser gerado
			response.setHeader("Content-disposition", "inline; filename=\"RelatorioAtividades.pdf\"");

			//Length total do arquivo
			response.setContentLength(bytes.length);

			ServletOutputStream outputStream = response.getOutputStream();

			outputStream.write(bytes, 0, bytes.length);

			outputStream.flush();

			outputStream.close();
			
			}
		}
	 
		public String getPathToReportPackage() {
			return this.pathToReportPackage;
		}
		
		public String getPath() {
			return this.path;
		}
	}