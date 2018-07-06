package com.wowpmd.taglib.model;

public class Code implements Parameter {
	private String valueCode;

	private String valueName;

    public String getValueName() {
		return valueName;
	}

	public void setValueName(String valueName) {
		this.valueName = valueName;
	}

	public String getValueCode() {
		return valueCode;
	}

	public void setValueCode(String valueCode) {
		this.valueCode = valueCode;
	}

	@Override
    public String getKey() {
        return this.getValueCode();
    }

    @Override
    public String getValue() {
        return this.getValueName();
    }
}