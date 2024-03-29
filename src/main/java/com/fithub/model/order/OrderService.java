package com.fithub.model.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fithub.model.orderitem.OrderItem;
import com.fithub.model.orderitem.OrderItemRepository;

@Service
public class OrderService implements IOrderService {

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private OrderItemRepository orderItemRepo;

	@Override
	public List<Order> findAll() {
		return orderRepo.findAll();

	}

	@Override
	public Order insert(Order order) {
		return orderRepo.save(order);

	}

	@Override
	public Boolean update(Order order) {
		Boolean result = exitsById(order.getOrderId());
		if (result) {
			orderRepo.saveAndFlush(order);
			return true;
		}
		return false;
	}

	@Override
	public Boolean updateConditionById(Integer orderId, String orderCondition) {
		try {
			orderRepo.updateConditionById(orderId, orderCondition);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean deleteById(Integer id) {
		Boolean result = orderRepo.existsById(id);

		if (result) {
			orderRepo.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public Boolean exitsById(Integer id) {
		return orderRepo.existsById(id);

	}

	@Override
	public Order findById(Integer id) {
		Optional<Order> result = orderRepo.findById(id);
		return result.get();
	}

	@Override
	public void deleteAllById(Iterable<Integer> selectIds) {
		orderRepo.deleteAllById(selectIds);

	}

	@Override
	@Transactional
	public Order createOrder(Order order) {

		try {
			// 取得複製orderitem集合
			int i = 0;
			int[] classids = new int[order.getOrderItem().size()];
			int[] couponids = new int[order.getOrderItem().size()];

			for (OrderItem orderItem : order.getOrderItem()) {
				classids[i] = orderItem.getClassId();
				couponids[i] = orderItem.getCouponId();
				i++;
			}

			// 清空OrderItem
			order.setOrderItem(null);

			// 新增訂單取得訂單編號
			Order savedOrder = orderRepo.save(order);
			List<OrderItem> orderItems = new ArrayList<>();

			for (int x = 0; x < classids.length; x++) {
				// 建立訂單項目 塞入新集合
				OrderItem orderItem = new OrderItem();
				orderItem.setOrderId(savedOrder.getOrderId());
				orderItem.setClassId(classids[x]);

				// 只在第一筆訂單項目上設定couponId
				if (x == 0) {
					orderItem.setCouponId(couponids[x]);
				} else {
					// 在其他訂單項目上設為0或null，視情況而定
					orderItem.setCouponId(22); // 或者 orderItem.setCouponId(null);
				}

				orderItems.add(orderItem);
			}

			order.setOrderItem(orderItems);
			Order resultOrder = orderRepo.save(order);

			return resultOrder;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 查詢全部並分頁
	@Override
	public Page<Order> findAllPage(String date, int number, int size) {
		// 回傳第幾頁 每頁10筆
		Pageable pageable = PageRequest.of(number, size);
		return orderRepo.findAllPage(date, pageable);
	}

	@Override
	public Page<Order> findPageByMemberId(String date, Integer memberid, int number, int size) {
		Pageable pageable = PageRequest.of(number, size);
		return orderRepo.findPageByMemberId(date, memberid, pageable);
	}

	@Override
	public List<Order> getOrdersByMemberId(Integer memberid) {
		try {
			List<Order> resultBeans = orderRepo.getOrdersByMemberId(memberid);
			return resultBeans;
		} catch (Exception e) {
			return null;
		}
	}

	// 取得租借場地總金額
	@Override
	public int findOrdertotalAmount() {
		int total = 0;
		List<Map<String, Object>> result = orderRepo.findOrdertotalAmount();
		for (Map<String, Object> map : result) {
			if (map != null) {
				int orderTotalAmount = (int) map.get("orderTotalAmount");
				total = total + orderTotalAmount;
			}
		}
		return total;
	}

	// ChrisLafolia 返回在指定classId及memberId的的課程購買數量
	@Override
	public Map<String, Object> getAlreadyBuyAmountByClassIdANDMemberid(int classId, int memberId) {
		Map<String, Object> result = orderRepo.getAlreadyBuyAmountByClassIdANDMemberid(classId, memberId);
		if (result != null) {
			return result;
		}
		return null;
	}

}
