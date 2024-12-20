package com.ddd.basic.api;

import com.ddd.basic.application.invitation.CircleInviteDto;
import com.ddd.basic.application.invitation.CircleInvitationApplicationService;
import com.ddd.basic.application.invitation.InvitationResponseDto;
import com.ddd.basic.common.ResponseModel;
import com.ddd.basic.common.ResultMessage;
import com.ddd.basic.common.constants.ExceptionMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("invitation")
@Tag(name = "Invitation", description = "서클 초대 관련 api")
public class CircleInvitationController {

    private final CircleInvitationApplicationService invitationApplicationService;

    @Operation(summary = "서클 초대", description = "사용자가 다른 사용자에게 서클 초대를 보냅니다.")
    @PostMapping()
    public ResponseModel invite(@RequestBody CircleInviteDto inviteInfo) {
        ResponseModel res = new ResultMessage<>();
        try {
            invitationApplicationService.invite(inviteInfo);
            res.created();
        } catch(NullPointerException e) {
            log.error(e.getMessage(), e);
            res.error(ExceptionMessage.valueOf(e.getMessage()));
        } catch(NegativeArraySizeException e) {
            log.error(ExceptionMessage.FULL_CIRCLE_MEMBERS.getMessage(), e);
            res.error(ExceptionMessage.FULL_CIRCLE_MEMBERS);
        }
        return res;
    }

    @Operation(summary = "서클 초대 응답", description = "사용자가 받은 초대에 응답합니다.")
    @PutMapping("{id}")
    public ResponseModel responseInvitation(@PathVariable("id") Long userId,
                                            @RequestBody InvitationResponseDto responseInfo) {
        ResponseModel res = new ResultMessage<>();
        try {
            invitationApplicationService.responseInvitation(userId, responseInfo);
            res.success();
        } catch (NullPointerException e) {
            log.error(ExceptionMessage.NOT_FOUND_CIRCLE_INVITATION.getMessage(), e);
            res.error(ExceptionMessage.NOT_FOUND_CIRCLE_INVITATION);
        }
        return res;
    }
}
