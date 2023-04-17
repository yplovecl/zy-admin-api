package com.ruoyi.project.seismograph.service;

import java.util.List;
import com.ruoyi.project.seismograph.domain.AppUser;

/**
 * APP用户Service接口
 * 
 * @author pange
 * @date 2023-04-17
 */
public interface IAppUserService 
{
    /**
     * 查询APP用户
     * 
     * @param userId APP用户主键
     * @return APP用户
     */
    public AppUser selectAppUserByUserId(Long userId);

    /**
     * 查询APP用户列表
     * 
     * @param appUser APP用户
     * @return APP用户集合
     */
    public List<AppUser> selectAppUserList(AppUser appUser);

    /**
     * 新增APP用户
     * 
     * @param appUser APP用户
     * @return 结果
     */
    public int insertAppUser(AppUser appUser);

    /**
     * 修改APP用户
     * 
     * @param appUser APP用户
     * @return 结果
     */
    public int updateAppUser(AppUser appUser);

    /**
     * 批量删除APP用户
     * 
     * @param userIds 需要删除的APP用户主键集合
     * @return 结果
     */
    public int deleteAppUserByUserIds(Long[] userIds);

    /**
     * 删除APP用户信息
     * 
     * @param userId APP用户主键
     * @return 结果
     */
    public int deleteAppUserByUserId(Long userId);
}
