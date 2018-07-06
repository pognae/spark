package com.wowpmd.taglib.model;

public class ParameterImpl implements Parameter {


	/**
     * KEY
     */
    private String key;

    /**
     * VALUE
     */
    private String value;

    /**
     * key, value 기본값 세팅
     *
     * @param key
     * @param value
     */
    public ParameterImpl(String key, String value) {
        super();
        this.key = key;
        this.value = value;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
   	public String toString() {
   		return "ParameterImpl [key=" + key + ", value=" + value + "]";
   	}

}