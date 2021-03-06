package com.zyw.tmall_springboot.dao;

import com.zyw.tmall_springboot.pojo.Order;
import com.zyw.tmall_springboot.pojo.OrderItem;
import com.zyw.tmall_springboot.pojo.Product;
import com.zyw.tmall_springboot.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: tmall_springboot
 * @description:
 * @author: Cengyuwen
 * @create: 2019-08-01 16:44
 **/
public interface OrderItemDAO extends JpaRepository<OrderItem,Integer> {
    List<OrderItem> findByOrderOrderByIdDesc(Order order);
    List<OrderItem> findByProduct(Product product);
    List<OrderItem> findByUserAndOrderIsNull(User user);
}
