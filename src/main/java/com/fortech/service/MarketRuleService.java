package com.fortech.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.fortech.model.MarketRule;
import com.fortech.model.MarketRulePK;

@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class MarketRuleService {

	@PersistenceContext
	private EntityManager entityManager;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void insertInDatabase(MarketRule marketRule) {
		entityManager.getTransaction().begin();
		entityManager.persist(marketRule);
		entityManager.getTransaction().commit();
		
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void updateInDatabase(MarketRule marketRule) {
		entityManager.getTransaction().begin();
		entityManager.merge(marketRule);
		entityManager.getTransaction().commit();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void deleteFromDatabase(MarketRulePK marketRulePK) {
		entityManager.getTransaction().begin();
		entityManager.remove(entityManager.find(MarketRule.class, marketRulePK));
		entityManager.getTransaction().commit();
	}
	
	public List<MarketRule> getAll(){
		@SuppressWarnings("unchecked")
		TypedQuery<MarketRule> marketQuery = (TypedQuery<MarketRule>) entityManager.createNamedQuery(MarketRule.MARKETRULE_FIND_ALL);		
		return marketQuery.getResultList();
	}
	
	public MarketRule findById(MarketRulePK marketRulePK){
		return entityManager.find(MarketRule.class, marketRulePK);
	}
	
}
