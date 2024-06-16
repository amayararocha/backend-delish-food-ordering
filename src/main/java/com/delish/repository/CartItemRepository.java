package com.delish.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delish.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{

}
