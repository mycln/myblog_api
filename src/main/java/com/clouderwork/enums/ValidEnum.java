package com.clouderwork.enums;

public enum ValidEnum {

    VALID(1, "有效"),

    INVALID(0, "无效");

    private final int value;
    private final String text;

    ValidEnum(int  value, String text) {
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
    public static ValidEnum getByValue(int  value) {
        for (ValidEnum temp : ValidEnum.values()) {
            if (temp.getValue() == value ) {
                return temp;
            }
        }
        return null;
    }
}
