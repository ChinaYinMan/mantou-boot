package com.mantou.boot.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mantou.boot.dto.param.SysUserParamDTO;
import com.mantou.boot.entity.SysUser;
import com.mantou.boot.exception.BusinessException;
import com.mantou.boot.mapper.SysUserMapper;
import com.mantou.boot.result.Result;
import com.mantou.boot.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 系统模块-用户表 服务实现类
 *
 * @author mantou
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 新增用户
     *
     * @param sysUser
     * @return
     */
    @Override
    public boolean saveUser(SysUser sysUser) {

        if (sysUser == null) {
            throw new BusinessException("SysUser empty!");
        }
        if (sysUser.getUserAccount() == null) {
            throw new BusinessException("UserAccount(用户登录账户) empty!");
        }
        if (sysUser.getUserPassword() == null) {
            throw new BusinessException("UserPassword(用户登录密码) empty!");
        }

        /**-------------------------------------用户名不能重复-------------------------------------**/
        // TODO 使用Wrapper进行查询时, column参数与数据库字段命名方式等同 user_account
        int count = this.count(new QueryWrapper<SysUser>()
                .eq("user_account", sysUser.getUserAccount()));

        if (count > 0) {
            throw new BusinessException("UserAccount(用户登录账户) 已存在!");
        }
        /**-------------------------------------用户名不能重复-------------------------------------**/

        // TODO 该新增方法, 会自动回显ID主键至新增对象(sysUser)中
        return this.save(sysUser);
    }

    /**
     * 批量新增用户
     * @param sysUserList
     * @return
     */
    @Override
    public boolean saveUserBatch(List<SysUser> sysUserList) {

        if (sysUserList == null || sysUserList.size() < 1) {
            throw new BusinessException("参数对象 empty!");
        }

        /**-------------------------------------用户名不能重复-------------------------------------**/
        List<Long> idList = Lists.newArrayList();
        //用户账户map, 用于校验用户名是否重复
        Map<String, String> sysUserAccountMap = Maps.newHashMap();
        sysUserList.forEach(sysUser -> {
            if (sysUserAccountMap.containsKey(sysUser.getUserAccount())) {
                throw new BusinessException("UserAccount(用户登录账户) 不能重复!");
            } else {
                sysUserAccountMap.put(sysUser.getUserAccount(), sysUser.getUserAccount());
                idList.add(sysUser.getUserId());
            }
        });

        //校验数据库是否存在用户名
        int count = this.count(new QueryWrapper<SysUser>().in("user_id", idList));

        if (count > 0) {
            throw new BusinessException("UserAccount(用户登录账户) 已存在!");
        }
        /**-------------------------------------用户名不能重复-------------------------------------**/

        return this.saveBatch(sysUserList);
    }

    /**
     * 分页查询( IPge + QueryWrapper查询 )
     * @param page
     * @param sysUserParamDTO
     * @return
     */
    @Override
    public IPage<SysUser> pageUser(Page page, SysUserParamDTO sysUserParamDTO) {

        //参数默认处理
        sysUserParamDTO = sysUserParamDTO != null ? sysUserParamDTO : SysUserParamDTO.newSysUserParamDTO();

        //不允许查询所有记录数, 默认分页
        if (page == null) {
            throw new BusinessException("Page empty!");
        }

        // TODO 注意: 字段动态查询, 无需使用if判断, 可以直接在相关方法中写condition
        IPage iPage = this.page(page, new QueryWrapper<SysUser>()
                .eq(sysUserParamDTO.getUserId() != null, "user_id", sysUserParamDTO.getUserId())
                // TODO like这里使用likeRight, 不使用% column % 因为如果该字段加了索引, 索引会断掉
                .likeRight(StringUtils.isNotEmpty(sysUserParamDTO.getUserName()), "user_name", sysUserParamDTO.getUserName())
                .likeRight(StringUtils.isNotEmpty(sysUserParamDTO.getUserAccount()), "user_name", sysUserParamDTO.getUserAccount()));

        return iPage;
    }

    /**
     * 分页查询( IPge + 原生Sql查询 )
     * @param page
     * @param sysUserParamDTO
     * @return
     */
    @Override
    public IPage<SysUser> pageUserByParam(Page page, SysUserParamDTO sysUserParamDTO) {

        //参数默认处理
        sysUserParamDTO = sysUserParamDTO != null ? sysUserParamDTO : SysUserParamDTO.newSysUserParamDTO();

        //不允许查询所有记录数, 默认分页
        if (page == null) {
            throw new BusinessException("Page empty!");
        }

        return sysUserMapper.pageUserByParam(page, sysUserParamDTO);
    }

    /**
     * 分页查询( IPge + WrapperSql查询 )
     * @param page
     * @param sysUserParamDTO
     * @return
     */
    @Override
    public IPage<SysUser> pageUserByWrapperSql(Page page, SysUserParamDTO sysUserParamDTO) {

        //参数默认处理
        sysUserParamDTO = sysUserParamDTO != null ? sysUserParamDTO : SysUserParamDTO.newSysUserParamDTO();

        //不允许查询所有记录数, 默认分页
        if (page == null) {
            throw new BusinessException("Page empty!");
        }

        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>()
                .eq(sysUserParamDTO.getUserId() != null, "user_id", sysUserParamDTO.getUserId())
                // TODO like这里使用likeRight, 不使用% column % 因为如果该字段加了索引, 索引会断掉
                .likeRight(StringUtils.isNotEmpty(sysUserParamDTO.getUserName()), "user_name", sysUserParamDTO.getUserName())
                .likeRight(StringUtils.isNotEmpty(sysUserParamDTO.getUserAccount()), "user_name", sysUserParamDTO.getUserAccount());

        return sysUserMapper.pageUserByWrapperSql(page, queryWrapper);
    }

    /**
     * 通过ID更新用户
     * @param sysUser
     * @return
     */
    @Override
    public boolean updateUserById(SysUser sysUser) {

        if(sysUser == null) {
            throw new BusinessException("用户对象 empty!");
        }
        if (sysUser.getUserId() == null) {
            throw new BusinessException("用户ID empty!");
        }

        // TODO 此处更新为动态SQL更新, 只会更新有值的字段
        return this.updateById(sysUser);
    }

    /**
     * 删除用户(物理删除)
     * @param sysUserParamDTO
     * @return
     */
    @Override
    public boolean deleteUser(SysUserParamDTO sysUserParamDTO) {

        //参数默认处理
        sysUserParamDTO = sysUserParamDTO != null ? sysUserParamDTO : SysUserParamDTO.newSysUserParamDTO();

        //单条删除
        boolean b1 = sysUserParamDTO.getUserId() != null ? this.removeById(sysUserParamDTO.getUserId()) : Boolean.TRUE;
        //批量删除
        boolean b2 = sysUserParamDTO.getUserIdList() != null && sysUserParamDTO.getUserIdList().size() > 0
                ? this.removeByIds(sysUserParamDTO.getUserIdList()) : Boolean.TRUE;

        return b1 && b2 ? Boolean.TRUE : Boolean.FALSE;
    }
}
