package com.bitwormhole.swissknife;

public abstract class AbstractAction implements KnifeAction {

	private PropertyTable properties;

	public PropertyTable getProperties() {
		return properties;
	}

	public void setProperties(PropertyTable properties) {
		this.properties = properties;
	}

}
