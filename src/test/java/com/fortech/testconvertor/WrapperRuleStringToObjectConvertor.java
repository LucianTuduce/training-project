package com.fortech.testconvertor;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.fortech.wrapper.WrapperRuleJAXB;
import com.fortech.wrappersrule.WrapperRules;

public class WrapperRuleStringToObjectConvertor {

	/**
	 * Method used to convert from the string XML form of the WrapperRuleJAXB to
	 * the object form of the WrapperRuleJAXB
	 * 
	 * @param wrapperRule
	 *            the string for of the rule
	 * @return the converted object from the string
	 */
	public static WrapperRuleJAXB getWrappreRuleFromXML(String wrapperRule) {
		try {
			StringReader objetReader = new StringReader(wrapperRule);
			JAXBContext jaxbContext = JAXBContext.newInstance(WrapperRuleJAXB.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			return (WrapperRuleJAXB) unmarshaller.unmarshal(objetReader);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Method used to convert from the string JSON form of the WrapperRuleJAXB
	 * to the object form of the WrapperRuleJAXB
	 * 
	 * @param wrapperRule
	 *            the string for of the rule
	 * @return the converted object from the string
	 */
	public static WrapperRuleJAXB geWrapperRuleFromJSON(String wrapperRule) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(wrapperRule, WrapperRuleJAXB.class);
		} catch (JsonParseException | JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param wrapperRules
	 * @return
	 */
	public static List<WrapperRuleJAXB> getConvertedListFromString(String wrapperRules) {

		JAXBContext jaxbContext = null;
		Unmarshaller jaxbUnmarshaller = null;
		StringReader reader = null;
		WrapperRules rules = null;

		try {
			jaxbContext = JAXBContext.newInstance(WrapperRules.class);
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			reader = new StringReader(wrapperRules);
			rules = (WrapperRules) jaxbUnmarshaller.unmarshal(reader);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return rules.getWrapperRules();
	}

	public static String getMarshaledListInString(WrapperRules wrapperRules){
		JAXBContext jaxbContext = null;
		StringWriter stringWriter = new StringWriter();
		try {
			jaxbContext = JAXBContext.newInstance(WrapperRules.class);
			 Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(wrapperRules, stringWriter);
		} catch (JAXBException e) {
			e.printStackTrace();
		}    
	    return stringWriter.toString();
	}
	
}
