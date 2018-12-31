package com.soft1841.sm.dao.impl;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.soft1841.sm.dao.AdminDAO;
import com.soft1841.sm.entity.Admin;

import java.sql.SQLException;
import java.util.List;

public class AdminDAOImpl implements AdminDAO {

    @Override
    public List<Entity> selectAdmins() throws SQLException {
        return Db.use().query("SELECT *FROM t_admin");
    }

    @Override
    public int deleteById(Long id) throws SQLException {
        return Db.use().del(Entity.create());
    }

    @Override
    public List<Entity> selectAdmin() throws SQLException {
        return Db.use().query("SELECT *FROM t_admin");
    }

    @Override
    public List<Entity> deleteAdminById(long id) throws SQLException {
        return Db.use().query("SELECT *FROM t_admin");
    }

    @Override
    public int deleteById(long id) throws SQLException {
        return Db.use().del(Entity.create());
    }
    @Override
    public Long insertAdmin(Admin admin) throws SQLException {
        return Db.use().insertForGeneratedKey(
                Entity.create("t_admin")
                        .set("name", admin.getName())
                        .set("avatar",admin.getAvatar())
                        .set("password",admin.getPassword())
                        .set("number",admin.getNumber())

        );
    }
}
