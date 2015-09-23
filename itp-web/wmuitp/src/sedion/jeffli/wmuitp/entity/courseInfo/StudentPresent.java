package sedion.jeffli.wmuitp.entity.courseInfo;

import java.util.List;

import sedion.jeffli.wmuitp.entity.StudentCourseRelation;

public class StudentPresent {	
	private String classStudentNumber;
	private String classStudentNumberAbsent;
	private String classStudentNumberAttend;
	private String attendRate;
	private String className;
	private String classId;
	
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getClassStudentNumberAbsent() {
		return classStudentNumberAbsent;
	}
	public void setClassStudentNumberAbsent(String classStudentNumberAbsent) {
		this.classStudentNumberAbsent = classStudentNumberAbsent;
	}
	public String getClassStudentNumber() {
		return classStudentNumber;
	}
	public void setClassStudentNumber(String classStudentNumber) {
		this.classStudentNumber = classStudentNumber;
	}
	public String getClassStudentNumberAttend() {
		return classStudentNumberAttend;
	}
	public void setClassStudentNumberAttend(String classStudentNumberAttend) {
		if(Integer.valueOf(classStudentNumberAttend)>Integer.valueOf(classStudentNumber)){
			this.classStudentNumberAttend = classStudentNumber;
			return;
		}
		this.classStudentNumberAttend = classStudentNumberAttend;
	}
	public String getAttendRate() {
		return attendRate;
	}
	public void setAttendRate(String attendRate) {
		this.attendRate = attendRate;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
}
