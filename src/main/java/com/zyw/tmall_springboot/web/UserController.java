package com.zyw.tmall_springboot.web;

import com.zyw.tmall_springboot.pojo.User;
import com.zyw.tmall_springboot.service.UserService;
import com.zyw.tmall_springboot.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: tmall_springboot
 * @description:
 * @author: Cengyuwen
 * @create: 2019-08-01 16:10
 **/
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/users")
    public Page4Navigator<User> list(@RequestParam(value="start",defaultValue = "0")int start,@RequestParam(value = "size",defaultValue = "5")int size)
    {
        start=start>0?start:0;
        Page4Navigator<User> page = userService.list(start,size,5);
        return page;
    }
}
