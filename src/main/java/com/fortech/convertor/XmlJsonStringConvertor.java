package com.fortech.convertor;

import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.fortech.modeljaxb.InterpretationRuleJAXB;
import com.fortech.modeljaxb.MappingRuleJAXB;
import com.fortech.modeljaxb.MarketRuleJAXB;

public class XmlJsonStringConvertor {

	public static String getXMLStringForRuleJAXB(MappingRuleJAXB mappingRuleJAXB) {
		StringWriter stringWriter = new StringWriter();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(MappingRuleJAXB.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(mappingRuleJAXB, stringWriter);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return stringWriter.toString();
	}
	
	public static String getXMLStringForRuleJAXB(MarketRuleJAXB marketRuleJAXB) {
		StringWriter stringWriter = new StringWriter();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(MarketRuleJAXB.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(marketRuleJAXB, stringWriter);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return stringWriter.toString();
	}
	
	public static String getXMLStringForRuleJAXB(InterpretationRuleJAXB interpretationRuleJAXB) {
		StringWriter stringWriter = new StringWriter();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(InterpretationRuleJAXB.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(interpretationRuleJAXB, stringWriter);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return stringWriter.toString();
	}
	
	public static String getJSONStringForRuleJAXB(MappingRuleJAXB mappingRuleJAXB) {
		ObjectMapper mapper = new ObjectMapper();
		StringWriter objectStringForm = new StringWriter();
		try {
			mapper.writeValue(objectStringForm, mappingRuleJAXB);
		} catch (JsonGenerationException | JsonMappingException  e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return objectStringForm.toString();
	}
	
	public static String getJSONStringForRuleJAXB(MarketRuleJAXB marketRuleJAXB) {
		ObjectMapper mapper = new ObjectMapper();
		StringWriter objectStringForm = new StringWriter();
		try {
			mapper.writeValue(objectStringForm, marketRuleJAXB);
		} catch (JsonGenerationException | JsonMappingException  e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return objectStringForm.toString();
	}
	
	public static String getJSONStringForRuleJAXB(InterpretationRuleJAXB interpretationRuleJAXB) {
		ObjectMapper mapper = new ObjectMapper();
		StringWriter objectStringForm = new StringWriter();
		try {
			mapper.writeValue(objectStringForm, interpretationRuleJAXB);
		} catch (JsonGenerationException | JsonMappingException  e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return objectStringForm.toString();
	}
	
	
}
