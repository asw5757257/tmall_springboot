package com.zyw.tmall_springboot.dao;

import com.zyw.tmall_springboot.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: tmall_springboot
 * @description:
 * @author: Cengyuwen
 * @create: 2019-07-27 16:55
 **/
public interface CategoryDAO extends JpaRepository<Category,Integer> {
}
