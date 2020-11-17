package com.ftg.kutuphane.enums;

public enum StateCode {
    SUCCESS(1),
    INFO(2),
    WARNING(3),
    ERROR(4);

    private final int stateCode;

    StateCode(int stateCode) {
        this.stateCode = stateCode;
    }

    public int getStateCode() {
        return stateCode;
    }
}
