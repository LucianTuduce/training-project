package com.fortech.transform;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.fortech.modeljaxb.MarketRuleJAXB;

/**
 * Class that makes the marshal and unmarshal of an object of type MarketRule
 * 
 * @author dariad
 *
 */
public class MarketRuleTrsFromToXML {

	/*
	 * Method that receives a String that's an object in xml format,
	 * transforms it into an object of type MarketRuleJaxB and returns it 
	 */
	public static MarketRuleJAXB transfromXML(String xmlString) {

		StringReader reader = new StringReader(xmlString);

		JAXBContext jaxbContext;
		MarketRuleJAXB marketRule = new MarketRuleJAXB();

		try {
			jaxbContext = JAXBContext.newInstance(MarketRuleJAXB.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			marketRule = (MarketRuleJAXB) jaxbUnmarshaller.unmarshal(reader);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return marketRule;
	}

	/*
	 * Method that receives an object of type MarketRuleJaxB,
	 * transforms it into a String in format xml and returns it 
	 */
	public static String transToXML(MarketRuleJAXB marketRuleJaxB) throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance(MarketRuleJAXB.class);
		StringWriter writer = new StringWriter();
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		marshaller.marshal(marketRuleJaxB, writer);
		
		String xml = writer.toString();
		return xml;
	}
}
