package com.fortech.wrappersrule;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fortech.wrapper.WrapperRuleJAXB;

@XmlRootElement(name = "WrapperTypeJAXBs")
@XmlAccessorType (XmlAccessType.FIELD)
public class WrapperRules {

	@XmlElement(name="WrapperTypeJAXB")
	private List<WrapperRuleJAXB> wrapperRules;

	public List<WrapperRuleJAXB> getWrapperRules() {
		return wrapperRules;
	}

	public void setWrapperRules(List<WrapperRuleJAXB> wrapperRules) {
		this.wrapperRules = wrapperRules;
	}
	
}
