package com.fortech.modeljaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fortech.enums.StockCategory;

@XmlRootElement(name = "MarketRuleIdJAXB")
@XmlAccessorType(XmlAccessType.FIELD)
public class MarketRuleIdJAXB {

	@XmlElement(required = true)
	private String countryNumber;

	@XmlElement(required = true)
	private int branch;

	@XmlElement(required = true)
	private StockCategory stockCategory;

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

	
}
