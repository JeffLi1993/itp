package sedion.jeffli.wmuitp.entity;

/**
 * DiscussionStudentReply entity. @author MyEclipse Persistence Tools
 */

public class DiscussionStudentReply implements java.io.Serializable {

	// Fields

	private Integer dsrId;
	private StudentInfo studentInfo;
	private DiscussionTopic discussionTopic;
	private String dsrContent;
	private String dsrDataTime;
	private String dsrTop;
	private String dsrFine;
	// Constructors

	/** default constructor */
	public DiscussionStudentReply() {
	}

	/** full constructor */
	public DiscussionStudentReply(StudentInfo studentInfo,
			DiscussionTopic discussionTopic, String dsrContent,
			String dsrDataTime) {
		this.studentInfo = studentInfo;
		this.discussionTopic = discussionTopic;
		this.dsrContent = dsrContent;
		this.dsrDataTime = dsrDataTime;
	}

	// Property accessors

	public Integer getDsrId() {
		return this.dsrId;
	}

	public void setDsrId(Integer dsrId) {
		this.dsrId = dsrId;
	}

	public StudentInfo getStudentInfo() {
		return this.studentInfo;
	}

	public void setStudentInfo(StudentInfo studentInfo) {
		this.studentInfo = studentInfo;
	}

	public DiscussionTopic getDiscussionTopic() {
		return this.discussionTopic;
	}

	public void setDiscussionTopic(DiscussionTopic discussionTopic) {
		this.discussionTopic = discussionTopic;
	}

	public String getDsrContent() {
		return this.dsrContent;
	}

	public void setDsrContent(String dsrContent) {
		this.dsrContent = dsrContent;
	}

	public String getDsrDataTime() {
		return this.dsrDataTime;
	}

	public void setDsrDataTime(String dsrDataTime) {
		this.dsrDataTime = dsrDataTime;
	}

	public String getDsrTop()
	{
		return dsrTop;
	}

	public void setDsrTop(String dsrTop)
	{
		this.dsrTop = dsrTop;
	}

	public String getDsrFine()
	{
		return dsrFine;
	}

	public void setDsrFine(String dsrFine)
	{
		this.dsrFine = dsrFine;
	}

}