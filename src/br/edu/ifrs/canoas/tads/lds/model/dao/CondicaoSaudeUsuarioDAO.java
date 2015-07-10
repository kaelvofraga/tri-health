package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.io.Serializable;

import javax.ejb.Stateless;

import br.edu.ifrs.canoas.tads.lds.bean.CondicaoSaudeUsuario;

/**
 * @author Luana
 */

@Stateless
public class CondicaoSaudeUsuarioDAO extends BaseDAO<CondicaoSaudeUsuario, Long> implements Serializable{

	private static final long serialVersionUID = 3538887461219854472L;

}
