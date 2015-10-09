package com.fortech.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the "InterpretationInnerRule" database table.
 * 
 */
@Entity
@Table(name = "\"InterpretationInnerRule\"", schema="DARIAD")
@NamedQuery(name = "InterpretationInnerRule.findAll", query = "SELECT i FROM InterpretationInnerRule i")
public class InterpretationInnerRule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "\"id\"", unique = true, nullable = false)
	private int id;

	@Column(name = "\"ruleId\"", nullable = false)
	private int ruleId;

	@Column(name = "\"vehicleAttribute\"", length = 45)
	private String vehicleAttribute;

	@Column(name = "\"vehicleAttributeValues\"", length = 45)
	private String vehicleAttributeValues;

	// bi-directional many-to-one association to InterpretationRule
	@ManyToOne
	@JoinColumn(name = "\"id\"", insertable=false, updatable=false)
	private InterpretationRule interpretationRule;

	public InterpretationInnerRule() {
	}

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