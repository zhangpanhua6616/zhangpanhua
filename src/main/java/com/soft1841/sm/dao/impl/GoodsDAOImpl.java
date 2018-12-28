package com.soft1841.sm.dao.impl;

import sm.hutool.db.Db;
import sm.hutool.db.Entity;
import com.soft1841.sm.dao.GoodsDAO;
import com.soft1841.sm.entity.Goods;

import javax.swing.text.html.parser.Entity;
import java.sql.SQLException;
import java.util.List;
/**
 * 商品DAO
 * @author
 */
public class GoodsDAOImpl implements GoodsDAO {

    @Override
    public List<Entity> getAllGoods() throws SQLException {
        return Db.use().query("SELECT * FROM t_goods");
    }

    @Override
    public Long insertGoods(Goods goods) throws SQLException {
        return Db.use().insertForGeneratedKey(
                sm.hutool.db.Entity.create("t_goods")
                        .set("name",goods.getName())
                        .set("typename",goods.getTypename())
                        .set("barCode",goods.getBarCode())
                        .set("price",goods.getPrice())
                        .set("avatar",goods.getAvatar())
                        .set("quantity",goods.getQuantity())
                        .set("description",goods.getDescription())
        );
    }


    @Override
    public int deleteGoodsByID(long id) throws SQLException {
        return Db.use().del(
                sm.hutool.db.Entity.create("t_goods").set("id",id)
        );
    }

    @Override
    public Entity getGoodsById(int id) throws SQLException {
        return Db.use().queryOne("SELECT * FROM t_goods WHERE id = ?",id);
    }

    @Override
    public int updateGoods(Goods goods) throws SQLException {
        return Db.use().update(
                Entity.create().set("price",goods.getPrice())
                        .set("avatar",goods.getAvatar())
                        .set("quantity",goods.getQuantity())
                        .set("description",goods.getDescription())
                        .set("barCode",goods.getBarCode()),
                Entity.create("t_goods").set("id",goods.getId())
        );

    }
}