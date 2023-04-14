package im.dart.auto.magic.controller;

import im.dart.auto.magic.config.CusConfig;
import im.dart.auto.magic.data.ChatData;
import im.dart.boot.common.constant.DartCode;
import im.dart.boot.common.data.Result;
import im.dart.boot.common.utils.Checker;
import im.dart.boot.common.utils.UUID;
import im.dart.boot.open.openai.OpenAI;
import im.dart.boot.open.openai.data.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * <p>描述: 暂无描述信息</p>
 *
 * <p>创建时间：2023-04-14 10:22</p>
 * <p>更新时间：暂无</p>
 *
 * @author Kevin.Xu
 * @version 1.0
 */
@RestController
public class MainController {

    @Autowired
    private CusConfig config;

    @Autowired
    private HttpServletRequest request;


    @RequestMapping(value = {"/test"}, method = {RequestMethod.GET, RequestMethod.POST}, consumes = {"*/*"})
    public Result test() {
        return Result.SUCCESS();
    }

    @RequestMapping(value = {"/chat"}, method = {RequestMethod.GET, RequestMethod.POST}, consumes = {"*/*"})
    public Result chat(@RequestBody ChatData data) {
        if (Checker.hasEmpty(data, data.getKey(), data.getContent())) {
            return DartCode.RECEIVED_DATA_EMPTY.result();
        }
        ChatResponse res = new OpenAI(data.getKey(), "org-", null).chat(data.getContent(), UUID.ulid(), data.getModel());
        if (Checker.isEmpty(res)) {
            return DartCode.EXEC_ERROR.result("Can not get any answer.");
        }
        if (data.getJustContent()) {
            return Result.of(res.getContent());
        }
        return Result.of(res);
    }

    @PostMapping(value = {"/own-chat"}, consumes = {"*/*"})
    public Result ownChat(@RequestBody ChatData data) {
        if (Checker.isEmpty(data.getContent())) {
            return DartCode.RECEIVED_DATA_EMPTY.result();
        }
        String token = request.getHeader("token");
        if (Checker.hasEmpty(config.getKey(), config.getOrg(), config.getToken(), token)) {
            return DartCode.NOT_FOUND.result();
        }
        if (config.getToken().length() < 30) {
            return DartCode.NUMBER_RANGE_ERROR.result();
        }
        if (!Objects.equals(token, config.getToken())) {
            return DartCode.DATA_MISMATCH.result();
        }
        ChatResponse res = new OpenAI(config.getKey(), config.getOrg(), null).chat(data.getContent(), UUID.ulid(), config.getModel());
        if (Checker.isEmpty(res)) {
            return DartCode.EXEC_ERROR.result("Can not get any answer.");
        }
        if (data.getJustContent()) {
            return Result.of(res.getContent());
        }
        return Result.of(res);
    }
}
