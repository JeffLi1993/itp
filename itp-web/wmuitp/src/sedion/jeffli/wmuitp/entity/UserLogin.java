package sedion.jeffli.wmuitp.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * UserLogin entity. @author MyEclipse Persistence Tools
 */

public class UserLogin implements java.io.Serializable {

	// Fields

	private Integer ulId;
	private String ulName;
	private String ulPassword;
	private String ulSign;
	private Set messageSenders = new HashSet(0);
	private Set messageReceives = new HashSet(0);
	private Set studentInfos = new HashSet(0);
	private Set teacherInfos = new HashSet(0);

	// Constructors

	/** default constructor */
	public UserLogin() {
	}

	/** full constructor */
	public UserLogin(String ulName, String ulPassword, String ulSign,
			Set messageSenders, Set messageReceives, Set studentInfos,
			Set teacherInfos) {
		this.ulName = ulName;
		this.ulPassword = ulPassword;
		this.ulSign = ulSign;
		this.messageSenders = messageSenders;
		this.messageReceives = messageReceives;
		this.studentInfos = studentInfos;
		this.teacherInfos = teacherInfos;
	}

	// Property accessors

	public Integer getUlId() {
		return this.ulId;
	}

	public void setUlId(Integer ulId) {
		this.ulId = ulId;
	}

	public String getUlName() {
		return this.ulName;
	}

	public void setUlName(String ulName) {
		this.ulName = ulName;
	}

	public String getUlPassword() {
		return this.ulPassword;
	}

	public void setUlPassword(String ulPassword) {
		this.ulPassword = ulPassword;
	}

	public String getUlSign() {
		return this.ulSign;
	}

	public void setUlSign(String ulSign) {
		this.ulSign = ulSign;
	}

	public Set getMessageSenders() {
		return this.messageSenders;
	}

	public void setMessageSenders(Set messageSenders) {
		this.messageSenders = messageSenders;
	}

	public Set getMessageReceives() {
		return this.messageReceives;
	}

	public void setMessageReceives(Set messageReceives) {
		this.messageReceives = messageReceives;
	}

	public Set getStudentInfos() {
		return this.studentInfos;
	}

	public void setStudentInfos(Set studentInfos) {
		this.studentInfos = studentInfos;
	}

	public Set getTeacherInfos() {
		return this.teacherInfos;
	}

	public void setTeacherInfos(Set teacherInfos) {
		this.teacherInfos = teacherInfos;
	}

}