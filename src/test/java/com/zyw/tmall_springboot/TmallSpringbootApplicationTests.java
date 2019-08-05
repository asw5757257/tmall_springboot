package com.zyw.tmall_springboot;

import com.zyw.tmall_springboot.dao.ProductDAO;
import com.zyw.tmall_springboot.dao.PropertyDAO;
import com.zyw.tmall_springboot.pojo.Product;
import com.zyw.tmall_springboot.pojo.Property;
import com.zyw.tmall_springboot.service.CategoryService;
import com.zyw.tmall_springboot.service.PropertyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TmallSpringbootApplicationTests {
    @Autowired
    private ProductDAO productDAO;
    @Test
    public void contextLoads() {
        System.out.println(productDAO.findOne(1));
    }

}
