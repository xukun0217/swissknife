package com.bitwormhole.swissknife;

import java.util.Map;

public class PropertyTable {

	private Map<String, String> table;

	public PropertyTable() {
	}

	public Map<String, String> getTable() {
		return table;
	}

	public void setTable(Map<String, String> table) {
		this.table = table;
	}

	public String getRequiredProperty(String key) {
		String value = table.get(key);
		if (value == null) {
			String fmt = "need property [%s] .";
			String msg = String.format(fmt, key);
			throw new RuntimeException(msg);
		}
		return value;
	}

}
