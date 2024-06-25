package com.delish.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delish.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
