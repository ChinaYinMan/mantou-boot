package com.mantou.boot.dto.param;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 用户参数对象DTO
 * @author mantou
 */
@Data
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "newSysUserParamDTO")
public class SysUserParamDTO {

    private String userAccount;

    private String userName;

    private Long userId;

    private List<Long> userIdList;
}
