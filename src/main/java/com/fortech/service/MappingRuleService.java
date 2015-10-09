package com.fortech.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.fortech.model.MappingRule;
import com.fortech.modeljaxb.MappingRuleJAXB;

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
		entityManager.getTransaction().begin();
		entityManager.persist(mappingRule);
		entityManager.getTransaction().commit();
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
		entityManager.getTransaction().begin();
		entityManager.merge(mappingRule);
		entityManager.getTransaction().commit();
	}

	/**
	 * Method used in order to delete an MappingRule in the database.
	 * 
	 * @param idMappingRule
	 *            the id of the rule that will be deleted
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void deleteFromDatabase(int idMappingRule) {
		entityManager.getTransaction().begin();
		entityManager.remove(entityManager.find(MappingRule.class, idMappingRule));
		entityManager.getTransaction().commit();
	}

	/**
	 * Method used to get all the MappingRules that are present in the
	 * database
	 * 
	 * @return list with all the rules in the database
	 */
	public List<MappingRuleJAXB> getAllMappingRule() {
		@SuppressWarnings("unchecked")
		TypedQuery<MappingRule> typedQuery = (TypedQuery<MappingRule>) entityManager.createNamedQuery(MappingRule.FIND_ALL_MAPPING_RULE);

		List<MappingRule> mappingRule = new ArrayList<MappingRule>(typedQuery.getResultList());
		
		List<MappingRuleJAXB> mappingRulesJaxB = new ArrayList<MappingRuleJAXB>();
		
		for (MappingRule i : mappingRule) {
			
			MappingRuleJAXB j = new MappingRuleJAXB();
			j.setId(i.getId());
			j.setSourceValue(i.getSourceValue());
			j.setTargetValue(i.getTargetValue());
			j.setVehicleAttribute(i.getVehicleAttribute());
			
			mappingRulesJaxB.add(j);

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
	
}
