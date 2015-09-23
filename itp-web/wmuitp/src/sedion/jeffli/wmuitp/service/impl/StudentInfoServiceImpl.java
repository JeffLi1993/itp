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

import sedion.jeffli.wmuitp.constant.main.Constant;
import sedion.jeffli.wmuitp.constant.database.CommonConstant;
import sedion.jeffli.wmuitp.dao.ClassInfoDAO;
import sedion.jeffli.wmuitp.dao.StudentInfoDAO;
import sedion.jeffli.wmuitp.dao.UserLoginDAO;
import sedion.jeffli.wmuitp.entity.ClassInfo;
import sedion.jeffli.wmuitp.entity.StudentInfo;
import sedion.jeffli.wmuitp.entity.UserLogin;
import sedion.jeffli.wmuitp.exception.EntityException;
import sedion.jeffli.wmuitp.service.StudentInfoService;
import sedion.jeffli.wmuitp.util.Page;
import sedion.jeffli.wmuitp.util.session.AdminUtil;

//是一个泛化的概念，仅仅表示一个组件 (Bean) ，可以作用在任何层次。
//@Service 通常作用在业务层，但是目前该功能与 @Component 相同。
@Service
public class StudentInfoServiceImpl implements StudentInfoService
{
	private static final String ALL_STUDENT_INFOS = "FROM StudentInfo AS si ";

	@Autowired
	private StudentInfoDAO studentInfoDAO;
	@Autowired
	private UserLoginDAO userLoginDAO;
	@Autowired
	private ClassInfoDAO classInfoDAO;
	@Autowired
	public HttpSession session;

	@Override
	public int initStudentInfoPassword(String studentInfoId) 
	{
		try
		{
			StudentInfo studentInfo = studentInfoDAO.findById(studentInfoId);
			UserLogin userLogin = studentInfo.getUserLogin();
			userLogin.setUlPassword(Constant.INIT_PASSWORD);
			userLoginDAO.updateEntity(userLogin);
		} 
		catch (Exception e)
		{
			throw new EntityException(
					"Error!  StudentInfoServiceImpl.initStudentInfoPassword(...) ",
					e);
		}
		return Constant.RESULT_SUCCESS;
	}

