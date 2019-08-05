package com.zyw.tmall_springboot.dao;

import com.zyw.tmall_springboot.pojo.Category;
import com.zyw.tmall_springboot.pojo.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: tmall_springboot
 * @description:
 * @author: Cengyuwen
 * @create: 2019-07-30 15:06
 **/

public interface PropertyDAO extends JpaRepository<Property,Integer> {
    Page<Property> findByCategory(Category category, Pageable pageable);
    List<Property> findByCategory(Category category);
}
