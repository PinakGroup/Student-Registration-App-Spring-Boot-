package edu.mum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.mum.domain.Course;

//@Repository
//public interface CourseRepository extends CrudRepository<Course, Long> {
//   public Course findByCourseName(String courseName);
//   
//}

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

	@Query(value = "select * from Course c  where c.prerequisite_id = ?1", nativeQuery = true)
	List<Course> coursesWithPrerequisite(Long id);
		
	@Query(value = "select * from Course c  where c.id = ?1 and c.title = ?2", nativeQuery = true)
	List<Course> findCourseByTitleAndID(Long id, String title);
	
	/*@Query(value = "update Course c set c.title = ?1, c.courseId = ?2, c.prerequisite = ?3 where c.id = ?4")
	public void update(@Param("title") String title
			, @Param("courseId") int courseId, @Param("prerequisite") Course prerequisite, @Param("id") Long id);*/
	
	@Query(value = "select * from Course c  where c.prerequisite_id = NULL", nativeQuery = true)
	List<Course> getCoursesWithNoPrerequisite();

	 @Query(value = "select * from Course c where c.title = ?1", nativeQuery = true)
	public Course findCourseByTitle(String courseName);

}
