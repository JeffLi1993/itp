package sedion.jeffli.wmuitp.entity.courseInfo;

import sedion.jeffli.wmuitp.entity.CourseInfo;

public class CourseInfoDetail {
	private CourseInfo courseInfo;
	private String classNames;

	public void setCourseInfo(CourseInfo courseInfo) {
		this.courseInfo = courseInfo;
	}

	public String getClassNames() {
		return classNames;
	}

	public void setClassNames(String classNames) {
		this.classNames = classNames;
	}

	public CourseInfo getCourseInfo() {
		return courseInfo;
	}
	
}
