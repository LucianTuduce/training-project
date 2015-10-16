package com.fortech.restservicetest;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Before;
import org.junit.Test;

import com.fortech.convertor.WrapperRuleFlattener;
import com.fortech.convertor.XmlJsonStringConvertor;
import com.fortech.enums.RuleType;
import com.fortech.enums.StockCategory;
import com.fortech.model.MarketRulePK;
import com.fortech.modeljaxb.InterpretationRuleJAXB;
import com.fortech.modeljaxb.MappingRuleJAXB;
import com.fortech.modeljaxb.MarketRuleFlattedJAXB;
import com.fortech.modeljaxb.MarketRuleJAXB;
import com.fortech.testconvertor.WrapperRuleStringToObjectConvertor;
import com.fortech.wrapper.WrapperRuleJAXB;
import com.fortech.wrappersrule.WrapperRules;

/**
 * Test class used to check if the RESTfulService class responds to the users
 * requests.
 * 
 * @author lucian.tuduce
 *
 */
public class RESTfulServiceTest {

	private static final String URL_GET_ONE_RULE_XML = "http://localhost:9080/UVSRulesApi/car/test/json/market";
	private static final String URL_GET_MORE_RULES_XML = "http://localhost:9080/UVSRulesApi/car/rule/xml";
	private static final String URL_PUT_RULE_XML = "http://localhost:9080/UVSRulesApi/car/rule/xml";
	private static final String URL_DELETE_RULE_XML = "http://localhost:9080/UVSRulesApi/car/rule/market/xml";
	private static final String URL_POST_GET_BY_ID_RULE = "http://localhost:9080/UVSRulesApi/car/rule/xml/market";
	private static final String URL_POST_ADD_LIST_OF_RULES_IN_DB = "http://localhost:9080/UVSRulesApi/car/rule/xml";

	private HttpClient client;
	private HttpGet requestOne;
	private HttpGet requestMoreRules;
	private HttpPut putRequest;
	private HttpDelete deleteRequest;
	private HttpPost postRequest;
	private HttpPost postRequestAdd;

	@Before
	public void init() {
		client = HttpClientBuilder.create().build();
		requestOne = new HttpGet(URL_GET_ONE_RULE_XML);
		requestMoreRules = new HttpGet(URL_GET_MORE_RULES_XML);
		putRequest = new HttpPut(URL_PUT_RULE_XML);
		deleteRequest = new HttpDelete(URL_DELETE_RULE_XML);
		postRequest = new HttpPost(URL_POST_GET_BY_ID_RULE);
		postRequestAdd = new HttpPost(URL_POST_ADD_LIST_OF_RULES_IN_DB);
	}

	/**
	 * Method used to check if the @GET HTTP method return's a rule from the
	 * database. The test checks if the rule is not null, that the response from
	 * the server good (200), and that the object has the values that are
	 * required.
	 */
	@Test
	public void getWrapperRuleJAXB_checkServerResponse_Status200() {

		HttpResponse response = null;
		BufferedReader rd = null;
		StringBuffer result = null;
		WrapperRuleJAXB wrapperRuleJAXB = null;

		try {
			response = client.execute(requestOne);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {

			rd = new BufferedReader(new InputStreamReader(response.getEntity()
					.getContent()));
			result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}

		} catch (UnsupportedOperationException | IOException e) {
			e.printStackTrace();
		}

		wrapperRuleJAXB = WrapperRuleStringToObjectConvertor.geWrapperRuleFromJSON(result.toString());
		assertEquals(response.getStatusLine().getStatusCode(), 200);
		assertNotNull(wrapperRuleJAXB);
		assertEquals(wrapperRuleJAXB.getRuleType(), RuleType.MARKET);
		assertNotNull(wrapperRuleJAXB.getJsonORxml());
	}

	/**
	 * Method used to check if the @GET HTTP method return's all the rules from
	 * the database. The test checks if the rule list is not null, that the
	 * response from the server good (200), and that the object has the values
	 * that are required.
	 */
	@Test
	public void getRules_ObtainAllRulesFromDatabase_Success() {

		HttpResponse response = null;
		BufferedReader rd = null;
		StringBuffer result = null;

		try {
			response = client.execute(requestMoreRules);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
		} catch (UnsupportedOperationException | IOException e) {
			e.printStackTrace();
		}

		List<WrapperRuleJAXB> list = WrapperRuleStringToObjectConvertor.getConvertedListFromString(result.toString());
		assertEquals(response.getStatusLine().getStatusCode(), 200);
		assertNotNull(list);
		assertNotNull(list.get(0).getRuleType());
		assertNotNull(list.get(0).getJsonORxml());
	}

	/**
	 * Method used to check if the @DELETE HTTP method is called by a client and
	 * that the communication with the service classes is made. The test checks
	 * if the call to the rest service is made.
	 */
	@Test
	public void upadteRuleInDatabase_UpdateRuleInDatabase_SuccessStatus200() {
		WrapperRuleJAXB wrapperRuleJAXB = new WrapperRuleJAXB();
		wrapperRuleJAXB.setRuleType(RuleType.MARKET);
		wrapperRuleJAXB.setJsonORxml(XmlJsonStringConvertor.getXMLStringForRuleJAXB(getMarketRule()));
		HttpResponse response = null;

		putRequest.addHeader("Content-Type", "application/xml");
		putRequest.addHeader("Accept", "application/xml");
		putRequest.addHeader("Accept", "application/json");
		try {
			putRequest.setEntity(new StringEntity(marshalledWrapperRuleJAXB()));
			response = client.execute(putRequest);
		} catch (IOException e2) {
			e2.printStackTrace();
		}	
		assertNotNull(response);
		assertEquals(200, response.getStatusLine().getStatusCode());
		assertEquals(new ProtocolVersion("HTTP", 1, 1), response.getStatusLine().getProtocolVersion());

	}

