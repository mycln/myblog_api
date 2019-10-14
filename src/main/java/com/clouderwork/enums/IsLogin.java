package com.clouderwork.enums;

public enum IsLogin {

    NOLOGIN(0, "未登录"),

    LOGIN(1, "登录");


    private final int value;
    private final String text;

    IsLogin(int  value, String text) {
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
    public static IsLogin getByValue(int  value) {
        for (IsLogin temp : IsLogin.values()) {
            if (temp.getValue() == value ) {
                return temp;
            }
        }
        return null;
    }
}
