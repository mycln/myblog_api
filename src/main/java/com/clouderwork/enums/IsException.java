package com.clouderwork.enums;

public enum IsException {

    NOEXCEPTION(0, "无异常"),

    EXCEPTION(1, "异常");


    private final int value;
    private final String text;

    IsException(int  value, String text) {
        this.value = value;
        this.text = text;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * Gets text.
     *
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * Gets by value.
     *
     * @param value the value
     * @return the by value
     */
    public static IsException getByValue(int  value) {
        for (IsException temp : IsException.values()) {
            if (temp.getValue() == value ) {
                return temp;
            }
        }
        return null;
    }
}
