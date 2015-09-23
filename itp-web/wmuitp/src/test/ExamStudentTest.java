package test;

import sedion.jeffli.wmuitp.entity.UserLogin;
import sedion.jeffli.wmuitp.service.impl.ExamStudentResultServiceImpl;

public class ExamStudentTest {

	/**
	 * @param args
	 */
	public static void Test() {
		final ExamStudentResultServiceImpl exri = new ExamStudentResultServiceImpl();
		for(int i=6;i<347;i++){
			final Integer id = i ;
			new Thread(new Runnable() {
				@Override
				public void run() {
					String answer = "1,A-2,A-3,A-4,A-5,A-6,A-7,B-8,B-9,A-10,A-11,A-12,B-13,A-14,B-15,B-16,A-17,B-18,B-19,A-20,B-21,B-22,A-23,B-24,B-25,A-26,A-27,A-28,A-29,B-30,B-31,A-32,A-33,A-34,B-35,A-36,A-37,A-38,A-39,A-40,A-41,B-42,B-43,A-44,B-45,A-46,A-47,B-48,C-49,A-50,B-51,A-52,B-53,A-54,A-55,B-56,A";
					String paperId="4";
					UserLogin ul = new UserLogin(); 
					ul.setUlId(id);
					exri.saveExamStudentResults(answer, ul, paperId);
				}
			}).start();
		}
	}

}
