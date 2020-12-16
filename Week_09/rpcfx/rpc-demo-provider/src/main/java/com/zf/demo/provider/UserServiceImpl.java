package com.zf.demo.provider;

import com.zf.demo.api.Order;
import com.zf.demo.api.User;
import com.zf.demo.api.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public User findById(int id) {
        Order order = new Order(1, "d", 1.0f);
        return new User(id, "KK" + System.currentTimeMillis(), order);
    }
}
