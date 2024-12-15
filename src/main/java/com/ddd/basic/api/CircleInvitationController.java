package com.ddd.basic.api;

import com.ddd.basic.application.circle.CircleApplicationService;
import com.ddd.basic.application.circle.CircleInviteDto;
import com.ddd.basic.application.invitation.CircleInvitationApplicationService;
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
@RestController
@RequestMapping("invitation")
public class CircleInvitationController {

    private final CircleInvitationApplicationService invitationApplicationService;

    @PostMapping("invite")
    public ResponseModel invite(@RequestBody CircleInviteDto inviteInfo) {
        ResponseModel res = new ResultMessage<>();
        try {
            invitationApplicationService.invite(inviteInfo);
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
