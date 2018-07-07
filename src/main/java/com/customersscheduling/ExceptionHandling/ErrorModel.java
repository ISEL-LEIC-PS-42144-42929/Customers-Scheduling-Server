package com.customersscheduling.ExceptionHandling;

public class ErrorModel {

    private final String title;
    private final String detail;
    private final String instance;

    public ErrorModel(String title, String detail, String instance) {
        this.title = title;
        this.detail = detail;
        this.instance = instance;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    public String getInstance() {
        return instance;
    }
}
