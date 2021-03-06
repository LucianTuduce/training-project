package com.fortech.model;

import com.fortech.enums.StockCategory;

/**
 * The equivalent class for the MarketRule class, with the difference of the
 * stockCategory type which is of type Enumeration and of active which is of
 * type Boolean.
 * 
 * @author dariad
 *
 */
public class MarketRuleFlatted {
	private String countryNumber;
	private int branch;
	private StockCategory stockCategory;
	private Boolean active;
	private String rule;

	public String getCountryNumber() {
		return countryNumber;
	}

	public void setCountryNumber(String countryNumber) {
		this.countryNumber = countryNumber;
	}

	public int getBranch() {
		return branch;
	}

	public void setBranch(int branch) {
		this.branch = branch;
	}

	public StockCategory getStockCategory() {
		return stockCategory;
	}

	public void setStockCategory(StockCategory stockCategory) {
		this.stockCategory = stockCategory;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}
}
