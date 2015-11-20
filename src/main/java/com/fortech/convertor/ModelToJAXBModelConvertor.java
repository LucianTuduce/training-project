package com.fortech.convertor;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.fortech.helpers.JAXBRuleConvertor;
import com.fortech.model.InterpretationRule;
import com.fortech.model.MappingRule;
import com.fortech.model.MarketRule;
import com.fortech.modeljaxb.InterpretationRuleJAXB;
import com.fortech.modeljaxb.MappingRuleJAXB;
import com.fortech.modeljaxb.MarketRuleFlattedJAXB;

public class ModelToJAXBModelConvertor {

	public static List<InterpretationRuleJAXB> getAllInterpretationRulesConvertedToJAXBRules(List<InterpretationRule> interpretationRules) {
		List<InterpretationRuleJAXB> interpretationRulesJAXB = new ArrayList<InterpretationRuleJAXB>();
		InterpretationRuleJAXB ruleJAXB = null;
		for (InterpretationRule rule : interpretationRules) {
			ruleJAXB = new InterpretationRuleJAXB();
			ruleJAXB.setId(rule.getId());
			ruleJAXB.setTargetVehicles(rule.getTargetVehicles());
			ruleJAXB.setInterpretationInnerRules(rule.getInterpretationInnerRules());
			interpretationRulesJAXB.add(ruleJAXB);
		}

		return interpretationRulesJAXB;
	}

	public static List<MappingRuleJAXB> getAllMappingRulesConvertedToJAXBRules(List<MappingRule> mappingRules) {
		List<MappingRuleJAXB> mappingRulesJAXB = new ArrayList<MappingRuleJAXB>();
		MappingRuleJAXB ruleJAXB = null;
		for (MappingRule rule : mappingRules) {
			ruleJAXB = new MappingRuleJAXB();
			ruleJAXB.setId(rule.getId());
			ruleJAXB.setSourceValue(rule.getSourceValue());
			ruleJAXB.setTargetValue(rule.getTargetValue());
			ruleJAXB.setVehicleAttribute(rule.getVehicleAttribute());
			mappingRulesJAXB.add(ruleJAXB);
		}
		return mappingRulesJAXB;
	}

	public static List<MarketRuleFlattedJAXB> getAllMarketRulesConvertedToJAXBRules(List<MarketRule> marketRules) {
		 List<MarketRuleFlattedJAXB> marketRulesFlattedJAXB = new ArrayList<MarketRuleFlattedJAXB>();
		 MarketRuleFlattedJAXB marketRuleFlattedJAXB = null;
		  for (MarketRule rule : marketRules) {
			  marketRuleFlattedJAXB = JAXBRuleConvertor.copyPropertiesFrom(rule);
			  marketRulesFlattedJAXB.add(marketRuleFlattedJAXB);
		  }
		  return marketRulesFlattedJAXB;
	}
	
	public static MappingRuleJAXB getMappingRuleJAXB(MappingRule rule){
		MappingRuleJAXB ruleJAXB = new MappingRuleJAXB();
		try {
			BeanUtils.copyProperties(ruleJAXB, rule);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return ruleJAXB;
	}
	
	public static InterpretationRuleJAXB getInterpretationRuleJAXB(InterpretationRule rule){
		InterpretationRuleJAXB ruleJAXB = new InterpretationRuleJAXB();
		try {
			BeanUtils.copyProperties(ruleJAXB, rule);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return ruleJAXB;
	}
	
}
