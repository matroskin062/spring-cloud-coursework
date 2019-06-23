package com.client.client.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Authorities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public Authorities(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }

    public Authorities() {
    }

    private String username;
    private String authority;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
