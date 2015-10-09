package com.fortech.rest;

import java.util.ArrayList;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.fortech.convertor.WrapperRuleFlattener;
import com.fortech.modeljaxb.MarketRuleJAXB;
import com.fortech.modeljaxb.WrapperRuleJAXB;

import com.fortech.service.InterpretationRuleService;
import com.fortech.service.MappingRuleService;
import com.fortech.service.MarketRuleService;

@Stateless
@Path("/rest")
public class RESTfulService {

	@EJB
	private MarketRuleService marketRuleService;

	@EJB
	private MappingRuleService mappingRuleService;
	
	@EJB
	private InterpretationRuleService interpretationRuleService;

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
		return rules;
	}


	@DELETE
	@Path("/{ruleType}")
	@Consumes({"application/xml","application/json"})
	public Response deleteRuleFromDatavaseThatHasId(@PathParam("ruleType") String ruleType, String idRule){
		if(ruleType.equals("mapping")){
			mappingRuleService.deleteFromDatabase(Integer.parseInt(idRule));
			return Response.status(200).entity("Deleted mapping rule with id: "+ idRule).build();
		}else if(ruleType.equals("market")){
			//marketRuleService.deleteFromDatabase(marketRuleService.getMarketPK(idRule));
			return Response.status(200).entity("Deleted market rule with id: "+ idRule).build();
		}else if(ruleType.equals("interpretation")){
			
			interpretationRuleService.deleteFromDatabase(Integer.parseInt(idRule));
			return Response.status(200).entity("Deleted interpretation rule with id: "+ idRule).build();
		}
		return Response.status(500).entity("FAILED to delete rule").build();
	}
}
