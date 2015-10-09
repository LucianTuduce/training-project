package com.fortech.rest;

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

import com.fortech.model.MarketRule;
import com.fortech.service.InterpretationRuleService;
import com.fortech.service.MappingRuleService;
import com.fortech.service.MarketRuleService;
import com.fortech.wrapper.WrapperRule;

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


	@DELETE
	@Path("/{ruleType}")
	@Consumes({"application/xml","application/json"})
	public Response deleteRuleFromDatavaseThatHasId(@PathParam("ruleType") String ruleType, String idRule){
		if(ruleType.equals("mapping")){
			mappingRuleService.deleteFromDatabase(Integer.parseInt(idRule));
			return Response.status(200).entity("Deleted mapping rule with id: "+ idRule).build();
		}else if(ruleType.equals("market")){
			marketRuleService.deleteFromDatabase(marketRuleService.getMarketPK(idRule));
			return Response.status(200).entity("Deleted market rule with id: "+ idRule).build();
		}else if(ruleType.equals("interpretation")){
			interpretationRuleService.deleteFromDatabase(Integer.parseInt(idRule));
			return Response.status(200).entity("Deleted interpretation rule with id: "+ idRule).build();
		}
		return Response.status(500).entity("FAILED to delete rule").build();
	}
	
	@GET
	@Path("/all")
	@Produces("application/json")
	public List<MarketRule> getAllRules(){
		return marketRuleService.getAll();
	}
}
