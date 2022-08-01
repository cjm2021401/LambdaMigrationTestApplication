package com.whatap.SpringLambda.Domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Member {
    @Id
    private String email;
    private String name;
    private int count=0;

    public Member(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public Member() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
