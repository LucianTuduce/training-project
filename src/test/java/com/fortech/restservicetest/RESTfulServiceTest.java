package com.fortech.restservicetest;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Before;
import org.junit.Test;

import com.fortech.convertor.WrapperRuleFlattener;
import com.fortech.convertor.XmlJsonStringConvertor;
import com.fortech.enums.RuleType;
import com.fortech.model.MarketRulePK;
import com.fortech.modeljaxb.MarketRuleJAXB;
import com.fortech.testconvertor.WrapperRuleStringToObjectConvertor;
import com.fortech.wrapper.WrapperRuleJAXB;

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

	private HttpClient client;
	private HttpGet requestOne;
	private HttpGet requestMoreRules;
	private HttpPut putRequest;
	private HttpDelete deleteRequest;

	@Before
	public void init() {
		client = HttpClientBuilder.create().build();
		requestOne = new HttpGet(URL_GET_ONE_RULE_XML);
		requestMoreRules = new HttpGet(URL_GET_MORE_RULES_XML);
		putRequest = new HttpPut(URL_PUT_RULE_XML);
		deleteRequest = new HttpDelete(URL_DELETE_RULE_XML);
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

		List<WrapperRuleJAXB> list = WrapperRuleStringToObjectConvertor.getConvertedListFromString(result.toString());
		assertEquals(response.getStatusLine().getStatusCode(), 200);
		assertNotNull(list);
		assertEquals(list.get(0).getRuleType(), RuleType.MARKET);
		assertNotNull(list.get(0).getJsonORxml());
	}

	/**
	 * Method used to check if the @DELETE HTTP method is called by a client and
	 * that the communication with the service classes is made. The test checks
	 * if the call to the rest service is made.
	 */
	@Test
	public void addRuleInDatabase_AddRuleInDatabase_SuccessStatus200() {
		WrapperRuleJAXB wrapperRuleJAXB = new WrapperRuleJAXB();
		wrapperRuleJAXB.setRuleType(RuleType.MARKET);
		wrapperRuleJAXB.setJsonORxml(XmlJsonStringConvertor.getXMLStringForRuleJAXB(getMarketRule()));
		HttpResponse response = null;
		putRequest.addHeader("Content-Type", "application/xml");
		putRequest.addHeader("Accept", "application/xml");
		try {
			putRequest.setEntity(new StringEntity(marshalledWrapperRuleJAXB()));
			response = client.execute(putRequest);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		assertNotNull(response);
		assertEquals(response.getStatusLine().getStatusCode(), 200);
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
		marketRulePK.setCountryNumber("CCCCCAA");
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

}
