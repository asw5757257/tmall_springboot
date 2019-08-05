package com.zyw.tmall_springboot.web;

import com.zyw.tmall_springboot.pojo.Product;
import com.zyw.tmall_springboot.pojo.PropertyValue;
import com.zyw.tmall_springboot.service.ProductService;
import com.zyw.tmall_springboot.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: tmall_springboot
 * @description:
 * @author: Cengyuwen
 * @create: 2019-08-01 15:32
 **/
@RestController
public class PropertyValueController {
    @Autowired
    private PropertyValueService propertyValueService;
    @Autowired
    private ProductService productService;
    @GetMapping("/products/{pid}/propertyValues")
    public List<PropertyValue> list(@PathVariable("pid")int pid)
    {
        Product product= productService.get(pid);
        propertyValueService.init(product);
        List<PropertyValue> propertyValues = propertyValueService.list(product);
        return propertyValues;
    }
    @PutMapping("/propertyValues")
    public Object update(@RequestBody PropertyValue propertyValue)
    {
        propertyValueService.update(propertyValue);
        return propertyValue;
    }
}
