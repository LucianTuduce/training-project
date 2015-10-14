package com.fortech.rest.test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.fortech.convertor.XmlJsonStringConvertor;
import com.fortech.enums.RuleType;
import com.fortech.model.MarketRulePK;
import com.fortech.modeljaxb.MappingRuleJAXB;
import com.fortech.modeljaxb.MarketRuleJAXB;
import com.fortech.wrapper.WrapperRuleJAXB;

@Path("/test")
public class RestService {

	@GET
	@Path("/{xmlOrJson}/map")
	@Produces("application/xml")
	public WrapperRuleJAXB getWrapperRuleJAXB(@PathParam("xmlOrJson") String xmlOrJson){
		if(xmlOrJson.equals("xml")){
			MappingRuleJAXB mapJAXB = new MappingRuleJAXB();
			WrapperRuleJAXB wrapperRuleJAXB = new WrapperRuleJAXB(); 
			mapJAXB.setId(1);
			mapJAXB.setSourceValue("3333");
			mapJAXB.setTargetValue("4000");
			mapJAXB.setVehicleAttribute("Running");
			wrapperRuleJAXB.setRuleType(RuleType.MAPPING);
			wrapperRuleJAXB.setJsonORxml(XmlJsonStringConvertor.getXMLStringForRuleJAXB(mapJAXB));
			return wrapperRuleJAXB;
		}else if(xmlOrJson.equals("json")){
			MappingRuleJAXB mapJAXB = new MappingRuleJAXB();
			WrapperRuleJAXB wrapperRuleJAXB = new WrapperRuleJAXB(); 
			mapJAXB.setId(1);
			mapJAXB.setSourceValue("3333");
			mapJAXB.setTargetValue("4000");
			mapJAXB.setVehicleAttribute("Running");
			wrapperRuleJAXB.setRuleType(RuleType.MAPPING);
			wrapperRuleJAXB.setJsonORxml(XmlJsonStringConvertor.getJSONStringForRuleJAXB(mapJAXB));
			return wrapperRuleJAXB;
		}
		
		return null;
	}
	
	@GET
	@Path("/{xmlOrJson}/market")
	@Produces("application/json")
	public WrapperRuleJAXB getRule(@PathParam("xmlOrJson") String xmlOrJson){
		if(xmlOrJson.equals("xml")){
			MarketRuleJAXB marketRuleJAXB = new MarketRuleJAXB();
			MarketRulePK pk = new MarketRulePK();
			marketRuleJAXB.setActive((short)1);
			marketRuleJAXB.setRule("Good to go");
			pk.setBranch(333);
			pk.setCountryNumber("XX-XX-XXX");
			pk.setStockCategory((short)0);
			marketRuleJAXB.setId(pk);
			WrapperRuleJAXB wrapperRuleJAXB = new WrapperRuleJAXB(); 
			wrapperRuleJAXB.setRuleType(RuleType.MARKET);
			wrapperRuleJAXB.setJsonORxml(XmlJsonStringConvertor.getXMLStringForRuleJAXB(marketRuleJAXB));
			return wrapperRuleJAXB;
			
		}else if(xmlOrJson.equals("json")){
			MarketRuleJAXB marketRuleJAXB = new MarketRuleJAXB();
			MarketRulePK pk = new MarketRulePK();
			marketRuleJAXB.setActive((short)1);
			marketRuleJAXB.setRule("Good to go");
			pk.setBranch(333);
			pk.setCountryNumber("XX-XX-XXX");
			pk.setStockCategory((short)0);
			marketRuleJAXB.setId(pk);
			WrapperRuleJAXB wrapperRuleJAXB = new WrapperRuleJAXB(); 
			wrapperRuleJAXB.setRuleType(RuleType.MARKET);
			wrapperRuleJAXB.setJsonORxml(XmlJsonStringConvertor.getJSONStringForRuleJAXB(marketRuleJAXB));
			return wrapperRuleJAXB;
		}
		
		return null;
	}
}
