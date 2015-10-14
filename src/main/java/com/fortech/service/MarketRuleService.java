package com.fortech.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.*;

import com.fortech.helpers.JAXBRuleConvertor;
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
		System.out.println("Aici am schimbat");
		entityManager.persist(marketRule);
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
		entityManager.merge(marketRule);
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
		entityManager.remove(entityManager.find(MarketRule.class, marketRulePK));
	}


	/**
	 * Method used to get all the MarketRules that are present in the
	 * database
	 * 
	 * @return list with all the rules in the database
	 */
	public List<MarketRuleJAXB> getAllMarketRule(){
		@SuppressWarnings("unchecked")
		TypedQuery<MarketRule> marketQuery = (TypedQuery<MarketRule>) entityManager.createNamedQuery(MarketRule.MARKETRULE_FIND_ALL);
		List<MarketRule> marketRules = new ArrayList<MarketRule>(marketQuery.getResultList());
		List<MarketRuleJAXB> marketRulesC = new ArrayList<MarketRuleJAXB>();
		
		for (MarketRule rule : marketRules) {
			MarketRuleJAXB marketRuleJAXB = JAXBRuleConvertor.copyPropertiesFrom(rule);
			marketRulesC.add(marketRuleJAXB);
		}
		return marketRulesC;
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
