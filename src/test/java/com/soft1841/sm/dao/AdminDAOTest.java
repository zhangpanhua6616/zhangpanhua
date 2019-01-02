package com.soft1841.sm.dao;

import cn.hutool.db.Entity;
import com.soft1841.sm.utils.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class AdminDAOTest {
    private AdminDAO adminDAO = DAOFactory.getAdminDAOInstance();

    @Test
    public void selectAdmins() throws SQLException {
        List<Entity> adminList = adminDAO.selectAdmin();
        adminList.forEach(entity -> System.out.println(entity));
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void selectAdmin() {
    }

    @Test
    public void deleteAdminById() {
    }

    @Test
    public void deleteById1() throws SQLException{
        adminDAO.deleteAdminById(10L);
    }

    @Test
    public void insertAdmin() {
    }
}