package com.fortech.transform;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.fortech.model.MarketRuleFlatted;
import com.fortech.modeljaxb.MarketRuleJAXB;

/**
 * Class that makes the change from/to of an object of type MarketRules to/from a String Json 
 * @author dariad
 *
 */
public class MarketRuleTrsFromToJson {

	/**
	 * Method that receives a String that's an object in json format,
	 * transforms it into an object of type MarketRulesJaxB and returns it 
	 */
	public static MarketRuleJAXB transfromJson(String json) {

		MarketRuleFlatted marketJson = new MarketRuleFlatted();
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			marketJson = objectMapper.readValue(json, MarketRuleFlatted.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		MarketRuleJAXB MarketRulesJaxB = new MarketRuleJAXB();
		
		MarketRulesJaxB.setActive(marketJson.getActive());
		MarketRulesJaxB.setBranch(marketJson.getBranch());
		MarketRulesJaxB.setCountryNumber(marketJson.getCountryNumber());
		MarketRulesJaxB.setRule(marketJson.getRule());
		MarketRulesJaxB.setStockCategory(marketJson.getStockCategory());
		
		return MarketRulesJaxB;

	}

	/**
	 * Method that receives an object of type MarketRulesJaxB,
	 * transforms it into a String in format json and returns it 
	 */
	public static String transToJson(MarketRuleJAXB MarketRulesJaxB) {

		ObjectMapper objectMapper = new ObjectMapper();
		String json = "";
		
		try {
			json = objectMapper.writeValueAsString(MarketRulesJaxB);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return json;
	}

}
