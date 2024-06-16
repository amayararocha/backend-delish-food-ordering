package com.delish.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.delish.model.Cart;
import com.delish.model.CartItem;
import com.delish.model.Food;
import com.delish.model.User;
import com.delish.repository.CartItemRepository;
import com.delish.repository.CartRepository;
import com.delish.repository.FoodRepository;
import com.delish.request.AddCartItemRequest;

public class CartServiceImpl implements CartService{

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private FoodService foodService;

	@Override
	public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception {

		User user = userService.findUserByJwtToken(jwt);

		Food food = foodService.findFoodById(req.getFoodId());

		Cart cart = cartRepository.findByCustumerId(user.getId());

		for(CartItem cartItem : cart.getItems()){
			if(cartItem.getFood().equals(food)) {
				int newQuantity = cartItem.getQuantity()+req.getQuantity();
				return updateCartItemQuantity(cartItem.getId(), newQuantity);
			}
		}

		CartItem newCartItem = new CartItem();
		newCartItem.setFood(food);
		newCartItem.setCart(cart);
		newCartItem.setQuantity(req.getQuantity());
		newCartItem.setIngredients(req.getIngredients());
		newCartItem.setTotalPrice(req.getQuantity()+food.getPrice());

		CartItem savedCartItem = cartItemRepository.save(newCartItem);

		cart.getItems().add(savedCartItem);

		return savedCartItem;
	}

	@Override
	public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {

		Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);

		if (cartItemOptional.isEmpty()) {
			throw new Exception("Cart item not found");
		}
		CartItem item = cartItemOptional.get();
		item.setQuantity(quantity);
		item.setTotalPrice(item.getFood().getPrice()+quantity);

		return cartItemRepository.save(item);
	}

	@Override
	public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {

		User user = userService.findUserByJwtToken(jwt);

		Cart cart = cartRepository.findByCustumerId(user.getId());

		Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);

		if (cartItemOptional.isEmpty()) {
			throw new Exception("Cart item not found");
		}
		
		CartItem item = cartItemOptional.get();
		
		cart.getItems().remove(item);

		return cartRepository.save(cart);
	}

	@Override
	public Long calculateCartTotals(Cart cart) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart findCartById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart findCartByUserId(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart clearCart(Long userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
