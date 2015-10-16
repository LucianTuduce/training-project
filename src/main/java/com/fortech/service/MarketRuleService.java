package com.fortech.service;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.*;
import com.fortech.convertor.MarketRuleFlattener;
import com.fortech.convertor.XmlJsonObjectConvertor;
import com.fortech.helpers.JAXBRuleConvertor;
import com.fortech.model.MarketRule;
import com.fortech.model.MarketRulePK;
import com.fortech.modeljaxb.MarketRuleFlattedJAXB;
import com.fortech.modeljaxb.MarketRuleJAXB;
import com.fortech.wrapper.WrapperRuleJAXB;

/**
 * Service class for the MarketRule.
 * 
 * @author lucian.tuduce
 *
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class MarketRuleService {

	/**
	 * The object that will manage the connection with the database and perform
	 * the CRUD operations on the rule.
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
		entityManager.persist(marketRule);
	}

	/**
	 * Method used in order to update an existing MarketRule in the database.
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
	 *            the id of the rule that will be deleted. The id is a composed
	 *            primary key.
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void deleteFromDatabase(MarketRulePK marketRulePK) {
		entityManager
				.remove(entityManager.find(MarketRule.class, marketRulePK));
	}

	/**
	 * Method used to get all the MarketRules that are present in the database
	 * 
	 * @return list with all the rules in the database
	 */
	public List<MarketRuleFlattedJAXB> getAllMarketRule() {
		@SuppressWarnings("unchecked")
		TypedQuery<MarketRule> marketQuery = (TypedQuery<MarketRule>) entityManager
				.createNamedQuery(MarketRule.MARKETRULE_FIND_ALL);
		List<MarketRule> marketRules = new ArrayList<MarketRule>(
				marketQuery.getResultList());
		List<MarketRuleFlattedJAXB> marketRulesFlattedJaxB = new ArrayList<MarketRuleFlattedJAXB>();

		for (MarketRule rule : marketRules) {
			MarketRuleFlattedJAXB marketRuleFJAXB = JAXBRuleConvertor
					.copyPropertiesFrom(rule);
			marketRulesFlattedJaxB.add(marketRuleFJAXB);
		}
		return marketRulesFlattedJaxB;
	}

	/**
	 * Method used in order to get a MarketRule from the database based on an
	 * id
	 * 
	 * @param idMappingRule
	 *            id of the rule that will be obtained from the database.The id
	 *            is a composed primary key.
	 * 
	 * @return the rule that will have the id as the parameter of the method
	 */
	public MarketRule findById(MarketRulePK marketRulePK) {
		return entityManager.find(MarketRule.class, marketRulePK);
	}

	/**
	 * Method used in order to get a MarketRule from the database based on an
	 * id
	 * @param id of the rule that will be obtained from the database.
	 * @return he rule that will have the id as the parameter of the method
	 */
	public MarketRuleFlattedJAXB getById(WrapperRuleJAXB wrapperRuleJAXB) {

		MarketRuleFlattener convertor = new MarketRuleFlattener();
		MarketRuleFlattedJAXB marketRuleFlattedJAXB = XmlJsonObjectConvertor.getMarketRuleFFromXML(wrapperRuleJAXB.getJsonORxml());
		
		MarketRulePK pk = new MarketRulePK();
		pk.setBranch(marketRuleFlattedJAXB.getBranch());
		pk.setCountryNumber(marketRuleFlattedJAXB.getCountryNumber());
		pk.setStockCategory(convertor.changeEnumToShort(marketRuleFlattedJAXB.getStockCategory()));
		
		MarketRule marketRule = entityManager.find(MarketRule.class, pk);
				
		marketRuleFlattedJAXB.setActive(convertor.changeShortToBoolean(marketRule.getActive()));
		marketRuleFlattedJAXB.setBranch(pk.getBranch());
		marketRuleFlattedJAXB.setCountryNumber(pk.getCountryNumber());
		marketRuleFlattedJAXB.setRule(marketRule.getRule());
		marketRuleFlattedJAXB.setStockCategory(convertor.changeShortToEnum(pk.getStockCategory()));
		
		return marketRuleFlattedJAXB;
	}
}
