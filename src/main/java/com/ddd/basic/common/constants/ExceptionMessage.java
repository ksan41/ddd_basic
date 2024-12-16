package com.ddd.basic.common.constants;

public enum ExceptionMessage {
    NOT_FOUND_USER("찾을 수 없는 사용자 입니다.", 404),
    NOT_FOUND_CIRCLE("서클을 찾을 수 없었습니다.", 404),
    NOT_FOUND_CIRCLE_INVITATION("서클 초대 정보를 찾을 수 없었습니다.", 404),
    NOT_VALID_USER_NAME("사용자 이름이 올바르지 않습니다. *2~10자의 한글 입력", 400),
    NOT_VALID_USER_PASSWORD("사용자 비밀번호가 올바르지 않습니다. *6자 이상 20자 이하의 숫자, 영문자", 400),
    AUTHENTICATION_FAILED("접근 권한이 없습니다.", 403),
    NOT_VALID_CIRCLE_NAME("서클 이름이 올바르지 않습니다. *2~20자 입력", 400),
    FULL_CIRCLE_MEMBERS("서클 가입 허용 인원을 초과합니다.", 409),
    DUPLICATED_CIRCLE_NAME("서클명이 중복되었습니다.", 409),
    DUPLICATED_USER_EMAIL("이미 가입된 이메일 주소입니다.", 409);

    private String message;
    private int status;

    ExceptionMessage(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
                return this.message;
    }

    public int getStatus() {return this.status;}

}

