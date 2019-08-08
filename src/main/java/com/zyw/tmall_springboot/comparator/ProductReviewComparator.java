package com.zyw.tmall_springboot.comparator;

import com.zyw.tmall_springboot.pojo.Product;

import java.util.Comparator;

/**
 * @program: tmall_springboot
 * @description:
 * @author: Cengyuwen
 * @create: 2019-08-06 16:45
 **/

public class ProductReviewComparator  implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return o2.getReviewCount()-o1.getReviewCount();
    }
}
