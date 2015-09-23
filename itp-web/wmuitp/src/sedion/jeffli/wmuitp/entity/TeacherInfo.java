package sedion.jeffli.wmuitp.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * TeacherInfo entity. @author MyEclipse Persistence Tools
 */

public class TeacherInfo implements java.io.Serializable {

	// Fields

	private Integer tiId;
	private ProfessionInfo professionInfo;
	private UserLogin userLogin;
	private String tiName;
	private String tiJob;
	private String tiAge;
	private String tiSign;
	private String tiSkills;
	private String tiAddress;
	private Set subjectInfors = new HashSet(0);
	private Set paperInfos = new HashSet(0);
	private Set discussionTeacherReplies = new HashSet(0);
	private Set courseTeacherRelations = new HashSet(0);

	// Constructors

	/** default constructor */
	public TeacherInfo() {
	}

	/** full constructor */
	public TeacherInfo(ProfessionInfo professionInfo, UserLogin userLogin,
			String tiName, String tiJob, String tiAge, String tiSign,
			String tiSkills, Set subjectInfors, Set paperInfos,
			Set discussionTeacherReplies, Set courseTeacherRelations) {
		this.professionInfo = professionInfo;
		this.userLogin = userLogin;
		this.tiName = tiName;
		this.tiJob = tiJob;
		this.tiAge = tiAge;
		this.tiSign = tiSign;
		this.tiSkills = tiSkills;
		this.subjectInfors = subjectInfors;
		this.paperInfos = paperInfos;
		this.discussionTeacherReplies = discussionTeacherReplies;
		this.courseTeacherRelations = courseTeacherRelations;
	}

	// Property accessors

	public Integer getTiId() {
		return this.tiId;
	}

	public void setTiId(Integer tiId) {
		this.tiId = tiId;
	}

	public ProfessionInfo getProfessionInfo() {
		return this.professionInfo;
	}

	public void setProfessionInfo(ProfessionInfo professionInfo) {
		this.professionInfo = professionInfo;
	}

	public UserLogin getUserLogin() {
		return this.userLogin;
	}

	public void setUserLogin(UserLogin userLogin) {
		this.userLogin = userLogin;
	}

	public String getTiName() {
		return this.tiName;
	}

	public void setTiName(String tiName) {
		this.tiName = tiName;
	}

	public String getTiJob() {
		return this.tiJob;
	}

	public void setTiJob(String tiJob) {
		this.tiJob = tiJob;
	}

	public String getTiAge() {
		return this.tiAge;
	}

	public void setTiAge(String tiAge) {
		this.tiAge = tiAge;
	}

	public String getTiSign() {
		return this.tiSign;
	}

	public void setTiSign(String tiSign) {
		this.tiSign = tiSign;
	}

	public String getTiSkills() {
		return this.tiSkills;
	}

	public void setTiSkills(String tiSkills) {
		this.tiSkills = tiSkills;
	}

	public String getTiAddress() {
		return tiAddress;
	}

	public void setTiAddress(String tiAddress) {
		this.tiAddress = tiAddress;
	}

	public Set getSubjectInfors() {
		return this.subjectInfors;
	}

	public void setSubjectInfors(Set subjectInfors) {
		this.subjectInfors = subjectInfors;
	}

	public Set getPaperInfos() {
		return this.paperInfos;
	}

	public void setPaperInfos(Set paperInfos) {
		this.paperInfos = paperInfos;
	}

	public Set getDiscussionTeacherReplies() {
		return this.discussionTeacherReplies;
	}

	public void setDiscussionTeacherReplies(Set discussionTeacherReplies) {
		this.discussionTeacherReplies = discussionTeacherReplies;
	}

	public Set getCourseTeacherRelations() {
		return this.courseTeacherRelations;
	}

	public void setCourseTeacherRelations(Set courseTeacherRelations) {
		this.courseTeacherRelations = courseTeacherRelations;
	}

}