package com.ddd.basic.api;

import com.ddd.basic.application.user.UserApplicationService;
import com.ddd.basic.application.user.UserPostDto;
import com.ddd.basic.application.user.UserPutDto;
import com.ddd.basic.application.user.UserViewDto;
import com.ddd.basic.common.ResponseModel;
import com.ddd.basic.common.ResultListMessage;
import com.ddd.basic.common.ResultMessage;
import com.ddd.basic.common.constants.ExceptionMessage;
import com.ddd.basic.domain.model.user.IUserRepository;
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
public class UserController {

    private final UserApplicationService userApplicationService;
    private final IUserRepository userRepository;

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
