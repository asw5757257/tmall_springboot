package com.zyw.tmall_springboot.dao;

import com.zyw.tmall_springboot.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: tmall_springboot
 * @description:
 * @author: Cengyuwen
 * @create: 2019-08-01 16:06
 **/
public interface UserDAO extends JpaRepository<User,Integer> {
}
