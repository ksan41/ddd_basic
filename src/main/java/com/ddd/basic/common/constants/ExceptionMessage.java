package com.ddd.basic.common.constants;

public enum ExceptionMessage {
    NOT_VALID_ARGUMENT_LENGTH(""),
    NOT_FOUND_USER("찾을 수 없는 사용자 입니다."),
    NOT_FOUND_CIRCLE("서클을 찾을 수 없었습니다."),
    NOT_VALID_USER_NAME("사용자 이름이 올바르지 않습니다. *2~10자의 한글 입력"),
    NOT_VALID_CIRCLE_NAME("서클 이름이 올바르지 않습니다. *2~20자 입력"),
    FULL_CIRCLE_MEMBERS("서클 가입 허용 인원을 초과합니다."),
    DUPLICATED_CIRCLE_NAME("서클명이 중복되었습니다.");

    private String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
                return this.message;
    }


}

