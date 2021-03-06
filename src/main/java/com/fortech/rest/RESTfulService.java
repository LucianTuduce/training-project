package com.fortech.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.fortech.convertor.ModelToJAXBModelConvertor;
import com.fortech.convertor.WrapperRuleBuilder;
import com.fortech.convertor.XmlJsonObjectConvertor;
import com.fortech.convertor.XmlJsonStringConvertor;
import com.fortech.enums.RuleType;
import com.fortech.helpers.JAXBRuleConvertor;
import com.fortech.model.InterpretationRule;
import com.fortech.model.MappingRule;
import com.fortech.model.MarketRule;
import com.fortech.model.MarketRuleFlatted;
import com.fortech.modeljaxb.InterpretationRuleJAXB;
import com.fortech.modeljaxb.MappingRuleJAXB;
import com.fortech.modeljaxb.MarketRuleFlattedJAXB;
import com.fortech.modeljaxb.MarketRuleIdJAXB;
import com.fortech.service.InterpretationRuleService;
import com.fortech.service.MappingRuleService;
import com.fortech.service.MarketRuleService;
import com.fortech.wrapper.WrapperRuleJAXB;

/**
 * Class used to communicate with the network. Works with the HTTP protocol.
 * 
 * @author lucian.tuduce
 *
 */

