package com.zyw.tmall_springboot.web;

import com.zyw.tmall_springboot.pojo.Category;
import com.zyw.tmall_springboot.service.CategoryService;
import com.zyw.tmall_springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/forehome")
    public Object home()
    {
        List<Category> categories = categoryService.list();
        productService.fill(categories);
        productService.fillByRow(categories);
        categoryService.removeCategoryFromProduct(categories);
        return categories;

    }
}
