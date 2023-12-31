package com.fithub.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fithub.model.activity.Activity;
import com.fithub.model.activity.ActivityRepository;
import com.fithub.model.activity.IActivityService;
import com.fithub.model.activitypic.ActivityPic;
import com.fithub.model.activitypic.IActivityPicService;
import com.fithub.model.employee.IEmployeeService;

@CrossOrigin
@RestController
@RequestMapping("/activity")
public class ActivityController {

	@Autowired
	private IActivityService iActivityService;

	@Autowired
	private IEmployeeService iEmployeeService;



	// 列出所有活動
	@GetMapping("/list")
	public ResponseEntity<?> findAllActivities() {
		try {
			List<Activity> activities = iActivityService.findAll();
			return new ResponseEntity<>(activities, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 列出篩選顯示為是活動並依照排序(數字越大優先)
	@GetMapping("/filteredAndSortedActivities")
	public ResponseEntity<?> filteredAndSortedActivities() {
		
		LocalDate today = LocalDate.now();
		Date sqlDate = Date.valueOf(today);
		
		try {
			List<Map<String, Object>> result = iActivityService.filteredAndSortedActivities(sqlDate);
//			for (Map<String, Object> map : result) {
//			    for (Map.Entry<String, Object> entry : map.entrySet()) {
//			        String key = entry.getKey();
//			        Object value = entry.getValue();
//			        System.out.println("Key: " + key + ", Value: " + value);
//			    }
//			}
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 列出所有員工姓名和編號
	@GetMapping("/findAllemployeenameAndemployeeid")
	public ResponseEntity<?> findAllemployeenameAndemployeeid() {
		try {
			List<Object[]> AllemployeenameAndemployeeid = iEmployeeService.findAllemployeenameAndemployeeid();
			return new ResponseEntity<>(AllemployeenameAndemployeeid, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 取得活動內容
	@GetMapping("/findDescriptionDateNameById/{activityid}")
	public ResponseEntity<?> findDescriptionDateNameById(@PathVariable("activityid") String activityid) {
		try {
			Map<String, Object> result = iActivityService.findDescriptionDateNameById(activityid);

			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 新增單筆活動
	@PostMapping("/insert")
	public ResponseEntity<?> insertActivity(@RequestBody Activity activity) {
		try {
			iActivityService.insert(activity);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 更新單筆活動
	@PutMapping("/update")
	public ResponseEntity<?> updateActivity(@RequestBody Activity activity) {

		try {
			iActivityService.updateById(activity);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 刪除單筆活動
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteActivity(@PathVariable("id") int id) {
		try {
			iActivityService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 刪除多筆活動
	@DeleteMapping("/delete/multiple")
	public ResponseEntity<?> deleteMultipleActivities(@RequestBody List<Integer> ids) {
		try {
			iActivityService.deleteAllById(ids);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
