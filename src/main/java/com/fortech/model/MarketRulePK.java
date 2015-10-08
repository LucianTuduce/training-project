package com.fortech.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the "MarketRule" database table.
 * 
 */
@Embeddable
public class MarketRulePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="\"countryNumber\"", unique=true, nullable=false, length=45)
	private String countryNumber;

	@Column(name="\"branch\"", unique=true, nullable=false)
	private int branch;

	@Column(name="\"stockCategory\"", unique=true, nullable=false)
	private short stockCategory;

	public MarketRulePK() {
	}
	public String getCountryNumber() {
		return this.countryNumber;
	}
	public void setCountryNumber(String countryNumber) {
		this.countryNumber = countryNumber;
	}
	public int getBranch() {
		return this.branch;
	}
	public void setBranch(int branch) {
		this.branch = branch;
	}
	public short getStockCategory() {
		return this.stockCategory;
	}
	public void setStockCategory(short stockCategory) {
		this.stockCategory = stockCategory;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MarketRulePK)) {
			return false;
		}
		MarketRulePK castOther = (MarketRulePK)other;
		return 
			this.countryNumber.equals(castOther.countryNumber)
			&& (this.branch == castOther.branch)
			&& (this.stockCategory == castOther.stockCategory);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.countryNumber.hashCode();
		hash = hash * prime + this.branch;
		hash = hash * prime + ((int) this.stockCategory);
		
		return hash;
	}
}