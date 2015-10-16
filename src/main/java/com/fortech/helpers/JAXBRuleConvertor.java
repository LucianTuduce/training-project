package com.fortech.helpers;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.fortech.convertor.MarketRuleFlattener;
import com.fortech.convertor.XmlJsonObjectConvertor;
import com.fortech.model.InterpretationRule;
import com.fortech.model.MappingRule;
import com.fortech.model.MarketRule;
import com.fortech.model.MarketRuleFlatted;
import com.fortech.model.MarketRulePK;
import com.fortech.modeljaxb.InterpretationRuleJAXB;
import com.fortech.modeljaxb.MappingRuleJAXB;
import com.fortech.modeljaxb.MarketRuleFlattedJAXB;
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
	public static MarketRuleFlatted getMarketRuleFlatted(WrapperRuleJAXB wrapperRuleJAXB, String xmlOrJson) {
		MarketRuleFlattedJAXB marketRuleFlattedJAXB = null;
		if (xmlOrJson.equals("xml")) {
			marketRuleFlattedJAXB = XmlJsonObjectConvertor.getMarketRuleFFromXML(wrapperRuleJAXB.getJsonORxml());
		} else if (xmlOrJson.equals("json")) {
			marketRuleFlattedJAXB = XmlJsonObjectConvertor.getMarketRuleFlattedFromJSON(wrapperRuleJAXB.getJsonORxml());
		}

		MarketRuleFlatted marketRuleFlatted = new MarketRuleFlatted();
		try {
			BeanUtils.copyProperties(marketRuleFlatted, marketRuleFlattedJAXB);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return marketRuleFlatted;
	}
	
	/**
	 * Method used to copy properties from MarketRuleFlattedJAXB to MarketRuleFlatted
	 * 
	 * @param marketRule
	 *            rule that the properties will be copied to the jaxb rule
	 * @return the jaxb rule with the same properties as the rule
	 */
	public static MarketRuleFlattedJAXB copyPropertiesFrom(MarketRule marketRule) {
		MarketRuleFlattener marketRuleFlattener = new MarketRuleFlattener();
		MarketRuleFlattedJAXB flatted = new MarketRuleFlattedJAXB();
		flatted.setActive(marketRuleFlattener.changeShortToBoolean(marketRule.getActive()));
		flatted.setBranch(marketRule.getId().getBranch());
		flatted.setCountryNumber(marketRule.getId().getCountryNumber());
		flatted.setRule(marketRule.getRule());
		flatted.setStockCategory(marketRuleFlattener.changeShortToEnum(marketRule.getId().getStockCategory()));
		return flatted;
	}

	/**
	 * Method used to copy properties from MarketRuleFlattedJAXB to MarketRuleFlatted
	 * 
	 * @param marketRule
	 *            rule that the properties will be copied to the jaxb rule
	 * @return the jaxb rule with the same properties as the rule
	 */
	public static MarketRuleFlatted copyPropertiesFrom(MarketRuleFlattedJAXB flattedJAXB) {
		MarketRuleFlatted flatted = new MarketRuleFlatted();
		try {
			BeanUtils.copyProperties(flatted, flattedJAXB);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return flatted;
	}

	public static MarketRule getConvertedRuleFrom(MarketRuleFlatted flatted) {
		MarketRuleFlattener flattener = new MarketRuleFlattener();
		
		MarketRule marketRule = new MarketRule();
		marketRule.setId(getMarketRulePK(flattener, flatted));
		marketRule.setActive(flattener.changeBooleanToShort(flatted.getActive()));
		marketRule.setRule(flatted.getRule());
		
		return marketRule;
	}
	
	private static MarketRulePK getMarketRulePK(MarketRuleFlattener flattener, MarketRuleFlatted flatted){
		MarketRulePK pk = new MarketRulePK();
		pk.setBranch(flatted.getBranch());
		pk.setCountryNumber(flatted.getCountryNumber());
		pk.setStockCategory(flattener.changeEnumToShort(flatted.getStockCategory()));
		return pk;
	}
}
