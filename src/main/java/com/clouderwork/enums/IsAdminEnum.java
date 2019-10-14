package com.clouderwork.enums;

public enum IsAdminEnum {

    isAdmin(1, "是admin"),

    noAdmin(0, "非admin");

    private final int value;
    private final String text;

    IsAdminEnum(int  value, String text) {
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
    public static IsAdminEnum getByValue(int  value) {
        for (IsAdminEnum temp : IsAdminEnum.values()) {
            if (temp.getValue() == value ) {
                return temp;
            }
        }
        return null;
    }
}
