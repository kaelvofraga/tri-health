package br.edu.ifrs.canoas.tads.lds.relatorio;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.ifrs.canoas.tads.lds.bean.Cateterismo;
import br.edu.ifrs.canoas.tads.lds.bean.Medico;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;
import br.edu.ifrs.canoas.tads.lds.bean.UsuarioReportBean;
import br.edu.ifrs.canoas.tads.lds.bean.jasper.PerfilUsuarioBean;
  
public class RelatorioTeste 
{
	public static void main(String[] args)
	{
		PerfilUsuarioBean perfil = new PerfilUsuarioBean();
		List<PerfilUsuarioBean> lista = new ArrayList<PerfilUsuarioBean>();
		perfil.setNome("Luciano Bohrer Acosta");
		perfil.setNascimento("30/06/94");
		perfil.setNacionalidade("Brasileiro");
		perfil.setCelular("51 80310336");
		perfil.setEmail("bohrerluciano@gmail.com");
		perfil.setCidade("Canoas");
		perfil.setEndereco("Rua bla bla bla, 99999-460");
		perfil.setGenero("Masculino");
		perfil.setTelefone("51 30595044");
		perfil.setIdiomas("Português Inglês");
		perfil.setCidade("Canoas");
		perfil.setPais("Brasil");
		perfil.setEstado("RS");

		lista.add(perfil);
		try{
			PerfilUsuarioREL relatorio = new PerfilUsuarioREL();
			relatorio.imprimir(lista);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}
}