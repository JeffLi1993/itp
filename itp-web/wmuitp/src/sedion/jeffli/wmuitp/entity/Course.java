package sedion.jeffli.wmuitp.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Course entity. @author MyEclipse Persistence Tools
 */

public class Course implements java.io.Serializable {

	// Fields

	private Integer CId;
	private String CName;
	private String CDescription;
	private Set courseChapters =new HashSet(0);
	private Set courseTeacherRelations = new HashSet(0);

	// Constructors

	/** default constructor */
	public Course() {
	}

	/** full constructor */
	public Course(String CName, String CDescription, Set courseChapters,
			Set courseTeacherRelations) {
		this.CName = CName;
		this.CDescription = CDescription;
		this.courseChapters = courseChapters;
		this.courseTeacherRelations = courseTeacherRelations;
	}

	// Property accessors

	public Integer getCId() {
		return this.CId;
	}

	public void setCId(Integer CId) {
		this.CId = CId;
	}

	public String getCName() {
		return this.CName;
	}

	public void setCName(String CName) {
		this.CName = CName;
	}

	public String getCDescription() {
		return this.CDescription;
	}

	public void setCDescription(String CDescription) {
		this.CDescription = CDescription;
	}

	public Set getCourseChapters() {
		return this.courseChapters;
	}

	public void setCourseChapters(Set courseChapters) {
		this.courseChapters = courseChapters;
	}

	public Set getCourseTeacherRelations() {
		return this.courseTeacherRelations;
	}

	public void setCourseTeacherRelations(Set courseTeacherRelations) {
		this.courseTeacherRelations = courseTeacherRelations;
	}

}