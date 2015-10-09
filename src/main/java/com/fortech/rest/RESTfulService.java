package com.fortech.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;

import com.fortech.convertor.WrapperRuleFlattener;
import com.fortech.modeljaxb.MappingRuleJAXB;
import com.fortech.modeljaxb.MarketRuleJAXB;
import com.fortech.modeljaxb.WrapperRuleJAXB;
import com.fortech.service.MappingRuleService;
import com.fortech.service.MarketRuleService;

@Stateless
@Path("/rest")
public class RESTfulService extends Application {

	@EJB
	private MarketRuleService marketRuleService;

	@EJB
	private MappingRuleService mappingRuleService;

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
	@GET
	@Path("/{xmlORjson}")
	@Produces({ "application/xml", "application/json" })
	public List<WrapperRuleJAXB> getRules(
			@PathParam("xmlORjson") String xmlORjson) {

		List<WrapperRuleJAXB> rules = new ArrayList<WrapperRuleJAXB>();
		List<MarketRuleJAXB> marketRuleJaxB = new ArrayList<MarketRuleJAXB>();
		List<MappingRuleJAXB> mappingRuleJaxB = new ArrayList<MappingRuleJAXB>();

		marketRuleJaxB = marketRuleService.getAllMarketRule();
		if (xmlORjson.equals("xml")) {
			for (MarketRuleJAXB market : marketRuleJaxB) {
				rules.add(WrapperRuleFlattener
						.createXMLWrapperRuleForMarketRuleJAXB(market));
			}

		} else if (xmlORjson.equals("json")) {
			for (MarketRuleJAXB market : marketRuleJaxB) {
				rules.add(WrapperRuleFlattener
						.createJSONWrapperRuleForMarketRule(market));
			}
		}

		// mappingRuleJaxB = mappingRuleService.getAllMappingRule();
		// if (xmlORjson.equals("xml")) {
		// for (MappingRuleJAXB mapping : mappingRuleJaxB) {
		// rules.add(WrapperRuleFlattener
		// .createXMLWrapperRuleForMappingRuleJAXB(mapping));
		// }
		//
		// } else if (xmlORjson.equals("json")) {
		// for (MarketRuleJAXB market : marketRuleJaxB) {
		// rules.add(WrapperRuleFlattener
		// .createJSONWrapperRuleForMappingRule(mapping));
		// }
		// }

		return rules;
	}

}
