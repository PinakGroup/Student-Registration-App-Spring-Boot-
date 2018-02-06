package edu.mum.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//@Entity
//public class Section {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private long id;
//	private String sectionCode="TEST-A";
//	private int limitCapacity=25;
//
//	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
//	private Course course;
//
//	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
//	//@JoinColumn(name = "faculty_id")
//	private Faculty faculty;
//
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "block_id")
//	private Block block;
//
//	@ManyToMany(mappedBy = "sections", cascade = CascadeType.PERSIST)
//	List<Student> students = new ArrayList<Student>();
//
//	public Section() {
//	}
//
//	public Section(Block block) {
//		this.block = block;
//
//	}
//
//	public Section(Block block, Course course) {
//		this.block = block;
//		this.course = course;
//
//	}
//
//	public Block getBlock() {
//		return block;
//	}
//
//	public void setBlock(Block block) {
//		this.block = block;
//	}
//
//	public Faculty getFaculty() {
//		return faculty;
//	}
//
//	public void setFaculty(Faculty faculty) {
//		this.faculty = faculty;
//	}
//
//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}
//
//	public String getSectionCode() {
//		return sectionCode;
//	}
//
//	public void setSectionCode(String sectionCode) {
//		this.sectionCode = sectionCode;
//	}
//
//	public Course getCourse() {
//		return course;
//	}
//
//	public void setCourse(Course course) {
//		this.course = course;
//	}
//
//	public int getLimitCapacity() {
//		return limitCapacity;
//	}
//
//	public void setLimitCapacity(int limitCapacity) {
//		this.limitCapacity = limitCapacity;
//	}
//
//	public List<Student> getStudents() {
//		return students;
//	}
//
//	public void setStudents(List<Student> students) {
//		this.students = students;
//	}
//	
//	
//
//}

@Entity
@Table(name="section")
public class Section {
	
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;	
	
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="course_id", referencedColumnName = "id")
	private Course course;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	//@JoinColumn(name = "faculty_id")
	private Faculty faculty;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "block_id")
	private Block block;

	@ManyToMany(mappedBy = "sections", cascade = CascadeType.PERSIST)
	List<Student> students = new ArrayList<Student>();


	@Column(name="title")
    private String title;	
	
	@Column(name="location")
    private String location;	
	
	@Column(name="description", length=1024)
    private String description;
	
	private final int numOfStudents = 25;
	
	public Section() {
		}
	
		public Section(Block block) {
			this.block = block;
	
		}
		
		public Section(Block block, Course course) {
			this.block = block;
			this.course = course;
	
		}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNumOfStudents() {
		return numOfStudents;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	

}

