package com.clouderwork.enums;

public enum YESNOEnum {

    YES(1, "是"),

    NO(0, "否");

    private final int value;
    private final String text;

    YESNOEnum(int  value, String text) {
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
    public static YESNOEnum getByValue(int  value) {
        for (YESNOEnum temp : YESNOEnum.values()) {
            if (temp.getValue() == value ) {
                return temp;
            }
        }
        return null;
    }
}
