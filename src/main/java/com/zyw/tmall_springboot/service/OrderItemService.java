package com.zyw.tmall_springboot.service;

import com.zyw.tmall_springboot.dao.OrderDAO;
import com.zyw.tmall_springboot.dao.OrderItemDAO;
import com.zyw.tmall_springboot.pojo.Order;
import com.zyw.tmall_springboot.pojo.OrderItem;
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
}
