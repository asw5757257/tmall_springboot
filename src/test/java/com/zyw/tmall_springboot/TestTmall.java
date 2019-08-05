package com.zyw.tmall_springboot;
   
import com.zyw.tmall_springboot.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
   
public class TestTmall {

    private PropertyService propertyService;
    public static void main(String args[]){

        //准备分类测试数据：
   
        /*try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
   
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/tmall_springboot?useUnicode=true&characterEncoding=utf8",
                        "root", "root");
                Statement s = c.createStatement();
        )
        {
            for (int i = 1; i <=10 ; i++) {
                String sqlFormat = "insert into property values (null, null,'测试属性%d')";
                String sql = String.format(sqlFormat, i);
                s.execute(sql);
            }
              
            System.out.println("已经成功创建10条分类测试数据");
   
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/


    }
   
}