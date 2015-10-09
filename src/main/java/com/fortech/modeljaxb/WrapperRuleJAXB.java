package com.fortech.modeljaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fortech.enums.RuleType;


@XmlRootElement(name = "WrapperTypeJAXB")
@XmlAccessorType(XmlAccessType.FIELD)
public class WrapperRuleJAXB {
	
	@XmlElement(required = true)
	private RuleType ruleType;
	
	@XmlElement(required = true)
	private String jsonORxml;

	public RuleType getRuleType() {
		return ruleType;
	}

	public void setRuleType(RuleType ruleType) {
		this.ruleType = ruleType;
	}

	public String getJsonORxml() {
		return jsonORxml;
	}

	public void setJsonORxml(String jsonORxml) {
		this.jsonORxml = jsonORxml;
	}
	
	@Override
	public String toString() {
		return "[ "+ruleType+" "+jsonORxml+" ]";
	}
}
