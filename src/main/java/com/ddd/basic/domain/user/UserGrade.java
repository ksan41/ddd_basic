package com.ddd.basic.domain.user;

public enum UserGrade {
    NORMAL("normal"),
    PREMIUM("premium");

    private String grade;

    UserGrade(String grade) { this.grade = grade;}

    public String getGrade() {
        return this.grade;
    }
}
