package com.zz.bill.model.event;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;


@Data
@Builder
public class EventInfo {


    private Integer id;

    @NotNull(message = "event name cannot be null!! ")
    private String eventName;

    private EventStatus status;

    private Integer creatorUid;


}
