package com.fortech.helpers;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.fortech.convertor.XmlJsonObjectConvertor;
import com.fortech.model.InterpretationRule;
import com.fortech.model.MappingRule;
import com.fortech.model.MarketRule;
import com.fortech.modeljaxb.InterpretationRuleJAXB;
import com.fortech.modeljaxb.MappingRuleJAXB;
import com.fortech.modeljaxb.MarketRuleJAXB;
import com.fortech.wrapper.WrapperRuleJAXB;

/**
 * Class used to copy the properties from the JAXB class of the rules to the
 * corresponding rule type.
 * 
 * @author lucian.tuduce
 *
 */
public class JAXBRuleConvertor {

	/**
	 * Method used to get the MappingRule with all the properties copied from
	 * the MappingRuleJAXB
	 * 
	 * @param wrapperRuleJAXB
	 *            the wrapper that contains the string XML or JSON form of the
	 *            rule
	 * @param xmlOrJson
	 *            the type that the rule is present in the form
	 * @return the rule with all the properties of the jaxb rule
	 */
	public static MappingRule getMappingRule(WrapperRuleJAXB wrapperRuleJAXB, String xmlOrJson) {
		MappingRuleJAXB mappingRuleJAXB = null;
		if (xmlOrJson.equals("xml")) {
			mappingRuleJAXB = XmlJsonObjectConvertor.getMappingRuleFromXML(wrapperRuleJAXB.getJsonORxml());
		} else if (xmlOrJson.equals("json")) {
			mappingRuleJAXB = XmlJsonObjectConvertor.getMappingRuleFromJSON(wrapperRuleJAXB.getJsonORxml());
		}

		MappingRule mappingRule = new MappingRule();
		try {
			BeanUtils.copyProperties(mappingRule, mappingRuleJAXB);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return mappingRule;
	}

	/**
	 * Method used to get the MappingRule with all the properties copied from
	 * the MappingRuleJAXB
	 * 
	 * @param wrapperRuleJAXB
	 *            the wrapper that contains the string XML or JSON form of the
	 *            rule
	 * @param xmlOrJson
	 *            the type that the rule is present in the form
	 * @return the rule with all the properties of the jaxb rule
	 */
	public static MarketRule getMarketRule(WrapperRuleJAXB wrapperRuleJAXB, String xmlOrJson) {
		MarketRuleJAXB marketRuleJAXB = null;
		if (xmlOrJson.equals("xml")) {
			marketRuleJAXB = XmlJsonObjectConvertor.getMarketRuleFromXML(wrapperRuleJAXB.getJsonORxml());
		} else if (xmlOrJson.equals("json")) {
			marketRuleJAXB = XmlJsonObjectConvertor.getMarketRuleFromJSON(wrapperRuleJAXB.getJsonORxml());
		}

		MarketRule marketRule = new MarketRule();
		try {
			BeanUtils.copyProperties(marketRule, marketRuleJAXB);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return marketRule;
	}

	/**
	 * Method used to get the MappingRule with all the properties copied from
	 * the MappingRuleJAXB
	 * 
	 * @param wrapperRuleJAXB
	 *            the wrapper that contains the string XML or JSON form of the
	 *            rule
	 * @param xmlOrJson
	 *            the type that the rule is present in the form
	 * @return the rule with all the properties of the jaxb rule
	 */
	public static InterpretationRule getInterpretationRule(WrapperRuleJAXB wrapperRuleJAXB, String xmlOrJson) {
		InterpretationRuleJAXB interpretationRuleJAXB = null;
		if (xmlOrJson.equals("xml")) {
			interpretationRuleJAXB = XmlJsonObjectConvertor.getInterpretationRuleFromXML(wrapperRuleJAXB.getJsonORxml());
		} else if (xmlOrJson.equals("json")) {
			interpretationRuleJAXB = XmlJsonObjectConvertor.getInterpretationRuleFromJSON(wrapperRuleJAXB.getJsonORxml());
		}

		InterpretationRule interpretationRule = new InterpretationRule();
		try {
			BeanUtils.copyProperties(interpretationRule, interpretationRuleJAXB);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return interpretationRule;
	}
}
