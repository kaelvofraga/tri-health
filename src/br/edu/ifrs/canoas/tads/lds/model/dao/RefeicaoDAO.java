package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.edu.ifrs.canoas.tads.lds.bean.Alimento;
import br.edu.ifrs.canoas.tads.lds.bean.Refeicao;
import br.edu.ifrs.canoas.tads.lds.bean.RefeicaoAlimento;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;
import br.edu.ifrs.canoas.tads.lds.util.DateUtil;

@Stateless
public class RefeicaoDAO extends BaseDAO<Refeicao, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2825399566888601704L;

	public List<Refeicao> buscaRefeicoes(Date dataDe, Date dataAte, Alimento alimento, Usuario usuario) {
		
		if(usuario == null) return new ArrayList<Refeicao>();		
		
		if(dataDe == null || dataAte == null || dataDe.compareTo(dataAte) > 0){	
			dataDe = DateUtil.getDataAtual();
			dataAte =  DateUtil.getDataAtualIncrementa(1);
		}		
		
		Criteria criteriaRefeicao = super.createCriteria().add(Restrictions.eq("usuario.id", usuario.getId()));
		List<Refeicao> refeicoes = criteriaRefeicao.add(Restrictions.between("refeicaoData", dataDe, dataAte)).list();
		
		for (Refeicao refeicao : refeicoes) {
			
			Criteria criteriaRefeicaoAlimento = this.getSection().createCriteria(RefeicaoAlimento.class)
			.add(Restrictions.eq("refeicao.id", refeicao.getId()));
			
			if(alimento != null){
				criteriaRefeicaoAlimento.add(Restrictions.eq("alimento.id", alimento.getId()));
			}			
			refeicao.getRefeicaoAlimentos().addAll(criteriaRefeicaoAlimento.list());				
		}
		
		return refeicoes;
	}
	
	
}
