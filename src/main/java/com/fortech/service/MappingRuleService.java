package com.fortech.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.fortech.convertor.ModelToJAXBModelConvertor;
import com.fortech.convertor.XmlJsonObjectConvertor;
import com.fortech.model.MappingRule;
import com.fortech.modeljaxb.MappingRuleJAXB;
import com.fortech.wrapper.WrapperRuleJAXB;

/**
 * Service class for the MappingRule.
 * @author lucian.tuduce
 *
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class MappingRuleService {

	/**
	 * The object that will manage the connection with the
	 * database and perform the CRUD operations on the rule.
	 */
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Method used in order to insert a MappingRule in the database.
	 * 
	 * @param mappingRule
	 *            the rule that will be added in the database
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void insertInDatabase(MappingRule mappingRule) {
		entityManager.persist(mappingRule);
	}

	/**
	 * Method used in order to update an existing MappingRule in the
	 * database.
	 * 
	 * @param mappingRule
	 *            the object with the new values that the rule will have
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void updateInDatabase(MappingRule mappingRule) {
		entityManager.merge(mappingRule);
	}

	/**
	 * Method used in order to delete an MappingRule in the database.
	 * 
	 * @param idMappingRule
	 *            the id of the rule that will be deleted
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void deleteFromDatabase(int idMappingRule) {
		entityManager.remove(entityManager.find(MappingRule.class, idMappingRule));
	}

	/**
	 * Method used to get all the MappingRules that are present in the
	 * database
	 * 
	 * @return list with all the mapping rules in the database
	 */
	public List<MappingRuleJAXB> getAllMappingRule() {
		@SuppressWarnings("unchecked")
		TypedQuery<MappingRule> mappingQuery = (TypedQuery<MappingRule>) entityManager.createNamedQuery(MappingRule.FIND_ALL_MAPPING_RULE);
		List<MappingRule> mappingRule = new ArrayList<MappingRule>(mappingQuery.getResultList());
		return createJAXBList(mappingRule);
	}

	private List<MappingRuleJAXB> createJAXBList(List<MappingRule> mappingRule) {
		List<MappingRuleJAXB> mappingRulesJaxB = new ArrayList<MappingRuleJAXB>();

		for (MappingRule rule : mappingRule) {		
			mappingRulesJaxB.add(ModelToJAXBModelConvertor.getMappingRuleJAXB(rule));
		}
		return mappingRulesJaxB;
	}
	
	
	/**
	 * Method used in order to get a MappingRule from the database based
	 * on an id
	 * 
	 * @param idMappingRule
	 *            id of the rule that will be obtained from the database
	 * @return the rule that will have the id as the parameter of the method
	 */
	public MappingRule findById(int idMappingRule) {
		return entityManager.find(MappingRule.class, idMappingRule);
	}

	/**
	 * Method used in order to get a MappingRule from the database based
	 * on an id
	 * 
	 * @param idMappingRule
	 *            id of the rule that will be obtained from the database
	 * @return the rule that will have the id as the parameter of the method
	 */
	public MappingRuleJAXB getById(WrapperRuleJAXB wrapperRuleJAXB) {
		int id = XmlJsonObjectConvertor.getMappingRuleFromXML(wrapperRuleJAXB.getJsonORxml()).getId();
		return createJAXBRule(id);
	}

	private MappingRuleJAXB createJAXBRule(int id) {
		MappingRule mappingRule = entityManager.find(MappingRule.class, id);
		return ModelToJAXBModelConvertor.getMappingRuleJAXB(mappingRule);
	}
	
}
