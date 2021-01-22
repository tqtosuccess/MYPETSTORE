package org.csu.mypetstore.persistence;
import org.csu.mypetstore.domain.Cart;
public interface CartDAO {
    void insertCart(String userid,String itemid);
    void deleteCart(String userid,String itemid);
    Cart getCart(String userid);
    void deleteCartAll(String userid);
}
