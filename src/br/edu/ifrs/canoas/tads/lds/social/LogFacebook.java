/**
 * 
 */
package br.edu.ifrs.canoas.tads.lds.social;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.edu.ifrs.canoas.tads.lds.bean.BaseEntity;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;

/**
 * Entity implementation class for Entity: LogFacebook
 * 
 * @brief Classe que representa a tabela de registro de compartilhamentos no Facebook.
 * @author Kael Fraga
 * @since 23/06/2015
 * 
 * Atributos:
 * - usuario (Usuario): usuário da seção.
 * - data (Date): data do compartilhamento.
 * 
 * **/
@Entity
public class LogFacebook extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = 4482033077122637438L;

	@NotNull @ManyToOne
	@JoinColumn(name="USUARIO_ID")
	private Usuario usuario;
	
	@NotNull @Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	public LogFacebook() {}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
}
