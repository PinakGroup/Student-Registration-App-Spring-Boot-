package edu.mum.controller;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import edu.mum.domain.Course;
import edu.mum.service.CourseService;

@Controller
public class CourseController {
	@Autowired
	private CourseService courseService;

	@RequestMapping(value = "/admin/course/all", method = RequestMethod.GET)
	public String getAllCourses(Model model) {
		List<Course> courses = this.courseService.getAllCourses();
		model.addAttribute("courses", courses);
		return "manageCourse";
	}

	@RequestMapping(value = "/admin/course/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		model.addAttribute("courses",
				this.courseService.getAllCourses().stream().filter(c -> c.getId() != id).collect(Collectors.toList()));
		model.addAttribute("course", this.courseService.readCourse(id));
		model.addAttribute("pTitle", "Edit Course");
		return "addCourse";
	}

	@RequestMapping(value = "/admin/course/add", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("pTitle", "Add Course");
		model.addAttribute("courses", this.courseService.getAllCourses());
		model.addAttribute("course", new Course());
		return "addCourse";
	}

	@RequestMapping(value = "/course", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("course") Course course, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("errors", result.getAllErrors());
			return "addCourse";
		}
		course = this.courseService.save(course);
		return "redirect:/admin/course/all";
	}

	@RequestMapping(value = "admin/course/delete/{id}", method = RequestMethod.GET)
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
