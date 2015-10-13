package com.fortech.modeljaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fortech.model.MarketRulePK;

/**
 * The JAXB equivalent of the MarketRule. This class is used in order to get the
 * XML form of the class.
 * 
 * @author lucian.tuduce
 *
 */
@XmlRootElement(name = "MarketRuleJAXB")
@XmlAccessorType(XmlAccessType.FIELD)
public class MarketRuleJAXB {

	@XmlElement(required = true)
	private MarketRulePK id;
	
	@XmlElement(required = true)
	private short active;

	@XmlElement(required = true)
	private String rule;

	
	public MarketRulePK getId() {
		return this.id;
	}

	public void setId(MarketRulePK id) {
		this.id = id;
	}

	public short getActive() {
		return this.active;
	}

	public void setActive(short active) {
		this.active = active;
	}

	public String getRule() {
		return this.rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

}
