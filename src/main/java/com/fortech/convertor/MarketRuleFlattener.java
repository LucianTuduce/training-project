package com.fortech.convertor;

import com.fortech.enums.StockCategory;
/**
 * 
 * @author dariad
 *
 */
public class MarketRuleFlattener {

	public StockCategory changeShortToEnum(short rule) {

		if (rule == 0) {
			return StockCategory.NEW;
		}

		if (rule == 1) {
			return StockCategory.USED;
		}

		return null;

	}

	public Short changeEnumToShort(StockCategory stock) {

		if (stock == StockCategory.NEW) {
			return 0;
		}

		if (stock == StockCategory.USED) {
			return 1;
		}

		return null;
	}

	public boolean changeShortToBoolean(short rule) {

		if (rule == 0) {
			return false;
		}

		if (rule == 1) {
			return true;
		}

		return false;
	}

	public short changeBooleanToShort(boolean rule) {

		if (rule == false) {
			return 0;
		}

		if (rule == true) {
			return 1;
		}

		return 0;
	}
}
