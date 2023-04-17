package com.ruoyi.project.seismograph.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.seismograph.mapper.AppUserMapper;
import com.ruoyi.project.seismograph.domain.AppUser;
import com.ruoyi.project.seismograph.service.IAppUserService;

/**
 * APP用户Service业务层处理
 * 
 * @author pange
 * @date 2023-04-17
 */
@Service
public class AppUserServiceImpl implements IAppUserService 
{
    @Autowired
    private AppUserMapper appUserMapper;

    /**
     * 查询APP用户
     * 
     * @param userId APP用户主键
     * @return APP用户
     */
    @Override
    public AppUser selectAppUserByUserId(Long userId)
    {
        return appUserMapper.selectAppUserByUserId(userId);
    }

    /**
     * 查询APP用户列表
     * 
     * @param appUser APP用户
     * @return APP用户
     */
    @Override
    public List<AppUser> selectAppUserList(AppUser appUser)
    {
        return appUserMapper.selectAppUserList(appUser);
    }

    /**
     * 新增APP用户
     * 
     * @param appUser APP用户
     * @return 结果
     */
    @Override
    public int insertAppUser(AppUser appUser)
    {
        appUser.setCreateTime(DateUtils.getNowDate());
        return appUserMapper.insertAppUser(appUser);
    }

    /**
     * 修改APP用户
     * 
     * @param appUser APP用户
     * @return 结果
     */
    @Override
    public int updateAppUser(AppUser appUser)
    {
        appUser.setUpdateTime(DateUtils.getNowDate());
        return appUserMapper.updateAppUser(appUser);
    }

    /**
     * 批量删除APP用户
     * 
     * @param userIds 需要删除的APP用户主键
     * @return 结果
     */
    @Override
    public int deleteAppUserByUserIds(Long[] userIds)
    {
        return appUserMapper.deleteAppUserByUserIds(userIds);
    }

    /**
     * 删除APP用户信息
     * 
     * @param userId APP用户主键
     * @return 结果
     */
    @Override
    public int deleteAppUserByUserId(Long userId)
    {
        return appUserMapper.deleteAppUserByUserId(userId);
    }
}
