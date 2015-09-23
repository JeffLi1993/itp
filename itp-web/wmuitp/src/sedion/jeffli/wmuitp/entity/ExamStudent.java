package sedion.jeffli.wmuitp.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * ExamStudent entity. @author MyEclipse Persistence Tools
 */

public class ExamStudent implements java.io.Serializable {

	// Fields

	private Integer esId;
	private StudentInfo studentInfo;
	private PaperInfo paperInfo;
	private String esObjectiveScore;
	private String esSign;
	private Set examStudentResults = new HashSet(0);

	// Constructors

	/** default constructor */
	public ExamStudent() {
	}

	/** full constructor */
	public ExamStudent(StudentInfo studentInfo, PaperInfo paperInfo,
			String esObjectiveScore, String esSign, Set examStudentResults) {
		this.studentInfo = studentInfo;
		this.paperInfo = paperInfo;
		this.esObjectiveScore = esObjectiveScore;
		this.esSign = esSign;
		this.examStudentResults = examStudentResults;
	}

	// Property accessors

	public Integer getEsId() {
		return this.esId;
	}

	public void setEsId(Integer esId) {
		this.esId = esId;
	}

	public StudentInfo getStudentInfo() {
		return this.studentInfo;
	}

	public void setStudentInfo(StudentInfo studentInfo) {
		this.studentInfo = studentInfo;
	}

	public PaperInfo getPaperInfo() {
		return this.paperInfo;
	}

	public void setPaperInfo(PaperInfo paperInfo) {
		this.paperInfo = paperInfo;
	}

	public String getEsObjectiveScore() {
		return this.esObjectiveScore;
	}

	public void setEsObjectiveScore(String esObjectiveScore) {
		this.esObjectiveScore = esObjectiveScore;
	}

	public String getEsSign() {
		return this.esSign;
	}

	public void setEsSign(String esSign) {
		this.esSign = esSign;
	}

	public Set getExamStudentResults() {
		return this.examStudentResults;
	}

	public void setExamStudentResults(Set examStudentResults) {
		this.examStudentResults = examStudentResults;
	}

	@Override
	public String toString()
	{
		return "ExamStudent [esId=" + esId + ", studentInfo=" + studentInfo
				+ ", paperInfo=" + paperInfo + ", esObjectiveScore="
				+ esObjectiveScore + ", esSign=" + esSign
				+ ", examStudentResults=" + examStudentResults + "]";
	}

}