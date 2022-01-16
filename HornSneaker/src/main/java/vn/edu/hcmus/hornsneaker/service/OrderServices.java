package vn.edu.hcmus.hornsneaker.service;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import vn.edu.hcmus.hornsneaker.dao.domain.CartEntity;
import vn.edu.hcmus.hornsneaker.dao.domain.CartEntry;
import vn.edu.hcmus.hornsneaker.dao.domain.OrderDetailsEntity;
import vn.edu.hcmus.hornsneaker.dao.domain.OrderEntity;
import vn.edu.hcmus.hornsneaker.dao.domain.ProductEntity;
import vn.edu.hcmus.hornsneaker.dao.domain.UserAccountEntity;
import vn.edu.hcmus.hornsneaker.dao.repository.CartRepository;
import vn.edu.hcmus.hornsneaker.dao.repository.OrderDetailsRepository;
import vn.edu.hcmus.hornsneaker.dao.repository.OrderRepository;


@Service
public class OrderServices {
	@Autowired
	private OrderRepository orderRepo;
	@Autowired
	private OrderDetailsRepository orderDetailsRepo;
	@Autowired
	private CartRepository cartRepo;
	@Autowired
	private UserAccountServices userAccountServices;

	public OrderEntity findById(Long id) {
		return orderRepo.getById(id);		
	}
	
	public UserAccountEntity getUserInfo(String email) {
		return userAccountServices.getUserAccountByEmail(email);
	}
	
	public List<OrderDetailsEntity> findByOrderId(Long id){
		return orderDetailsRepo.findByOrderId(id);
	}

	public List<OrderEntity> findAll() {
		return orderRepo.findAll();
	}

	public List<OrderEntity> findAll(int i) {
		List<OrderEntity> list = orderRepo.findAll(Sort.by(Direction.ASC,"created"));
		if (i >= list.size()) i = list.size();
		if (i < 0) return null;
		return list.subList(0, i);
	}

    public void add(OrderEntity news) {
    	orderRepo.save(news);
    }

	public void delete(Long id) {
		orderRepo.deleteById(id);
	}
	
	public static String getUserName() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && !auth.getPrincipal().equals("anonymousUser")) {
			User user = (User) auth.getPrincipal();
			return user.getUsername();
		}
		return "";
	}
	
	public static Date getDate() {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        return date;
	}
	
	Long getLoggedUserId() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && !auth.getPrincipal().equals("anonymousUser")) {
			User user = (User) auth.getPrincipal();
			UserAccountEntity userAccount = userAccountServices.getUserAccountByEmail(user.getUsername());
			return userAccount.getId();
		}
		return -1L;
	}
	
	public Long findOrderId() {	
		String UserName = OrderServices.getUserName();
		List<OrderEntity> orderList = orderRepo.findAll();
		for (OrderEntity order : orderList) {
			if (order.getCustomer().equals(UserName)) {
				return order.getId();
			}
		}
		return 1L;
	}
	
	public void createOrder() {
		OrderEntity order = new OrderEntity();
		System.out.println("order id" + order.getId());
		order.setCustomer(OrderServices.getUserName());
		order.setDate(OrderServices.getDate());
		order.setStatus("Chờ xác nhận");
		orderRepo.save(order);	
		
		Long userId = getLoggedUserId();
		
		List<CartEntity> all = cartRepo.findAll(Sort.by("id"));;		
		for (CartEntity cartEntity : all) {
			if (cartEntity.getUserId().equals(userId)) {
				OrderDetailsEntity orderDetail = new OrderDetailsEntity();
				orderDetail.setProductId(cartEntity.getProductId());
				orderDetail.setOrderId(order.getId());
				orderDetailsRepo.save(orderDetail);
			}
		}
	}
}