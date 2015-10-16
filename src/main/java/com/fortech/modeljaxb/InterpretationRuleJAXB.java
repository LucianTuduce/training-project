package com.fortech.modeljaxb;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import com.fortech.model.InterpretationInnerRule;
import com.fortech.model.TargetVehicle;

/**
 * The JAXB equivalent of the InterpretationRule. This class is used in order to
 * get the XML form of the class.
 * 
 * @author lucian.tuduce
 *
 */
@XmlRootElement(name = "InterpretationRuleJAXB")
@XmlAccessorType(XmlAccessType.FIELD)
public class InterpretationRuleJAXB {

	@XmlElement(required = true)
	private int id;

	@XmlElement(name = "target-Vehicles")
	private List<TargetVehicle> targetVehicles;

	@XmlElement(name = "interpretation-Inner-Rules")
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
