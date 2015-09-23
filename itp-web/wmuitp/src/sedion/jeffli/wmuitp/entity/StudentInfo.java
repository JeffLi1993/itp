package sedion.jeffli.wmuitp.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * StudentInfo entity. @author MyEclipse Persistence Tools
 */

public class StudentInfo implements java.io.Serializable {

	// Fields

	private Integer siId;
	private UserLogin userLogin;
	private ClassInfo classInfo;
	private String siNum;
	private String siRealName;
	private String siSex;
	private String siSign;
	private String siInformation;
	private Set discussionStudentReplies = new HashSet(0);
	private Set studentCourseRelations = new HashSet(0);
	private Set examStudents = new HashSet(0);
	private Set discussionTopics = new HashSet(0);

	// Constructors

	/** default constructor */
	public StudentInfo() {
	}

	/** full constructor */
	public StudentInfo(Integer siId, UserLogin userLogin, ClassInfo classInfo,
			String siNum, String siRealName, String siSex, String siSign,
			String siInformation, Set discussionStudentReplies,
			Set studentCourseRelations, Set examStudents, Set discussionTopics) {
		super();
		this.siId = siId;
		this.userLogin = userLogin;
		this.classInfo = classInfo;
		this.siNum = siNum;
		this.siRealName = siRealName;
		this.siSex = siSex;
		this.siSign = siSign;
		this.siInformation = siInformation;
		this.discussionStudentReplies = discussionStudentReplies;
		this.studentCourseRelations = studentCourseRelations;
		this.examStudents = examStudents;
		this.discussionTopics = discussionTopics;
	}

	// Property accessors

	public Integer getSiId() {
		return this.siId;
	}

	public void setSiId(Integer siId) {
		this.siId = siId;
	}

	public UserLogin getUserLogin() {
		return this.userLogin;
	}

	public void setUserLogin(UserLogin userLogin) {
		this.userLogin = userLogin;
	}

	public ClassInfo getClassInfo() {
		return this.classInfo;
	}

	public void setClassInfo(ClassInfo classInfo) {
		this.classInfo = classInfo;
	}

	public String getSiNum() {
		return this.siNum;
	}

	public void setSiNum(String siNum) {
		this.siNum = siNum;
	}

	public String getSiRealName() {
		return this.siRealName;
	}

	public void setSiRealName(String siRealName) {
		this.siRealName = siRealName;
	}

	public String getSiSex() {
		return this.siSex;
	}

	public void setSiSex(String siSex) {
		this.siSex = siSex;
	}

	public String getSiSign() {
		return this.siSign;
	}

	public void setSiSign(String siSign) {
		this.siSign = siSign;
	}

	public String getSiInformation() {
		return this.siInformation;
	}

	public void setSiInformation(String siInformation) {
		this.siInformation = siInformation;
	}

	public Set getDiscussionStudentReplies() {
		return this.discussionStudentReplies;
	}

	public void setDiscussionStudentReplies(Set discussionStudentReplies) {
		this.discussionStudentReplies = discussionStudentReplies;
	}

	public Set getStudentCourseRelations() {
		return this.studentCourseRelations;
	}

	public void setStudentCourseRelations(Set studentCourseRelations) {
		this.studentCourseRelations = studentCourseRelations;
	}

	public Set getExamStudents() {
		return this.examStudents;
	}

	public void setExamStudents(Set examStudents) {
		this.examStudents = examStudents;
	}

	public Set getDiscussionTopics() {
		return discussionTopics;
	}

	//这个方法是没用的   但是由于HIbernate必须添加set方法。如需使用该方法，先修改inverse属性
	private void setDiscussionTopics(Set discussionTopics) {
		this.discussionTopics = discussionTopics;
	}

}