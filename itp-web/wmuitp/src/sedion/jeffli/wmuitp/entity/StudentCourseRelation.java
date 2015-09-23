package sedion.jeffli.wmuitp.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * StudentCourseRelation entity. @author MyEclipse Persistence Tools
 */

public class StudentCourseRelation implements java.io.Serializable {

	// Fields

	private Integer scrId;
	private StudentInfo studentInfo;
	private CourseInfo courseInfo;
	private String scrCourseEvaluation;
	private String scrPresent;
	private Set paperInfos = new HashSet(0);

	// Constructors

	/** default constructor */
	public StudentCourseRelation() {
	}

	/** full constructor */
	public StudentCourseRelation(StudentInfo studentInfo,
			CourseInfo courseInfo, String scrCourseEvaluation,String scrPresent, Set paperInfos) {
		this.studentInfo = studentInfo;
		this.courseInfo = courseInfo;
		this.scrCourseEvaluation = scrCourseEvaluation;
		this.scrPresent = scrPresent;
		this.paperInfos = paperInfos;
	}

	// Property accessors

	public Integer getScrId() {
		return this.scrId;
	}

	public void setScrId(Integer scrId) {
		this.scrId = scrId;
	}

	public StudentInfo getStudentInfo() {
		return this.studentInfo;
	}

	public void setStudentInfo(StudentInfo studentInfo) {
		this.studentInfo = studentInfo;
	}

	public CourseInfo getCourseInfo() {
		return this.courseInfo;
	}

	public void setCourseInfo(CourseInfo courseInfo) {
		this.courseInfo = courseInfo;
	}

	public String getScrCourseEvaluation() {
		return this.scrCourseEvaluation;
	}

	public void setScrCourseEvaluation(String scrCourseEvaluation) {
		this.scrCourseEvaluation = scrCourseEvaluation;
	}

	public String getScrPresent() {
		return scrPresent;
	}

	public void setScrPresent(String scrPresent) {
		this.scrPresent = scrPresent;
	}

	public Set getPaperInfos() {
		return this.paperInfos;
	}

	public void setPaperInfos(Set paperInfos) {
		this.paperInfos = paperInfos;
	}

}