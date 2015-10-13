package com.fortech.convertor;

import com.fortech.enums.RuleType;
import com.fortech.modeljaxb.InterpretationRuleJAXB;
import com.fortech.modeljaxb.MappingRuleJAXB;
import com.fortech.modeljaxb.MarketRuleJAXB;
import com.fortech.wrapper.WrapperRuleJAXB;

/**
 * 
 * The class transform a MarketJAXB/MappingJAXB/InterpretationRuleJAXB into an object of
 * WrapperRule
 * 
 * @author lucian.tuduce
 *
 */
public class WrapperRuleFlattener {

	/**
	 * The method creates an object WrapperRule(with the String jsonORxml in the
	 * format xml that it's asked) from a marketRuleJAXB
	 */
	public static WrapperRuleJAXB createXMLWrapperRuleFor(MarketRuleJAXB marketRuleJAXB) {

		WrapperRuleJAXB marketWrapperRule = new WrapperRuleJAXB();
		String jsonORxml = new String();
		jsonORxml = XmlJsonStringConvertor.getXMLStringForRuleJAXB(marketRuleJAXB);
		marketWrapperRule.setRuleType(RuleType.MARKET);
		marketWrapperRule.setJsonORxml(jsonORxml);
		return marketWrapperRule;
	}
	

	/**
	 * The method creates an object WrapperRule(with the String jsonORxml in the
	 * format json that it's asked) from a marketRuleJAXB
	 */
	public static WrapperRuleJAXB createJSONWrapperRuleFor(MarketRuleJAXB marketRuleJAXB) {

		WrapperRuleJAXB marketWrapperRule = new WrapperRuleJAXB();
		String jsonORxml = new String();
		jsonORxml = XmlJsonStringConvertor.getJSONStringForRuleJAXB(marketRuleJAXB);
		marketWrapperRule.setRuleType(RuleType.MARKET);
		marketWrapperRule.setJsonORxml(jsonORxml);
		return marketWrapperRule;
	}
	
	
	/**
	 * The method creates an object WrapperRule(with the String jsonORxml in the
	 * format xml that it's asked) from a mappingRuleJAXB
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
	 * The method creates an object WrapperRule(with the String jsonORxml in the
	 * format json that it's asked) from a mappingRuleJAXB
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
	 * The method creates an object WrapperRule(with the String jsonORxml in the
	 * format xml that it's asked) from a interpretationRuleJAXB
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
	 * The method creates an object WrapperRule(with the String jsonORxml in the
	 * format json that it's asked) from a interpretationRuleJAXB
	 */
	public static WrapperRuleJAXB createJSONWrapperRuleFor(InterpretationRuleJAXB interpretationRuleJAXB) {

		WrapperRuleJAXB interpretationWrapperRule = new WrapperRuleJAXB();
		String jsonORxml = new String();
		jsonORxml = XmlJsonStringConvertor.getJSONStringForRuleJAXB(interpretationRuleJAXB);
		interpretationWrapperRule.setRuleType(RuleType.MARKET);
		interpretationWrapperRule.setJsonORxml(jsonORxml);
		return interpretationWrapperRule;
	}
}
