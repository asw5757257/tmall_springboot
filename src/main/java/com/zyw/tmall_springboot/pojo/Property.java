package com.zyw.tmall_springboot.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.annotation.Generated;
import javax.persistence.*;

/**
 * @program: tmall_springboot
 * @description:
 * @author: Cengyuwen
 * @create: 2019-07-30 14:58
 **/
@Entity
@Table(name="property")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;



    @Column(name="name")
    private  String name;

    @ManyToOne
    @JoinColumn(name="cid")

    private  Category category;

    public Property() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Property [id=" + id + ", name=" + name + ", category=" + category + "]";
    }
}
