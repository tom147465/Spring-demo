package com.zz.bill.controller;


import com.zz.bill.CommonCode;
import com.zz.bill.entity.User;
import com.zz.bill.model.JsonResult;
import com.zz.bill.model.event.EventInfo;
import com.zz.bill.service.eventBiz.IEventBizService;
import com.zz.bill.util.CurrentUserHolder;
import com.zz.bill.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/internal/event")
public class EventController {

    @Autowired
    private IEventBizService eventBizService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public JsonResult createEvent(@RequestBody @Valid EventInfo eventInfo, BindingResult bindingResult){

//        if(bindingResult.hasErrors()){
//            String msg = bindingResult.getAllErrors().get(0).getDefaultMessage();
//            return JsonResult.builder().code(CommonCode.FAIL).msg(msg).data(false).build();
//        }
        if(StringUtils.isEmpty(eventInfo.getEventName()))
            return JsonResult.builder()
                    .code(CommonCode.PARAM_ERR)
                    .msg("parameters error")
                    .data(false).build();

        User currentUser = CurrentUserHolder.getCurrentUser();

        return eventBizService.createEvent(eventInfo, currentUser.getId());
    }

    @RequestMapping(value = "/{eventId}/qr", method = RequestMethod.GET)
    public String getQrCode(@PathVariable Integer eventId){
        return Utils.encodeQr(eventId);
    }


    @RequestMapping(value = "/invite/qr/{code}", method = RequestMethod.POST)
    public JsonResult joinWithQr(@PathVariable String code){

        Integer eventId = Utils.decodeQr(code);
        return eventBizService.joinWithScan(eventId, CurrentUserHolder.getCurrentUser().getId());
    }

    @RequestMapping(value = "/invite/creator/{eventId}/{userIds}", method = RequestMethod.POST)
    public JsonResult joinEvent(@PathVariable Integer eventId,
                                @PathVariable List<Integer> userIds){

        return eventBizService.joinWithInvite(eventId, userIds);
    }

    @RequestMapping(value = "/internal/event/{eventId}/kickout/{userId}", method = RequestMethod.POST)
    public JsonResult removeUserFromEvent(@PathVariable Integer eventId,
                                          @PathVariable Integer removeUid){
        if(removeUid.equals(CurrentUserHolder.getCurrentUser().getId()))
            return JsonResult.builder().code(CommonCode.FAIL).msg("cannot remove yourself!").data(false).build();

        return eventBizService.removeUserFromEvent(eventId, removeUid);
    }

    @PostMapping(value = "/internal/event/spends/{eventId}")
    JsonResult getSpendShareInfoInEvent(@PathVariable Integer eventId){
        Integer currencyUid = CurrentUserHolder.getCurrentUser().getId();

        return eventBizService.getSpendShareInfoInEvent(eventId, currencyUid);
    }
}