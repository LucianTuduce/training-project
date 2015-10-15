package com.fortech.service;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.fortech.convertor.XmlJsonObjectConvertor;
import com.fortech.model.InterpretationRule;
import com.fortech.modeljaxb.InterpretationRuleJAXB;
import com.fortech.wrapper.WrapperRuleJAXB;

/**
 * Service class for the InterpretationRule.
 * 
 * @author lucian.tuduce
 *
 */

@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class InterpretationRuleService {

	/**
	 * The object that will manage the connection with the
	 * database and perform the CRUD operations on the rule.
	 */
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Method used in order to insert a InterpretaionRule in the database.
	 * 
	 * @param interpretationRule
	 *            the rule that will be added in the database
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void insertInDatabase(InterpretationRule interpretationRule) {
		entityManager.persist(interpretationRule);
	}

	/**
	 * Method used in order to update an existing InterpretationRule in the
	 * database.
	 * 
	 * @param interpretationRule
	 *            the object with the new values that the rule will have
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void updateInDatabase(InterpretationRule interpretationRule) {
		entityManager.merge(interpretationRule);
	}

	/**
	 * Method used in order to delete an InterpretationRule in the database.
	 * 
	 * @param idInterpretationRule
	 *            the id of the rule that will be deleted
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void deleteFromDatabase(int idInterpretationRule) {
		entityManager.remove(entityManager.find(InterpretationRule.class, idInterpretationRule));
	}

	/**
	 * Method used to get all the InterpretationRules that are present in the
	 * database
	 * 
	 * @return list with all the rules in the database
	 */
	public List<InterpretationRule> getAll() {
		@SuppressWarnings("unchecked")
		TypedQuery<InterpretationRule> typedQuery = (TypedQuery<InterpretationRule>) entityManager.createNamedQuery(InterpretationRule.FIND_ALL_INTERPRETATION_RULE);
		return typedQuery.getResultList();
	}

	/**
	 * Method used in order to get a InterpretationRule from the database based
	 * on an id
	 * 
	 * @param idInterpretationRule
	 *            id of the rule that will be obtained from the database
	 * @return the rule that will have the id as the parameter of the method
	 */
	public InterpretationRule findById(int idInterpretationRule) {
		return entityManager.find(InterpretationRule.class, idInterpretationRule);
	}

	/**
	 * Method used to get all the MappingRules that are present in the
	 * database
	 * 
	 * @return list with all the interpretation rules in the database
	 */
	public List<InterpretationRuleJAXB> getAllInterpretationRule() {
		@SuppressWarnings("unchecked")
		TypedQuery<InterpretationRule> interpretationQuery = (TypedQuery<InterpretationRule>) entityManager.createNamedQuery(InterpretationRule.FIND_ALL_INTERPRETATION_RULE);
		List<InterpretationRule> interpretationRule = new ArrayList<InterpretationRule>(interpretationQuery.getResultList());

		List<InterpretationRuleJAXB> interpretationRuleJaxB = new ArrayList<InterpretationRuleJAXB>();
				
		for(InterpretationRule i : interpretationRule){
			InterpretationRuleJAXB j = new InterpretationRuleJAXB();
			j.setId(i.getId());
			j.setTargetVehicles(i.getTargetVehicles());
			j.setInterpretationInnerRules(i.getInterpretationInnerRules());
						
			interpretationRuleJaxB.add(j);
		}
		
		return interpretationRuleJaxB;
	}

	/**
	 * Method used in order to get a InterpretationRule from the database based
	 * on an id
	 * 
	 * @param idInterpretationRule
	 *            id of the rule that will be obtained from the database
	 * @return the rule that will have the id as the parameter of the method
	 */
	public InterpretationRuleJAXB getById(WrapperRuleJAXB wrapperRuleJAXB) {
		InterpretationRuleJAXB interpretationRuleJAXB = new InterpretationRuleJAXB();
		
		int id = XmlJsonObjectConvertor.getInterpretationRuleFromXML(wrapperRuleJAXB.getJsonORxml()).getId();
		InterpretationRule interpretationRule = entityManager.find(InterpretationRule.class, id);
		
		interpretationRuleJAXB.setId(interpretationRule.getId());
		interpretationRuleJAXB.setInterpretationInnerRules(interpretationRule.getInterpretationInnerRules());
		interpretationRuleJAXB.setTargetVehicles(interpretationRule.getTargetVehicles());
		
		return interpretationRuleJAXB;
	}
}
