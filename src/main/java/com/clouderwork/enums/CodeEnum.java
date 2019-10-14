package com.clouderwork.enums;

public enum CodeEnum {

    EFFECTIVE(1, "验证码有效"),

    FAILURE(2, "验证码失效");


    private final int value;
    private final String text;

    CodeEnum(int  value, String text) {
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
    public static CodeEnum getByValue(int  value) {
        for (CodeEnum temp : CodeEnum.values()) {
            if (temp.getValue() == value ) {
                return temp;
            }
        }
        return null;
    }
}
