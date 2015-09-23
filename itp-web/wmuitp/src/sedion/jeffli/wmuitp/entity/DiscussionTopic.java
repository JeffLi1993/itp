package sedion.jeffli.wmuitp.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * DiscussionTopic entity. @author MyEclipse Persistence Tools
 */

public class DiscussionTopic implements java.io.Serializable {

	// Fields

	private Integer dtId;
	private CourseInfo courseInfo;
	private String dtName;
	private String dtContent;
	private String dtState;
	private String dtDateTime;
	private String dtTop;
	private String dtFine;
	private String dtScan;
	private String dtReply;
	private String dtSign;
	private Set discussionTeacherReplies = new HashSet(0);
	private Set discussionStudentReplies = new HashSet(0);
	private Set studentInfos = new HashSet(0);

	// Constructors

	/** default constructor */
	public DiscussionTopic() {
	}

	/** full constructor */
	public DiscussionTopic(Integer dtId, CourseInfo courseInfo, String dtName,
			String dtContent, String dtState, String dtDateTime, String dtTop,
			String dtFine, String dtScan, String dtReply, String dtSign,
			Set discussionTeacherReplies, Set discussionStudentReplies,
			Set studentInfos) {
		super();
		this.dtId = dtId;
		this.courseInfo = courseInfo;
		this.dtName = dtName;
		this.dtContent = dtContent;
		this.dtState = dtState;
		this.dtDateTime = dtDateTime;
		this.dtTop = dtTop;
		this.dtFine = dtFine;
		this.dtScan = dtScan;
		this.dtReply = dtReply;
		this.dtSign = dtSign;
		this.discussionTeacherReplies = discussionTeacherReplies;
		this.discussionStudentReplies = discussionStudentReplies;
		this.studentInfos = studentInfos;
	}

	// Property accessors

	public Integer getDtId() {
		return this.dtId;
	}

	

	public void setDtId(Integer dtId) {
		this.dtId = dtId;
	}

	public CourseInfo getCourseInfo() {
		return this.courseInfo;
	}

	public void setCourseInfo(CourseInfo courseInfo) {
		this.courseInfo = courseInfo;
	}

	public String getDtName() {
		return this.dtName;
	}

	public void setDtName(String dtName) {
		this.dtName = dtName;
	}

	public String getDtState() {
		return this.dtState;
	}

	public void setDtState(String dtState) {
		this.dtState = dtState;
	}

	public String getDtDateTime() {
		return this.dtDateTime;
	}

	public void setDtDateTime(String dtDateTime) {
		this.dtDateTime = dtDateTime;
	}

	public String getDtSign() {
		return this.dtSign;
	}

	public void setDtSign(String dtSign) {
		this.dtSign = dtSign;
	}

	public String getDtContent()
	{
		return dtContent;
	}

	public void setDtContent(String dtContent)
	{
		this.dtContent = dtContent;
	}

	public String getDtTop()
	{
		return dtTop;
	}

	public void setDtTop(String dtTop)
	{
		this.dtTop = dtTop;
	}

	public String getDtFine()
	{
		return dtFine;
	}

	public void setDtFine(String dtFine)
	{
		this.dtFine = dtFine;
	}

	public String getDtScan()
	{
		return dtScan;
	}

	public void setDtScan(String dtScan)
	{
		this.dtScan = dtScan;
	}

	public String getDtReply()
	{
		return dtReply;
	}

	public void setDtReply(String dtReply)
	{
		this.dtReply = dtReply;
	}

	public Set getDiscussionTeacherReplies() {
		return this.discussionTeacherReplies;
	}

	public void setDiscussionTeacherReplies(Set discussionTeacherReplies) {
		this.discussionTeacherReplies = discussionTeacherReplies;
	}

	public Set getDiscussionStudentReplies() {
		return this.discussionStudentReplies;
	}

	public void setDiscussionStudentReplies(Set discussionStudentReplies) {
		this.discussionStudentReplies = discussionStudentReplies;
	}

	public Set getStudentInfos() {
		return studentInfos;
	}

	public void setStudentInfos(Set studentInfos) {
		this.studentInfos = studentInfos;
	}
	/*************************************/
	public void addStudentInfo(StudentInfo studentInfo) {
		System.out.println("调用Adddddd");
		studentInfos.add(studentInfo);
	}
}