package com.fortech.convertor;

import com.fortech.enums.StockCategory;

/**
 * Class that helps convert the MarketRule entity with the correct data data
 * type. That we use this because when we generate the entities from the
 * database, the data type of some fields are not the ones that we want soo in
 * order to obtain the correct data type of the we need a converter class.
 * 
 * @author dariad && @author lucian.tuduce (with the javadoc)
 *
 */
public class MarketRuleFlattener {

	/**
	 * Method that helps convert the MarketRule stock category from short to
	 * enumeration.
	 * 
	 * @param rule
	 *            the category type, 0 for NEW and 1 for used
	 * @return the converted form of the "active" variable
	 */
	public StockCategory changeShortToEnum(short rule) {

		if (rule == 0) {
			return StockCategory.NEW;
		}

		if (rule == 1) {
			return StockCategory.USED;
		}

		return null;

	}

	/**
	 * Method that helps convert the MarketRule stock category back from
	 * enumeration to short.
	 * 
	 * @param stock
	 *            the stock category type of the object that will be converted
	 *            to the new type
	 * @return the converted form of the "active" variable
	 */
	public Short changeEnumToShort(StockCategory stock) {

		if (stock == StockCategory.NEW) {
			return 0;
		}

		if (stock == StockCategory.USED) {
			return 1;
		}

		return null;
	}

	/**
	 * Method that helps convert the MarketRule "active" variable from short to
	 * boolean.
	 * 
	 * @param rule
	 *            the rule , 0 for false and 1 for true
	 * @return the converted form of the "active" variable
	 */

	public boolean changeShortToBoolean(short rule) {

		if (rule == 0) {
			return false;
		}

		if (rule == 1) {
			return true;
		}

		return false;
	}

	/**
	 * Method that helps convert the MarketRuleFlatted "active" variable from
	 * boolean to short.
	 * 
	 * @param rule
	 *            the rule , 0 for false and 1 for true
	 * @return the converted form of the "active" variable
	 */
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
