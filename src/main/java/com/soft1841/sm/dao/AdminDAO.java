package com.soft1841.sm.dao;

import cn.hutool.db.Entity;
import com.soft1841.sm.entity.Admin;


import java.sql.SQLException;
import java.util.List;

/**
 *
 */
public interface AdminDAO {
    List<Entity> selectAdmins() throws SQLException;

    int deleteById(Long id) throws SQLException;

    /**
     *
     * @return List<Entity>
     * @throws SQLException
     */
    List<Entity> selectAdmin()throws SQLException;

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    List<Entity> deleteAdminById(long id) throws SQLException;

    int deleteById(long id) throws SQLException;

    /**
     * 新增一个读者，返回自增主键
     * @param admin
     * @return
     * @throws SQLException
     */
    Long insertAdmin(Admin admin) throws SQLException;

}
