package com.zz.bill.model.event;

public enum  SpendStatus {
    DEPRECATED(0),
    ACTIVE(1),
    CLOSE(2);

    private int value;

    private SpendStatus(int value){
        this.value = value;
    }

}