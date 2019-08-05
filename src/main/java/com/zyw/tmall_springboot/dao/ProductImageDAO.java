package com.zyw.tmall_springboot.dao;

import com.zyw.tmall_springboot.pojo.Product;
import com.zyw.tmall_springboot.pojo.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: tmall_springboot
 * @description:
 * @author: Cengyuwen
 * @create: 2019-07-31 16:46
 **/
public interface ProductImageDAO extends JpaRepository<ProductImage,Integer> {
    public List<ProductImage> findByProductAndTypeOrderByIdDesc(Product product, String type);
}
