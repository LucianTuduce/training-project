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
import com.fortech.modeljaxb.MarketRuleFlattedJAXB;
import com.fortech.modeljaxb.MarketRuleJAXB;

/**
 * Class used to convert from XML or JSON format to the corresponding object.
 * 
 * @author lucian.tuduce
 *
 */
public class XmlJsonObjectConvertor {

	/**
	 * Method used to convert from the string XML form of the MappingRuleJAXB to
	 * the object form of the MappingRuleJAXB
	 * 
	 * @param mappingRule
	 *            the string for of the rule
	 * @return the converted object from the string
	 */
	public static MappingRuleJAXB getMappingRuleFromXML(String mappingRule) {
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

	/**
	 * Method used to convert from the string XML form of the MarketRuleJAXB to
	 * the object form of the MarketRuleJAXB
	 * 
	 * @param mappingRule
	 *            the string for of the rule
	 * @return the converted object from the string
	 */
	public static MarketRuleJAXB getMarketRuleFromXML(String marketRule) {
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

	/**
	 * Method used to convert from the string XML form of the
	 * MarketRuleFlattedJAXB to the object form of the MarketRuleFlattedJAXB
	 * 
	 * @param mappingRule
	 *            the string for of the rule
	 * @return the converted object from the string
	 */
	public static MarketRuleFlattedJAXB getMarketRuleFFromXML(String marketRule) {

		StringReader reader = new StringReader(marketRule);

		JAXBContext jaxbContext;
		MarketRuleFlattedJAXB marketRuleF = new MarketRuleFlattedJAXB();

		try {
			jaxbContext = JAXBContext.newInstance(MarketRuleFlattedJAXB.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			marketRuleF = (MarketRuleFlattedJAXB) jaxbUnmarshaller.unmarshal(reader);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		return marketRuleF;
	}

	/**
	 * Method used to convert from the string XML form of the
	 * InterpretationRuleJAXB to the object form of the InterpretationRuleJAXB
	 * 
	 * @param mappingRule
	 *            the string for of the rule
	 * @return the converted object from the string
	 */
	public static InterpretationRuleJAXB getInterpretationRuleFromXML(String interpretationRule) {
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
	/**
	 * Method used to convert from the string JSON form of the MappingRuleJAXB
	 * to the object form of the MappingRuleJAXB
	 * 
	 * @param mappingRule
	 *            the string for of the rule
	 * @return the converted object from the string
	 */
	public static MappingRuleJAXB getMappingRuleFromJSON(String mappingRule) {
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

	/**
	 * Method used to convert from the string JSON form of the MarketRuleJAXB to
	 * the object form of the MarketRuleJAXB
	 * 
	 * @param mappingRule
	 *            the string for of the rule
	 * @return the converted object from the string
	 */
	public static MarketRuleJAXB getMarketRuleFromJSON(String marketRule) {
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

	/**
	 * Method used to convert from the string JSON form of the
	 * InterpretationRuleJAXB to the object form of the InterpretationRuleJAXB
	 * 
	 * @param mappingRule
	 *            the string for of the rule
	 * @return the converted object from the string
	 */
	public static InterpretationRuleJAXB getInterpretationRuleFromJSON(String interpretationRule) {
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

	/**
	 * Method used to convert from the string JSON form of the
	 * MarketRuleFlattedJAXB to the object form of the MarketRuleFlattedJAXB
	 * 
	 * @param marketRuleFlattedJAXB
	 *            the string for of the rule
	 * @return the converted object from the string
	 */
	public static MarketRuleFlattedJAXB getMarketRuleFlattedFromJSON(String marketRuleFlattedJAXB) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(marketRuleFlattedJAXB, MarketRuleFlattedJAXB.class);
		} catch (JsonParseException | JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
