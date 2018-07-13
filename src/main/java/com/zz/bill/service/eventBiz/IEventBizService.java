package com.zz.bill.service.eventBiz;

import com.zz.bill.model.JsonResult;
import com.zz.bill.model.event.EventInfo;
import com.zz.bill.model.event.EventPayloadData;

public interface IEventBizService {

    JsonResult createEvent(EventInfo eventInfo, Integer creatorUid);
    JsonResult joinWithScan(Integer eventId, Integer joinUid);
    JsonResult joinWithInvite(Integer eventId, Integer joinUid);
    JsonResult removeUserFromEvent(Integer eventId, Integer removeUid);
}
