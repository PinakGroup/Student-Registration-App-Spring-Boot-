package edu.mum.controller;
r
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import edu.mum.domain.Section;
import edu.mum.service.BlockService;
import edu.mum.service.CourseService;
import edu.mum.service.FacultyService;
import edu.mum.service.SectionService;

@Controller
public class SectionController {
	@Autowired
	private CourseService courseService;

	@Autowired
	private SectionService sectionService;

	@Autowired
	private BlockService blockService;

	@Autowired
	private FacultyService facultyService;

	@RequestMapping(value = "/admin/section/all", method = RequestMethod.GET)
	public String getAllSections(Model model) {
		List<Section> sections = this.sectionService.getAllSections();
		model.addAttribute("sections", sections);
		return "manageSection";
	}

	@RequestMapping(value = "/admin/section/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		model.addAttribute("courses", this.courseService.getAllCourses());
		model.addAttribute("blocks", this.blockService.getAllBlock());
		model.addAttribute("faculties", this.facultyService.getAllfaculty());
		model.addAttribute("section", this.sectionService.readSection(id));
		model.addAttribute("pTitle", "Edit Section");
		return "addSection";
	}

	@RequestMapping(value = "/admin/section/add", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("courses", this.courseService.getAllCourses());
		model.addAttribute("blocks", this.blockService.getAllBlock());
		model.addAttribute("faculties", this.facultyService.getAllfaculty());
		model.addAttribute("section", new Section());
		model.addAttribute("pTitle", "Add Section");
		return "addSection";
	}

	// no error handling so far
	@RequestMapping(value = "/section", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("section") Section section, BindingResult result, Model model) {
		if (result.hasErrors()) {
			// model.addAttribute("errors", result.getAllErrors());
			return "section/section";
		}
		section = this.sectionService.save(section);
		return "redirect:/admin/section/all";
	}

	@RequestMapping(value = "admin/section/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable Long id, Model model) {
		this.sectionService.delete(id);
		return "redirect:/admin/section/all";
	}
}
