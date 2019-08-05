package com.zyw.tmall_springboot.service;

import com.zyw.tmall_springboot.dao.CategoryDAO;
import com.zyw.tmall_springboot.dao.PropertyDAO;
import com.zyw.tmall_springboot.pojo.Category;
import com.zyw.tmall_springboot.pojo.Property;
import com.zyw.tmall_springboot.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @program: tmall_springboot
 * @description:
 * @author: Cengyuwen
 * @create: 2019-07-30 15:08
 **/
@Service
public class PropertyService {
    @Autowired
    private PropertyDAO propertyDAO;
    @Autowired
    private CategoryService categoryService;
    public void add(Property bean)
    {
        propertyDAO.save(bean);
    }

    public void delete(int id)
    {
        propertyDAO.delete(id);
    }

    public Property get(int id) {
        return propertyDAO.findOne(id);
    }

    public void update(Property bean)
    {
        propertyDAO.save(bean);
    }

    public Page4Navigator<Property> list(int cid,int start,int size ,int navigatePages)
    {
        Category category = categoryService.get(cid);
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start,size,sort);
        Page<Property> pageFromJPA = propertyDAO.findByCategory(category,pageable);
        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }
    public List<Property> listByCategory(Category category){
        return propertyDAO.findByCategory(category);
    }
}
