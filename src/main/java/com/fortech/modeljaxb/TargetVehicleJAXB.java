package com.fortech.modeljaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fortech.model.InterpretationRule;

/**
 * The JAXB equivalent of the TargetVehicle. This class is used in order to
 * get the XML form of the class.
 * 
 * @author lucian.tuduce
 *
 */
@XmlRootElement(name = "TargetVehicleJAXB")
@XmlAccessorType(XmlAccessType.FIELD)
public class TargetVehicleJAXB {

	@XmlElement(required = true)
	private int id;

	@XmlElement(required = true)
	private int ruleId;

	@XmlElement(required = true)
	private String vehicleAttribute;

	@XmlElement(required = true)
	private String vehicleAttributeValue;

	@XmlElement(required = true)
	private InterpretationRule interpretationRule;

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

	public String getVehicleAttributeValue() {
		return vehicleAttributeValue;
	}

	public void setVehicleAttributeValue(String vehicleAttributeValue) {
		this.vehicleAttributeValue = vehicleAttributeValue;
	}

	public InterpretationRule getInterpretationRule() {
		return interpretationRule;
	}

	public void setInterpretationRule(InterpretationRule interpretationRule) {
		this.interpretationRule = interpretationRule;
	}

}
