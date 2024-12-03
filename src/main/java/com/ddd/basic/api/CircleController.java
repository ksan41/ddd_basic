package com.ddd.basic.api;

import com.ddd.basic.application.circle.CircleApplicationService;
import com.ddd.basic.application.circle.CircleCreateDto;
import com.ddd.basic.common.ResponseModel;
import com.ddd.basic.common.ResultMessage;
import com.ddd.basic.common.constants.ExceptionMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController("circle")
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
}
