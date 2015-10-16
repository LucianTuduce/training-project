package com.fortech.convertor;

import com.fortech.enums.RuleType;
import com.fortech.modeljaxb.InterpretationRuleJAXB;
import com.fortech.modeljaxb.MappingRuleJAXB;
import com.fortech.modeljaxb.MarketRuleFlattedJAXB;
import com.fortech.wrapper.WrapperRuleJAXB;

/**
 * 
 * The class transform a MarketJAXB/MappingJAXB/InterpretationRuleJAXB into an
 * object of WrapperRule
 * 
 * @author lucian.tuduce
 *
 */
public class WrapperRuleBuilder {

	/**
	 * Method used to obtained the WrapperRuleJAXB object with the string
	 * variable having the value of the converted method parameter to XML form
	 * 
	 * @param marketRuleJAXB
	 *            the rule that will be converted to an XML form
	 * @return wrapperrule object with the correct values in his instance
	 *         variables
	 */
	public static WrapperRuleJAXB createXMLWrapperRuleFor(MarketRuleFlattedJAXB marketRuleFJAXB) {

		WrapperRuleJAXB marketWrapperRule = new WrapperRuleJAXB();
		String jsonORxml = new String();
		jsonORxml = XmlJsonStringConvertor.getXMLStringForRuleJAXB(marketRuleFJAXB);
		marketWrapperRule.setRuleType(RuleType.MARKET);
		marketWrapperRule.setJsonORxml(jsonORxml);
		return marketWrapperRule;
	}

	/**
	 * Method used to obtained the WrapperRuleJAXB object with the string
	 * variable having the value of the converted method parameter to JSON form
	 * 
	 * @param MarketRuleFlattedJAXB
	 *            the rule that will be converted to an JSON form
	 * @return wrapperrule object with the correct values in his instance
	 *         variables
	 */
	public static WrapperRuleJAXB createJSONWrapperRuleFor(MarketRuleFlattedJAXB MarketRuleFlattedJAXB) {

		WrapperRuleJAXB marketWrapperRule = new WrapperRuleJAXB();
		String jsonORxml = new String();
		jsonORxml = XmlJsonStringConvertor.getJSONStringForRuleJAXB(MarketRuleFlattedJAXB);
		marketWrapperRule.setRuleType(RuleType.MARKET);
		marketWrapperRule.setJsonORxml(jsonORxml);
		return marketWrapperRule;
	}

	/**
	 * Method used to obtained the WrapperRuleJAXB object with the string
	 * variable having the value of the converted method parameter to XML form
	 * 
	 * @param mappingRuleJAXB
	 *            the rule that will be converted to an XML form
	 * @return wrapperrule object with the correct values in his instance
	 *         variables
	 */
	public static WrapperRuleJAXB createXMLWrapperRuleFor(MappingRuleJAXB mappingRuleJAXB) {

		WrapperRuleJAXB mappingWrapperRule = new WrapperRuleJAXB();
		String jsonORxml = new String();
		jsonORxml = XmlJsonStringConvertor.getXMLStringForRuleJAXB(mappingRuleJAXB);
		mappingWrapperRule.setRuleType(RuleType.MAPPING);
		mappingWrapperRule.setJsonORxml(jsonORxml);
		return mappingWrapperRule;
	}

	/**
	 * Method used to obtained the WrapperRuleJAXB object with the string
	 * variable having the value of the converted method parameter to JSON form
	 * 
	 * @param mappingRuleJAXB
	 *            the rule that will be converted to an JSON form
	 * @return wrapperrule object with the correct values in his instance
	 *         variables
	 */
	public static WrapperRuleJAXB createJSONWrapperRuleFor(MappingRuleJAXB mappingRuleJAXB) {

		WrapperRuleJAXB mappingWrapperRule = new WrapperRuleJAXB();
		String jsonORxml = new String();
		jsonORxml = XmlJsonStringConvertor.getJSONStringForRuleJAXB(mappingRuleJAXB);
		mappingWrapperRule.setRuleType(RuleType.MAPPING);
		mappingWrapperRule.setJsonORxml(jsonORxml);
		return mappingWrapperRule;
	}

	/**
	 * Method used to obtained the WrapperRuleJAXB object with the string
	 * variable having the value of the converted method parameter to XML form
	 * 
	 * @param interpretationRuleJAXB
	 *            the rule that will be converted to an XML form
	 * @return wrapperrule object with the correct values in his instance
	 *         variables
	 */
	public static WrapperRuleJAXB createXMLWrapperRuleFor(InterpretationRuleJAXB interpretationRuleJAXB) {

		WrapperRuleJAXB interpretationWrapperRule = new WrapperRuleJAXB();
		String jsonORxml = new String();
		jsonORxml = XmlJsonStringConvertor.getXMLStringForRuleJAXB(interpretationRuleJAXB);
		interpretationWrapperRule.setRuleType(RuleType.INTERPRETATION);
		interpretationWrapperRule.setJsonORxml(jsonORxml);
		return interpretationWrapperRule;
	}

	/**
	 * Method used to obtained the WrapperRuleJAXB object with the string
	 * variable having the value of the converted method parameter to JSON form
	 * 
	 * @param interpretationRuleJAXB
	 *            the rule that will be converted to an JSON form
	 * @return wrapperrule object with the correct values in his instance
	 *         variables
	 */
	public static WrapperRuleJAXB createJSONWrapperRuleFor(InterpretationRuleJAXB interpretationRuleJAXB) {

		WrapperRuleJAXB interpretationWrapperRule = new WrapperRuleJAXB();
		String jsonORxml = new String();
		jsonORxml = XmlJsonStringConvertor.getJSONStringForRuleJAXB(interpretationRuleJAXB);
		interpretationWrapperRule.setRuleType(RuleType.INTERPRETATION);
		interpretationWrapperRule.setJsonORxml(jsonORxml);
		return interpretationWrapperRule;
	}
}
