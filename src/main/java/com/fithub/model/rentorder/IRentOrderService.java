package com.fithub.model.rentorder;

import java.util.List;

public interface IRentOrderService {

	// 查詢全部
	List<RentOrder> findAll();

	// 新增單筆活動
	RentOrder insert(RentOrder rentOrder);

	// 修改單筆
	void updateById(RentOrder rentOrder);

	// 刪除單筆
	void deleteById(Integer id);

	// 確認id存在
	Boolean findById(Integer id);

	RentOrder createRentOrderWithClassroom(RentOrder rentOrder);

}