package com.fzc.lalw.mapper;

import com.fzc.lalw.domain.Admin;

/**
* @author PerfectFu
* @description 针对表【admin】的数据库操作Mapper
* @createDate 2022-03-11 19:02:45
* @Entity com.fzc.lalw.domain.Admin
*/
public interface AdminMapper {
    /**
     * 添加管理员
     * @param admin:管理员对象
     * @return 影响行数
     */
    int addAdmin(Admin admin);

    /**
     * 获取密码
     * @param account 账号
     * @return 密码
     */
    String getPasswordByAccount(String account);

    /**
     * 获取密码
     * @param id id
     * @return 密码
     */
    String getPasswordById(Integer id);

    /**
     * 通过id获取admin信息
     * @param id id
     * @return admin对象
     */
    Admin getAdminById(Integer id);

    /**
     * 通过账号来查询
     * @param account 账户名
     * @return 查询到的Admin
     */
    Admin getAdminByAccount(String account);
    /**
     * 获取所有管理员
     * @param index 起始位置
     * @param length 长度
     * @return 查询到的所有管理员
     */
    Admin[] getAdmin(Integer index, Integer length);

    /**
     * 修改admin的信息
     * @param newAdmin 修改后的信息
     * @return 影响了几条数据
     */
    int modifyAdmin(Admin newAdmin);

    /**
     * 删除指定admin
     * @param ids 指定的id集合
     * @return 删除了几条数据
     */
    int deleteAdmin(Integer[] ids);
}
