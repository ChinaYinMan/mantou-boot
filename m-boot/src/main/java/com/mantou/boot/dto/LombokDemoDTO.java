package com.mantou.boot.dto;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * Lombok测试类
 * @author mantou
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "newLombokDemoDTO")
public class LombokDemoDTO {

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;
}
