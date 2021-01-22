package org.csu.mypetstore.persistence.Impl;

import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.persistence.CartDAO;
import org.csu.mypetstore.persistence.DBUtil;
import org.csu.mypetstore.service.CatalogService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CartDAOImpl implements CartDAO {
    private static final String insertCartString = "insert into cart (userid, itemid,instock) VALUES (?, ?,1)";
    private static final String deleteCartString = "delete from cart where userid=? AND itemid=? ";
    private static final String get_Cart="select * from  cart where userid=?";
    private static final String deleteCartAll="delete from cart where userid=?";
    @Override
    public void insertCart(String userid,String itemid){
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertCartString);
            preparedStatement.setString(1, userid);
            preparedStatement.setString(2, itemid);


            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void deleteCart(String userid,String itemid){
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(deleteCartString);
            preparedStatement.setString(1, userid);
            preparedStatement.setString(2, itemid);

            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public Cart getCart(String userid){
        Cart cart =new Cart();
        CatalogService catalogService=new CatalogService();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(get_Cart);
            preparedStatement.setString(1, userid);
            ResultSet rs =preparedStatement.executeQuery();
            while (rs.next()){
                Item item =catalogService.getItem(rs.getString(2));
                cart.addItem(item,rs.getBoolean(3));
            }
            preparedStatement.executeQuery();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return cart;
        }
        @Override
    public void deleteCartAll(String userid){
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(deleteCartAll);
            preparedStatement.setString(1, userid);
            preparedStatement.execute();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    }

