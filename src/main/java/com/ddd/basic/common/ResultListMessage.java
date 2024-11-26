package com.ddd.basic.common;

import java.util.List;

public class ResultListMessage<T> extends ResponseModel{
    private List<T> result;
    @Override
    public void successWithResult(Object result) {
        this.result = (List<T>) result;
        this.status = 200;
    }
}
