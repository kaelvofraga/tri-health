package br.edu.ifrs.canoas.tads.lds.model.dao;
/**
 * DAO implementation class: MedicamentoDAO
 * @author Alisson Lorscheiter
 *
 */
import java.util.List;
import javax.ejb.Stateless;
import br.edu.ifrs.canoas.tads.lds.bean.Medicamento;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;

@Stateless
public class MedicamentoDAO extends BaseDAO< Medicamento, Long>{

	private static final long serialVersionUID = -3548199147864190785L;

    /*Metodo para buscar do banco os medicamentos do usuario*/
	@SuppressWarnings("unchecked")
	public List<Medicamento> buscaNomeMedicamentoPorUsuario(Usuario usuario) {
		
		return em.createQuery(
				"SELECT m " 
		         + "FROM Medicamento m "
		         + "WHERE m.id in "
		         + "(select mu.medicamento.id "
		         + "	from MedicamentoUsuario mu "
		         + "	where mu.usuario.id = :usuario) ")
		         .setParameter("usuario", usuario.getId())
		         .getResultList();
	}

	 /*Metodo para buscar do banco os medicamentos pelo nome*/
	@SuppressWarnings("unchecked")
	public List<Medicamento> buscaPorNome(String nome) {
		return em.createQuery(
				"SELECT m " 
		         + "FROM Medicamento m "
		         + "WHERE lower(m.nome) = lower(:nome) ")
		         .setParameter("nome", nome)
		         .getResultList();
	}
}
