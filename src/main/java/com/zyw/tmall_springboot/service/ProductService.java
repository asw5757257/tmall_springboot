package com.zyw.tmall_springboot.service;

import com.zyw.tmall_springboot.dao.ProductDAO;
import com.zyw.tmall_springboot.pojo.Category;
import com.zyw.tmall_springboot.pojo.Product;
import com.zyw.tmall_springboot.util.Page4Navigator;
import org.apache.tomcat.jni.Proc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: tmall_springboot
 * @description:
 * @author: Cengyuwen
 * @create: 2019-07-30 17:49
 **/
@Service
public class ProductService {
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private  CategoryService categoryService;
    @Autowired
    private ProductImageService productImageService;
    public void add(Product bean)
    {
        productDAO.save(bean);
    }
    public void delete(int id)
    {
        productDAO.delete(id);
    }
    public Product get(int id)
    {
        return productDAO.findOne(id);

    }
    public void update(Product bean)
    {
        productDAO.save(bean);
    }
    public Page4Navigator<Product> list(int cid, int start, int size, int navigatePages)
    {
        Category category = categoryService.get(cid);
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        Pageable pageable = new PageRequest(start,size,sort);
        Page<Product> pageFromJPA = productDAO.findByCategory(category,pageable);
        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }
    public List<Product> listByCategory(Category category)
    {
        return productDAO.findByCategoryOrderById(category);
    }
    public void fill(Category category)
    {
        List<Product> products = listByCategory(category);
        productImageService.setFirstProdutImages(products);
        category.setProducts(products);

    }
    public void fill(List<Category> categories)
    {
        for(Category category:categories)
        {
            fill((category));
        }
    }
    public void fillByRow(List<Category> categories)
    {
        int productNumberEachRow = 8;
        for(Category category:categories)
        {
            List<Product> products = category.getProducts();
            List<List<Product>> productsByRow =  new ArrayList<>();
            for (int i = 0; i < products.size(); i+=productNumberEachRow) {
                int size = i+productNumberEachRow;
                size= size>products.size()?products.size():size;
                List<Product> productsOfEachRow =products.subList(i, size);
                productsByRow.add(productsOfEachRow);
            }
            category.setProductsByRow(productsByRow);
        }
    }
}
