package com.mantou.boot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mantou.boot.dto.param.SysUserParamDTO;
import com.mantou.boot.entity.SysUser;

import java.util.List;

/**
 * 系统模块-用户表 服务类
 *
 * @author mantou
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 新增用户
     * @param sysUser
     * @return
     */
    boolean saveUser(SysUser sysUser);

    /**
     * 批量新增用户
     * @param sysUserList
     * @return
     */
    boolean saveUserBatch(List<SysUser> sysUserList);

    /**
     * 分页查询( IPge + QueryWrapper查询 )
     * @param page
     * @param sysUserParamDTO
     * @return
     */
    IPage<SysUser> pageUser(Page page, SysUserParamDTO sysUserParamDTO);

    /**
     * 分页查询( IPge + 自定义参数对象 )
     * @param page
     * @param sysUserParamDTO
     * @return
     */
    IPage<SysUser> pageUserByParam(Page page, SysUserParamDTO sysUserParamDTO);

    /**
     * 分页查询( IPge + WrapperSql查询 )
     * @param page
     * @param sysUserParamDTO
     * @return
     */
    IPage<SysUser> pageUserByWrapperSql(Page page, SysUserParamDTO sysUserParamDTO);

    /**
     * 通过ID更新用户
     * @param sysUser
     * @return
     */
    boolean updateUserById(SysUser sysUser);

    /**
     * 删除用户(物理删除)
     * @param sysUserParamDTO
     * @return
     */
    boolean deleteUser(SysUserParamDTO sysUserParamDTO);
}
