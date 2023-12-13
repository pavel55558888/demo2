package com.example.demo.models.redactor;

import jakarta.persistence.*;

@Entity
@Table(name = "Therapist")
public class Therapist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "fio")
    private String fio;
    @Column(name = "phone")
    private String tel;
    @Column(name = "login")
    private String login;
    @Column(name = "date")
    private String date;

    public Therapist(String fio, String tel, String login, String date) {
        this.fio = fio;
        this.tel = tel;
        this.login = login;
        this.date = date;
    }

    public Therapist() {
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Traumatologist{" +
                "fio='" + fio + '\'' +
                ", tel='" + tel + '\'' +
                ", login='" + login + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
