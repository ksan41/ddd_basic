package com.ddd.basic.api;

import com.ddd.basic.application.circle.CircleApplicationService;
import com.ddd.basic.application.circle.CircleCreateDto;
import com.ddd.basic.application.circle.CircleInviteDto;
import com.ddd.basic.application.circle.CircleJoinDto;
import com.ddd.basic.common.ResponseModel;
import com.ddd.basic.common.ResultMessage;
import com.ddd.basic.common.constants.ExceptionMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("circle")
@RestController()
public class CircleController {

    private final CircleApplicationService circleApplicationService;

    @PostMapping()
    public ResponseModel create(@RequestBody CircleCreateDto circleInfo) {
        ResponseModel res = new ResultMessage<>();
        try{
            circleApplicationService.create(circleInfo);
            res.success();
        }catch(NullPointerException e) {
            log.error(ExceptionMessage.NOT_FOUND_USER.getMessage(), e);
            res.error(ExceptionMessage.NOT_FOUND_USER);
        } catch(IllegalArgumentException e) {
            log.error(ExceptionMessage.DUPLICATED_CIRCLE_NAME.getMessage(), e);
            res.error(ExceptionMessage.DUPLICATED_CIRCLE_NAME);
        }
        return res;
    }

    @PostMapping("join")
    public ResponseModel join(@RequestBody CircleJoinDto joinInfo) {
        ResponseModel res = new ResultMessage<>();
        try {
            circleApplicationService.join(joinInfo);
            res.success();
        } catch(NullPointerException e) {
            log.error(ExceptionMessage.NOT_FOUND_CIRCLE.getMessage(), e);
            res.error(ExceptionMessage.NOT_FOUND_CIRCLE);
        } catch(NegativeArraySizeException e) {
            log.error(ExceptionMessage.FULL_CIRCLE_MEMBERS.getMessage(), e);
            res.error(ExceptionMessage.FULL_CIRCLE_MEMBERS);
        }
        return res;
    }

    @PostMapping("invite")
    public ResponseModel invite(@RequestBody CircleInviteDto inviteInfo) {
        ResponseModel res = new ResultMessage<>();
        try {
            circleApplicationService.invite(inviteInfo);
            res.success();
        } catch(NullPointerException e) {
            log.error(e.getMessage(), e);
            res.error(ExceptionMessage.valueOf(e.getMessage()));
        } catch(NegativeArraySizeException e) {
            log.error(ExceptionMessage.FULL_CIRCLE_MEMBERS.getMessage(), e);
            res.error(ExceptionMessage.FULL_CIRCLE_MEMBERS);
        }
        return res;
    }
}
