package com.fortech.modeljaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "MappingRuleJAXB")
@XmlAccessorType(XmlAccessType.FIELD)
public class MappingRuleJAXB {

	@XmlElement(required = true)
	private int id;

	@XmlElement(required = true)
	private String sourceValue;
	
	@XmlElement(required = true)
	private String targetValue;

	@XmlElement(required = true)
	private String vehicleAttribute;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSourceValue() {
		return sourceValue;
	}

	public void setSourceValue(String sourceValue) {
		this.sourceValue = sourceValue;
	}

	public String getTargetValue() {
		return targetValue;
	}

	public void setTargetValue(String targetValue) {
		this.targetValue = targetValue;
	}

	public String getVehicleAttribute() {
		return vehicleAttribute;
	}

	public void setVehicleAttribute(String vehicleAttribute) {
		this.vehicleAttribute = vehicleAttribute;
	}
	
	@Override
	public String toString() {
		return "[ "+id+" "+vehicleAttribute+" "+sourceValue+" "+targetValue+" ]";
	}


}
