package sedion.jeffli.wmuitp.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * ProfessionInfo entity. @author MyEclipse Persistence Tools
 */

public class ProfessionInfo implements java.io.Serializable {

	// Fields

	private Integer piId;
	private String piProfession;
	private String piCollege;
	private String piSign;
	private Set teacherInfos = new HashSet(0);
	
	// Constructors

	/** default constructor */
	public ProfessionInfo() {
	}

	/** full constructor */
	public ProfessionInfo(String piProfession, String piCollege, String piSign,
			Set teacherInfos) {
		this.piProfession = piProfession;
		this.piCollege = piCollege;
		this.piSign = piSign;
		this.teacherInfos = teacherInfos;
	}

	// Property accessors

	public Integer getPiId() {
		return this.piId;
	}

	public void setPiId(Integer piId) {
		this.piId = piId;
	}

	public String getPiProfession() {
		return this.piProfession;
	}

	public void setPiProfession(String piProfession) {
		this.piProfession = piProfession;
	}

	public String getPiCollege() {
		return this.piCollege;
	}

	public void setPiCollege(String piCollege) {
		this.piCollege = piCollege;
	}

	public String getPiSign() {
		return this.piSign;
	}

	public void setPiSign(String piSign) {
		this.piSign = piSign;
	}

	public Set getTeacherInfos() {
		return this.teacherInfos;
	}

	public void setTeacherInfos(Set teacherInfos) {
		this.teacherInfos = teacherInfos;
	}

}