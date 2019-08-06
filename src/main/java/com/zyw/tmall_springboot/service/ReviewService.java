package com.zyw.tmall_springboot.service;

import com.zyw.tmall_springboot.dao.ReviewDAO;
import com.zyw.tmall_springboot.pojo.Product;
import com.zyw.tmall_springboot.pojo.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: tmall_springboot
 * @description:
 * @author: Cengyuwen
 * @create: 2019-08-05 17:57
 **/
@Service
public class ReviewService {
    @Autowired
    private ReviewDAO reviewDAO;
    @Autowired
    private ProductService productService;
    public void add(Review review)
    {
        reviewDAO.save(review);
    }
    public List<Review> list(Product product)
    {
        List<Review> list = reviewDAO.findByProductOrderByIdDesc(product);
        return list;
    }
    public int getCount(Product product)
    {
        return reviewDAO.countByProduct(product);
    }
}
