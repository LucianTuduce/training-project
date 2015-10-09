package com.fortech.convertor;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;

import com.fortech.enums.RuleType;
import com.fortech.modeljaxb.InterpretationRuleJAXB;
import com.fortech.modeljaxb.MappingRuleJAXB;
import com.fortech.modeljaxb.MarketRuleJAXB;
import com.fortech.wrapper.WrapperRuleJAXB;

public class XmlJsonConvertor {

	public static String getXMLStringForRuleJAXB(WrapperRuleJAXB wrapperRuleJAXB) {
		
		if(wrapperRuleJAXB.getRuleType().equals(RuleType.MARKET)){
			try {
				return getStringMarshaledMarketRule(getUnmarshaledMarketRuleJAXB(wrapperRuleJAXB));
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}else if(wrapperRuleJAXB.getRuleType().equals(RuleType.MAPPING)){
			try {
				return getStringMarshaledMappingRuleJAXB(getUnmarshaledMappingRuleJAXB(wrapperRuleJAXB));
			} catch (PropertyException e) {
				e.printStackTrace();
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}else if(wrapperRuleJAXB.getRuleType().equals(RuleType.INTERPRETATION)){
			try {
				return getStringMarshaledInterpretationRuleJAXB(getUnmarshaledInterpretationRuleJAXB(wrapperRuleJAXB));
			} catch (PropertyException e) {
				e.printStackTrace();
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	private static String getStringMarshaledMarketRule(MarketRuleJAXB marketRuleJAXB) throws JAXBException, PropertyException {
		JAXBContext jaxbContext = JAXBContext.newInstance(MarketRuleJAXB.class);
		StringWriter stringWriter = new StringWriter();
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		marshaller.marshal(marketRuleJAXB, stringWriter);
		return stringWriter.toString();
	}

	private static MarketRuleJAXB getUnmarshaledMarketRuleJAXB(WrapperRuleJAXB wrapperRuleJAXB) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(MarketRuleJAXB.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		StringReader reader = new StringReader(wrapperRuleJAXB.getJsonORxml());
		return (MarketRuleJAXB) jaxbUnmarshaller.unmarshal(reader);
	}
	
	private static String getStringMarshaledMappingRuleJAXB(MappingRuleJAXB mappingRuleJAXB) throws JAXBException, PropertyException {
		JAXBContext jaxbContext = JAXBContext.newInstance(MappingRuleJAXB.class);
		StringWriter stringWriter = new StringWriter();
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		marshaller.marshal(mappingRuleJAXB, stringWriter);
		return stringWriter.toString();
	}

	private static MappingRuleJAXB getUnmarshaledMappingRuleJAXB(WrapperRuleJAXB wrapperRuleJAXB) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(MappingRuleJAXB.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		StringReader reader = new StringReader(wrapperRuleJAXB.getJsonORxml());
		return (MappingRuleJAXB) jaxbUnmarshaller.unmarshal(reader);
	}
	
	private static String getStringMarshaledInterpretationRuleJAXB(InterpretationRuleJAXB interpreationRuleJAXB) throws JAXBException, PropertyException {
		JAXBContext jaxbContext = JAXBContext.newInstance(InterpretationRuleJAXB.class);
		StringWriter stringWriter = new StringWriter();
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		marshaller.marshal(interpreationRuleJAXB, stringWriter);
		return stringWriter.toString();
	}

	private static InterpretationRuleJAXB getUnmarshaledInterpretationRuleJAXB(WrapperRuleJAXB wrapperRuleJAXB) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(InterpretationRuleJAXB.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		StringReader reader = new StringReader(wrapperRuleJAXB.getJsonORxml());
		return (InterpretationRuleJAXB) jaxbUnmarshaller.unmarshal(reader);
	}


}
