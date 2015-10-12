package com.fortech.convertor;

import javax.xml.bind.JAXBException;

import com.fortech.enums.RuleType;
import com.fortech.modeljaxb.MarketRuleJAXB;
import com.fortech.wrapper.WrapperRuleJAXB;
import com.fortech.transform.MarketRuleTrsFromToJson;
import com.fortech.transform.MarketRuleTrsFromToXML;


/**
 * The class transform a MarketJAXB/MappingJAXB/InterpretationRuleJAXB into an object of
 * WrapperRule

 
 * @author lucian.tuduce
 *
 */
public class WrapperRuleFlattener {

	/*
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
