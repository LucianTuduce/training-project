package com.fortech.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.fortech.model.MappingRule;
import com.fortech.model.MarketRule;
import com.fortech.model.MarketRulePK;
import com.fortech.service.InterpretationRuleService;
import com.fortech.service.MappingRuleService;
import com.fortech.service.MarketRuleService;

/**
 * The REST service class that is used to communicate with the server and
 * respond to the user's different type of requests.
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

	@POST
	@Path("/add")
	public Response insertInDatabase() {
		MarketRule marketRule = new MarketRule();
		marketRule.setActive((short) 1);
		marketRule.setRule("Noua");
		MarketRulePK marketRulePK = new MarketRulePK();
		marketRulePK.setBranch(121);
		marketRulePK.setCountryNumber("CJ-28-LUK");
		marketRulePK.setStockCategory((short) 1);
		marketRule.setId(marketRulePK);

		MappingRule mappingRule = new MappingRule();
		mappingRule.setId(4);
		mappingRule.setSourceValue("2000");
		mappingRule.setTargetValue("2400");
		mappingRule.setVehicleAttribute("Quatro");

		mappingRuleService.insertInDatabase(mappingRule);
		marketRuleService.insertInDatabase(marketRule);

		return Response.status(200).entity("INSERTEd").build();
	}

	@GET
	@Path("/id")
	@Produces("application/json")
	public MappingRule getRule() {
		return mappingRuleService.findById(4);
	}

	@GET
	@Path("/all")
	@Produces("application/json")
	public List<MappingRule> getAllRule() {
		return mappingRuleService.getAll();
	}

}
