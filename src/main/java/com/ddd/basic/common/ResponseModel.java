package com.ddd.basic.common;

import com.ddd.basic.common.constants.ExceptionMessage;

public abstract class ResponseModel {
    protected String message;
    protected int status;

    public void success() {
        this.status = 200;
    }
    public void created() {this.status = 201;}
    public void error(ExceptionMessage exception) {
        this.status = exception.getStatus();
        this.message = exception.getMessage();
    }
    public void error(String message, int status) {
        this.status = status;
        this.message = message;
    }
    public abstract void successWithResult(Object result);
}