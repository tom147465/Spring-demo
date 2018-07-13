package com.zz.bill.model.account;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfo {
    private Integer uid;
    private String nickName;
    private String account;
    private String authToken;
    private String avatar;
}
