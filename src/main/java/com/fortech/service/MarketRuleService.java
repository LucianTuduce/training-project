package com.fortech.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.fortech.convertor.MarketRuleFlattener;
import com.fortech.model.MarketRule;
import com.fortech.model.MarketRulePK;
import com.fortech.modeljaxb.MarketRuleJAXB;

/**
 * Service class for the MarketRule.
 * @author lucian.tuduce
 *
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class MarketRuleService {

	/**
	 * The object that will manage the connection with the
	 * database and perform the CRUD operations on the rule.
	 */
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * Method used in order to insert a MarketRule in the database.
	 * 
	 * @param marketRule
	 *            the rule that will be added in the database
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void insertInDatabase(MarketRule marketRule) {
		entityManager.getTransaction().begin();
		entityManager.persist(marketRule);
		entityManager.getTransaction().commit();
		
	}

	/**
	 * Method used in order to update an existing MarketRule in the
	 * database.
	 * 
	 * @param marketRule
	 *            the object with the new values that the rule will have
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void updateInDatabase(MarketRule marketRule) {
		entityManager.getTransaction().begin();
		entityManager.merge(marketRule);
		entityManager.getTransaction().commit();
	}

	/**
	 * Method used in order to delete an MarketRule in the database.
	 * 
	 * @param marketRulePK
	 *            the id of the rule that will be deleted. The id is
	 *            a composed primary key.
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void deleteFromDatabase(MarketRulePK marketRulePK) {
		entityManager.getTransaction().begin();
		entityManager
				.remove(entityManager.find(MarketRule.class, marketRulePK));
		entityManager.getTransaction().commit();
	}




	/**
	 * Method used to get all the MarketRules that are present in the
	 * database
	 * 
	 * @return list with all the rules in the database
	 */
	public List<MarketRule> getAll(){
		@SuppressWarnings("unchecked")
		TypedQuery<MarketRule> marketQuery = (TypedQuery<MarketRule>) entityManager.createNamedQuery(MarketRule.MARKETRULE_FIND_ALL);
		List<MarketRule> marketRules = new ArrayList<MarketRule>(marketQuery.getResultList());

		List<MarketRuleJAXB> marketRulesC = new ArrayList<MarketRuleJAXB>();
		MarketRulePK markPK = new MarketRulePK();

		MarketRuleFlattener chg = new MarketRuleFlattener();

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

		return marketRules;
	}
	
	/**
	 * Method used in order to get a MappingRule from the database based
	 * on an id
	 * 
	 * @param idMappingRule
	 *            id of the rule that will be obtained from the database.The id is
	 *            a composed primary key.
	 *            
	 * @return the rule that will have the id as the parameter of the method
	 */
	public MarketRule findById(MarketRulePK marketRulePK){
		return entityManager.find(MarketRule.class, marketRulePK);
	}	
}
