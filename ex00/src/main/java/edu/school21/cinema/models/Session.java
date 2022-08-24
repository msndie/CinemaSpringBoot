package edu.school21.cinema.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Session implements Serializable {
    private Long id;
    private Timestamp date_time;
    private String ip;
    private Long user_id;

    public Session() {}

    public Session(Timestamp timestamp, String ip, Long user_id) {
        this.date_time = timestamp;
        this.ip = ip;
        this.user_id = user_id;
        this.id = null;
    }

    public Timestamp getDate_time() {
        return date_time;
    }

    public void setDate_time(Timestamp date_time) {
        this.date_time = date_time;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getFormatedDate() {
        return new SimpleDateFormat("MMMM dd, yyyy").format(date_time);
    }

    public String getFormatedTime() {
        return new SimpleDateFormat("HH:mm").format(date_time);
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", timestamp=" + date_time +
                ", ip='" + ip + '\'' +
                ", user_id=" + user_id +
                '}';
    }
}
