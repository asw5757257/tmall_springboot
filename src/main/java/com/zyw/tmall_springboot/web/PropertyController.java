package com.zyw.tmall_springboot.web;

import com.zyw.tmall_springboot.pojo.Property;
import com.zyw.tmall_springboot.service.PropertyService;
import com.zyw.tmall_springboot.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: tmall_springboot
 * @description:
 * @author: Cengyuwen
 * @create: 2019-07-30 15:20
 **/
@RestController
public class PropertyController {
    @Autowired
    private PropertyService propertyService;
    @GetMapping("/categories/{cid}/properties")
    public Page4Navigator<Property> list(@PathVariable("cid")int cid,@RequestParam(value = "start",defaultValue = "0")int start,
                                         @RequestParam(value = "size",defaultValue = "5")int size)
    {
        start = start>0?start:0;
        Page4Navigator<Property> page = propertyService.list(cid,start,size,5);
        return page;
    }
    @GetMapping("/properties/{id}")
    public Property get(@PathVariable("id")int id){
        Property bean=propertyService.get(id);
        return bean;
    }
    @PostMapping("/properties")
    public Object add(@RequestBody Property bean)
    {
           propertyService.add(bean);
           return bean;
    }
    @DeleteMapping("/properties/{id}")
    public String delete(@PathVariable("id")int id, HttpServletRequest request)
    {
        propertyService.delete(id);
        return null;
    }
    @PutMapping("/properties")
    public Object update(@RequestBody Property bean)
    {
        propertyService.update(bean);
        return bean;
    }

}
