package com.clouderwork.enums;

public enum ResultCodeEnum {

    OK(0, "ok") ,
    ERROR(1, "error"),
    UNAUTHORIZED(401, "请登录后再试!");

    private final int value;
    private final String text;

    ResultCodeEnum(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public int getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

    public static ResultCodeEnum getByValue(int  value) {
        for (ResultCodeEnum temp : ResultCodeEnum.values()) {
            if (temp.getValue() == value ) {
                return temp;
            }
        }
        return null;
    }

    public static ResultCodeEnum getByText(String  text) {
        for (ResultCodeEnum temp : ResultCodeEnum.values()) {
            if (temp.getText().equals(text)) {
                return temp;
            }
        }
        return null;
    }
}
