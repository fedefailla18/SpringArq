package com.helper.work.courses.completablefuture.demo.model;

public class EmailConcurrency {

    private Long id;

    private String name;

    public EmailConcurrency(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserConcurrency{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
