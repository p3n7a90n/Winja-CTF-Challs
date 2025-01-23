package com.winja.domain;

import java.util.Set;

public enum Role {


    SUPERADMIN(0,"Read,Write,Modify"),
    ADMIN(1,"Read,Write"),
    USER(2,"Read");

    private int id;
    private String desc;

    private Role(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }
}
