package com.zz.bill.model.event;


// 选择在 mysql里 用tinyint 而不是使用 enum
// 因为 if column is not null then it produces first value of enum. else If column is null then produces null.
// It would cause misunderstanding If you set a column with enum('0','1','2','3','4','5').

public enum EventStatus {
    DONE (0),//等同于 0
    IN_PROGRESS (1);//等同于1

    private int value;

    private EventStatus(int value){
        this.value = value;
    }
}
