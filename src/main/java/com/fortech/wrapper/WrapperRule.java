package com.fortech.wrapper;

import com.fortech.enums.RuleType;

/**
 * Class used to obtain all the rules by transforming them from object to string
 * XML or JSON form of the object
 * 
 * @author lucian.tuduce
 *
 */
public class WrapperRule {

	private RuleType ruleType;
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
		return "[ " + ruleType + " " + jsonORxml + " ]";
	}
}