package com.fortech.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;

import com.fortech.service.*;


/**
 * Class that is used as a REST service class. In here the communication with
 * the web is made.
 *
 */
@Stateless
@ApplicationPath("/car")
public class RuleApiApplication extends Application{

	@EJB
	private MarketRuleService marketRuleService;

	/**
	 * method that return to the web all the rules with the type ruleType in the
	 * format xmlORjson asked
	 * 
	 * @param xmlORjson
	 *            The format in with the rules will be returned
	 * @param ruleType
	 *            The type of the rule
	 * @return a list of WrapperRule with all the rules got from db
	 */
//	@GET
//	@Path("/{xmlORjson}/{ruleType}")
//	@Produces({ "application/xml", "application/json" })
//	public List<Object> getRules(
//			@PathParam("xmlORjson") String xmlORjson,
//			@PathParam("ruleType") String ruleType) {
//
//		List<WrapperRuleJAXB> rules = new ArrayList<WrapperRuleJAXB>();
//		List<MarketRuleJAXB> marketRuleJaxB = new ArrayList<MarketRuleJAXB>();
//
//		if (ruleType.equals("market")) {
//			marketRuleJaxB = marketRuleService.getAllMarketRule();
//			if (xmlORjson.equals("xml")) {
//				for (MarketRuleJAXB market : marketRuleJaxB) {
//					rules.add(Initializator
//							.createXMLWrapperRuleForMarketRuleJAXB(market));
//				}
//
//			} else if (xmlORjson.equals("json")) {
//				for (MarketRuleJAXB market : marketRuleJaxB) {
//					rules.add(Initializator
//							.createJSONWrapperRuleForMarketRule(market));
//				}
//			}
//		}
//		// else if (ruleType.equals("mapping")) {
//		// if (xmlORjson.equals("xml")) {
//		//
//		// } else if (xmlORjson.equals("json")) {
//		//
//		// }
//		// } else if (ruleType.equals("interpretation")) {
//		// if (xmlORjson.equals("xml")) {
//		//
//		// } else if (xmlORjson.equals("json")) {
//		//
//		// }
//
//		return rules;
//	}

}
