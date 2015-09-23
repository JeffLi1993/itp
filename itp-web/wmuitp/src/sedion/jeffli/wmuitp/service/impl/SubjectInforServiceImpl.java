package sedion.jeffli.wmuitp.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import sedion.jeffli.wmuitp.constant.database.CommonConstant;
import sedion.jeffli.wmuitp.constant.main.Constant;
import sedion.jeffli.wmuitp.dao.CourseChapterDAO;
import sedion.jeffli.wmuitp.dao.CourseDAO;
import sedion.jeffli.wmuitp.dao.SubjectInforDAO;
import sedion.jeffli.wmuitp.dao.TeacherInfoDAO;
import sedion.jeffli.wmuitp.entity.CourseChapter;
import sedion.jeffli.wmuitp.entity.SubjectInfor;
import sedion.jeffli.wmuitp.entity.TeacherInfo;
import sedion.jeffli.wmuitp.entity.UserLogin;
import sedion.jeffli.wmuitp.exception.EntityException;
import sedion.jeffli.wmuitp.service.SubjectInforService;
import sedion.jeffli.wmuitp.util.Page;
import sedion.jeffli.wmuitp.util.session.AdminUtil;

//是一个泛化的概念，仅仅表示一个组件 (Bean) ，可以作用在任何层次。
//@Service 通常作用在业务层，但是目前该功能与 @Component 相同。
@Service
public class SubjectInforServiceImpl implements SubjectInforService {

	private static final String All_SUBJECT_INFORS = "FROM SubjectInfor AS si ";

	@Autowired
	private CourseDAO			courseDAO;
	@Autowired
	private TeacherInfoDAO		teacherInfoDAO;
	@Autowired
	private SubjectInforDAO		subjectInforDAO;
	@Autowired
	private CourseChapterDAO	courseChapterDAO;
	@Autowired
	public HttpSession			session;

