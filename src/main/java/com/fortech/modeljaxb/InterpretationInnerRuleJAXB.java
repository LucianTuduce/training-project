package com.fortech.modeljaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import com.fortech.model.InterpretationRule;

/**
 * The JAXB equivalent of the InterpretationInnerRule. This class is used in
 * order to get the XML form of the class.
 * 
 * @author lucian.tuduce
 *
 */
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

	public String getVehicleAttributeValues() {
		return vehicleAttributeValues;
	}

	public void setVehicleAttributeValues(String vehicleAttributeValues) {
		this.vehicleAttributeValues = vehicleAttributeValues;
	}
	
	public InterpretationRule getInterpretationRule() {
		return interpretationRule;
	}

	public void setInterpretationRule(InterpretationRule interpretationRule) {
		this.interpretationRule = interpretationRule;
	}

}
