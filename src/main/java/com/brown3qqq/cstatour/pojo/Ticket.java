package com.brown3qqq.cstatour.pojo;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Document(collection = "ticket")
public class Ticket {
    private String id;
    private Date expired;
    private int status;// 0有效，1无效
    private String ticket;

    public Ticket(){}
    public Ticket(String id, Date expired) {
        this.id = id;
        this.expired = expired;
    }

    public Ticket(String id, Date expired, int status, String ticket) {
        this.id = id;
        this.expired = expired;
        this.status = status;
        this.ticket = ticket;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}
