package com.zyw.tmall_springboot.service;

import com.zyw.tmall_springboot.dao.PropertyValueDAO;
import com.zyw.tmall_springboot.pojo.Product;
import com.zyw.tmall_springboot.pojo.Property;
import com.zyw.tmall_springboot.pojo.PropertyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: tmall_springboot
 * @description:
 * @author: Cengyuwen
 * @create: 2019-07-31 19:09
 **/
@Service
public class PropertyValueService {
    @Autowired
    private PropertyValueDAO propertyValueDAO;
    @Autowired
    private PropertyService propertyService;

    public void update(PropertyValue propertyValue)
    {
        propertyValueDAO.save(propertyValue);
    }
    public List<PropertyValue> list(Product product) {
        return propertyValueDAO.findByProductOrderByIdDesc(product);
    }
    public PropertyValue getByPropertyAndProduct(Product product, Property property) {
        return propertyValueDAO.getByPropertyAndProduct(property,product);
    }
    public void init(Product product)
    {
        List<Property> propertys= propertyService.listByCategory(product.getCategory());
        for (Property property: propertys) {
            PropertyValue propertyValue = getByPropertyAndProduct(product, property);
            if(null==propertyValue){
                propertyValue = new PropertyValue();
                propertyValue.setProduct(product);
                propertyValue.setProperty(property);
                propertyValueDAO.save(propertyValue);
            }
        }
    }
}
