package com.mantou.boot.controller.demo;

import com.mantou.boot.dto.LombokDemoDTO;
import com.mantou.boot.result.Result;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Lombok测试接口
 * @author mantou
 */
@Slf4j
@RestController
@RequestMapping("/lombok")
public class LombokDemoController {

    /**
     * 获取Lombok实例
     * @return
     */
    @GetMapping(value = "/getLombokInstance")
    public Result getLombokInstance() {

        LombokDemoDTO lombokDemoDTO = LombokDemoDTO.newLombokDemoDTO()
                .setContent("内容").setTitle("标题");

        String content = lombokDemoDTO.getContent();

        log.info("content : {}", content);

        return Result.success(lombokDemoDTO.toString());
    }
}
