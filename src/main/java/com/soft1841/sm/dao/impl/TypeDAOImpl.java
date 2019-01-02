package com.soft1841.sm.dao.impl;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.soft1841.sm.dao.TypeDAO;
import com.soft1841.sm.entity.Type;

import java.sql.SQLException;
import java.util.List;

public class TypeDAOImpl implements TypeDAO {

    @Override
    public Long insertType(Type type) throws SQLException {
        //采用了另一种新增方法，可以返回插入记录的主键（Long类型）
        return Db.use().insertForGeneratedKey(
                Entity.create("t_type")
                        .set("name", type.getTypeName())
        );
    }

    @Override
    public int deleteTypeById(long id) throws SQLException {
        return Db.use().del(
                Entity.create("t_type").set("type_id", id)
        );
    }


    @Override
    public List<Entity> selectAllTypes() throws SQLException {
        //1.采用默认的查询
        //return Db.use().findAll("t_type");
        //2.采用自定义查询语句查询
        return Db.use().query("SELECT * FROM t_type ");
    }

    @Override
    public Entity getTypeById(int id) throws SQLException {
        //采用自定义带参查询语句，返回单个实体
        return Db.use().queryOne("SELECT * FROM t_type WHERE type_id = ? ", id);
    }
}
