package com.zyw.tmall_springboot.web;

import com.zyw.tmall_springboot.comparator.*;
import com.zyw.tmall_springboot.pojo.*;
import com.zyw.tmall_springboot.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.util.*;

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
    @Autowired
    private ProductImageService productImageService;
    @Autowired
    private PropertyValueService propertyValueService;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private ReviewService reviewService;
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

    @GetMapping("/foreproduct/{pid}")
    public Object product(@PathVariable("pid")int pid)
    {
        Product product = productService.get(pid);
        List<ProductImage> productSingleImages = productImageService.listSingleProductImages(product);
        List<ProductImage> productDetailImages = productImageService.listDetailProductImages(product);
        product.setProductSingleImages(productSingleImages);
        product.setProductDetailImages(productDetailImages);
        List<PropertyValue> pvs = propertyValueService.list(product);
        List<Review> reviews = reviewService.list(product);
        productService.setSaleAndReviewNumber(product);
        productImageService.setFirstProdutImage(product);
        Map<String,Object> map= new HashMap<>();
        map.put("product", product);
        map.put("pvs", pvs);
        map.put("reviews", reviews);

        return Result.success(map);

    }
    @GetMapping("forecheckLogin")
    public Object checkLogin( HttpSession session) {
        User user =(User)  session.getAttribute("user");
        if(null!=user)
            return Result.success();
        return Result.fail("未登录");
    }
    @GetMapping("forecategory/{cid}")
    public Object category(@PathVariable("cid")int cid,String sort)
    {
        Category c = categoryService.get(cid);
        productService.fill(c);
        productService.setSaleAndReviewNumber(c.getProducts());
        categoryService.removeCategoryFromProduct(c);
        if(null!=sort)
        {
            switch (sort)
            {
                case "review":
                    Collections.sort(c.getProducts(), new ProductReviewComparator());
                    break;
                case "date":
                    Collections.sort(c.getProducts(),new ProductDateComparator());
                    break;
                case "saleCount" :
                    Collections.sort(c.getProducts(),new ProductSaleCountComparator());
                    break;
                case "price":
                    Collections.sort(c.getProducts(),new ProductPriceComparator());
                    break;
                case "all":
                    Collections.sort(c.getProducts(),new ProductAllComparator());
                    break;
            }
        }
        return c;
    }
    @PostMapping("foresearch")
    public Object search(String keyword)
    {
        if(null==keyword)
        {
            keyword="";
        }
        List<Product> list = productService.search(keyword,0,20);
        productImageService.setFirstProdutImages(list);
        productService.setSaleAndReviewNumber(list);
        return list;

    }
    @GetMapping("forebuyone")
    public Object buyone(int pid,int num,HttpSession session)
    {
        return buyoneAndAddCart(pid,num,session);
    }
    private Object buyoneAndAddCart(int pid,int num,HttpSession session)
    {
        Product product = productService.get(pid);
        int oiid = 0;
        User user = (User) session.getAttribute("user");
        boolean found = false;
        List<OrderItem> ois = orderItemService.listByUser(user);
        for(OrderItem orderItem:ois)
        {
            if(orderItem.getProduct().getId()==product.getId())
            {
                orderItem.setNumber(orderItem.getNumber()+num);
                orderItemService.update(orderItem);
                found = true;
                oiid = orderItem.getId();
                break;
            }
        }
        if(!found)
        {
            OrderItem orderItem = new OrderItem();
            orderItem.setUser(user);
            orderItem.setNumber(num);
            orderItem.setProduct(product);
            orderItemService.add(orderItem);
            oiid = orderItem.getId();
        }
        return oiid;
    }
    @GetMapping("forebuy")
    public Object buy(String[] oiid,HttpSession session)
    {
        List<OrderItem> orderItems = new ArrayList<>();
        float total = 0;
        for(String sid:oiid)
        {
            int id = Integer.parseInt(sid);
            OrderItem orderItem = orderItemService.get(id);
            total +=orderItem.getProduct().getPromotePrice()*orderItem.getNumber();
            orderItems.add(orderItem);

        }
        productImageService.setFirstProdutImagesOnOrderItems(orderItems);
        session.setAttribute("ois",orderItems);
        Map<String,Object> map = new HashMap<>();
        map.put("orderItems", orderItems);
        map.put("total", total);
        return Result.success(map);
    }
    @GetMapping("foreaddCart")
    public Object addCart(int pid,int num,HttpSession session)
    {
        buyoneAndAddCart(pid,num,session);
        return Result.success();
    }
    @GetMapping("forecart")
    public Object cart(HttpSession session)
    {
        User user = (User) session.getAttribute("user");
        List<OrderItem> orderItems = orderItemService.listByUser(user);
        productImageService.setFirstProdutImagesOnOrderItems(orderItems);
        return orderItems;
    }
    @GetMapping("forechangeOrderItem")
    public Object changeOrderItem(HttpSession session,int pid,int num)
    {
        User user = (User) session.getAttribute("user");
        if(null == user) return Result.fail("未登录");
        List<OrderItem> orderItems = orderItemService.listByUser(user);
        for(OrderItem orderItem:orderItems)
        {
            if(orderItem.getProduct().getId()==pid)
            {
                orderItem.setNumber(num);
                orderItemService.update(orderItem);
                break;
            }
        }
        return Result.success();
    }
    @GetMapping("foredeleteOrderItem")
    public Object deleteOrderItem(HttpSession session,int oiid)
    {
        User user = (User) session.getAttribute("user");
        if(null == user) return Result.fail("未登录");
        orderItemService.delete(oiid);
        return Result.success();
    }
}