@Path("/rule")
@Stateless
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
	// @formatter:off
	@GET
	@Path("/{xmlORjson}")
	@Produces({ "application/xml", "application/json" })
	public List<WrapperRuleJAXB> getRules(@PathParam("xmlORjson") String xmlORjson) {

		List<WrapperRuleJAXB> rules = new ArrayList<WrapperRuleJAXB>();
		
		List<MarketRuleFlattedJAXB> marketRulesFJaxB = null ;
		List<MappingRuleJAXB> mappignRulesJAXB = null;
		List<InterpretationRuleJAXB> interpretationRulesJAXB = null;
		
		marketRulesFJaxB = new ArrayList<MarketRuleFlattedJAXB>(marketRuleService.getAllMarketRule());
		mappignRulesJAXB = new ArrayList<MappingRuleJAXB>(mappingRuleService.getAllMappingRule());
		interpretationRulesJAXB = new ArrayList<InterpretationRuleJAXB>(interpretationRuleService.getAllInterpretationRule());
		if (xmlORjson.equalsIgnoreCase("xml")) {
			for (MarketRuleFlattedJAXB market : marketRulesFJaxB) {
				rules.add(WrapperRuleBuilder.createXMLWrapperRuleFor(market));
			}
			
			for (MappingRuleJAXB mapping : mappignRulesJAXB) {
				rules.add(WrapperRuleBuilder.createXMLWrapperRuleFor(mapping));
			}
			
			for (InterpretationRuleJAXB interpretation : interpretationRulesJAXB) {
				rules.add(WrapperRuleBuilder.createXMLWrapperRuleFor(interpretation));
			}

		} else if (xmlORjson.equalsIgnoreCase("json")) {
			for (MarketRuleFlattedJAXB market : marketRulesFJaxB) {
				rules.add(WrapperRuleBuilder.createJSONWrapperRuleFor(market));
			}
			
			for (MappingRuleJAXB mapping : mappignRulesJAXB) {
				rules.add(WrapperRuleBuilder.createJSONWrapperRuleFor(mapping));
			}
			
			for (InterpretationRuleJAXB interpretation : interpretationRulesJAXB) {
				rules.add(WrapperRuleBuilder.createJSONWrapperRuleFor(interpretation));
			}
		}
		return rules;
	}
	
	
	@POST
	@Path("/{xmlOrJson}/{ruleType}")
	@Produces({"application/xml", "application/json"})
	public String getRulesByID(@PathParam("xmlOrJson") String xmlOrJson, @PathParam("ruleType") String ruleType, WrapperRuleJAXB wrapperRuleJAXB) {
		
		if (ruleType.equals("market")) {
			MarketRuleIdJAXB id = obtainMarketRuleID(wrapperRuleJAXB);
			MarketRuleFlattedJAXB marketRuleFJAXB = marketRuleService.getById(id);
			if (xmlOrJson.equals("xml")) {
				return XmlJsonStringConvertor.getXMLStringForRuleJAXB(marketRuleFJAXB);
			} else {
				return XmlJsonStringConvertor.getJSONStringForRuleJAXB(marketRuleFJAXB);
			}
		}

		if (ruleType.equals("mapping")) {
			MappingRuleJAXB mappingRuleJAXB = new MappingRuleJAXB();
			mappingRuleJAXB = mappingRuleService.getById(wrapperRuleJAXB);
			if (xmlOrJson.equals("xml")) {
				return XmlJsonStringConvertor.getXMLStringForRuleJAXB(mappingRuleJAXB);
			} else {
				return XmlJsonStringConvertor.getJSONStringForRuleJAXB(mappingRuleJAXB);
			}
		}

		if (ruleType.equals("interpretation")) {
			InterpretationRuleJAXB interpretationRuleJAXB = new InterpretationRuleJAXB();
			interpretationRuleJAXB = interpretationRuleService.getById(wrapperRuleJAXB);
			if (xmlOrJson.equals("xml")) {
				return XmlJsonStringConvertor.getXMLStringForRuleJAXB(interpretationRuleJAXB);
			} else {
				return XmlJsonStringConvertor.getJSONStringForRuleJAXB(interpretationRuleJAXB);
			}
		}	
		return "Nope";
	}


	private MarketRuleIdJAXB obtainMarketRuleID(WrapperRuleJAXB wrapperRuleJAXB) {
		MarketRuleIdJAXB id = new MarketRuleIdJAXB();
		MarketRuleFlattedJAXB market = XmlJsonObjectConvertor.getMarketRuleFFromXML(wrapperRuleJAXB.getJsonORxml());
		id.setBranch(market.getBranch());
		id.setCountryNumber(market.getCountryNumber());
		id.setStockCategory(market.getStockCategory());
		return id;
	}
	
	
	@GET
	@Path("/{xmlOrJson}/{ruleType}/{idRule}")
	@Produces({"application/xml", "application/json"})
	public String getRulesByID(@PathParam("xmlOrJson") String xmlOrJson, @PathParam("ruleType") String ruleType, @PathParam("idRule") String idRule) {

		if(xmlOrJson.equalsIgnoreCase("xml")){
			if(ruleType.equalsIgnoreCase("mapping")){
				MappingRule mappingRule = mappingRuleService.findById(Integer.parseInt(idRule));
				MappingRuleJAXB mappingRuleJAXB = ModelToJAXBModelConvertor.getMappingRuleJAXB(mappingRule);
				return XmlJsonStringConvertor.getXMLStringForRuleJAXB(mappingRuleJAXB);
			}else if(ruleType.equalsIgnoreCase("interpretation")){
				InterpretationRule interpretationRule = interpretationRuleService.findById(Integer.parseInt(idRule));
				InterpretationRuleJAXB interpretationRuleJAXB = ModelToJAXBModelConvertor.getInterpretationRuleJAXB(interpretationRule);
				return XmlJsonStringConvertor.getXMLStringForRuleJAXB(interpretationRuleJAXB);
			}
		}else if(xmlOrJson.equalsIgnoreCase("json")){
			if(ruleType.equalsIgnoreCase("mapping")){
				MappingRule mappingRule = mappingRuleService.findById(Integer.parseInt(idRule));
				MappingRuleJAXB mappingRuleJAXB = ModelToJAXBModelConvertor.getMappingRuleJAXB(mappingRule);
				return XmlJsonStringConvertor.getJSONStringForRuleJAXB(mappingRuleJAXB);
			}else if(ruleType.equalsIgnoreCase("interpretation")){
				InterpretationRule interpretationRule = interpretationRuleService.findById(Integer.parseInt(idRule));
				InterpretationRuleJAXB interpretationRuleJAXB = ModelToJAXBModelConvertor.getInterpretationRuleJAXB(interpretationRule);
				return XmlJsonStringConvertor.getJSONStringForRuleJAXB(interpretationRuleJAXB);
			}
		}
		return "Nope";
	}
	
	

	// @formatter:on

	/**
	 * REST service method used to communicate with the application business
	 * login in order to delete a rule from the database
	 * 
	 * @param ruleType
	 *            the type of the rule that will be deleted
	 * @param xmlOrJson
	 *            the form of the rule type
	 * @param wrapperRuleJAXB
	 *            wrapper that will contain the rule in the form mentioned above
	 * @return a response to the server if the DELETE form the database was
	 *         successfully or not
	 */
	// @formatter:off
	@DELETE
	@Path("/{xmlOrJson}/{ruleType}")
	@Consumes({"application/xml","application/json"})
	public Response deleteRuleFromDatabase(@PathParam("ruleType") String ruleType, @PathParam("xmlOrJson") String xmlOrJson, WrapperRuleJAXB wrapperRuleJAXB){
		
		if(xmlOrJson.equals("xml")){
			if(ruleType.equals("mapping")){
				int id = XmlJsonObjectConvertor.getMappingRuleFromXML(wrapperRuleJAXB.getJsonORxml()).getId();
				mappingRuleService.deleteFromDatabase(id);
				return Response.status(200).entity("Deleted mapping rule with id: "+ id).build();
			}else if(ruleType.equals("market")){
				MarketRuleFlattedJAXB flattedJAXB = XmlJsonObjectConvertor.getMarketRuleFFromXML(wrapperRuleJAXB.getJsonORxml());
				MarketRuleFlatted flatted = JAXBRuleConvertor.copyPropertiesFrom(flattedJAXB);
				MarketRule rule = JAXBRuleConvertor.getConvertedRuleFrom(flatted);
				marketRuleService.deleteFromDatabase(rule.getId());
				return Response.status(200).entity("Deleted market rule with id: "+ rule.getId()).build();
			}else if(ruleType.equals("interpretation")){
				int id = XmlJsonObjectConvertor.getInterpretationRuleFromXML(wrapperRuleJAXB.getJsonORxml()).getId();
				interpretationRuleService.deleteFromDatabase(id);
				return Response.status(200).entity("Deleted interpretation rule with id: "+ id).build();
			}
			
		}else if (xmlOrJson.equals("json")){
			
			if(ruleType.equals("mapping")){
				int id = XmlJsonObjectConvertor.getMappingRuleFromJSON(wrapperRuleJAXB.getJsonORxml()).getId();
				mappingRuleService.deleteFromDatabase(id);
				return Response.status(200).entity("Deleted mapping rule with id: "+ id).build();
			}else if(ruleType.equals("market")){
				MarketRuleFlattedJAXB flattedJAXB = XmlJsonObjectConvertor.getMarketRuleFlattedFromJSON(wrapperRuleJAXB.getJsonORxml());
				MarketRuleFlatted flatted = JAXBRuleConvertor.copyPropertiesFrom(flattedJAXB);
				MarketRule rule = JAXBRuleConvertor.getConvertedRuleFrom(flatted);
				marketRuleService.deleteFromDatabase(rule.getId());
				return Response.status(200).entity("Deleted market rule with id: "+ rule.getId()).build();
			}else if(ruleType.equals("interpretation")){
				int id = XmlJsonObjectConvertor.getInterpretationRuleFromJSON(wrapperRuleJAXB.getJsonORxml()).getId();
				interpretationRuleService.deleteFromDatabase(id);
				return Response.status(200).entity("Deleted interpretation rule with id: "+ id).build();
			}
		}
		return Response.status(500).entity("FAILED to delete rule").build();
	}
	// @formatter:on

	/**
	 * REST service method used to communicate with the application business
	 * login in order to add a rule in the database
	 * 
	 * @param xmlOrJson
	 *            the form of the rule type
	 * @param wrapperRuleJAXB
	 *            wrapper that will contain the rule in the form mentioned above
	 * @return a response to the server if the ADD in the database was
	 *         successfully or not
	 */
	// @formatter:off
	@PUT
	@Path("/{xmlOrJson}")
	@Consumes({"application/xml","application/json"})
	public Response updateRuleInDatabase(@PathParam("xmlOrJson") String xmlOrJson, WrapperRuleJAXB wrapperRuleJAXB){
		
		if(xmlOrJson.equals("xml")){
			if(wrapperRuleJAXB.getRuleType().equals(RuleType.MAPPING)){
				mappingRuleService.updateInDatabase(JAXBRuleConvertor.getMappingRule(wrapperRuleJAXB, xmlOrJson));
				return Response.status(200).entity("Added mapping rule converted from XML").build();
			}else if(wrapperRuleJAXB.getRuleType().equals(RuleType.MARKET)){
				MarketRuleFlattedJAXB flattedJAXB = XmlJsonObjectConvertor.getMarketRuleFFromXML(wrapperRuleJAXB.getJsonORxml());
				MarketRuleFlatted flatted = JAXBRuleConvertor.copyPropertiesFrom(flattedJAXB);
				MarketRule rule = JAXBRuleConvertor.getConvertedRuleFrom(flatted);
				marketRuleService.updateInDatabase(rule);
				return Response.status(200).entity("Added market rule converted from XML").build();
			}else if(wrapperRuleJAXB.getRuleType().equals(RuleType.INTERPRETATION));
				interpretationRuleService.updateInDatabase(JAXBRuleConvertor.getInterpretationRule(wrapperRuleJAXB, xmlOrJson));
				return Response.status(200).entity("Added interpreation rule converted from XML").build();
			} 
		else if(xmlOrJson.equals("json")){
			if(wrapperRuleJAXB.getRuleType().equals(RuleType.MAPPING)){
				mappingRuleService.updateInDatabase(JAXBRuleConvertor.getMappingRule(wrapperRuleJAXB, xmlOrJson));
				return Response.status(200).entity("Added mapping rule converted from JSON").build();
			}else if(wrapperRuleJAXB.getRuleType().equals(RuleType.MARKET)){
				MarketRuleFlattedJAXB flattedJAXB = XmlJsonObjectConvertor.getMarketRuleFlattedFromJSON(wrapperRuleJAXB.getJsonORxml());
				MarketRuleFlatted flatted = JAXBRuleConvertor.copyPropertiesFrom(flattedJAXB);
				MarketRule rule = JAXBRuleConvertor.getConvertedRuleFrom(flatted);
				marketRuleService.updateInDatabase(rule);
				return Response.status(200).entity("Added market rule converted from JSON").build();
			}else if(wrapperRuleJAXB.getRuleType().equals(RuleType.INTERPRETATION)){
				interpretationRuleService.updateInDatabase(JAXBRuleConvertor.getInterpretationRule(wrapperRuleJAXB, xmlOrJson));
				return Response.status(200).entity("Added interpreation rule converted from JSON").build();
			}
		}
		return Response.status(500).entity("FAILED to add rule").build();
	}	
	// @formatter:on

	// @formatter:off
	@POST
	@Path("/{xmlOrJson}")
	@Consumes({ "application/xml", "application/json" })
	public Response insertRulesInDatabase(@PathParam("xmlOrJson") String xmlOrJson,List<WrapperRuleJAXB> rules) {

		for (WrapperRuleJAXB jaxb : rules) {
			if (xmlOrJson.equals("xml")) {
				if (jaxb.getRuleType().equals(RuleType.MAPPING)) {
					mappingRuleService.insertInDatabase(JAXBRuleConvertor.getMappingRule(jaxb, xmlOrJson));
				} else if (jaxb.getRuleType().equals(RuleType.MARKET)) {
					MarketRuleFlattedJAXB flattedJAXB = XmlJsonObjectConvertor.getMarketRuleFFromXML(jaxb.getJsonORxml());
					MarketRuleFlatted flatted = JAXBRuleConvertor.copyPropertiesFrom(flattedJAXB);
					MarketRule rule = JAXBRuleConvertor.getConvertedRuleFrom(flatted);
					marketRuleService.insertInDatabase(rule);
				} else if (jaxb.getRuleType().equals(RuleType.INTERPRETATION)) {
					interpretationRuleService.insertInDatabase(JAXBRuleConvertor.getInterpretationRule(jaxb, xmlOrJson));
				}
			} else if (xmlOrJson.equals("json")) {
				if (jaxb.getRuleType().equals(RuleType.MAPPING)) {
					mappingRuleService.insertInDatabase(JAXBRuleConvertor.getMappingRule(jaxb, xmlOrJson));
				} else if (jaxb.getRuleType().equals(RuleType.MARKET)) {
					MarketRuleFlattedJAXB flattedJAXB = XmlJsonObjectConvertor.getMarketRuleFlattedFromJSON(jaxb.getJsonORxml());
					MarketRuleFlatted flatted = JAXBRuleConvertor.copyPropertiesFrom(flattedJAXB);
					MarketRule rule = JAXBRuleConvertor.getConvertedRuleFrom(flatted);
					marketRuleService.insertInDatabase(rule);
				} else if (jaxb.getRuleType().equals(RuleType.INTERPRETATION)) {
					interpretationRuleService.insertInDatabase(JAXBRuleConvertor.getInterpretationRule(jaxb, xmlOrJson));
				}
			}
		}
		return Response.status(200).entity("Success in adding object/objects in database").build();
	}
	// @formatter:on
}