	@Override
	public int addStudentByXls(String classInfoId, MultipartFile xlsFile) 
	{
		int numberNo = -1, nameNo = -1, sexNo = -1, detailNo = -1;
		// 保存错误列表
		List<Cell[]> errors = new ArrayList<>();
		if (new File(Constant.getCachePath()).exists())
			new File(Constant.getCachePath()).mkdir();

		File x = new File(Constant.getCachePath(), Calendar.getInstance()
				.getTimeInMillis() + ".xls");
		try {
			xlsFile.transferTo(x);
		} catch (IllegalStateException e1) {
			System.out.println("读取文件异常");
			e1.printStackTrace();
		} catch (IOException e1) {
			System.out.println("读取文件异常");
			e1.printStackTrace();
		}
		int count = 0;
		Workbook wb = null;
		try {
			// 构造Workbook（工作薄）对象
			wb = Workbook.getWorkbook(x);
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
				for (int i = 0; i < sheet.length; i++)
				{
					// 得到当前工作表的行数
					int rowNum = sheet[i].getRows();
					int j = 0;
					for (j = 0; j < rowNum; j++)
					{
						Cell[] cellSign = sheet[i].getRow(j);
						//获得下列文字对应的列
						for (int k = 0; k < cellSign.length; k++) {
							switch (StringUtils.trimToEmpty(cellSign[k].getContents()))
							{
								case "学生学号":
									numberNo = k;
									break;
								case "学生真实名字":
									nameNo = k;
									break;
								case "性别":
									sexNo = k;
									break;
								case "学生信息详情":
									detailNo = k;
									break;
								default:
									break;
							}
						}
						//如果4个对应的列都获得了就退出循环
						if (numberNo > -1 && nameNo > -1 && sexNo > -1
								&& detailNo > -1)
							break;

						// System.out.println("numberNo="+numberNo+"nameNo="+nameNo+"sexNo="+sexNo+"detailNo="+detailNo);
					}
					//如果4列中某列没获得，直接返回-1(错误标志)
					if (numberNo <= -1 || nameNo <= -1 || sexNo <= -1|| detailNo <= -1)
						return -1;

					Cell[] cells = null;
					for (j++; j < rowNum; j++) 
					{
						// 得到当前行的所有单元格
						try 
						{
							cells = sheet[i].getRow(j);

							String number = cells[numberNo].getContents().trim();
							String name = cells[nameNo].getContents().trim();
							String sex = cells[sexNo].getContents().trim();
							String detail = cells[detailNo].getContents().trim();

							// 获得班级
							if (number.equals("") || name.equals("")) 
								throw new Exception();

							ClassInfo classInfo = classInfoDAO.findById(classInfoId);
							// 判断用户名是否存在
							UserLogin results = userLoginDAO
									.getUserLoginByName(number);
							// System.out.println("results="+results.toString());
							if (results != null)
								throw new Exception();

							UserLogin userLogin = new UserLogin();
							userLogin.setUlName(number);
							userLogin.setUlSign("stu");
							userLogin.setUlPassword(Constant.INIT_PASSWORD);
							userLoginDAO.updateEntity(userLogin);

							StudentInfo studentInfo = new StudentInfo();
							studentInfo.setClassInfo(classInfo);
							studentInfo.setUserLogin(userLogin);
							studentInfo.setSiNum(number);
							studentInfo.setSiRealName(name);
							studentInfo.setSiSex(sex);
							studentInfo.setSiSign("T");
							studentInfo.setSiInformation(detail);
							studentInfoDAO.updateEntity(studentInfo);

						} 
						catch (Exception e) 
						{
							//记录保存错误行
							errors.add(cells);
							count++;
							System.out.println("Add Student By Xls Error!");
							continue;
						}

					}
				}
			}
		}finally{
			// 最后关闭资源，释放内存
			if(wb!=null)
				wb.close();
			x.delete();
		}
		
		OutputStream os = null;
		WritableWorkbook book = null;
		// 整理错误数据
		if (errors.size() != 0) 
		{
			System.out.println("错误");
			try
			{
				UserLogin ul = AdminUtil.getUserLoginFromHttpSession(session);
				File f = new File(Constant.getCachePath(), ul.getUlName()
						+ ".xls");
				if (f.exists()) 
					f.delete();
				// f.createTempFile(ul.getUlName(), ".xls");

				os = new FileOutputStream(Constant.getCachePath()
						+ ul.getUlName() + ".xls");
				book = Workbook.createWorkbook(os);

				WritableSheet ws = book.createSheet("Error Line", 0);
				//首行信息
				Label label1 = new Label(0, 0,"学生学号");
				Label label2 = new Label(1, 0,"学生真实名字");
				Label label3 = new Label(2, 0,"性别");
				Label label4 = new Label(3, 0,"学生信息详情");
				ws.addCell(label1);
				ws.addCell(label2);
				ws.addCell(label3);
				ws.addCell(label4);
				
				for (int i = 1; i < errors.size(); i++)
				{
					for (int j = 0; j < errors.get(i).length; j++)
					{
						System.out.println("内容"+i+"行"+j+"列"+errors.get(i)[j].getContents());
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
				} catch (IOException e) {
					e.printStackTrace();
				} catch (WriteException e) {
					e.printStackTrace();
				}
			}

		}
		return count;
	}

