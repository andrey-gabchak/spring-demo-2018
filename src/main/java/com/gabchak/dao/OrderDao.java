package com.gabchak.dao;


import com.gabchak.model.Order;
import com.gabchak.model.User;

import java.util.List;

public interface OrderDao {

    void create(Order order);

    void update(Order order);

    void delete(Long id);

    Order findById(Long id);

    List<Order> findAll();

    Order findOpenOrderByUserId(Long id);

    Order findOpenOrderByUser(User user);
}