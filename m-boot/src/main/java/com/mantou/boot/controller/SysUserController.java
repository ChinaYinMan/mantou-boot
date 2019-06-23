package com.mantou.boot.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mantou.boot.dto.param.SysUserParamDTO;
import com.mantou.boot.entity.SysUser;
import com.mantou.boot.exception.BusinessException;
import com.mantou.boot.result.Result;
import com.mantou.boot.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**

 * 系统模块-用户表
 *
 * @author mantou
 */
@Slf4j
@RestController
@RequestMapping("/sysUser")
@Api(tags = "用户管理")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 新增用户
     * @param json
     * @return
     */
    @ApiOperation(value = "新增用户")
    @PostMapping(path = "/saveUser")
    public Result saveUser(@RequestBody(required = false) SysUser sysUser) {

        if (sysUser == null) {
            return Result.failed("参数对象 empty!");
        }

        boolean isSuccess = sysUserService.saveUser(sysUser);

        return Result.isSuccess(isSuccess, "新增用户失败!", null);
    }

    /**
     * 批量新增用户
     * @param sysUserList
     * @return
     */
    @ApiOperation(value = "批量新增用户", hidden = true)
    @PostMapping(path = "/saveUserBatch")
    public Result saveUserBatch(@RequestBody(required = false) List<SysUser> sysUserList) {

        if (sysUserList == null || sysUserList.size() < 1) {
            return Result.failed("参数对象 empty!");
        }

        boolean isSuccess = sysUserService.saveUserBatch(sysUserList);

        return Result.isSuccess(isSuccess, "新增用户失败!", null);
    }

    /**
     * 分页查询( IPge + QueryWrapper查询 )
     * @param userId
     * @param userName
     * @param userAccount
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "分页查询( IPge + QueryWrapper查询 )")
    @GetMapping(path = "/pageUser")
    public Result<IPage<SysUser>> pageUser(@ApiParam(value = "用户ID", required = true,  hidden = false) @RequestParam(required = false) Long userId
            , @RequestParam(required = false) String userName
            , @RequestParam(required = false) String userAccount
            , @RequestParam(required = false) Integer pageNum
            , @RequestParam(required = false) Integer pageSize) {

        //默认分页处理
        Page page = pageNum == null || pageNum < 1 || pageSize == null || pageSize < 1 ? new Page() : new Page(pageNum, pageSize);

        //参数对象
        SysUserParamDTO sysUserParamDTO = SysUserParamDTO.newSysUserParamDTO()
                .setUserId(userId).setUserName(userName).setUserAccount(userAccount);

        return Result.success(sysUserService.pageUser(page, sysUserParamDTO));
    }

    /**
     * 分页查询( IPge + 原生SQL查询 )
     * @param userId
     * @param userName
     * @param userAccount
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "分页查询( IPge + 原生SQL查询 )")
    @GetMapping(path = "/pageUserByParam")
    public Result<IPage<SysUser>> pageUserByParam(@RequestParam(required = false) Long userId , @RequestParam(required = false) String userName
            , @RequestParam(required = false) String userAccount, @RequestParam(required = false) Integer pageNum
            , @RequestParam(required = false) Integer pageSize) {

        //默认分页处理
        Page page = pageNum == null || pageNum < 1 || pageSize == null || pageSize < 1 ? new Page() : new Page(pageNum, pageSize);

        //参数对象
        SysUserParamDTO sysUserParamDTO = SysUserParamDTO.newSysUserParamDTO()
                .setUserId(userId).setUserName(userName).setUserAccount(userAccount);

        return Result.success(sysUserService.pageUserByParam(page, sysUserParamDTO));
    }


    /**
     * 分页查询( IPge + WrapperSql查询 )
     * @param userId
     * @param userName
     * @param userAccount
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "分页查询( IPge + WrapperSql查询 )")
    @GetMapping(path = "/pageUserByWrapperSql")
    public Result<IPage<SysUser>> pageUserByWrapperSql(@RequestParam(required = false) Long userId , @RequestParam(required = false) String userName
            , @RequestParam(required = false) String userAccount, @RequestParam(required = false) Integer pageNum
            , @RequestParam(required = false) Integer pageSize) {

        //默认分页处理
        Page page = pageNum == null || pageNum < 1 || pageSize == null || pageSize < 1 ? new Page() : new Page(pageNum, pageSize);

        //参数对象
        SysUserParamDTO sysUserParamDTO = SysUserParamDTO.newSysUserParamDTO()
                .setUserId(userId).setUserName(userName).setUserAccount(userAccount);

        return Result.success(sysUserService.pageUserByWrapperSql(page, sysUserParamDTO));
    }


    /**
     * 通过ID更新用户
     * @param sysUser
     * @return
     */
    @ApiOperation(value = "通过ID更新用户")
    @PostMapping(path = "/updateUserById")
    public Result updateUserById(@RequestBody(required = false) SysUser sysUser) {

        if (sysUser == null) {
            return Result.failed("参数对象 empty!");
        }
        if (sysUser.getUserId() == null) {
            return Result.failed("用户ID empty");
        }

        boolean isSuccess = sysUserService.updateUserById(sysUser);

        return Result.isSuccess(isSuccess, "通过ID更新用户失败!", null);
    }

    /**
     * 删除用户(物理删除)
     * @param sysUserParamDTO
     * @return
     */
    @ApiOperation(value = "删除用户(物理删除)")
    @PostMapping(path = "/deleteUser")
    public Result deleteUser(@RequestBody(required = false) SysUserParamDTO sysUserParamDTO) {

        if (sysUserParamDTO == null) {
            return Result.failed("参数对象 empty!");
        }

        boolean b = sysUserParamDTO.getUserId() == null && (sysUserParamDTO.getUserIdList() == null || sysUserParamDTO.getUserIdList().size() < 1);

        if (b) {
            return Result.failed("用户ID empty");
        }

        boolean isSuccess = sysUserService.deleteUser(sysUserParamDTO);

        return Result.isSuccess(isSuccess, "通过ID更新用户失败!", null);
    }
}

