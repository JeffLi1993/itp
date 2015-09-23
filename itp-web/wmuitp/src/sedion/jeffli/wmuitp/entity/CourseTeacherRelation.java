package sedion.jeffli.wmuitp.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * CourseTeacherRelation entity. @author MyEclipse Persistence Tools
 */

public class CourseTeacherRelation implements java.io.Serializable {

	// Fields

	private Integer ctrId;
	private Course course;
	private TeacherInfo teacherInfo;
	private String ctrSign;
	private Set courseInfos = new HashSet(0);

	// Constructors

	/** default constructor */
	public CourseTeacherRelation() {
	}

	/** full constructor */
	public CourseTeacherRelation(Course course, TeacherInfo teacherInfo,
			String ctrSign, Set courseInfos) {
		this.course = course;
		this.teacherInfo = teacherInfo;
		this.ctrSign = ctrSign;
		this.courseInfos = courseInfos;
	}

	// Property accessors

	public Integer getCtrId() {
		return this.ctrId;
	}

	public void setCtrId(Integer ctrId) {
		this.ctrId = ctrId;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public TeacherInfo getTeacherInfo() {
		return this.teacherInfo;
	}

	public void setTeacherInfo(TeacherInfo teacherInfo) {
		this.teacherInfo = teacherInfo;
	}

	public String getCtrSign() {
		return this.ctrSign;
	}

	public void setCtrSign(String ctrSign) {
		this.ctrSign = ctrSign;
	}

	public Set getCourseInfos() {
		return this.courseInfos;
	}

	public void setCourseInfos(Set courseInfos) {
		this.courseInfos = courseInfos;
	}

}