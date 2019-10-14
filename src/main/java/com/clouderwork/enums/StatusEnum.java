package com.clouderwork.enums;

public enum StatusEnum {


    INUSE(1, "1上架、启用 正常"),

    UNUSE(2, "下架、停用 冻结"),

    DEL(3, "删除");

    private final int value;
    private final String text;

    StatusEnum(int  value, String text) {
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
    public static StatusEnum getByValue(int  value) {
        for (StatusEnum temp : StatusEnum.values()) {
            if (temp.getValue() == value ) {
                return temp;
            }
        }
        return null;
    }
}
