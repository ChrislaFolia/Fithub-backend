package com.fithub.model.cart;

import java.util.List;

public interface ICartService {
	
	// 查詢全部
	public List<Cart> findAll();

	// 新增單筆活動
	public Cart insert(Cart cart);

	// 修改單筆
	public Boolean update(Cart cart);

	// 刪除單筆
	public Boolean deleteById(Integer id);
	
	// 刪除多筆
	public void deleteAllById(Iterable<Integer> selectIds);

	// 確認id存在
	public Boolean exitsById(Integer id);
	
	// 查詢單筆
	public Cart findById(Integer id);

}
