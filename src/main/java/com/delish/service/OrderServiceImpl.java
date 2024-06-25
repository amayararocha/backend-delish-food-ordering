package com.delish.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.delish.model.Address;
import com.delish.model.Cart;
import com.delish.model.CartItem;
import com.delish.model.Order;
import com.delish.model.OrderItem;
import com.delish.model.Restaurant;
import com.delish.model.User;
import com.delish.repository.AddressRepository;
import com.delish.repository.OrderItemRepository;
import com.delish.repository.OrderRepository;
import com.delish.repository.UserRepository;
import com.delish.request.OrderRequest;

public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private CartService cartService;
	
	@Override
	public Order createOrder(OrderRequest order, User user) throws Exception {
		Address shipAddress = order.getDeliveryAddress();
		
		Address savedAddres = addressRepository.save(shipAddress);
		
		if(user.getAddresses().contains(savedAddres)) {
			user.getAddresses().add(savedAddres);
			userRepository.save(user);
		}
		
		Restaurant restaurant = restaurantService.findRestaurantById(order.getRestaurantId());
		
		Order createdOrder = new Order();
		createdOrder.setCustomer(user);
		createdOrder.setCreatedAt(new Date());
		createdOrder.setOrderStatus("PENDING");
		createdOrder.setDeliveryAddress(savedAddres);
		createdOrder.setRestaurant(restaurant);
		
		Cart cart = cartService.findCartByUserId(user.getId());
		
		List<OrderItem> orderItems = new ArrayList<>();
		
		for(CartItem cartItem : cart.getItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setFood(cartItem.getFood());
			orderItem.setIngredients(cartItem.getIngredients());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setTotalPrice(cartItem.getTotalPrice());
			
			OrderItem savedOrderItem = orderItemRepository.save(orderItem);
			orderItems.add(savedOrderItem);
		}
		
		Long totalPrice = cartService.calculateCartTotals(cart);
		
		createdOrder.setItems(orderItems);
		createdOrder.setTotalPrice(totalPrice);
		
		Order savedOrder = orderRepository.save(createdOrder);
		restaurant.getOrders().add(savedOrder);
		
		return createdOrder;
	}

	@Override
	public Order updateOrder(Long orderId, String orderStatus) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteOrder(Long orderId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Order> getUserOrder(Long userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getRestaurantsOrder(Long restaurantId, String orderStatus) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
