package com.fortech.modeljaxb;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fortech.model.InterpretationInnerRule;
import com.fortech.model.TargetVehicle;


@XmlRootElement(name = "InterpretationRuleJAXB")
@XmlAccessorType(XmlAccessType.FIELD)
public class InterpretationRuleJAXB {

	@XmlElement(required = true)
	private int id;

	@XmlElement(required = true)
	private List<TargetVehicle> targetVehicles;

	@XmlElement(required = true)
	private List<InterpretationInnerRule> interpretationInnerRules;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<TargetVehicle> getTargetVehicles() {
		return targetVehicles;
	}

	public void setTargetVehicles(List<TargetVehicle> targetVehicles) {
		this.targetVehicles = targetVehicles;
	}

	public List<InterpretationInnerRule> getInterpretationInnerRules() {
		return interpretationInnerRules;
	}

	public void setInterpretationInnerRules(
			List<InterpretationInnerRule> interpretationInnerRules) {
		this.interpretationInnerRules = interpretationInnerRules;
	}
	
	
}
