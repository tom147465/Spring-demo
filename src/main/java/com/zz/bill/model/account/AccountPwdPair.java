package com.zz.bill.model.account;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AccountPwdPair {
    @NotNull(message = "Account cannot be Null !")
    String account;
    @NotNull(message = "Password cannot be Null !")
    String pwd;
}
