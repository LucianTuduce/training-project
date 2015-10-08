package com.fortech.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.fortech.model.InterpretationRule;

@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class InterpretationRuleService {

	@PersistenceContext
	private EntityManager entityManager;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void insertInDatabase(InterpretationRule interpretationRule) {
		entityManager.getTransaction().begin();
		entityManager.persist(interpretationRule);
		entityManager.getTransaction().commit();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void updateInDatabase(InterpretationRule interpretationRule) {
		entityManager.getTransaction().begin();
		entityManager.merge(interpretationRule);
		entityManager.getTransaction().commit();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void deleteFromDatabase(int idInterpretationRule) {
		entityManager.getTransaction().begin();
		entityManager.remove(entityManager.find(InterpretationRule.class, idInterpretationRule));
		entityManager.getTransaction().commit();
	}

	public List<InterpretationRule> getAll() {
		@SuppressWarnings("unchecked")
		TypedQuery<InterpretationRule> typedQuery = (TypedQuery<InterpretationRule>) entityManager.createNamedQuery(InterpretationRule.FIND_ALL_INTERPRETATION_RULE);

		return typedQuery.getResultList();
	}
	
	public InterpretationRule findById(int idInterpretationRule) {
		return entityManager.find(InterpretationRule.class, idInterpretationRule);
	}
}
