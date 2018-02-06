package edu.mum.service;

import java.util.List;
import org.springframework.stereotype.Service;

import edu.mum.domain.Course;



@Service
public interface ICourseService {	
	List<Course> getAllCourses();
	Course readCourse(Long id);
	Course save(Course course);
	void delete(Long id);
	List<Course> coursesWithPrerequisite(Long id);
	List<Course> findCourseByTitleAndID(Long id, String title);
	List<Course> getCoursesWithNoPrerequisite();
	
}
