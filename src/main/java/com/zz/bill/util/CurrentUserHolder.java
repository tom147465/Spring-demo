package com.zz.bill.util;

import com.zz.bill.entity.User;

public class CurrentUserHolder {


    private static ThreadLocal<User> USER_HOLDER = new ThreadLocal<User>();

    public static User getCurrentUser(){
            return USER_HOLDER.get();
        }
    public static void setCurrentUser(User user){
            USER_HOLDER.set(user);
        }
}
