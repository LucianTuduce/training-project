package com.fortech.convertor;

import javax.xml.bind.JAXBException;

import com.fortech.enums.RuleType;
import com.fortech.modeljaxb.MappingRuleJAXB;
import com.fortech.modeljaxb.MarketRuleJAXB;
import com.fortech.modeljaxb.WrapperRuleJAXB;
import com.fortech.transform.MarketRuleTrsFromToJson;
import com.fortech.transform.MarketRuleTrsFromToXML;


/**
 * The class transform a Market/Mapping/InterpretationRuleJAXB into an object of
 * WrapperRule
 
 * @author dariad
 *
 */
public class WrapperRuleFlattener {

	/*
	 * The method creates an object WrapperRule(with the String jsonORxml in the
	 * format xml that it's asked) from a marketRuleJAXB
	 */
	public static WrapperRuleJAXB createXMLWrapperRuleForMarketRuleJAXB(
			MarketRuleJAXB marketRuleJaxB) {

		WrapperRuleJAXB wrapperRule = new WrapperRuleJAXB();

		String jsonORxml = new String();

		try {
			jsonORxml = MarketRuleTrsFromToXML.transToXML(marketRuleJaxB);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		wrapperRule.setRuleType(RuleType.MARKET);
		wrapperRule.setJsonORxml(jsonORxml);

		return wrapperRule;
	}
	

	/*
	 * The method creates an object WrapperRule(with the String jsonORxml in the
	 * format json that it's asked) from a marketRuleJAXB
	 */
	public static WrapperRuleJAXB createJSONWrapperRuleForMarketRule(
			MarketRuleJAXB marketRuleJaxB) {

		WrapperRuleJAXB wrapperRule = new WrapperRuleJAXB();

		String jsonORxml = new String();

		jsonORxml = MarketRuleTrsFromToJson.transToJson(marketRuleJaxB);

		wrapperRule.setRuleType(RuleType.MARKET);
		wrapperRule.setJsonORxml(jsonORxml);

		return wrapperRule;
	}

	
}
