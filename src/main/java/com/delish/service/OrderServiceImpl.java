package com.delish.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

		Order order = findOrderById(orderId);
		if (orderStatus.equals("OUT_FOR_DELIVERY") 
				|| orderStatus.equals("DELIVERED") 
				|| orderStatus.equals("COMPLETED")
				|| orderStatus.equals("PENDING")) {
			order.setOrderStatus(orderStatus);
			return orderRepository.save(order);
		}
		throw new Exception("Please select a valid order status");
	}

	@Override
	public List<Order> getUserOrder(Long userId) throws Exception {

		return orderRepository.findByCustomerId(userId);
	}

	@Override
	public List<Order> getRestaurantsOrder(Long restaurantId, String orderStatus) throws Exception {
		List<Order> orders = orderRepository.findByRestaurantId(restaurantId);
		if (orderStatus != null) {
			orders = orders.stream()
					.filter(order -> order.getOrderStatus().equals(orderStatus))
					.collect(Collectors.toList());
		}
		return orders;
	}


	@Override
	public Order findOrderById(Long orderId) throws Exception {

		Optional<Order> optionalOrder = orderRepository.findById(orderId);
		if(optionalOrder.isEmpty()) {
			throw new Exception("Order not found");
		}

		return optionalOrder.get();
	}

	@Override
	public void cancelOrder(Long orderId) throws Exception {

		Order order = findOrderById(orderId);
		orderRepository.deleteById(orderId);

	}
}
