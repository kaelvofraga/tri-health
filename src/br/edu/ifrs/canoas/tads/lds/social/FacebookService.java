package br.edu.ifrs.canoas.tads.lds.social;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import facebook4j.*;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;
import br.edu.ifrs.canoas.tads.lds.util.Mensagens;

/** Service da US Compartilhar Atividades nas Redes Sociais
* @author Kael Fraga
* @since 23/06/2015
* */
@Stateless
public class FacebookService {
	
	private final static int FACEBOOK_DUPLICATE_POST = 506;
	
	@Inject
	private LogFacebookDAO logFacebookDAO;
		
	private Facebook facebook;
	
	public FacebookService() {}
	
	/** 
	 * @brief Cria instância do Facebook. 
	 * @param void
	 * @return void
	 * */
	public void initFacebook() {
		this.facebook = new FacebookFactory().getInstance();
	}
	
	/** 
	 * @brief Publica compartilhamento no Facebook do usuário da seção.	
	 * @param Usuario usuario: usuário da seção  	 		  
	 * @param String message: mensagem a ser publicada
	 * @return true se publicou com sucesso, false se um erro ocorreu
	 * */
	public boolean publicarAtividade(Usuario usuario, String message) {
		if ( message != null &&
			 this.facebook != null ) {		
			
			LogFacebook logFacebook = new LogFacebook();
			logFacebook.setUsuario(usuario);
			logFacebook.setData(Calendar.getInstance().getTime());
			
			try {				
				ResourceBundle resBundle = ResourceBundle.getBundle("facebook4j");
				
				PostUpdate post = new PostUpdate(new URL(resBundle.getString("facebookPost.url")))
                								.picture(new URL(resBundle.getString("facebookPost.logo")))
                								.name(message)
                								.caption(resBundle.getString("facebookPost.caption"))
                								.description(resBundle.getString("facebookPost.slogan"));
				
				facebook.postFeed(post);
				
				logFacebookDAO.insere(logFacebook);
				
				Mensagens.define(FacesMessage.SEVERITY_INFO,
						"Facebook.compartilhar.sucesso");
				
			} catch (FacebookException e) {
				
				if(e.getErrorCode() == FACEBOOK_DUPLICATE_POST){					
					Mensagens.define(FacesMessage.SEVERITY_ERROR,
							"Facebook.compartilhar.erro.duplicado");					
				}else{
					Mensagens.define(FacesMessage.SEVERITY_ERROR,
							"Facebook.compartilhar.erro");
					e.printStackTrace();
				}					
				return false;
				
			} catch (MalformedURLException e){
				e.printStackTrace();
				return false;
			}
			
			return true;
		}		
		
		return false;
	}	
}
