package sedion.jeffli.wmuitp.entity;

/**
 * ExamStudentResult entity. @author MyEclipse Persistence Tools
 */

public class ExamStudentResult implements java.io.Serializable {

	// Fields

	private Integer esrId;
	private SubjectInfor subjectInfor;
	private ExamStudent examStudent;
	private String esrContent;
	private String esrScore;

	// Constructors

	/** default constructor */
	public ExamStudentResult() {
	}

	/** full constructor */
	public ExamStudentResult(SubjectInfor subjectInfor,
			ExamStudent examStudent, String esrContent, String esrScore) {
		this.subjectInfor = subjectInfor;
		this.examStudent = examStudent;
		this.esrContent = esrContent;
		this.esrScore = esrScore;
	}

	// Property accessors

	public Integer getEsrId() {
		return this.esrId;
	}

	public void setEsrId(Integer esrId) {
		this.esrId = esrId;
	}

	public SubjectInfor getSubjectInfor() {
		return this.subjectInfor;
	}

	public void setSubjectInfor(SubjectInfor subjectInfor) {
		this.subjectInfor = subjectInfor;
	}

	public ExamStudent getExamStudent() {
		return this.examStudent;
	}

	public void setExamStudent(ExamStudent examStudent) {
		this.examStudent = examStudent;
	}

	public String getEsrContent() {
		return this.esrContent;
	}

	public void setEsrContent(String esrContent) {
		this.esrContent = esrContent;
	}

	public String getEsrScore() {
		return this.esrScore;
	}

	public void setEsrScore(String esrScore) {
		this.esrScore = esrScore;
	}

}