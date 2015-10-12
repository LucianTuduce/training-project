package com.fortech.convertor;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.fortech.modeljaxb.InterpretationRuleJAXB;
import com.fortech.modeljaxb.MappingRuleJAXB;
import com.fortech.modeljaxb.MarketRuleJAXB;

public class XmlJsonObjectConvertor {

	public static MappingRuleJAXB getMappingRuleFromXML(String mappingRule){
		try {
			StringReader objetReader = new StringReader(mappingRule);
			JAXBContext jaxbContext = JAXBContext.newInstance(MappingRuleJAXB.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			return (MappingRuleJAXB) unmarshaller.unmarshal(objetReader);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static MarketRuleJAXB getMarketRuleFromXML(String marketRule){
		try {
			StringReader objetReader = new StringReader(marketRule);
			JAXBContext jaxbContext = JAXBContext.newInstance(MarketRuleJAXB.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			return (MarketRuleJAXB) unmarshaller.unmarshal(objetReader);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static InterpretationRuleJAXB getInterpretationRuleFromXML(String interpretationRule){
		try {
			StringReader objetReader = new StringReader(interpretationRule);
			JAXBContext jaxbContext = JAXBContext.newInstance(InterpretationRuleJAXB.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			return (InterpretationRuleJAXB) unmarshaller.unmarshal(objetReader);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static MappingRuleJAXB getMappingRuleFromJSON(String mappingRule){
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(mappingRule, MappingRuleJAXB.class);
		} catch (JsonParseException | JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static MarketRuleJAXB getMarketRuleFromJSON(String marketRule){
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(marketRule, MarketRuleJAXB.class);
		} catch (JsonParseException | JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static InterpretationRuleJAXB getInterpretationRuleFromJSON(String interpretationRule){
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(interpretationRule, InterpretationRuleJAXB.class);
		} catch (JsonParseException | JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
