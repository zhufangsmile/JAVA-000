package com.zf.demo.api;

/**
 * @author zhufang
 * @date 2020/12/13 6:54 下午
 */
public class User {
    private Integer id;
    private String name;

    public User(Integer id, String name, Order order) {
        this.id = id;
        this.name = name;
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
}
