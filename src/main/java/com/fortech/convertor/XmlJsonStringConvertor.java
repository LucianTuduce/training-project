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
import com.fortech.modeljaxb.MarketRuleFlattedJAXB;

/**
 * Class used to convert from the object to the corresponding XML or JSON format
 * 
 * @author lucian.tuduce
 *
 */
public class XmlJsonStringConvertor {

	/**
	 * Method used to convert from the MappingRuleJAXB object to the XML string
	 * form of the object
	 * 
	 * @param mappingRuleJAXB
	 *            the mappingRuleJAXB object that will be converted
	 * @return the XML string form of the mappingRuleJAXB object
	 */
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

	/**
	 * Method used to convert from the InterpretationRuleJAXB object to the XML
	 * string form of the object
	 * 
	 * @param interpretationRuleJAXB
	 *            the interpretationRuleJAXB object that will be converted
	 * @return the XML string form of the interpretationRuleJAXB object
	 */
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

	/**
	 * Method used to convert from the MarketRuleFlattedJAXB object to the XML
	 * string form of the object
	 * 
	 * @param marketRuleFlattedJAXB
	 *            the marketRuleFlattedJAXB object that will be converted
	 * @return the XML string form of the marketRuleFlattedJAXB object
	 * 
	 */
	public static String getXMLStringForRuleJAXB(MarketRuleFlattedJAXB marketRuleFlattedJAXB) {
		StringWriter stringWriter = new StringWriter();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(MarketRuleFlattedJAXB.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(marketRuleFlattedJAXB, stringWriter);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return stringWriter.toString();
	}
	
	/**
	 * Method used to convert from the MappingRuleJAXB object to the JSON string
	 * form of the object
	 * 
	 * @param mappingRuleJAXB
	 *            the mappingRuleJAXB object that will be converted
	 * @return the JSON string form of the mappingRuleJAXB object
	 */
	public static String getJSONStringForRuleJAXB(MappingRuleJAXB mappingRuleJAXB) {
		ObjectMapper mapper = new ObjectMapper();
		StringWriter objectStringForm = new StringWriter();
		try {
			mapper.writeValue(objectStringForm, mappingRuleJAXB);
		} catch (JsonGenerationException | JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return objectStringForm.toString();
	}

	/**
	 * Method used to convert from the InterpretationRuleJAXB object to the JSON
	 * string form of the object
	 * 
	 * @param interpretationRuleJAXB
	 *            the interpretationRuleJAXB object that will be converted
	 * @return the JSON string form of the interpretationRuleJAXB object
	 */
	public static String getJSONStringForRuleJAXB(MarketRuleFlattedJAXB marketRuleFlattedJAXB) {
		ObjectMapper mapper = new ObjectMapper();
		StringWriter objectStringForm = new StringWriter();
		try {
			mapper.writeValue(objectStringForm, marketRuleFlattedJAXB);
		} catch (JsonGenerationException | JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return objectStringForm.toString();
	}
	
	/**
	 * Method used to convert from the InterpretationRuleJAXB object to the JSON
	 * string form of the object
	 * 
	 * @param interpretationRuleJAXB
	 *            the interpretationRuleJAXB object that will be converted
	 * @return the JSON string form of the interpretationRuleJAXB object
	 */
	public static String getJSONStringForRuleJAXB(InterpretationRuleJAXB interpretationRuleJAXB) {
		ObjectMapper mapper = new ObjectMapper();
		StringWriter objectStringForm = new StringWriter();
		try {
			mapper.writeValue(objectStringForm, interpretationRuleJAXB);
		} catch (JsonGenerationException | JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return objectStringForm.toString();
	}
}
