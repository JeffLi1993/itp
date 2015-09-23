package sedion.jeffli.wmuitp.entity;

/**
 * DiscussionTeacherReply entity. @author MyEclipse Persistence Tools
 */

public class DiscussionTeacherReply implements java.io.Serializable {

	// Fields

	private Integer dtrId;
	private DiscussionTopic discussionTopic;
	private TeacherInfo teacherInfo;
	private String dtrContent;
	private String dtrDataTime;

	// Constructors

	/** default constructor */
	public DiscussionTeacherReply() {
	}

	/** full constructor */
	public DiscussionTeacherReply(DiscussionTopic discussionTopic,
			TeacherInfo teacherInfo, String dtrContent, String dtrDataTime) {
		this.discussionTopic = discussionTopic;
		this.teacherInfo = teacherInfo;
		this.dtrContent = dtrContent;
		this.dtrDataTime = dtrDataTime;
	}

	// Property accessors

	public Integer getDtrId() {
		return this.dtrId;
	}

	public void setDtrId(Integer dtrId) {
		this.dtrId = dtrId;
	}

	public DiscussionTopic getDiscussionTopic() {
		return this.discussionTopic;
	}

	public void setDiscussionTopic(DiscussionTopic discussionTopic) {
		this.discussionTopic = discussionTopic;
	}

	public TeacherInfo getTeacherInfo() {
		return this.teacherInfo;
	}

	public void setTeacherInfo(TeacherInfo teacherInfo) {
		this.teacherInfo = teacherInfo;
	}

	public String getDtrContent() {
		return this.dtrContent;
	}

	public void setDtrContent(String dtrContent) {
		this.dtrContent = dtrContent;
	}

	public String getDtrDataTime() {
		return this.dtrDataTime;
	}

	public void setDtrDataTime(String dtrDataTime) {
		this.dtrDataTime = dtrDataTime;
	}

}