	@Override
	public List<StudentInfo> getStudentInfoPagesForSearch(
			Page<StudentInfo> page, int[] pageParams, String collegeName,
			String professionName, String studentNum, String studentName)
	{
		
		collegeName = StringUtils.trimToEmpty(collegeName);
		professionName = StringUtils.trimToEmpty(professionName);
		studentNum = StringUtils.trimToEmpty(studentNum);
		studentName = StringUtils.trimToEmpty(studentName);

		List<StudentInfo> results = new ArrayList<>();
		StringBuilder resultsHQL = new StringBuilder(ALL_STUDENT_INFOS)
				.append(CommonConstant.ONE_EQUALS_ONE);
		resultsHQL.append(" AND  si.classInfo.ciCollege LIKE '%").append(collegeName).append("%' ");
		resultsHQL.append(" AND  si.classInfo.ciProfession LIKE '%").append(professionName).append("%' ");
		resultsHQL.append(" AND  si.siNum LIKE '%").append(studentNum).append("%' ");
		resultsHQL.append(" AND  si.siRealName LIKE '%").append(studentName).append("%' ");

		try
		{
			results = studentInfoDAO.findByPage(resultsHQL.toString(),
					pageParams[0], pageParams[1]);

			page.setTotalCount(studentInfoDAO.getCount(resultsHQL.toString()));
			page.setResult(results);

		} 
		catch (Exception e)
		{
			throw new EntityException(
					" StudentInfoServiceImpl getStudentInfoPagesForSearch() Error!!",e);
		}

		return results;
	}

	@Override
	public int deleteStudentInfo(String studentInfoIdStr) 
	{
		String[] studentInfoIdStrs;

		if (studentInfoIdStr != null && !studentInfoIdStr.equals(""))
		{
			studentInfoIdStrs = studentInfoIdStr.split(Constant.LINE);
			for (String studentInfoId : studentInfoIdStrs)
			{
				try 
				{
					StudentInfo studentInfo = studentInfoDAO.findById(studentInfoId);
					userLoginDAO.turnTransient(studentInfo.getUserLogin());
					studentInfoDAO.turnTransient(studentInfo);// 删除
				} 
				catch (Exception e)
				{
					throw new EntityException("Error!  StudentInfoServiceImpl.deleteStudentInfo(String studentInfoIdStr) ",e);
				}
			}

			return Constant.RESULT_SUCCESS;
		}

		return Constant.RESULT_FAIL;
	}

	@Override
	public int saveOrUpdateStudentInfo(StudentInfo studentInfo,
			UserLogin userLogin, String classInfoId) 
	{
		if (userLogin.getUlId() == null)// 判断用户名是否存在
		{
			UserLogin result = userLoginDAO.getUserLoginByName(userLogin.getUlName());
			if (result != null) 
			{
				return Constant.RESULT_EXIST;
			}
		}
		userLogin.setUlSign("stu");
		userLogin.setUlPassword(Constant.INIT_PASSWORD);// 设置初始密码
		userLoginDAO.updateEntity(userLogin);
		ClassInfo classInfo = null;

		if (classInfoId != null && !classInfoId.equals(""))
			classInfo = classInfoDAO.findById(classInfoId);
		else
			return Constant.RESULT_FAIL;

		studentInfo.setSiSign("T");
		studentInfo.setUserLogin(userLogin);
		studentInfo.setClassInfo(classInfo);

		studentInfoDAO.updateEntity(studentInfo);

		return Constant.RESULT_SUCCESS;
	}

	@Override
	public List<StudentInfo> getStudentInfoPages(Page<StudentInfo> page,
			int[] pageParams) 
	{
		List<StudentInfo> results = new ArrayList<>();

		StringBuilder resultsHQL = new StringBuilder(ALL_STUDENT_INFOS);

		try 
		{
			results = studentInfoDAO.findByPage(resultsHQL.toString(),
					pageParams[0], pageParams[1]);

			page.setTotalCount(studentInfoDAO.getCount(resultsHQL.toString()));
			page.setResult(results);

		}
		catch (Exception e)
		{
			throw new EntityException(
					"Error!  StudentInfoServiceImpl.getStudentInfoPages(...) ",e);
		}

		return results;
	}

	@Override
	public StudentInfo findStudentInfoById(String studentInfoID) 
	{
		return studentInfoDAO.findById(studentInfoID);
	}

	@Override
	public StudentInfo findStudentInfoByULID(String userLoginID) 
	{
		StringBuilder hql = new StringBuilder(ALL_STUDENT_INFOS)
				.append(CommonConstant.ONE_EQUALS_ONE)
				.append(" AND si.userLogin.ulId=").append(userLoginID);

		return studentInfoDAO.getUniqueResultByHQL(hql.toString());
	}

}
