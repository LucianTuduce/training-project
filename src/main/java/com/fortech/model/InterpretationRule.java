package com.fortech.model;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonManagedReference;

import java.util.List;

/**
 * The persistent class for the "InterpretationRule" database table.
 * 
 */
@Entity
@Table(name = "\"InterpretationRule\"", schema="DARIAD")
@NamedQuery(name = "InterpretationRule.findAll", query = "SELECT i FROM InterpretationRule i")
public class InterpretationRule{
	
	public static final String FIND_ALL_INTERPRETATION_RULE = "InterpretationRule.findAll";
	
	@Id
	@Column(name = "\"id\"", unique = true, nullable = false)
	private int id;

	// bi-directional many-to-one association to TargetVehicle
	@OneToMany(fetch=FetchType.EAGER, mappedBy = "interpretationRule", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<TargetVehicle> targetVehicles;

	// bi-directional many-to-one association to InterpretationInnerRule
	@OneToMany(fetch=FetchType.EAGER, mappedBy = "interpretationRule", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<InterpretationInnerRule> interpretationInnerRules;

	public InterpretationRule() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<TargetVehicle> getTargetVehicles() {
		return this.targetVehicles;
	}

	public void setTargetVehicles(List<TargetVehicle> targetVehicles) {
		this.targetVehicles = targetVehicles;
	}

	public TargetVehicle addTargetVehicle(TargetVehicle targetVehicle) {
		getTargetVehicles().add(targetVehicle);
		targetVehicle.setInterpretationRule(this);

		return targetVehicle;
	}

	public TargetVehicle removeTargetVehicle(TargetVehicle targetVehicle) {
		getTargetVehicles().remove(targetVehicle);
		targetVehicle.setInterpretationRule(null);

		return targetVehicle;
	}

	public List<InterpretationInnerRule> getInterpretationInnerRules() {
		return this.interpretationInnerRules;
	}

	public void setInterpretationInnerRules(
			List<InterpretationInnerRule> interpretationInnerRules) {
		this.interpretationInnerRules = interpretationInnerRules;
	}

	public InterpretationInnerRule addInterpretationInnerRule(
			InterpretationInnerRule interpretationInnerRule) {
		getInterpretationInnerRules().add(interpretationInnerRule);
		interpretationInnerRule.setInterpretationRule(this);

		return interpretationInnerRule;
	}

	public InterpretationInnerRule removeInterpretationInnerRule(
			InterpretationInnerRule interpretationInnerRule) {
		getInterpretationInnerRules().remove(interpretationInnerRule);
		interpretationInnerRule.setInterpretationRule(null);

		return interpretationInnerRule;
	}

}