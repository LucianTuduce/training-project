package com.fortech.rest;

import javax.ejb.Stateless;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;



/**
 * Class that is used as a REST service class. In here the communication with
 * the web is made.
 *
 */
@Stateless
@ApplicationPath("/car")
public class RuleApiApplication extends Application{

	
}
