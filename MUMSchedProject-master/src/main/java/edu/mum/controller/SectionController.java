package edu.mum.controller;

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
import org.springframework.web.bind.annotation.RequestParam;

import edu.mum.domain.Block;
import edu.mum.domain.Course;
import edu.mum.domain.Faculty;
import edu.mum.domain.Section;
import edu.mum.service.BlockService;
import edu.mum.service.CourseService;
import edu.mum.service.FacultyService;
import edu.mum.service.SectionService;

//@Controller
//@RequestMapping("/admin")
//public class SectionController {
//
//	@Autowired
//	private SectionsService sectionService;
//	
//	@Autowired
//	private CourseService courseService;
//	
//	@Autowired
//	private BlockService blockService;
//	
//	@Autowired
//	private FacultyService facultyService;
//	
//	
//	
//	@RequestMapping({"/addSectionForm"})
//	public  String viewSection(Model model){
//		List<Course> courses = courseService.getAllCourser();
//		List<Faculty> faculties = facultyService.getAllfaculty();
//		List<Block> blocks = blockService.getAllBlock();
//		model.addAttribute("courses", courses);
//		model.addAttribute("faculties", faculties);
//		model.addAttribute("blocks", blocks);
//		return "addSection";
//	}	
//	
//	@RequestMapping(value= {"/addSection"},method=RequestMethod.POST)
//	public String saveSectioin(@RequestParam String sectionCode, @RequestParam String course, 
//			@RequestParam String faculty, @RequestParam String blockMonth,  @RequestParam int limitCapacity, Model model){	
//		
//		Block block = blockService.getBlock(blockMonth);
//		Faculty facultyAssigned = facultyService.getFacultyByName(faculty);
//		Course courseOffered = courseService.getCourseByName(course);
//		
//		Section newSection = new Section();
//		newSection.setBlock(block);
//		newSection.setCourse(courseOffered);
//		newSection.setFaculty(facultyAssigned);
//	
//		newSection.setSectionCode(sectionCode);
//		newSection.setLimitCapacity(limitCapacity);
//		
//		block.getSections().add(newSection);
//		
//		blockService.saveBlock(block, block.getEntry().getId());
//		model.addAttribute("block_id", block.getId());
//		model.addAttribute("sections", block.getSections());
//			
//		return "sectionList";
//	}
//	
//	@RequestMapping(value= {"/listSections"}, method=RequestMethod.POST)
//	public String listSectioin(@RequestParam String block_id, Model model){	
//		Block block = blockService.getBlockById(Long.parseLong(block_id));
//		List<Section> sections = block.getSections();
//		model.addAttribute("block_id", block_id);
//		model.addAttribute("sections", sections);	
//		return "sectionList";
//	}
//	
//	@RequestMapping(value= {"/findSection"},method=RequestMethod.POST)
//	public String findBlock(@RequestParam String id, Model model){
//		
//		Section sectionUpdateable = sectionService.getSectionById(new Long(id));
//		model.addAttribute("sectionUpdateable", sectionUpdateable);
//		
//		List<Course> courses = courseService.getAllCourser();
//		List<Faculty> faculties = facultyService.getAllfaculty();
//		List<Block> blocks = blockService.getAllBlock();
//		model.addAttribute("courses", courses);
//		model.addAttribute("faculties", faculties);
//		model.addAttribute("blocks", blocks);
//		
//		return "updateSectionForm";
//	}
//	
//	@RequestMapping(value= {"/updateSection"},method=RequestMethod.POST)
//	public String updateSection(@RequestParam String sectionCode, @RequestParam String course, 
//			@RequestParam String faculty, @RequestParam String blockMonth,  @RequestParam int limitCapacity, 
//			@RequestParam String section_id, Model model){
//		Block block = blockService.getBlock(blockMonth);
//		Faculty facultyAssigned = facultyService.getFacultyByName(faculty);
//		Course courseOffered = courseService.getCourseByName(course);
//		
//		Section section = sectionService.getSectionById(new Long(section_id));
//		section.setSectionCode(sectionCode);
//		section.setLimitCapacity(limitCapacity);
//		section.setBlock(block);
//		section.setCourse(courseOffered);
//		section.setFaculty(facultyAssigned);
//		
//		sectionService.saveSection(section);
//		
//		model.addAttribute("block_id", block.getId());
//		model.addAttribute("sections", block.getSections());
//		
//		return "sectionList";
//	}	
//	
//	@RequestMapping(value= {"/deleteSection"},method=RequestMethod.POST)
//	public String deleteSection(@RequestParam String section_id, @RequestParam String block_id, Model model) {
//		Block block = blockService.getBlockById(new Long(block_id));
//		List<Section> sections = block.getSections();
//		Long newSectionId = Long.parseLong(section_id);
//		for(int i=0;i<sections.size();i++) {
//			if(sections.get(i).getId()==newSectionId) {
//				sections.remove(i);
//			}
//		}
//		block.setSections(sections);
//		sectionService.deleteSection(newSectionId);
//		blockService.saveBlock(block, block.getEntry().getId());
//		model.addAttribute("block_id", block_id);
//		model.addAttribute("sections", sections);	
//		return "sectionList";
//	}
//}

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
