package com.zz.bill.controller;

import com.zz.bill.CommonCode;
import com.zz.bill.entity.User;
import com.zz.bill.model.JsonResult;
import com.zz.bill.model.event.EventInfo;
import com.zz.bill.service.eventBiz.IEventBizService;
import com.zz.bill.util.CurrentUserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Currency;

@RestController
@RequestMapping("/internal/event/{version}")
public class EventController {

    @Autowired
    private IEventBizService eventBizService;

    @RequestMapping(value = "/create/", method = RequestMethod.POST)
    public JsonResult createEvent(@RequestBody @Valid EventInfo eventInfo, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            String msg = bindingResult.getAllErrors().get(0).getDefaultMessage();
            return JsonResult.builder().code(CommonCode.FAIL).msg(msg).data(false).build();
        }

        //todo threadLocal  拿操作人
        User currentUser = CurrentUserHolder.getCurrentUser();

        return eventBizService.createEvent(eventInfo, currentUser.getId());
        /*
           1.创建Event
               1.1 eventInfo应该包含Event所必须的数据
           2.创建初始的EventUserRelation
               2.1 eventInfo应该包含相关用户的uid的List

           返回对象
               Data
                   eventInfo //event的基本信息
                   List<eventUserRel> 默认的参与人员列表
        */
    }

    // todo add joinEventWithScan

    public JsonResult joinEvent(Integer eventId,
                                Integer joinUid){
        User currentUser = CurrentUserHolder.getCurrentUser();

        if(joinUid.equals(currentUser.getId()))
            return eventBizService.joinWithScan(eventId, joinUid);

        else return eventBizService.joinWithInvite(eventId, joinUid);

       /*
           1.通过ThreadLocal去拿操作人，是谁在操作这个请求
               1.1 群主主动加人    //             !=
               1.2 我扫码主动添加  // threadlocal == joinId
                    1.2.1 check eventStatus  --> ok join

           返回对象
               Data
                   eventInfo //event的基本信息
                   List<eventUserRel> 默认的参与人员列表
               Data
                   TRUE/FALSE
        */
    }

    public JsonResult removeUserFromEvent(Integer eventId,
                                          Integer removeUid){
        if(removeUid.equals(CurrentUserHolder.getCurrentUser().getId()))
            return JsonResult.builder().code(CommonCode.FAIL).msg("cannot remove yourself!").data(false).build();

        return eventBizService.removeUserFromEvent(eventId, removeUid);
       /*
           1.通过ThreadLocal去拿操作人，是谁在操作这个请求
               1.1 判断权限
               1.2 用户是否在群里
                   操作
           返回对象
               Data
                   eventInfo //event的基本信息
                   List<eventUserRel> 默认的参与人员列表
               Data
                   TRUE/FALSE
        */
    }
}