	@Test
	public void addRulesInDatabase_AddedRulesInDatabase_SuccessStatus200() {
		WrapperRules wrapperRules = new WrapperRules();
		wrapperRules.setWrapperRules(getList());
		String xmlForm = WrapperRuleStringToObjectConvertor.getMarshaledListINString(wrapperRules);
		HttpResponse response = null;
		
		postRequestAdd.addHeader("Content-Type", "application/xml");
		postRequestAdd.addHeader("Accept", "application/xml");
		
		try {
			postRequestAdd.setEntity(new StringEntity(xmlForm));
			response = client.execute(postRequestAdd);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		assertNotNull(response);
		assertEquals(200, response.getStatusLine().getStatusCode());
		assertEquals(new ProtocolVersion("HTTP", 1, 1), response.getStatusLine().getProtocolVersion());
	}

	
	@Test
	public void deleteRuleFromDatabase_DeletedRuleFromDatabase_SuccessStatus200() {
		WrapperRuleJAXB wrapperRuleJAXB = new WrapperRuleJAXB();
		wrapperRuleJAXB.setRuleType(RuleType.MARKET);
		wrapperRuleJAXB.setJsonORxml(XmlJsonStringConvertor.getXMLStringForRuleJAXB(getMarketRule()));
		HttpResponse response = null;

		deleteRequest.addHeader("Content-Type", "application/xml");
		deleteRequest.addHeader("Accept", "application/xml");
		try {
			response = client.execute(deleteRequest);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		assertNotNull(response);
		assertEquals(400, response.getStatusLine().getStatusCode());
		assertEquals(new ProtocolVersion("HTTP", 1, 1), response.getStatusLine().getProtocolVersion());

	}

	//@Test
	public void getRule_ObtainRuleFromDatabase_Success200() {

		HttpResponse response = null;
		BufferedReader rd = null;
		StringBuffer result = null;

		postRequest.addHeader("Content-Type", "application/xml");
		postRequest.addHeader("Accept", "application/xml");
		try {
			postRequest.setEntity(new StringEntity(marshalledWrapperRuleJAXB()));
			response = client.execute(postRequest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
		} catch (UnsupportedOperationException | IOException e) {
			e.printStackTrace();
		}
		
//		MarketRuleJAXB marketRuleJAXB = XmlJsonObjectConvertor.getMarketRuleFromXML(result.toString());
//		assertNotNull(marketRuleJAXB);
//		assertEquals(RuleType.MARKET, marketRuleJAXB.getRule());
//		assertEquals(response.getStatusLine().getStatusCode(), 200);
//		assertEquals(new ProtocolVersion("HTTP", 1, 1), response.getStatusLine().getProtocolVersion());
	}
	
	private MarketRuleJAXB getMarketRule() {
		MarketRuleJAXB marketRuleJAXB = new MarketRuleJAXB();
		marketRuleJAXB.setActive((short) 1);
		marketRuleJAXB.setRule("Old car");
		marketRuleJAXB.setId(getMarketRulePKforTest());
		return marketRuleJAXB;
	}

	private MarketRulePK getMarketRulePKforTest() {
		MarketRulePK marketRulePK = new MarketRulePK();
		marketRulePK.setBranch(44);
		marketRulePK.setCountryNumber("dddd");
		marketRulePK.setStockCategory((short) 0);
		return marketRulePK;
	}

	private String marshalledWrapperRuleJAXB() {
		WrapperRuleJAXB rule = WrapperRuleFlattener.createXMLWrapperRuleFor(getMarketRule());
		StringWriter writer = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(WrapperRuleJAXB.class);
			writer = new StringWriter();
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(rule, writer);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return writer.toString();
	}
	
	public List<WrapperRuleJAXB> getList(){
		String xmlFormForMarketRuleFlattedJAXB = createDeafultMarketRule();
		String xmlFormForMappingRuleJAXB = cretaeDefaultMappingRule();
		String xmlFormForInterpretationRuleJAXB = creatDefaultInterpretationRule();
		
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

	private String creatDefaultInterpretationRule() {
		InterpretationRuleJAXB interpretationRuleJAXB = new InterpretationRuleJAXB();
		interpretationRuleJAXB.setId(56);	
		return XmlJsonStringConvertor.getXMLStringForRuleJAXB(interpretationRuleJAXB);
	}

	private String cretaeDefaultMappingRule() {
		MappingRuleJAXB mappingRuleJAXB = new MappingRuleJAXB();
		mappingRuleJAXB.setId(112);
		mappingRuleJAXB.setSourceValue("900");
		mappingRuleJAXB.setTargetValue("1300");
		mappingRuleJAXB.setVehicleAttribute("4 wheels");
		return XmlJsonStringConvertor.getXMLStringForRuleJAXB(mappingRuleJAXB);
	}

	private String createDeafultMarketRule() {
		MarketRuleFlattedJAXB flattedJAXB = new MarketRuleFlattedJAXB();
		flattedJAXB.setActive(true);
		flattedJAXB.setBranch(56);
		flattedJAXB.setCountryNumber("CC-CC-HHH");
		flattedJAXB.setRule("Old Car");
		flattedJAXB.setStockCategory(StockCategory.NEW);
		return XmlJsonStringConvertor.getXMLStringForRuleJAXB(flattedJAXB);
	}
}
