package com.fortech.model;

import javax.persistence.*;


/**
 * The persistent class for the "MappingRule" database table.
 * 
 */
@Entity
@Table(name="\"MappingRule\"", schema="DARIAD")
@NamedQuery(name="MappingRule.findAll", query="SELECT m FROM MappingRule m")
public class MappingRule extends BaseRuleModel {
	private static final long serialVersionUID = 1L;
	
	public static final String FIND_ALL_MAPPING_RULE = "MappingRule.findAll";

	@Id
	@Column(name="\"id\"", unique=true, nullable=false)
	private int id;

	@Column(name="\"sourceValue\"", length=45)
	private String sourceValue;

	@Column(name="\"targetValue\"", length=45)
	private String targetValue;

	@Column(name="\"vehicleAttribute\"", length=45)
	private String vehicleAttribute;

	public MappingRule() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSourceValue() {
		return this.sourceValue;
	}

	public void setSourceValue(String sourceValue) {
		this.sourceValue = sourceValue;
	}

	public String getTargetValue() {
		return this.targetValue;
	}

	public void setTargetValue(String targetValue) {
		this.targetValue = targetValue;
	}

	public String getVehicleAttribute() {
		return this.vehicleAttribute;
	}

	public void setVehicleAttribute(String vehicleAttribute) {
		this.vehicleAttribute = vehicleAttribute;
	}
}