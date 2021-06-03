package com.lv.spring.controller;

import com.lv.spring.service.OssService;
import com.lv.spring.vo.ResultVO;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

@RestController
@RequestMapping("/oss")
public class OssController {

    final
    OssService ossService;

    public OssController(OssService ossService) {
        this.ossService = ossService;
    }

    @GetMapping("/upload_token")
    public ResultVO getUplaodToken() {
        return ResultVO.ok(ossService.getToken());
    }
}
