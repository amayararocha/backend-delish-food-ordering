package com.delish.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delish.model.IngredientsItem;

public interface IngredientItemRepository extends JpaRepository<IngredientsItem, Long> {

}
