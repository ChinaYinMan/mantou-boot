package com.mantou.boot.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mantou.boot.dto.param.SysUserParamDTO;
import com.mantou.boot.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 系统模块-用户表 Mapper 接口
 *
 * @author mantou
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 分页查询( IPge + 原生Sql查询 )
     * @param page
     * @param sysUserParamDTO
     * @return
     */
    IPage<SysUser> pageUserByParam(Page page, @Param(value = "sysUserParamDTO") SysUserParamDTO sysUserParamDTO);

    /**
     * 分页查询( IPge + WrapperSql查询 )
     * @param page
     * @param queryWrapper
     * @return
     */
    IPage<SysUser> pageUserByWrapperSql(Page page, @Param(value = "ew") QueryWrapper<SysUser> queryWrapper);
}
