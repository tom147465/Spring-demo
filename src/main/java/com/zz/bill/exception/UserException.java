package com.zz.bill.exception;


public class UserException extends Exception {

    public UserException(){
        super("User Exception");
    }
    public UserException(String msg){
        super(msg);
    }
}
