package com.fortech.rest.test;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.fortech.convertor.XmlJsonStringConvertor;
import com.fortech.enums.RuleType;
import com.fortech.enums.StockCategory;
import com.fortech.modeljaxb.InterpretationRuleJAXB;
import com.fortech.modeljaxb.MappingRuleJAXB;
import com.fortech.modeljaxb.MarketRuleFlattedJAXB;
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
	@Path("/getlistxml")
	@Produces("application/json")
	public List<WrapperRuleJAXB> getList(){
		MarketRuleFlattedJAXB flattedJAXB = new MarketRuleFlattedJAXB();
		flattedJAXB.setActive(true);
		flattedJAXB.setBranch(56);
		flattedJAXB.setCountryNumber("CC-CC-CCC");
		flattedJAXB.setRule("Old Car");
		flattedJAXB.setStockCategory(StockCategory.NEW);
		String xmlFormForMarketRuleFlattedJAXB = XmlJsonStringConvertor.getXMLStringForRuleJAXB(flattedJAXB);
		
		MappingRuleJAXB mappingRuleJAXB = new MappingRuleJAXB();
		mappingRuleJAXB.setId(111);
		mappingRuleJAXB.setSourceValue("900");
		mappingRuleJAXB.setTargetValue("1300");
		mappingRuleJAXB.setVehicleAttribute("4 wheels");
		String xmlFormForMappingRuleJAXB = XmlJsonStringConvertor.getXMLStringForRuleJAXB(mappingRuleJAXB);
		
		InterpretationRuleJAXB interpretationRuleJAXB = new InterpretationRuleJAXB();
		interpretationRuleJAXB.setId(55);
		
		String xmlFormForInterpretationRuleJAXB = XmlJsonStringConvertor.getXMLStringForRuleJAXB(interpretationRuleJAXB);
		List<WrapperRuleJAXB> wrapperRules = new ArrayList<WrapperRuleJAXB>();
		
		WrapperRuleJAXB jaxb1 = new WrapperRuleJAXB();
		WrapperRuleJAXB jaxb2 = new WrapperRuleJAXB();
		WrapperRuleJAXB jaxb3= new WrapperRuleJAXB();
		
		jaxb1.setRuleType(RuleType.MARKET);
		jaxb1.setJsonORxml(xmlFormForMarketRuleFlattedJAXB);
		
		jaxb2.setRuleType(RuleType.MAPPING);
		jaxb2.setJsonORxml(xmlFormForMappingRuleJAXB);
		
		jaxb3.setRuleType(RuleType.INTERPRETATION);
		jaxb3.setJsonORxml(xmlFormForInterpretationRuleJAXB);
		
		wrapperRules.add(jaxb1);
		wrapperRules.add(jaxb2);
		wrapperRules.add(jaxb3);
		return wrapperRules;
	}
	
	@GET
	@Path("/getlistjson")
	@Produces("application/json")
	public List<WrapperRuleJAXB> getListJSON(){
		MarketRuleFlattedJAXB flattedJAXB = new MarketRuleFlattedJAXB();
		flattedJAXB.setActive(true);
		flattedJAXB.setBranch(56);
		flattedJAXB.setCountryNumber("CC-CC-CCC");
		flattedJAXB.setRule("Old Car");
		flattedJAXB.setStockCategory(StockCategory.NEW);
		String xmlFormForMarketRuleFlattedJAXB = XmlJsonStringConvertor.getJSONStringForRuleJAXB(flattedJAXB);
		
		MappingRuleJAXB mappingRuleJAXB = new MappingRuleJAXB();
		mappingRuleJAXB.setId(111);
		mappingRuleJAXB.setSourceValue("900");
		mappingRuleJAXB.setTargetValue("1300");
		mappingRuleJAXB.setVehicleAttribute("4 wheels");
		String xmlFormForMappingRuleJAXB = XmlJsonStringConvertor.getJSONStringForRuleJAXB(mappingRuleJAXB);
		
		InterpretationRuleJAXB interpretationRuleJAXB = new InterpretationRuleJAXB();
		interpretationRuleJAXB.setId(55);
		
		String xmlFormForInterpretationRuleJAXB = XmlJsonStringConvertor.getJSONStringForRuleJAXB(interpretationRuleJAXB);
		List<WrapperRuleJAXB> wrapperRules = new ArrayList<WrapperRuleJAXB>();
		
		WrapperRuleJAXB jaxb1 = new WrapperRuleJAXB();
		WrapperRuleJAXB jaxb2 = new WrapperRuleJAXB();
		WrapperRuleJAXB jaxb3= new WrapperRuleJAXB();
		
		jaxb1.setRuleType(RuleType.MARKET);
		jaxb1.setJsonORxml(xmlFormForMarketRuleFlattedJAXB);
		
		jaxb2.setRuleType(RuleType.MAPPING);
		jaxb2.setJsonORxml(xmlFormForMappingRuleJAXB);
		
		jaxb3.setRuleType(RuleType.INTERPRETATION);
		jaxb3.setJsonORxml(xmlFormForInterpretationRuleJAXB);
		
		wrapperRules.add(jaxb1);
		wrapperRules.add(jaxb2);
		wrapperRules.add(jaxb3);
		return wrapperRules;
	}
}
