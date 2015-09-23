package sedion.jeffli.wmuitp.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * PaperInfo entity. @author MyEclipse Persistence Tools
 */

public class PaperInfo implements java.io.Serializable {

	// Fields

	private Integer piId;
	private CourseInfo courseInfo;
	private TeacherInfo teacherInfo;
	private String piName;
	private String piSign;
	private String piZipName;
	private String piDateTime;
	private Set paperSubjectRelations = new HashSet(0);
	private Set examStudents = new HashSet(0);

	// Constructors

	/** default constructor */
	public PaperInfo() {
	}

	/** full constructor */
	public PaperInfo(CourseInfo courseInfo,
			TeacherInfo teacherInfo, String piName, String piSign,
			String piZipName, String piDateTime, Set paperSubjectRelations,
			Set examStudents) {
		this.courseInfo = courseInfo;
		this.teacherInfo = teacherInfo;
		this.piName = piName;
		this.piSign = piSign;
		this.piZipName = piZipName;
		this.piDateTime = piDateTime;
		this.paperSubjectRelations = paperSubjectRelations;
		this.examStudents = examStudents;
	}

	// Property accessors

	public Integer getPiId() {
		return this.piId;
	}

	public void setPiId(Integer piId) {
		this.piId = piId;
	}

	public CourseInfo getCourseInfo()
	{
		return courseInfo;
	}

	public void setCourseInfo(CourseInfo courseInfo)
	{
		this.courseInfo = courseInfo;
	}

	public TeacherInfo getTeacherInfo() {
		return this.teacherInfo;
	}

	public void setTeacherInfo(TeacherInfo teacherInfo) {
		this.teacherInfo = teacherInfo;
	}

	public String getPiName() {
		return this.piName;
	}

	public void setPiName(String piName) {
		this.piName = piName;
	}

	public String getPiSign()
	{
		return piSign;
	}

	public void setPiSign(String piSign)
	{
		this.piSign = piSign;
	}

	public String getPiZipName() {
		return this.piZipName;
	}

	public void setPiZipName(String piZipName) {
		this.piZipName = piZipName;
	}

	public String getPiDateTime() {
		return this.piDateTime;
	}

	public void setPiDateTime(String piDateTime) {
		this.piDateTime = piDateTime;
	}

	public Set getPaperSubjectRelations() {
		return this.paperSubjectRelations;
	}

	public void setPaperSubjectRelations(Set paperSubjectRelations) {
		this.paperSubjectRelations = paperSubjectRelations;
	}

	public Set getExamStudents() {
		return this.examStudents;
	}

	public void setExamStudents(Set examStudents) {
		this.examStudents = examStudents;
	}

}