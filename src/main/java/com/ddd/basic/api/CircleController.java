package com.ddd.basic.api;

import com.ddd.basic.application.circle.*;
import com.ddd.basic.common.ResponseModel;
import com.ddd.basic.common.ResultListMessage;
import com.ddd.basic.common.ResultMessage;
import com.ddd.basic.common.constants.ExceptionMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("circle")
@RestController()
@Tag(name = "Circle", description = "서클 관련 api")
public class CircleController {

    private final CircleApplicationService circleApplicationService;

    @Operation(summary = "서클 생성", description = "사용자가 서클을 생성합니다.")
    @PostMapping()
    public ResponseModel create(@RequestBody CircleCreateDto circleInfo) {
        ResponseModel res = new ResultMessage<>();
        try{
            circleApplicationService.create(circleInfo);
            res.created();
        }catch(NullPointerException e) {
            log.error(ExceptionMessage.NOT_FOUND_USER.getMessage(), e);
            res.error(ExceptionMessage.NOT_FOUND_USER);
        } catch(IllegalArgumentException e) {
            log.error(ExceptionMessage.DUPLICATED_CIRCLE_NAME.getMessage(), e);
            res.error(ExceptionMessage.DUPLICATED_CIRCLE_NAME);
        }
        return res;
    }

    @Operation(summary = "서클 가입", description = "사용자가 직접 서클에 가입합니다.")
    @PostMapping("join")
    public ResponseModel join(@RequestBody CircleJoinDto joinInfo) {
        ResponseModel res = new ResultMessage<>();
        try {
            circleApplicationService.join(joinInfo);
            res.created();
        } catch(NullPointerException e) {
            log.error(ExceptionMessage.NOT_FOUND_CIRCLE.getMessage(), e);
            res.error(ExceptionMessage.NOT_FOUND_CIRCLE);
        } catch(NegativeArraySizeException e) {
            log.error(ExceptionMessage.FULL_CIRCLE_MEMBERS.getMessage(), e);
            res.error(ExceptionMessage.FULL_CIRCLE_MEMBERS);
        }
        return res;
    }

    @Operation(summary = "추천 서클 조회")
    @GetMapping("recommend")
    public ResponseModel getRecommend() {
        ResponseModel res = new ResultListMessage<>();
        List<CircleViewDto> recommendList =
                circleApplicationService.getRecommend()
                        .stream()
                        .map(CircleViewDto::new)
                        .toList();
        res.successWithResult(recommendList);
        return res;
    }

    @Operation(summary = "서클 검색")
    @GetMapping()
    public ResponseModel search(@RequestBody CircleSearchDto searchInfo) {
        ResponseModel res = new ResultListMessage<>();
        res.successWithResult(circleApplicationService.search(searchInfo));
        return res;
    }
}
