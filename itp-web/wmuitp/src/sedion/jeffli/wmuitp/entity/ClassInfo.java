package sedion.jeffli.wmuitp.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * ClassInfo entity. @author MyEclipse Persistence Tools
 */

public class ClassInfo implements java.io.Serializable {

	// Fields

	private Integer ciId;
	private String ciName;
	private String ciStudentSum;
	private String ciProfession;
	private String ciCollege;
	private String ciSign;
	private Set studentInfos =new HashSet(0);

	// Constructors

	/** default constructor */
	public ClassInfo() {
	}

	/** full constructor */
	public ClassInfo(String ciName, String ciStudentSum, String ciProfession,
			String ciCollege, String ciSign, Set studentInfos) {
		this.ciName = ciName;
		this.ciStudentSum = ciStudentSum;
		this.ciProfession = ciProfession;
		this.ciCollege = ciCollege;
		this.ciSign = ciSign;
		this.studentInfos = studentInfos;
	}

	// Property accessors

	public Integer getCiId() {
		return this.ciId;
	}

	public void setCiId(Integer ciId) {
		this.ciId = ciId;
	}

	public String getCiName() {
		return this.ciName;
	}

	public void setCiName(String ciName) {
		this.ciName = ciName;
	}

	public String getCiStudentSum() {
		return this.ciStudentSum;
	}

	public void setCiStudentSum(String ciStudentSum) {
		this.ciStudentSum = ciStudentSum;
	}

	public String getCiProfession() {
		return this.ciProfession;
	}

	public void setCiProfession(String ciProfession) {
		this.ciProfession = ciProfession;
	}

	public String getCiCollege() {
		return this.ciCollege;
	}

	public void setCiCollege(String ciCollege) {
		this.ciCollege = ciCollege;
	}

	public String getCiSign() {
		return this.ciSign;
	}

	public void setCiSign(String ciSign) {
		this.ciSign = ciSign;
	}

	public Set getStudentInfos() {
		return this.studentInfos;
	}

	public void setStudentInfos(Set studentInfos) {
		this.studentInfos = studentInfos;
	}

}