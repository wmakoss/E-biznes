package org.example.zadanie8.entity;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "sessions")
public class Session {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    Integer id;

    @Column
    String uuid;

    @Column
    Integer userId;

    @Column
    String expirationDatetime;

    public Session() {}

    public Session(String uuid, Integer userId, String expirationDatetime) {
        this.uuid = uuid;
        this.userId = userId;
        this.expirationDatetime = expirationDatetime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getExpirationDatetime() {
        return expirationDatetime;
    }

    public void setExpirationDatetime(String expirationDatetime) {
        this.expirationDatetime = expirationDatetime;
    }
}
