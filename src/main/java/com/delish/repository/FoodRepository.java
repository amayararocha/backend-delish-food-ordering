package com.delish.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.delish.model.Food;

public interface FoodRepository extends JpaRepository<Food, Long>{

	List<Food> findByRestaurantId(Long restaurantId);
	
	@Query("SELECT f FROM Food f "
		     + "WHERE LOWER(f.name) LIKE LOWER(CONCAT('%', :keyword, '%')) "
		     + "OR LOWER(f.foodCategory.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
		List<Food> searchFood(@Param("keyword") String keyword);
}
