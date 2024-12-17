package com.ddd.basic.api;

import com.ddd.basic.application.user.*;
import com.ddd.basic.common.ResponseModel;
import com.ddd.basic.common.ResultListMessage;
import com.ddd.basic.common.ResultMessage;
import com.ddd.basic.common.constants.ExceptionMessage;
import com.ddd.basic.domain.model.user.IUserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("user")
@RestController()
@Tag(name = "User", description = "사용자 관련 api")
public class UserController {

    private final UserApplicationService userApplicationService;
    private final IUserRepository userRepository;

    @Operation(summary = "사용자 로그인")
    @PostMapping("login")
    public ResponseModel login(@RequestBody UserLoginDto loginInfo) {
        ResponseModel res = new ResultMessage<>();
        try {
            String token = userApplicationService.login(loginInfo);
            res.successWithResult(token);
        } catch(NullPointerException e) {
            log.error(ExceptionMessage.NOT_FOUND_USER.getMessage(), e);
            res.error(ExceptionMessage.NOT_FOUND_USER);
        } catch(IllegalIdentifierException e) {
            log.error(ExceptionMessage.AUTHENTICATION_FAILED.getMessage(), e);
            res.error(ExceptionMessage.AUTHENTICATION_FAILED);
        }
        return res;
    }

    @Operation(summary = "사용자 전체 조회")
    @GetMapping()
    public ResponseModel getAll() {
        ResponseModel res = new ResultListMessage<>();
        try {
            List<UserViewDto> users = userRepository.findAll().stream()
                            .map(UserViewDto::new)
                            .collect(Collectors.toList());
            res.successWithResult(users);
        } catch (NullPointerException e) {
            log.error(ExceptionMessage.NOT_FOUND_USER.getMessage(), e);
            res.error(ExceptionMessage.NOT_FOUND_USER);
        }
        return res;
    }

    @Operation(summary = "사용자 단일 조회")
    @GetMapping("{id}")
    public ResponseModel get(@PathVariable("id") Long userId) {
        ResponseModel res = new ResultMessage<>();
        try {
            UserViewDto user = new UserViewDto(userRepository.find(userId).orElseThrow(NullPointerException::new));
            res.successWithResult(user);
        }  catch (NullPointerException e) {
            log.error(ExceptionMessage.NOT_FOUND_USER.getMessage(), e);
            res.error(ExceptionMessage.NOT_FOUND_USER);
        }
        return res;
    }

    @Operation(summary = "사용자 등록", description = "사용자 등록(가입)을 진행합니다.")
    @PostMapping()
    public ResponseModel post(@RequestBody UserPostDto postUser) {
        ResponseModel res = new ResultMessage<>();
        try {
            Long userId = userApplicationService.register(postUser);
            res.successWithResult(userId);
        } catch (IllegalIdentifierException e) {
            log.error(ExceptionMessage.DUPLICATED_USER_EMAIL.getMessage(), e);
            res.error(ExceptionMessage.DUPLICATED_USER_EMAIL);
        }
        return res;
    }

    @Operation(summary = "사용자 정보 수정", description = "입력된 비밀번호가 일치할 경우 사용자의 일부 정보를 수정합니다.")
    @PutMapping("{id}")
    public ResponseModel put(@PathVariable("id") Long userId, @RequestBody UserPutDto putUserInfo) {
        ResponseModel res = new ResultMessage<>();
        try {
            userApplicationService.update(userId, putUserInfo);
            res.success();
        } catch(IllegalArgumentException | NullPointerException e) {
            log.error(e.getMessage(), e);
            res.error(e.getMessage(), 400);
        } catch(IllegalIdentifierException e) {
            log.error(e.getMessage(), e);
            res.error(e.getMessage(), 403);
        }
        return res;
    }

    @Operation(summary = "사용자 탈퇴")
    @DeleteMapping("{id}")
    public ResponseModel withdrawal(@PathVariable("id") Long userId) {
        ResponseModel res = new ResultMessage<>();
        try {
            userApplicationService.withdrawal(userId);
            res.success();
        } catch(NullPointerException e) {
            log.error(e.getMessage(), e);
            res.error(e.getMessage(), 400);
        }
        return res;
    }
}
