package sedion.jeffli.wmuitp.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * CourseChapter entity. @author MyEclipse Persistence Tools
 */

public class CourseChapter implements java.io.Serializable {

	// Fields

	private Integer ccId;
	private Course course;
	private String ccName;
	private String ccSign;
	private Set subjectInfors = new HashSet(0);

	// Constructors

	/** default constructor */
	public CourseChapter() {
	}

	/** full constructor */
	public CourseChapter(Course course, String ccName, String ccSign,
			Set subjectInfors) {
		this.course = course;
		this.ccName = ccName;
		this.ccSign = ccSign;
		this.subjectInfors = subjectInfors;
	}

	// Property accessors

	public Integer getCcId() {
		return this.ccId;
	}

	public void setCcId(Integer ccId) {
		this.ccId = ccId;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getCcName() {
		return this.ccName;
	}

	public void setCcName(String ccName) {
		this.ccName = ccName;
	}

	public String getCcSign() {
		return this.ccSign;
	}

	public void setCcSign(String ccSign) {
		this.ccSign = ccSign;
	}

	public Set getSubjectInfors() {
		return this.subjectInfors;
	}

	public void setSubjectInfors(Set subjectInfors) {
		this.subjectInfors = subjectInfors;
	}

}