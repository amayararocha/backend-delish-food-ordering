package com.delish.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delish.model.IngredientCategory;

public interface IngredientCategoryRepository extends JpaRepository<IngredientCategory, Long>{

}
