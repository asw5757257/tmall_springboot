package com.zyw.tmall_springboot.web;

import com.zyw.tmall_springboot.pojo.Category;
import com.zyw.tmall_springboot.pojo.Result;
import com.zyw.tmall_springboot.pojo.User;
import com.zyw.tmall_springboot.service.CategoryService;
import com.zyw.tmall_springboot.service.ProductService;
import com.zyw.tmall_springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @program: tmall_springboot
 * @description:
 * @author: Cengyuwen
 * @create: 2019-08-03 16:25
 **/
@RestController
public class ForeRESTController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @GetMapping("/forehome")
    public Object home()
    {
        List<Category> categories = categoryService.list();
        productService.fill(categories);
        productService.fillByRow(categories);
        categoryService.removeCategoryFromProduct(categories);
        return categories;

    }
    @PostMapping("/foreregister")
    public Object register(@RequestBody User user)
    {
        String name = user.getName();
        String password = user.getPassword();
        name = HtmlUtils.htmlEscape(name);
        user.setName(name);
        boolean exist = userService.isExist(name);
        if(exist)
        {
            String message = "用户名已经被使用,不能使用";
            return Result.fail(message);
        }
        user.setPassword(password);
        userService.add(user);
        return Result.success();
    }
    @PostMapping("/forelogin")
    public Object login(@RequestBody User userParam, HttpSession session)
    {
        String name = userParam.getName();
        name = HtmlUtils.htmlEscape(name);
        User user = userService.get(name,userParam.getPassword());
        if(null == user)
        {
            String message = "账号密码错误";
            return Result.fail(message);
        }
        else
        {
            session.setAttribute("user",user);
            return Result.success();
        }
    }
    @GetMapping("/forelogout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:home";
    }
}
