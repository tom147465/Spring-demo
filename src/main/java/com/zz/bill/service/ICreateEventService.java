package com.zz.bill.service;

import com.zz.bill.model.JsonResult;
import com.zz.bill.model.event.EventInfo;

public interface ICreateEventService {
    JsonResult createEvent(EventInfo eventInfo, Integer creatorUid);
}
