package com.fortech.modeljaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fortech.enums.StockCategory;
import com.fortech.model.MarketRulePK;

/**
 * The JAXB equivalent of the MarketRule. This class is used in order to get the
 * XML form of the class.
 * 
 * @author lucian.tuduce
 *
 */
@XmlRootElement(name = "MarketRuleJAXB")
@XmlAccessorType(XmlAccessType.FIELD)
public class MarketRuleJAXB {

	@XmlElement(required = true)
	private String countryNumber;

	@XmlElement(required = true)
	private int branch;

	@XmlElement(required = true)
	private StockCategory stockCategory;

	@XmlElement(required = true)
	private boolean active;

	@XmlElement(required = true)
	private String rule;

	@XmlElement(required = true)
	private MarketRulePK id;
	
	public MarketRulePK getId() {
		return id;
	}

	public void setId(MarketRulePK id) {
		this.id = id;
	}

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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	@Override
	public String toString() {
		return "[ " + countryNumber + " " + branch + " " + stockCategory + " "
				+ active + " " + rule + " ]";
	}

}
