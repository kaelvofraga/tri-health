package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;

import br.edu.ifrs.canoas.tads.lds.bean.BaseEntity;

@Stateless
// @Named
public abstract class BaseDAO<T extends BaseEntity<ID>, ID> implements
		Serializable {

	private static final long serialVersionUID = -5953225846505938118L;

	@PersistenceContext 
	protected EntityManager em;
	@SuppressWarnings("rawtypes")
	private Class entidade;

	public BaseDAO() {}

	@SuppressWarnings("rawtypes")
	public Class getEntityClass() {
		if (entidade == null) {
			entidade = (Class) ((ParameterizedType) getClass()
					.getGenericSuperclass()).getActualTypeArguments()[0];
		}
		return entidade;
	}

	@SuppressWarnings("rawtypes")
	public void setEntityClass(Class entidade) {
		this.entidade = entidade;
	}
	
	public Session getSection(){
		return (Session) em.getDelegate();
	}

	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public T busca(ID id) {
		return (T) em.find(getEntityClass(), id);
	}

	public void exclui(ID id) {
		@SuppressWarnings("unchecked")
		Object ref = em.getReference(getEntityClass(), id);
		em.remove(ref);
	}

	public T atualiza(T t) {
		return (T) em.merge(t);
	}

	public void insere(T t) {
		em.persist(t);
	}

	@SuppressWarnings("rawtypes")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List buscaTodos() {
		return em.createQuery(
				"Select entity FROM " + getEntityClass().getSimpleName()
						+ " entity").getResultList();
	}
	
	public Criteria createCriteria(){
		return this.getSection().createCriteria(this.getEntityClass());
	}
}