	@Override
	public int addSubjectInforByXls(String courseChaptersid,
			MultipartFile xlsfile) 
	{
		// 保存错误列表
		List<Cell[]> errors = new ArrayList<>();
		if (new File(Constant.getCachePath()).exists())
			new File(Constant.getCachePath()).mkdir();

		File file = new File(Constant.getCachePath(), Calendar.getInstance().getTimeInMillis()
				+ ".xls");
		try
		{
			xlsfile.transferTo(file);
		} catch (IllegalStateException e1) {
			System.out.println("读取文件异常");
			e1.printStackTrace();
		} catch (IOException e1) {
			System.out.println("读取文件异常");
			e1.printStackTrace();
		}
		int count = 0;
		Workbook wb = null;
		try
		{
			// 构造Workbook（工作薄）对象
			wb = Workbook.getWorkbook(file);
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (wb == null)
			return -1;
		// 获得了Workbook对象之后，就可以通过它得到Sheet（工作表）对象了
		Sheet[] sheet = wb.getSheets();

		try {
			if (sheet != null && sheet.length > 0) 
			{
				// 对每个工作表进行循环
				for (int i = 0; i < sheet.length; i++) {
					// 得到当前工作表的行数
					int rowNum = sheet[i].getRows();
					/******************************/
					Cell[] cells = null;
					for (int j = 1; j < rowNum; j++) {
						// 得到当前行的所有单元格
						try 
						{
							cells = sheet[i].getRow(j);

							String csTitle = cells[0].getContents().trim();
							String csA = cells[1].getContents().trim();
							String csB = cells[2].getContents().trim();
							String csC = cells[3].getContents().trim();
							String csD = cells[4].getContents().trim();
							String csE = cells[5].getContents().trim();
							String csAnswer = cells[6].getContents().trim()
									.toUpperCase();
							// 判断题目或者答案是否为空
							if (csTitle.equals("") || csAnswer.equals(""))
								throw new Exception();
							// 老师
							TeacherInfo teacherInfo = teacherInfoDAO.getTeacherInfoByUserLoginId(AdminUtil.getUserLoginIDFromHttpSession(session));
							// 进度
							CourseChapter courseChapter = courseChapterDAO.findById(courseChaptersid);

							// 保存题目
							SubjectInfor subjectInfor = new SubjectInfor();
							subjectInfor.setCourseChapter(courseChapter);
							subjectInfor.setTeacherInfo(teacherInfo);
							subjectInfor.setCsTitle(csTitle);
							subjectInfor.setCsA(csA);
							subjectInfor.setCsB(csB);
							subjectInfor.setCsC(csC);
							subjectInfor.setCsD(csD);
							subjectInfor.setCsE(csE);
							subjectInfor.setCsAnswer(csAnswer);

							subjectInforDAO.updateEntity(subjectInfor);

						} 
						catch (Exception e) 
						{
							errors.add(cells);
							count++;
							System.out.println("Add subjectInfor By Xls Fail!");
							continue;
						}

					}
				}
			}
		} finally{
			// 最后关闭资源，释放内存
			if(wb!=null)
				wb.close();
			if(file!=null)
				file.delete();
		}
		
		WritableWorkbook book = null;
		OutputStream os = null;
		// 整理错误数据
		if (errors.size() != 0) 
		{
			System.out.println("错误");
			try 
			{
				UserLogin ul = AdminUtil.getUserLoginFromHttpSession(session);
				File f = new File(Constant.getCachePath(), ul.getUlName() + ".xls");
				if (f.exists()) //旧的缓存文件如果存在，就删除
					f.delete();

				os = new FileOutputStream(Constant.getCachePath()
						+ ul.getUlName() + ".xls");
				book = Workbook.createWorkbook(os);

				WritableSheet ws = book.createSheet("Error Sheet 1", 0);
				//添加首行信息
				Label label1 = new Label(0, 0,"题目"),label2 = new Label(1, 0,"选项A"),label3 = new Label(2, 0,"选项B"),
					label4 = new Label(3, 0,"选项C"),label5 = new Label(4, 0,"选项D"),label6 = new Label(5, 0,"选项E"),label7 = new Label(6, 0,"答案");
				ws.addCell(label1);ws.addCell(label2);ws.addCell(label3);ws.addCell(label4);ws.addCell(label5);ws.addCell(label6);ws.addCell(label7);
				for (int i = 1; i < errors.size(); i++) 
				{
					for (int j = 0; j < errors.get(i).length; j++) 
					{
						// System.out.println("内容"+i+"行"+j+"列"+errors.get(i)[j].getContents());
						Label label = new Label(j, i,
								errors.get(i)[j].getContents());
						// 将定义好的单元格添加到工作表中
						ws.addCell(label);
					}
				}
				// 写入
				book.write();
				System.out.println("导入失败内容写出成功");
			} catch (Exception e) {
				System.out.println("导入失败内容写出失败");
				throw new EntityException(" StudentInfoServiceImpl addStudentByXls(...) Error!!",e);
			}finally{
				try {
					if(book!=null)
						book.close();
					if(os!=null)
						os.close();
				} catch (WriteException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return count;
	}

	@Override
	public List<SubjectInfor> getSubjectInforsByChapterTeacherAndCourseMore(
			String chapterName, String teacherName, String courseName) 
	{

		StringBuffer resultsHQL = new StringBuffer(All_SUBJECT_INFORS);

		chapterName = StringUtils.trimToEmpty(chapterName);
		String[] chapterNames = chapterName.split(",");//中英文分割逗号
		if (chapterNames.length == 1)
			chapterNames = chapterNames[0].split("，");

		teacherName = StringUtils.trimToEmpty(teacherName);//中英文分割逗号
		String[] teacherNames = teacherName.split(",");
		if (teacherNames.length == 1)
			teacherNames = teacherNames[0].split("，");

		courseName = StringUtils.trimToEmpty(courseName);//中英文分割逗号
		String[] courseNames = courseName.split(",");
		if (courseNames.length == 1)
			courseNames = courseNames[0].split("，");

//		if ((!(chapterNames.length == 1 && chapterNames[0].equals("")))
//				|| !(teacherNames.length == 1 && teacherNames[0].equals(""))
//				|| !(courseNames.length == 1 && courseNames[0].equals("")))
//			resultsHQL.append(CommonConstant.ONE_EQUALS_ZERO);
			resultsHQL.append(CommonConstant.ONE_EQUALS_ONE);

		if (!(chapterNames.length == 1 && chapterNames[0].equals(""))) 
		{
			resultsHQL.append(" AND (").append("1=0");
			for (int i = 0; i < chapterNames.length; i++) 
			{
				resultsHQL.append(" OR si.courseChapter.ccName LIKE '%");
				resultsHQL.append(chapterNames[i].trim());
				resultsHQL.append("%' ");
			}
			resultsHQL.append(") ");
		}
		if (!(teacherNames.length == 1 && teacherNames[0].equals("")))
		{
			resultsHQL.append(" AND (").append("1=0");
			for (int i = 0; i < teacherNames.length; i++)
			{
				resultsHQL.append(" OR si.teacherInfo.tiName LIKE '%");
				resultsHQL.append(teacherNames[i].trim());
				resultsHQL.append("%' ");
			}
			resultsHQL.append(") ");
		}
		if (!(courseNames.length == 1 && courseNames[0].equals(""))) 
		{
			resultsHQL.append(" AND (").append("1=0");
			for (int i = 0; i < courseNames.length; i++) 
			{
				resultsHQL.append(" OR si.courseChapter.course.CName LIKE '%");
				resultsHQL.append(courseNames[i].trim());
				resultsHQL.append("%' ");
			}
			resultsHQL.append(") ");
		}

		return subjectInforDAO.getListByHQL(resultsHQL.toString());
	}

	@Override
	public List<SubjectInfor> getSubjectInfors()
	{
		return subjectInforDAO.getListByHQL(All_SUBJECT_INFORS);
	}

	@Override
	public SubjectInfor getSubjectInforBySubjectInforID(String subjectInforID)
	{
		return subjectInforDAO.findById(subjectInforID);
	}

	@Override
	public int saveOrUpdateSubjectInfor(SubjectInfor subjectInfor,
			String courseChapterID, String teacherInfoID) 
	{
		CourseChapter courseChapter = null;
		TeacherInfo teacherInfo = null;

		if (courseChapterID != null && !courseChapterID.equals("")) 
		{
			courseChapter = courseChapterDAO.findById(courseChapterID);
		} else
			return Constant.RESULT_FAIL;

		if (teacherInfoID != null && !teacherInfoID.equals(""))
		{
			teacherInfo = teacherInfoDAO.findById(teacherInfoID);
		} else
			return Constant.RESULT_FAIL;

		subjectInfor.setCourseChapter(courseChapter);
		subjectInfor.setTeacherInfo(teacherInfo);

		subjectInforDAO.updateEntity(subjectInfor);

		return Constant.RESULT_SUCCESS;
	}

	@Override
	public int deleteSubjectInfors(String subjectInforIdStr) 
	{
		String[] subjectInforIdStrs;

		if (subjectInforIdStr != null && !subjectInforIdStr.equals(""))
		{
			subjectInforIdStrs = subjectInforIdStr.split("-");
			for (String subjectInforId : subjectInforIdStrs)
			{
				SubjectInfor subjectInfor = subjectInforDAO.findById(subjectInforId);
				subjectInforDAO.turnTransient(subjectInfor);// 删除
			}
			return Constant.RESULT_SUCCESS;
		}
		return Constant.RESULT_FAIL;
	}

	@Override
	public List<SubjectInfor> getSubjectInforsPages(Page<SubjectInfor> page,
			int[] pageParams, String subjectInfoName, String teacherInfoName,
			String courseName, String courserChapterName) 
	{
		List<SubjectInfor> results = new ArrayList<>();
		StringBuffer resultsHQL = new StringBuffer(All_SUBJECT_INFORS);
		// 去空
		subjectInfoName = StringUtils.trimToEmpty(subjectInfoName);
		teacherInfoName = StringUtils.trimToEmpty(teacherInfoName);
		courseName = StringUtils.trimToEmpty(courseName);
		courserChapterName = StringUtils.trimToEmpty(courserChapterName);
		
		// 分割成为数组 中英文逗号
		String[] subjectInfoNameArray = subjectInfoName.split(",");
		if (subjectInfoNameArray.length == 1)
			subjectInfoNameArray = subjectInfoNameArray[0].split("，");

		String[] teacherInfoNameArray = teacherInfoName.split(",");
		if (teacherInfoNameArray.length == 1)
			teacherInfoNameArray = teacherInfoNameArray[0].split("，");

		String[] courseNameArray = courseName.split(",");
		if (courseNameArray.length == 1)
			courseNameArray = courseNameArray[0].split("，");

		String[] courserChapterNameArray = courserChapterName.split(",");
		if (courserChapterNameArray.length == 1)
			courserChapterNameArray = courserChapterNameArray[0].split("，");
		// 判断4个参数是否为空
		if ((subjectInfoNameArray.length == 1 && subjectInfoNameArray[0].equals(""))
				&& (teacherInfoNameArray.length == 1 && teacherInfoNameArray[0].equals(""))
				&& (courseNameArray.length == 1 && courseNameArray[0].equals(""))
				&& (courserChapterNameArray.length == 1 && courserChapterNameArray[0].equals("")))
			resultsHQL.append(CommonConstant.ONE_EQUALS_ONE);
		else
			resultsHQL.append(CommonConstant.ONE_EQUALS_ZERO);
		
		for (String subjectInfoName1 : subjectInfoNameArray) 
		{
			// 去空
			subjectInfoName1 = StringUtils.trimToNull(subjectInfoName1);
			if (subjectInfoName1 == null)
				continue;
			resultsHQL.append(" OR si.csTitle LIKE '%")
					.append(subjectInfoName1).append("%' ");
		}

		for (String teacherInfoName1 : teacherInfoNameArray) 
		{
			// 去空
			teacherInfoName1 = StringUtils.trimToNull(teacherInfoName1);
			if (teacherInfoName1 == null)
				continue;
			resultsHQL.append(" OR si.teacherInfo.tiName LIKE '%")
					.append(teacherInfoName1).append("%' ");
		}
		for (String courseName1 : courseNameArray) 
		{
			// 去空
			courseName1 = StringUtils.trimToNull(courseName1);
			if (courseName1 == null)
				continue;
			resultsHQL.append(" OR si.courseChapter.course.CName LIKE '%")
					.append(courseName1).append("%' ");
		}
		for (String courserChapterName1 : courserChapterNameArray) 
		{
			// 去空
			courserChapterName1 = StringUtils.trimToNull(courserChapterName1);
			if (courserChapterName1 == null)
				continue;
			resultsHQL.append(" OR si.courseChapter.ccName LIKE '%")
					.append(courserChapterName1).append("%' ");
		}
		try 
		{
			results = subjectInforDAO.findByPage(resultsHQL.toString(),
					pageParams[0], pageParams[1]);
			page.setTotalCount(subjectInforDAO.getCount(resultsHQL.toString()));
			page.setResult(results);

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return results;
	}

	@Override
	public List<SubjectInfor> getSubjectInforsPagesTea(Page<SubjectInfor> page,
			int[] pageParams, String subjectInfoName, String teacherInfoName,
			String courseName, String courserChapterName) 
	{
		List<SubjectInfor> results = new ArrayList<>();
		StringBuffer resultsHQL = new StringBuffer(All_SUBJECT_INFORS);
		// 去空
		subjectInfoName = StringUtils.trimToEmpty(subjectInfoName);
		teacherInfoName = StringUtils.trimToEmpty(teacherInfoName);
		courseName = StringUtils.trimToEmpty(courseName);
		courserChapterName = StringUtils.trimToEmpty(courserChapterName);
		// 分割成为数组 中英文逗号
		String[] subjectInfoNameArray = subjectInfoName.split(",");
		if (subjectInfoNameArray.length == 1)
			subjectInfoNameArray = subjectInfoNameArray[0].split("，");

		String[] teacherInfoNameArray = teacherInfoName.split(",");
		if (teacherInfoNameArray.length == 1)
			teacherInfoNameArray = teacherInfoNameArray[0].split("，");

		String[] courseNameArray = courseName.split(",");
		if (courseNameArray.length == 1)
			courseNameArray = courseNameArray[0].split("，");

		String[] courserChapterNameArray = courserChapterName.split(",");
		if (courserChapterNameArray.length == 1)
			courserChapterNameArray = courserChapterNameArray[0].split("，");
		// 判断4个参数是否为空
		if ((subjectInfoNameArray.length == 1 && subjectInfoNameArray[0].equals(""))
				&& (teacherInfoNameArray.length == 1 && teacherInfoNameArray[0].equals(""))
				&& (courseNameArray.length == 1 && courseNameArray[0].equals(""))
				&& (courserChapterNameArray.length == 1 && courserChapterNameArray[0].equals("")))
			resultsHQL.append(CommonConstant.ONE_EQUALS_ONE);
		else
			resultsHQL.append(CommonConstant.ONE_EQUALS_ZERO);

		for (String subjectInfoName1 : subjectInfoNameArray)
		{
			// 去空
			subjectInfoName1 = StringUtils.trimToNull(subjectInfoName1);
			if (subjectInfoName1 == null)
				continue;
			resultsHQL.append(" OR si.csTitle LIKE '%")
					.append(subjectInfoName1).append("%' ");
		}

		for (String teacherInfoName1 : teacherInfoNameArray)
		{
			// 去空
			teacherInfoName1 = StringUtils.trimToNull(teacherInfoName1);
			if (teacherInfoName1 == null)
				continue;
			resultsHQL.append(" OR si.teacherInfo.tiName LIKE '%")
					.append(teacherInfoName1).append("%' ");
		}
		for (String courseName1 : courseNameArray) {
			// 去空
			courseName1 = StringUtils.trimToNull(courseName1);
			if (courseName1 == null)
				continue;
			resultsHQL.append(" OR si.courseChapter.course.CName LIKE '%")
					.append(courseName1).append("%' ");
		}
		for (String courserChapterName1 : courserChapterNameArray)
		{
			// 去空
			courserChapterName1 = StringUtils.trimToNull(courserChapterName1);
			if (courserChapterName1 == null)
				continue;
			resultsHQL.append(" OR si.courseChapter.ccName LIKE '%")
					.append(courserChapterName1).append("%' ");
		}
		try
		{
			UserLogin userLogin = AdminUtil
					.getUserLoginFromHttpSession(session);
			resultsHQL.append(" AND si.teacherInfo.userLogin.ulId=").append(
					userLogin.getUlId());
			results = subjectInforDAO.findByPage(resultsHQL.toString(),
					pageParams[0], pageParams[1]);
			page.setTotalCount(subjectInforDAO.getCount(resultsHQL.toString()));
			page.setResult(results);

		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return results;
	}

}
