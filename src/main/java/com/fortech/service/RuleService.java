package com.fortech.service;

import java.lang.reflect.ParameterizedType;
import java.util.Deque;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.fortech.model.InterpretationRule;


@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class RuleService<T> {

	
	@PersistenceContext
	private EntityManager entityManager;
	
	private Class<T>  entityClass;
	
	public RuleService (Class<T> entityClass) {
       this.entityClass = entityClass;
    }
	
	public RuleService () {
	      
	    }

	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public T find(T id) {
        return (T) this.entityManager.find(entityClass.getClass(), id);
    }
 
    public void delete(T id) {
        Object ref = this.entityManager.getReference(entityClass.getClass(), id);
        this.entityManager.remove(ref);
    }
 
    public T update(T t) {
        return this.entityManager.merge(t);
    }
 
    public void insert(T t) {
        this.entityManager.persist(t);
    }
 

	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<T> getRules(String query) {
		return (List<T>) entityManager.createNamedQuery(query).getResultList();
    }
	
}
