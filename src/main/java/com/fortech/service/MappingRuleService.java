package com.fortech.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.fortech.model.MappingRule;

@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class MappingRuleService {

	@PersistenceContext
	private EntityManager entityManager;

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void insertInDatabase(MappingRule mappingRule) {
		entityManager.getTransaction().begin();
		entityManager.persist(mappingRule);
		entityManager.getTransaction().commit();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void updateInDatabase(MappingRule mappingRule) {
		entityManager.getTransaction().begin();
		entityManager.merge(mappingRule);
		entityManager.getTransaction().commit();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void deleteFromDatabase(int idMappingRule) {
		entityManager.getTransaction().begin();
		entityManager.remove(entityManager.find(MappingRule.class, idMappingRule));
		entityManager.getTransaction().commit();
	}

	public List<MappingRule> getAll() {
		@SuppressWarnings("unchecked")
		TypedQuery<MappingRule> typedQuery = (TypedQuery<MappingRule>) entityManager.createNamedQuery(MappingRule.FIND_ALL_MAPPING_RULE);

		return typedQuery.getResultList();
	}
	
	public MappingRule findById(int idMappingRule) {
		return entityManager.find(MappingRule.class, idMappingRule);
	}
	
}
