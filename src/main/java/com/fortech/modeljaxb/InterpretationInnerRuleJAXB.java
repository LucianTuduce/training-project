package com.fortech.modeljaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "InterpretationInnerRuleJAXB")
@XmlAccessorType(XmlAccessType.FIELD)
public class InterpretationInnerRuleJAXB {

	@XmlElement(required = true)
	private int id;

	@XmlElement(required = true)
	private int ruleId;

	@XmlElement(required = true)
	private String vehicleAttribute;

	@XmlElement(required = true)
	private String vehicleAttributeValues;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRuleId() {
		return ruleId;
	}

	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
	}

	public String getVehicleAttribute() {
		return vehicleAttribute;
	}

	public void setVehicleAttribute(String vehicleAttribute) {
		this.vehicleAttribute = vehicleAttribute;
	}

	public String getVehicleAttributeValues() {
		return vehicleAttributeValues;
	}

	public void setVehicleAttributeValues(String vehicleAttributeValues) {
		this.vehicleAttributeValues = vehicleAttributeValues;
	}
	
}


