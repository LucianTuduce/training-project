package com.fortech.restservicetest;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Before;
import org.junit.Test;

import com.fortech.convertor.WrapperRuleFlattener;
import com.fortech.convertor.XmlJsonStringConvertor;
import com.fortech.enums.RuleType;
import com.fortech.model.MarketRulePK;
import com.fortech.modeljaxb.MarketRuleJAXB;
import com.fortech.testconvertor.WrapperRuleStringToObjectConvertor;
import com.fortech.wrapper.WrapperRuleJAXB;


public class RESTfulServiceTest {

	private static final String URL_GET_ONE_RULE_XML = "http://localhost:9080/UVSRulesApi/car/test/json/market";
	private static final String URL_GET_MORE_RULES_XML = "http://localhost:9080/UVSRulesApi/car/rule/xml";
	private static final String URL_PUT_RULE_XML = "http://localhost:9080/UVSRulesApi/car/rule/xml";
	
	private HttpClient client;
	private HttpGet requestOne;
	private HttpGet requestMoreRules;
	private HttpPut putOne;
	
	@Before
	public void init(){
		client = HttpClientBuilder.create().build();
		requestOne = new HttpGet(URL_GET_ONE_RULE_XML);
		requestMoreRules = new HttpGet(URL_GET_MORE_RULES_XML);
		putOne = new HttpPut(URL_PUT_RULE_XML);
	}
	
	@Test
	public void getWrapperRuleJAXB_checkServerResponse_Status200() {
		
		HttpResponse response = null;
		BufferedReader rd = null;
		StringBuffer result = null;
		WrapperRuleJAXB wrapperRuleJAXB=null;
		
		try {
			response = client.execute(requestOne);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			
			rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) { result.append(line);}
			
		} catch (UnsupportedOperationException | IOException e) {
			e.printStackTrace();
		} 
		
		wrapperRuleJAXB = WrapperRuleStringToObjectConvertor.geWrapperRuleFromJSON(result.toString());
		assertEquals(response.getStatusLine().getStatusCode(), 200);
		assertNotNull(wrapperRuleJAXB);
		assertEquals(wrapperRuleJAXB.getRuleType(), RuleType.MARKET);
		assertNotNull(wrapperRuleJAXB.getJsonORxml());
	}
	
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
		assertEquals(list.get(0).getRuleType(), RuleType.MARKET);
		assertNotNull(list.get(0).getJsonORxml());
	}

	
	/**
	 * Need work to be done on it!!!
	 */
	@Test
	public void addRuleInDatabase_AddRuleInDatabase_SuccessStatus200() {
		WrapperRuleJAXB wrapperRuleJAXB = new WrapperRuleJAXB();
		wrapperRuleJAXB.setRuleType(RuleType.MARKET);
		wrapperRuleJAXB.setJsonORxml(XmlJsonStringConvertor.getXMLStringForRuleJAXB(getMarketRule()));
		List<NameValuePair> rules = new ArrayList<NameValuePair>();
		rules.add(new BasicNameValuePair("data", marshalledWrapperRuleJAXB()));
		try {
			putOne.setEntity(new UrlEncodedFormEntity(rules));
		} catch (UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		HttpResponse response = null;
		try {
			response = client.execute(putOne);
		} catch (ClientProtocolException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Response Code : " 
	                + response.getStatusLine().getStatusCode());

		BufferedReader rd = null;
		try {
			rd = new BufferedReader(
			        new InputStreamReader(response.getEntity().getContent()));
		} catch (UnsupportedOperationException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		StringBuffer result = new StringBuffer();
		String line = "";
		try {
			while ((line = rd.readLine()) != null) {
				result.append(line);
				System.out.println(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private MarketRuleJAXB getMarketRule(){
		MarketRuleJAXB marketRuleJAXB = new MarketRuleJAXB();
		marketRuleJAXB.setActive((short) 1);
		marketRuleJAXB.setRule("Old car");
		marketRuleJAXB.setId(getMarketRulePKforTest());
		return marketRuleJAXB;
	}

	private MarketRulePK getMarketRulePKforTest() {
		MarketRulePK marketRulePK = new MarketRulePK();
		marketRulePK.setBranch(44);
		marketRulePK.setCountryNumber("XXXXXXX");
		marketRulePK.setStockCategory((short)0);
		return marketRulePK;
	}
	
	private String marshalledWrapperRuleJAXB(){
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