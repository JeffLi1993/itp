package sedion.jeffli.wmuitp.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * CourseInfo entity. @author MyEclipse Persistence Tools
 */

public class CourseInfo implements java.io.Serializable {

	// Fields

	private Integer ciId;
	private CourseTeacherRelation courseTeacherRelation;
	private String ciPlace;
	private String ciDateTime;
	private String ciPeriod;
	private String ciDesription;
	private String ciQrcode;
	private String ciState;
	private String ciOver;
	private Set discussionTopics = new HashSet(0);
	private Set studentCourseRelations = new HashSet(0);
	private Set paperInfos = new HashSet(0);

	// Constructors

	/** default constructor */
	public CourseInfo() {
	}

	/** full constructor */
	public CourseInfo(CourseTeacherRelation courseTeacherRelation,
			String ciPlace, String ciDateTime, String ciPeriod,
			String ciDesription, String ciQrcode,String ciState, Set discussionTopics,
			Set studentCourseRelations) {
		this.courseTeacherRelation = courseTeacherRelation;
		this.ciPlace = ciPlace;
		this.ciDateTime = ciDateTime;
		this.ciPeriod = ciPeriod;
		this.ciDesription = ciDesription;
		this.ciQrcode = ciQrcode;
		this.ciState = ciState;
		this.discussionTopics = discussionTopics;
		this.studentCourseRelations = studentCourseRelations;
	}

	// Property accessors

	public Integer getCiId() {
		return this.ciId;
	}

	public void setCiId(Integer ciId) {
		this.ciId = ciId;
	}

	public CourseTeacherRelation getCourseTeacherRelation() {
		return this.courseTeacherRelation;
	}

	public void setCourseTeacherRelation(
			CourseTeacherRelation courseTeacherRelation) {
		this.courseTeacherRelation = courseTeacherRelation;
	}

	public String getCiPlace() {
		return this.ciPlace;
	}

	public void setCiPlace(String ciPlace) {
		this.ciPlace = ciPlace;
	}

	public String getCiDateTime() {
		return this.ciDateTime;
	}

	public void setCiDateTime(String ciDateTime) {
		this.ciDateTime = ciDateTime;
	}

	public String getCiPeriod() {
		return this.ciPeriod;
	}

	public void setCiPeriod(String ciPeriod) {
		this.ciPeriod = ciPeriod;
	}

	public String getCiDesription() {
		return this.ciDesription;
	}

	public void setCiDesription(String ciDesription) {
		this.ciDesription = ciDesription;
	}

	public String getCiQrcode() {
		return this.ciQrcode;
	}

	public void setCiQrcode(String ciQrcode) {
		this.ciQrcode = ciQrcode;
	}
	
	public String getCiState() {
		return ciState;
	}

	public void setCiState(String ciState) {
		this.ciState = ciState;
	}

	public String getCiOver()
	{
		return ciOver;
	}

	public void setCiOver(String ciOver)
	{
		this.ciOver = ciOver;
	}

	public Set getDiscussionTopics() {
		return this.discussionTopics;
	}

	public void setDiscussionTopics(Set discussionTopics) {
		this.discussionTopics = discussionTopics;
	}

	public Set getStudentCourseRelations() {
		return this.studentCourseRelations;
	}

	public void setStudentCourseRelations(Set studentCourseRelations) {
		this.studentCourseRelations = studentCourseRelations;
	}

	public Set getPaperInfos() {
		return paperInfos;
	}

	public void setPaperInfos(Set paperInfos) {
		this.paperInfos = paperInfos;
	}

}