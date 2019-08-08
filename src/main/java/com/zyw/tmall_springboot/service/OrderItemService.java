package com.zyw.tmall_springboot.service;

import com.zyw.tmall_springboot.dao.OrderDAO;
import com.zyw.tmall_springboot.dao.OrderItemDAO;
import com.zyw.tmall_springboot.pojo.Order;
import com.zyw.tmall_springboot.pojo.OrderItem;
import com.zyw.tmall_springboot.pojo.Product;
import com.zyw.tmall_springboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: tmall_springboot
 * @description:
 * @author: Cengyuwen
 * @create: 2019-08-01 16:47
 **/
@Service
public class OrderItemService {
    @Autowired
    private OrderItemDAO orderItemDAO;
    @Autowired
    private ProductImageService productImageService;
    public void fill(List<Order> orders)
    {
        for(Order order:orders)
        {
            fill(order);
        }

    }
    public void fill(Order order)
    {
        List<OrderItem> orderItems = listByOrder(order);
        float total = 0;
        int totalNumber = 0;
        for(OrderItem orderItem:orderItems)
        {
            total+=orderItem.getNumber()*orderItem.getProduct().getPromotePrice();
            totalNumber+=orderItem.getNumber();
            productImageService.setFirstProdutImage(orderItem.getProduct());
        }
        order.setTotal(total);
        order.setOrderItems(orderItems);
        order.setTotalNumber(totalNumber);
        order.setOrderItems(orderItems);
    }
    public List<OrderItem> listByOrder(Order order) {
        return orderItemDAO.findByOrderOrderByIdDesc(order);
    }

    public int getSaleCount(Product product) {
        List<OrderItem> ois =listByProduct(product);
        int result =0;
        for (OrderItem oi : ois) {
            if(null!=oi.getOrder())
                if(null!= oi.getOrder() && null!=oi.getOrder().getPayDate())
                    result+=oi.getNumber();
        }
        return result;
    }
    public List<OrderItem> listByProduct(Product product) {
        return orderItemDAO.findByProduct(product);
    }
    public List<OrderItem> listByUser(User user)
    {
        return orderItemDAO.findByUserAndOrderIsNull(user);
    }
    public void update(OrderItem orderItem)
    {
        orderItemDAO.save(orderItem);
    }
    public void add(OrderItem orderItem)
    {
        orderItemDAO.save(orderItem);
    }
    public OrderItem get(int id) {
        return orderItemDAO.findOne(id);
    }
    public void delete(int id) {
        orderItemDAO.delete(id);
    }

}
