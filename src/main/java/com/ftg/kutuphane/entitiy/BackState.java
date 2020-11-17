package com.ftg.kutuphane.entitiy;

import com.ftg.kutuphane.enums.StateCode;

public class BackState {

    private int stateCode;

    private String message;

    public BackState(StateCode stateCode, String message) {
        this.stateCode = stateCode.getStateCode();
        this.message = message;
    }

    public BackState() {

    }

    public int getStateCode() {
        return stateCode;
    }

    public void setStateCode(StateCode stateCode) {
        this.stateCode = stateCode.getStateCode();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
