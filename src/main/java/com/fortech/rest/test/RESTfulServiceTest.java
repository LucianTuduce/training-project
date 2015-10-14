package com.fortech.rest.test;

import static org.junit.Assert.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.junit.Test;




import com.fortech.wrapper.WrapperRuleJAXB;

public class RESTfulServiceTest {

	@Test
	public void deleteRuleFromDatabase_gotObjectFromService_Success() {
		
		Client client = createClient();
		 WebTarget target = client
	                .target("http://localhost:9080/UVSRulesApi/car/test/json/mapping");

		 Response response = target.request("application/xml").accept("application/xml").get();
		 WrapperRuleJAXB rule = (WrapperRuleJAXB) response.getEntity();
		  assertEquals(Status.OK.getStatusCode(), response.getStatus());

	}

	private Client createClient() {
		return ClientBuilder
                .newBuilder()
                .register(JacksonJaxbJsonProvider.class)
                .build();
    }


}
