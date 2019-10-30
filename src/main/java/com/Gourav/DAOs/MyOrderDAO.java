package com.Gourav.DAOs;

import java.util.List;

import org.springframework.stereotype.Component;

import com.Gourav.models.MyOrder;

@Component
public interface MyOrderDAO
{
     public MyOrder addOrder(MyOrder order);
     public MyOrder updateOrder(MyOrder order);
     public long getOrderId();
     public MyOrder getOrderById(long orderId);
	 public List<MyOrder> getAllOrders(String email, String orderStatus);
	 public List<MyOrder> getAllOrdersAdmin(String orderStatus);
	 public void updateAllOrdersByProductId(String productId);
}
