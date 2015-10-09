package com.fortech.service;

import java.util.ArrayList;
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
	public void insertMarketRuleInDatabase(MarketRule MarketRule) {
		entityManager.getTransaction().begin();
		entityManager.persist(MarketRule);
		entityManager.getTransaction().commit();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void updateMarketRuleInDatabase(MarketRule MarketRule) {
		entityManager.getTransaction().begin();
		entityManager.merge(MarketRule);
		entityManager.getTransaction().commit();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void deleteMarketRuleFromDatabase(MarketRulePK marketRulePK) {
		entityManager.getTransaction().begin();
		entityManager
				.remove(entityManager.find(MarketRule.class, marketRulePK));
		entityManager.getTransaction().commit();
	}

	/**
	 * Getting all the marketRule from the DB
	 * 
	 * @return a list of MarketRuleJAXB
	 */
	public List<MarketRuleJAXB> getAllMarketRule() {

		@SuppressWarnings("unchecked")
		TypedQuery<MarketRule> marketQuery = (TypedQuery<MarketRule>) entityManager.createNamedQuery(MarketRule.MARKETRULE_FIND_ALL);
		List<MarketRule> marketRules = new ArrayList<MarketRule>(marketQuery.getResultList());

		List<MarketRuleJAXB> marketRulesC = new ArrayList<MarketRuleJAXB>();
		MarketRulePK markPK = new MarketRulePK();

		MarketRuleConvertor chg = new MarketRuleConvertor();

		for (MarketRule i : marketRules) {
			markPK = i.getId();
			MarketRuleJAXB j = new MarketRuleJAXB();
			j.setCountryNumber(markPK.getCountryNumber());
			j.setBranch(markPK.getBranch());
			j.setStockCategory(chg.changeShortToEnum(markPK.getStockCategory()));
			j.setActive(chg.changeShortToBoolean(i.getActive()));
			j.setRule(i.getRule());

			marketRulesC.add(j);

		}

		return marketRulesC;
	}

	public MarketRule findByIdMarketRule(MarketRulePK marketRulePK) {
		return entityManager.find(MarketRule.class, marketRulePK);
	}

}
