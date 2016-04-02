package com.generator.mapper;

import com.generator.pojo.SysUser;

public interface SysUserMapper {
    int deleteByPrimaryKey(Long sysUserId);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long sysUserId);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
}