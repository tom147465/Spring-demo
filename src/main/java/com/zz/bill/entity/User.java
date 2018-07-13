package com.zz.bill.entity;

import lombok.Data;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "Account cannot be Null!")
    @Column(name = "account", unique = true)
    private String account;

    @NotNull(message = "Password cannot be Null!")
    @Column(name = "pwd")
    private String pwd;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;



    @PrePersist
    public void prePersist(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        createdAt = timestamp;
        updatedAt = timestamp;
    }

    @PreUpdate
    public void preUpdate(){
        updatedAt = new Timestamp(System.currentTimeMillis());
    }
}
