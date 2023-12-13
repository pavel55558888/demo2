package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;//Имя
    private String patronymic;//Фамилия
    private String surname;//Отчество
    private String post;//Должность
    private String experience;//Стаж
    private String img;//Фото
    private String fullInformation;//полная информация

    public Staff(String name, String patronymic, String surname, String post, String experience, String img, String fullInformation) {
        this.name = name;
        this.patronymic = patronymic;
        this.surname = surname;
        this.post = post;
        this.experience = experience;
        this.img = img;
        this.fullInformation = fullInformation;
    }

    public Staff() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getFullInformation() {
        return fullInformation;
    }

    public void setFullInformation(String fullInformation) {
        this.fullInformation = fullInformation;
    }
}
