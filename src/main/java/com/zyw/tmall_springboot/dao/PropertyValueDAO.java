package com.zyw.tmall_springboot.dao;

import com.zyw.tmall_springboot.pojo.Product;
import com.zyw.tmall_springboot.pojo.Property;
import com.zyw.tmall_springboot.pojo.PropertyValue;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * @program: tmall_springboot
 * @description:
 * @author: Cengyuwen
 * @create: 2019-07-31 19:03
 **/
public interface PropertyValueDAO extends JpaRepository<PropertyValue, Integer> {
    List<PropertyValue> findByProductOrderByIdDesc(Product product);

    PropertyValue getByPropertyAndProduct(Property property,Product product);
}
