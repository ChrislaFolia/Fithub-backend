package com.fithub.model.course;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

public interface ICourseService {

	// 查詢全部
	public List<Course> findAll();

	// 新增單筆活動
	public Course insert(Course course);

	// 修改單筆
	public Boolean update(Course course);

	// 刪除單筆
	@Transactional
	public Boolean deleteById(Integer id);

	// 刪除多筆
	@Transactional
	public void deleteAllById(Iterable<Integer> selectIds);

	// 確認id存在
	public Boolean exitsById(Integer id);

	// 查詢單筆
	public Course findById(Integer id);

	// 全部課程分頁功能
	public Page<Course> findByPage(Integer pageNumber, Integer dataSize);

	// 各分類課程分頁功能
	public Page<Course> findCourseByCategoryId(Integer categoryId, Integer pageNumber, Integer dataSize);

}
