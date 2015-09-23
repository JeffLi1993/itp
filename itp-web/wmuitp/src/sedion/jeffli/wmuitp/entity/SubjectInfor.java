package sedion.jeffli.wmuitp.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * SubjectInfor entity. @author MyEclipse Persistence Tools
 */

public class SubjectInfor implements java.io.Serializable {

	// Fields

	private Integer siId;
	private TeacherInfo teacherInfo;
	private CourseChapter courseChapter;
	private String siState;
	private String siUseScope;
	private String csTitle;
	private String csA;
	private String csB;
	private String csC;
	private String csD;
	private String csE;
	private String csAnswer;
	private String csAnswerExplain;
	private String csType;
	private Set examStudentResults = new HashSet(0);
	private Set paperSubjectRelations = new HashSet(0);

	// Constructors

	/** default constructor */
	public SubjectInfor() {
	}

	/** full constructor */
	public SubjectInfor(TeacherInfo teacherInfo, CourseChapter courseChapter,
			String siState, String siUseScope, String csTitle, String csA,
			String csB, String csC, String csD, String csE, String csAnswer,
			String csAnswerExplain, String csType, Set examStudentResults,
			Set paperSubjectRelations) {
		this.teacherInfo = teacherInfo;
		this.courseChapter = courseChapter;
		this.siState = siState;
		this.siUseScope = siUseScope;
		this.csTitle = csTitle;
		this.csA = csA;
		this.csB = csB;
		this.csC = csC;
		this.csD = csD;
		this.csE = csE;
		this.csAnswer = csAnswer;
		this.csAnswerExplain = csAnswerExplain;
		this.csType = csType;
		this.examStudentResults = examStudentResults;
		this.paperSubjectRelations = paperSubjectRelations;
	}

	// Property accessors

	public Integer getSiId() {
		return this.siId;
	}

	public void setSiId(Integer siId) {
		this.siId = siId;
	}

	public TeacherInfo getTeacherInfo() {
		return this.teacherInfo;
	}

	public void setTeacherInfo(TeacherInfo teacherInfo) {
		this.teacherInfo = teacherInfo;
	}

	public CourseChapter getCourseChapter() {
		return this.courseChapter;
	}

	public void setCourseChapter(CourseChapter courseChapter) {
		this.courseChapter = courseChapter;
	}

	public String getSiState() {
		return this.siState;
	}

	public void setSiState(String siState) {
		this.siState = siState;
	}

	public String getSiUseScope() {
		return this.siUseScope;
	}

	public void setSiUseScope(String siUseScope) {
		this.siUseScope = siUseScope;
	}

	public String getCsTitle() {
		return this.csTitle;
	}

	public void setCsTitle(String csTitle) {
		this.csTitle = csTitle;
	}

	public String getCsA() {
		return this.csA;
	}

	public void setCsA(String csA) {
		this.csA = csA;
	}

	public String getCsB() {
		return this.csB;
	}

	public void setCsB(String csB) {
		this.csB = csB;
	}

	public String getCsC() {
		return this.csC;
	}

	public void setCsC(String csC) {
		this.csC = csC;
	}

	public String getCsD() {
		return this.csD;
	}

	public void setCsD(String csD) {
		this.csD = csD;
	}

	public String getCsE() {
		return this.csE;
	}

	public void setCsE(String csE) {
		this.csE = csE;
	}

	public String getCsAnswer() {
		return this.csAnswer;
	}

	public void setCsAnswer(String csAnswer) {
		this.csAnswer = csAnswer;
	}

	public String getCsAnswerExplain() {
		return this.csAnswerExplain;
	}

	public void setCsAnswerExplain(String csAnswerExplain) {
		this.csAnswerExplain = csAnswerExplain;
	}

	public String getCsType() {
		return this.csType;
	}

	public void setCsType(String csType) {
		this.csType = csType;
	}

	public Set getExamStudentResults() {
		return this.examStudentResults;
	}

	public void setExamStudentResults(Set examStudentResults) {
		this.examStudentResults = examStudentResults;
	}

	public Set getPaperSubjectRelations() {
		return this.paperSubjectRelations;
	}

	public void setPaperSubjectRelations(Set paperSubjectRelations) {
		this.paperSubjectRelations = paperSubjectRelations;
	}

	@Override
	public String toString()
	{
		return "SubjectInfor [siId=" + siId + ", teacherInfo=" + teacherInfo
				+ ", courseChapter=" + courseChapter + ", siState=" + siState
				+ ", siUseScope=" + siUseScope + ", csTitle=" + csTitle
				+ ", csA=" + csA + ", csB=" + csB + ", csC=" + csC + ", csD="
				+ csD + ", csE=" + csE + ", csAnswer=" + csAnswer
				+ ", csAnswerExplain=" + csAnswerExplain + ", csType=" + csType
				+ ", examStudentResults=" + examStudentResults
				+ ", paperSubjectRelations=" + paperSubjectRelations + "]";
	}

}