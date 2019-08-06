package com.zyw.tmall_springboot.dao;

import com.zyw.tmall_springboot.pojo.Product;
import com.zyw.tmall_springboot.pojo.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: tmall_springboot
 * @description:
 * @author: Cengyuwen
 * @create: 2019-08-05 17:55
 **/

public interface ReviewDAO extends JpaRepository<Review,Integer> {
    List<Review> findByProductOrderByIdDesc(Product product);
    int countByProduct(Product product);
}
