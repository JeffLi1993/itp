package sedion.jeffli.wmuitp.entity;

/**
 * PaperSubjectRelation entity. @author MyEclipse Persistence Tools
 */

public class PaperSubjectRelation implements java.io.Serializable {

	// Fields

	private Integer psrId;
	private SubjectInfor subjectInfor;
	private PaperInfo paperInfo;
	private String psrTime;

	// Constructors

	/** default constructor */
	public PaperSubjectRelation() {
	}

	/** full constructor */
	public PaperSubjectRelation(SubjectInfor subjectInfor, PaperInfo paperInfo,
			String psrTime) {
		this.subjectInfor = subjectInfor;
		this.paperInfo = paperInfo;
		this.psrTime = psrTime;
	}

	// Property accessors

	public Integer getPsrId() {
		return this.psrId;
	}

	public void setPsrId(Integer psrId) {
		this.psrId = psrId;
	}

	public SubjectInfor getSubjectInfor() {
		return this.subjectInfor;
	}

	public void setSubjectInfor(SubjectInfor subjectInfor) {
		this.subjectInfor = subjectInfor;
	}

	public PaperInfo getPaperInfo() {
		return this.paperInfo;
	}

	public void setPaperInfo(PaperInfo paperInfo) {
		this.paperInfo = paperInfo;
	}

	public String getPsrTime() {
		return this.psrTime;
	}

	public void setPsrTime(String psrTime) {
		this.psrTime = psrTime;
	}

}