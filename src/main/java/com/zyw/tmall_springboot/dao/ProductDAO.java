package com.zyw.tmall_springboot.dao;

import com.zyw.tmall_springboot.pojo.Category;
import com.zyw.tmall_springboot.pojo.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * @program: tmall_springboot
 * @description:
 * @author: Cengyuwen
 * @create: 2019-07-30 17:48
 **/
public interface ProductDAO extends JpaRepository<Product, Integer> {
    Page<Product> findByCategory(Category category, Pageable pageable);
    List<Product> findByCategoryOrderById(Category category);
}
