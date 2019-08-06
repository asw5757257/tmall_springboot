package com.zyw.tmall_springboot.service;

import com.zyw.tmall_springboot.dao.UserDAO;
import com.zyw.tmall_springboot.pojo.User;
import com.zyw.tmall_springboot.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

/**
 * @program: tmall_springboot
 * @description:
 * @author: Cengyuwen
 * @create: 2019-08-01 16:06
 **/
@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;
    public Page4Navigator<User> list(int start, int size, int navigatePages)
    {
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        Pageable pageable = new PageRequest(start, size,sort);
        Page pageFromJPA = userDAO.findAll(pageable);
        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }
    public User getByName(String name)
    {
        return userDAO.findByName(name);
    }
    public boolean isExist(String name)
    {
        User user = getByName(name);
        return null!=user;
    }
    public void add(User user)
    {
        userDAO.save(user);
    }
    public User get(String name, String password) {
        return userDAO.getByNameAndPassword(name,password);
    }
}
