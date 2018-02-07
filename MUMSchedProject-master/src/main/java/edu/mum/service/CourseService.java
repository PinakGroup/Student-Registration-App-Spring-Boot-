package edu.mum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.domain.Course;
import edu.mum.repository.CourseRepository;

@Service
public class CourseService implements ICourseService {

	@Autowired
	CourseRepository courseRepository;

	public List<Course> getAllCourses() {
		return this.courseRepository.findAll();
	}

	public Course readCourse(Long id) {
		return this.courseRepository.findOne(id);
	}

	public Course save(Course course) {
		return this.courseRepository.save(course);
	}

	public void delete(Long id) {
		this.courseRepository.delete(id);
	}

	public List<Course> coursesWithPrerequisite(Long id) {
		return this.courseRepository.coursesWithPrerequisite(id);
	}

	public List<Course> findCourseByTitleAndID(Long id, String title) {
		return this.courseRepository.findCourseByTitleAndID(id, title);
	}

	@Override
	public List<Course> getCoursesWithNoPrerequisite() {
		return this.courseRepository.getCoursesWithNoPrerequisite();
	}

}
