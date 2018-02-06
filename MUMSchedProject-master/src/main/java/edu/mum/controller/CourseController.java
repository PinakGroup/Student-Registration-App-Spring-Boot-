package edu.mum.controller;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.mum.domain.Course;
import edu.mum.service.CourseService;
import edu.mum.service.SpecializationsService;

//@Controller
//@RequestMapping("/admin/course")
//public class CourseController {
// 
//	@Autowired
//	
//	CourseService courseService;
//	
//	@Autowired
//	SpecializationsService specializationsService;
//
//	@PreAuthorize("hasRole('ROLE_Admin')")
//	@GetMapping("/all")
//	public String courseList( @ModelAttribute("newCourse") Course course,Model model)
//	{
//		model.addAttribute("courses",courseService.getAllCourser());
//		model.addAttribute("noPre","  ");
//		return "manageCourse";
//		
//	}
//	@PreAuthorize("hasRole('ROLE_Admin')")
//	@GetMapping("/add")
//	public String addCourse(@ModelAttribute("newCourse") Course course,Model model)
//	{
//		model.addAttribute("courseList",courseService.getAllCourser());
//		model.addAttribute("areaList",specializationsService.findAllspecalization());
//		return "addCourse";
//		
//	}
//	
//	@PostMapping("/add")
//	public String saveCourse(@Valid @ModelAttribute("newCourse") Course course,BindingResult result,Model model)
//	{
//		if(result.hasErrors())
//		{
//			if(!model.containsAttribute("courseList"))
//				model.addAttribute("courseList",courseService.getAllCourser());
//			if(!model.containsAttribute("areaList"))
//				model.addAttribute("areaList",specializationsService.findAllspecalization());
//			return "addCourse";
//		}
//		
//		course.setIsPreReq(false);
//		if(course.getPrerequisite()!=null){
//			course.getPrerequisite().forEach(c->c.setIsPreReq(true));
//		}
//		courseService.save(course);
//		
//		return "redirect:/admin/course/all";
//		
//	}
//	
//	@PreAuthorize("hasRole('ROLE_Admin')")
//	@GetMapping(value = "/update/{id}")
//	public String editCourse(@PathVariable("id") Long id, Model model) {
//		model.addAttribute("editedCourse", courseService.getCourseById(id));
//		model.addAttribute("courseList",courseService.getAllCourser());
//		model.addAttribute("areaList",specializationsService.findAllspecalization());
//		return "editCourse";
//	}
//	
//	@PreAuthorize("hasRole('ROLE_Admin')")
//	@PostMapping(value = "/update")
//	public String saveEditCourse(@Valid @ModelAttribute("editedCourse") Course course,BindingResult result,Model model) {
//		if(result.hasErrors())
//		{
//			if(!model.containsAttribute("courseList"))
//				model.addAttribute("courseList",courseService.getAllCourser());
//			if(!model.containsAttribute("areaList"))
//				model.addAttribute("areaList",specializationsService.findAllspecalization());
//			return "editCourse";
//		}
//		courseService.save(course);
//		return "redirect:/admin/course/all";
//	}
//	
//	
//	
//}

@Controller
@RequestMapping("/admin/course")
public class CourseController {
	
	private Long id_new;
	@Autowired
	private CourseService courseService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String getAllCourses(Model model) {
		List<Course> courses = this.courseService.getAllCourses();
		model.addAttribute("courses", courses);
		//return "course/list";
		return "manageCourse";
	}
   // undone
	@RequestMapping(value = "/course/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		model.addAttribute("courses", this.courseService.getAllCourses());
		model.addAttribute("course", this.courseService.readCourse(id));
		return "course/course";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String create(@Valid @ModelAttribute("course") Course course, Model model) {
		model.addAttribute("courseList", this.courseService.getAllCourses());
		//model.addAttribute("course", new Course());		
		return "addCourse";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("course") Course course, BindingResult result, Model model) {
		if (result.hasErrors()) {
			if(!model.containsAttribute("courseList"))
				model.addAttribute("courseList",courseService.getAllCourses());
			
			return "addCourse";
		}
		course.setIsPreReq(false);
		if(course.getPrerequisite()!=null){
			course.getPrerequisite().setIsPreReq(true);
		}
		course = this.courseService.save(course);
		return "redirect:/admin/course/all";
	}
	
	@RequestMapping(value= {"/update/{id}"},method=RequestMethod.POST)
	public String updateEntry(@RequestParam Long id, @RequestParam String title, @RequestParam int courseId, 
			@RequestParam Course Prerequisite){
		id_new = Long.valueOf(id);
		courseService.updateCourse(title, courseId, Prerequisite, id_new);
		
		
		return "redirect:/admin/course/all";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable Long id, Model model) {
		List<Course> coursesToRemove = this.courseService.coursesWithPrerequisite(id);
		for (Course course : coursesToRemove) {
			course.setPrerequisite(null);
			this.courseService.save(course);
		}
		this.courseService.delete(id);
		return "redirect:/admin/course/all";
	}
}

