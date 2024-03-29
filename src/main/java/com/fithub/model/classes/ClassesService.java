package com.fithub.model.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClassesService implements IClassesService {

	@Autowired
	private ClassesRepository classesRepo;

	@Override
	public List<Classes> findAll() {
		return classesRepo.findAll();

	}

	@Override
	public Classes insert(Classes classes) {
		return classesRepo.save(classes);

	}

	@Override
	public Boolean update(Classes classes) {
		Boolean result = exitsById(classes.getClassId());
		if (result) {
			classesRepo.saveAndFlush(classes);
			return true;
		}
		return false;
	}

	@Override
	public Boolean deleteById(Integer id) {
		Boolean result = classesRepo.existsById(id);

		if (result) {
			classesRepo.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public void deleteAllById(Iterable<Integer> selectIds) {
		classesRepo.deleteAllById(selectIds);

	}

	@Override
	@Transactional
	public void deleteAllByCourseId(Integer courseId) {
		classesRepo.deleteAllByCourseId(courseId);
	}

	@Override
	public Boolean exitsById(Integer id) {
		return classesRepo.existsById(id);

	}

	@Override
	public Boolean exitsByCourseId(Integer courseId) {
		return classesRepo.exitsByCourseId(courseId);
	}

	@Override
	public Classes findById(Integer id) {
		Optional<Classes> result = classesRepo.findById(id);
		return result.get();
	}

	@Override
	public List<Object[]> findAllclassDateAndclassTimeByClassroomId(Integer classroomId) {
		return classesRepo.findAllclassDateAndclassTimeByClassroomId(classroomId);
	}

	@Override
	public Classes checkClass(Integer classroomId, String classDate, String classTime) {
		return classesRepo.checkClass(classroomId, classDate, classTime);
	}

	@Override
	public List<ClassesDto> findAllByCourseIdAndDateRange(Integer courseId, String startDate, String endDate) {

		List<Object[]> resultList = classesRepo.findAllByCourseIdAndDateRange(courseId, startDate, endDate);
		List<ClassesDto> classesList = putObjectIntoDto(resultList);

		return classesList;
	}

	@Override
	public List<ClassesDto> findAllByDateRange(String startDate, String endDate) {
		List<Object[]> resultList = classesRepo.findAllByDateRange(startDate, endDate);
		List<ClassesDto> classesList = putObjectIntoDto(resultList);

		return classesList;
	}

	@Override
	public List<ClassesDto> findClassesByClassesId(List<Integer> classesIds) {
		List<Object[]> resultList = classesRepo.findClassesByClassesId(classesIds);
		List<ClassesDto> classesList = putObjectIntoDto(resultList);
		return classesList;
	}

	@Override
	public List<Map<String, Object>> findWishlistClassesByMemberId(int memberId) {
		System.out.println(memberId);
		try {
			List<Map<String, Object>> result = classesRepo.findWishlistClassesByMemberId(memberId);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Page<Classes> findByPage(Integer pageNumber, Integer dataSize) {
		// 按照courseId降冪排序
		PageRequest pgb = PageRequest.of(pageNumber - 1, dataSize, Sort.Direction.DESC, "classdate");

		Page<Classes> page = classesRepo.findAll(pgb);
		return page;
	}

	@Override
	public Page<Map<String, Object>> findAllByDateRangeInPage(String startDate, String endDate, Integer pageNumber,
			Integer dataSize) {
		// 按照classDate升冪排序
		PageRequest pgb = PageRequest.of(pageNumber - 1, dataSize, Sort.Direction.ASC, "classDate");
		Page<Map<String, Object>> page = classesRepo.findAllByDateRangeInPage(startDate, endDate, pgb);

		return page;
	}

	@Override
	public Page<Map<String, Object>> findByDateRangeAndCategoryIdInPage(int categoryId, String startDate,
			String endDate, Integer pageNumber, Integer dataSize) {
		// 按照classDate升冪排序
		PageRequest pgb = PageRequest.of(pageNumber - 1, dataSize, Sort.Direction.ASC, "classDate");
		Page<Map<String, Object>> page = classesRepo.findByDateRangeAndCategoryIdInPage(categoryId, startDate, endDate,
				pgb);

		return page;
	}

	private List<ClassesDto> putObjectIntoDto(List<Object[]> inputList) {
		List<ClassesDto> classesList = new ArrayList<>();

		for (Object[] result : inputList) {
			// Assuming the first element of the result array is the employee title and the
			// second is the employee ID
			int classId = (int) result[0];
			int courseIdResult = (int) result[1];
			String classDate = (String) result[2];
			String classTime = (String) result[3];
			int coachSubstitute = (int) result[4];
			int employeeId = (int) result[5];
			int applicantsCeil = (int) result[6];
			int applicantsFloor = (int) result[7];
			int price = (int) result[8];
			int classroomId = (int) result[9];
			String courseName = (String) result[10];
			String categoryName = (String) result[11];
			String employeename = (String) result[12];
			String classroomName = (String) result[13];
			int classroomCapacity = (int) result[14];

			ClassesDto classes = new ClassesDto();
			classes.setClassId(classId);
			classes.setCourseId(courseIdResult);
			classes.setClassDate(classDate);
			classes.setClassTime(classTime);
			classes.setCoachSubstitute(coachSubstitute);
			classes.setClassroomId(classroomId);
			classes.setEmployeeId(employeeId);
			classes.setApplicantsCeil(applicantsCeil);
			classes.setApplicantsFloor(applicantsFloor);
			classes.setPrice(price);
			classes.setClassroomId(classroomId);
			classes.setCourseName(courseName);
			classes.setCategoryName(categoryName);
			classes.setEmployeename(employeename);
			classes.setClassroomName(classroomName);
			classes.setClassroomCapacity(classroomCapacity);

			classesList.add(classes);
		}

		return classesList;
	}

}
