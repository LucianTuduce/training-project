package com.fortech.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.fortech.convertor.WrapperRuleFlattener;
import com.fortech.convertor.XmlJsonObjectConvertor;
import com.fortech.enums.RuleType;
import com.fortech.helpers.JAXBRuleConvertor;
import com.fortech.model.MarketRulePK;
import com.fortech.modeljaxb.InterpretationRuleJAXB;
import com.fortech.modeljaxb.MappingRuleJAXB;
import com.fortech.modeljaxb.MarketRuleJAXB;
import com.fortech.service.InterpretationRuleService;
import com.fortech.service.MappingRuleService;
import com.fortech.service.MarketRuleService;
import com.fortech.wrapper.WrapperRuleJAXB;

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
	 *            The format in which the rules will be returned
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
		List<MappingRuleJAXB> mappignRuleJAXB = new ArrayList<MappingRuleJAXB>();
		List<InterpretationRuleJAXB> interpretationRuleJAXB = new ArrayList<InterpretationRuleJAXB>();

		marketRuleJaxB = marketRuleService.getAllMarketRule();
		if (xmlORjson.equals("xml")) {
			for (MarketRuleJAXB market : marketRuleJaxB) {
				rules.add(WrapperRuleFlattener.createXMLWrapperRuleFor(market));
			}

		} else if (xmlORjson.equals("json")) {
			for (MarketRuleJAXB market : marketRuleJaxB) {
				rules.add(WrapperRuleFlattener.createJSONWrapperRuleFor(market));
			}
		}

		mappignRuleJAXB = mappingRuleService.getAllMappingRule();
		if (xmlORjson.equals("xml")) {
			for (MappingRuleJAXB mapping : mappignRuleJAXB) {
				rules.add(WrapperRuleFlattener.createXMLWrapperRuleFor(mapping));
			}

		} else if (xmlORjson.equals("json")) {
			for (MappingRuleJAXB mapping : mappignRuleJAXB) {
				rules.add(WrapperRuleFlattener
						.createJSONWrapperRuleFor(mapping));
			}
		}
		
		interpretationRuleJAXB = interpretationRuleService.getAllInterpretationRule();
		if (xmlORjson.equals("xml")) {
			for (InterpretationRuleJAXB interpretation : interpretationRuleJAXB) {
				rules.add(WrapperRuleFlattener.createXMLWrapperRuleFor(interpretation));
			}

		} else if (xmlORjson.equals("json")) {
			for (InterpretationRuleJAXB interpretation : interpretationRuleJAXB) {
				rules.add(WrapperRuleFlattener
						.createJSONWrapperRuleFor(interpretation));
			}
		}

		return rules;
	}

	@DELETE
	@Path("/{xmlOrJson}/{ruleType}")
	@Consumes({ "application/xml", "application/json" })
	public Response deleteRuleFromDatabase(
			@PathParam("ruleType") String ruleType,
			@PathParam("xmlOrJson") String xmlOrJson,
			WrapperRuleJAXB wrapperRuleJAXB) {

		if (xmlOrJson.equals("xml")) {
			if (ruleType.equals("mapping")) {
				int id = XmlJsonObjectConvertor.getMappingRuleFromXML(
						wrapperRuleJAXB.getJsonORxml()).getId();
				mappingRuleService.deleteFromDatabase(id);
				return Response.status(200)
						.entity("Deleted mapping rule with id: " + id).build();
			} else if (ruleType.equals("market")) {
				MarketRulePK idRule = XmlJsonObjectConvertor
						.getMarketRuleFromXML(wrapperRuleJAXB.getJsonORxml())
						.getId();
				marketRuleService.deleteFromDatabase(idRule);
				return Response.status(200)
						.entity("Deleted market rule with id: " + idRule)
						.build();
			} else if (ruleType.equals("interpretation")) {
				int id = XmlJsonObjectConvertor.getInterpretationRuleFromXML(
						wrapperRuleJAXB.getJsonORxml()).getId();
				interpretationRuleService.deleteFromDatabase(id);
				return Response.status(200)
						.entity("Deleted interpretation rule with id: " + id)
						.build();
			}

		} else if (xmlOrJson.equals("json")) {

			if (ruleType.equals("mapping")) {
				int id = XmlJsonObjectConvertor.getMappingRuleFromJSON(
						wrapperRuleJAXB.getJsonORxml()).getId();
				mappingRuleService.deleteFromDatabase(id);
				return Response.status(200)
						.entity("Deleted mapping rule with id: " + id).build();
			} else if (ruleType.equals("market")) {
				MarketRulePK idRule = XmlJsonObjectConvertor
						.getMarketRuleFromJSON(wrapperRuleJAXB.getJsonORxml())
						.getId();
				marketRuleService.deleteFromDatabase(idRule);
				return Response.status(200)
						.entity("Deleted market rule with id: " + idRule)
						.build();
			} else if (ruleType.equals("interpretation")) {
				int id = XmlJsonObjectConvertor.getInterpretationRuleFromJSON(
						wrapperRuleJAXB.getJsonORxml()).getId();
				interpretationRuleService.deleteFromDatabase(id);
				return Response.status(200)
						.entity("Deleted interpretation rule with id: " + id)
						.build();
			}
		}
		return Response.status(500).entity("FAILED to delete rule").build();
	}

	@PUT
	@Path("/{xmlOrJson}")
	@Consumes({ "aplication/xml", "application/xml" })
	public Response addRuleInDatabase(@PathParam("xmlOrJson") String xmlOrJson,
			WrapperRuleJAXB wrapperRuleJAXB) {

		if (xmlOrJson.equals("xml")) {
			if (wrapperRuleJAXB.getRuleType().equals(RuleType.MAPPING)) {
				mappingRuleService.insertInDatabase(JAXBRuleConvertor
						.getMappingRule(wrapperRuleJAXB, xmlOrJson));
				return Response.status(200)
						.entity("Added mapping rule converted from XML")
						.build();
			} else if (wrapperRuleJAXB.getRuleType().equals(RuleType.MARKET)) {
				marketRuleService.insertInDatabase(JAXBRuleConvertor
						.getMarketRule(wrapperRuleJAXB, xmlOrJson));
				return Response.status(200)
						.entity("Added market rule converted from XML").build();
			} else if (wrapperRuleJAXB.getRuleType().equals(
					RuleType.INTERPRETATION))
				;
			interpretationRuleService.insertInDatabase(JAXBRuleConvertor
					.getInterpretationRule(wrapperRuleJAXB, xmlOrJson));
			return Response.status(200)
					.entity("Added interpreation rule converted from XML")
					.build();
		} else if (xmlOrJson.equals("json")) {
			if (wrapperRuleJAXB.getRuleType().equals(RuleType.MAPPING)) {
				mappingRuleService.insertInDatabase(JAXBRuleConvertor
						.getMappingRule(wrapperRuleJAXB, xmlOrJson));
				return Response.status(200)
						.entity("Added mapping rule converted from JSON")
						.build();
			} else if (wrapperRuleJAXB.getRuleType().equals(RuleType.MARKET)) {
				marketRuleService.insertInDatabase(JAXBRuleConvertor
						.getMarketRule(wrapperRuleJAXB, xmlOrJson));
				return Response.status(200)
						.entity("Added market rule converted from JSON")
						.build();
			} else if (wrapperRuleJAXB.getRuleType().equals(
					RuleType.INTERPRETATION)) {
				interpretationRuleService.insertInDatabase(JAXBRuleConvertor
						.getInterpretationRule(wrapperRuleJAXB, xmlOrJson));
				return Response.status(200)
						.entity("Added interpreation rule converted from JSON")
						.build();
			}
		}
		return Response.status(500).entity("FAILED to add rule").build();
	}
}