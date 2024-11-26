package com.ddd.basic.common;


public class ResultMessage<T> extends ResponseModel{

    private T result;

    @Override
    public void successWithResult(Object result) {
        this.result = (T) result;
        this.status = 200;
    }
}
