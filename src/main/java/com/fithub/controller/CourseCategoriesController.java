package com.fithub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fithub.model.coursecategories.CourseCategories;
import com.fithub.model.coursecategories.ICourseCategoriesService;

@RestController
@RequestMapping("/coursecategories")
@CrossOrigin()
public class CourseCategoriesController {

	
	@Autowired
	private ICourseCategoriesService cService;
	
	@GetMapping("/{cid}")
	public CourseCategories findCourseCategory(@PathVariable("cid") int cid) {
		return cService.findById(cid); 
	}
	
	@GetMapping("/findAll")
	public List<CourseCategories> findAllCourseCategories() {
		return cService.findAll();
	}
	
	@PostMapping
	public CourseCategories insertCourseCategories(@RequestBody CourseCategories cBean) {	
		return cService.insert(cBean);
	}	
	
	@PutMapping
	public Boolean updateCourseCategories(@RequestBody CourseCategories cBean) {	
		return cService.update(cBean);
	}	
	
	@DeleteMapping("/{cid}")
	public Boolean deleteCourse(@PathVariable("cid") int cid) {
		return cService.deleteById(cid);
						
	}
}
