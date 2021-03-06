package com.soft1841.sm.dao;

import cn.hutool.db.Entity;
import com.soft1841.sm.entity.Type;
import com.soft1841.sm.utils.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class TypeDAOTest {

    private TypeDAO typeDAO = DAOFactory.getTypeDAOInstance();

    @Test
    public void insertType() throws SQLException {
        Type type = new Type();
        type.setTypeName("测试类别");
        System.out.println(typeDAO.insertType(type));
    }

    @Test
    public void deleteTypeById() throws SQLException{
        typeDAO.deleteTypeById(21);
    }

    @Test
    public void selectAllTypes() throws SQLException{
        List<Entity> typeList = typeDAO.selectAllTypes();
        typeList.forEach(entity -> System.out.println(entity));
    }

    @Test
    public void getTypeById() throws SQLException{
        Entity entity = typeDAO.getTypeById(1);
        System.out.println(entity);
    }
}