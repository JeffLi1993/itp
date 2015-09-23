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

import sedion.jeffli.wmuitp.constant.TeacherInfoConstant;
import sedion.jeffli.wmuitp.constant.main.Constant;
import sedion.jeffli.wmuitp.constant.database.CommonConstant;
import sedion.jeffli.wmuitp.dao.ProfessionInfoDAO;
import sedion.jeffli.wmuitp.dao.TeacherInfoDAO;
import sedion.jeffli.wmuitp.dao.UserLoginDAO;
import sedion.jeffli.wmuitp.entity.*;
import sedion.jeffli.wmuitp.exception.EntityException;
import sedion.jeffli.wmuitp.service.TeacherInfoService;
import sedion.jeffli.wmuitp.util.FileOperateUtil;
import sedion.jeffli.wmuitp.util.Page;
import sedion.jeffli.wmuitp.util.session.AdminUtil;

//是一个泛化的概念，仅仅表示一个组件 (Bean) ，可以作用在任何层次。
//@Service 通常作用在业务层，但是目前该功能与 @Component 相同。
@Service
public class TeacherInfoServiceImpl implements TeacherInfoService 
{

	private static final String All_TEACHERINFOS = "FROM TeacherInfo AS ti ";

	@Autowired
	private UserLoginDAO userLoginDAO;
	@Autowired
	private TeacherInfoDAO teacherInfoDAO;
	@Autowired
	private ProfessionInfoDAO professionInfoDAO;
	@Autowired
	public HttpSession session;
	
	@Override 
	public int addTeacherByXls(MultipartFile file)
	{
		// 保存错误列表
		List<Cell[]> errors = new ArrayList<>();
		if (new File(Constant.getCachePath()).exists())
			new File(Constant.getCachePath()).mkdir();

		File file1 = new File(Constant.getCachePath(), Calendar.getInstance().getTimeInMillis() + ".xls");
		try {
			file.transferTo(file1);
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
			wb = Workbook.getWorkbook(file1);
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
				// 对第一个工作表进行操作
				// 得到当前工作表的行数
				int rowNum = sheet[0].getRows();

				Cell[] cells = null;
				for (int j = 1; j < rowNum; j++) 
				{
					// 得到当前行的所有单元格
					try 
					{
						cells = sheet[0].getRow(j);

						String name 		= cells[0].getContents().trim();
						String number 		= cells[1].getContents().trim();
						String job 			= cells[2].getContents().trim();
						String age 			= cells[3].getContents().trim();
						String skillDetail 	= cells[4].getContents().trim();
						String profession 	= cells[5].getContents().trim();
						String college 		= cells[6].getContents().trim();

						//判断是否为空
						if (name.equals("") || number.equals("")|| profession.equals("")|| college.equals("")) 
							throw new Exception();

						
						// 判断用户名是否存在
						UserLogin results = userLoginDAO.getUserLoginByName(number);
						// System.out.println("results="+results.toString());
						if (results != null)
							throw new Exception();
						UserLogin userLogin = new UserLogin();
						userLogin.setUlSign("tea");
						userLogin.setUlPassword(Constant.INIT_PASSWORD);
						userLogin.setUlName(number);
						userLoginDAO.updateEntity(userLogin);
						
						//判断专业是否存在
						ProfessionInfo professionInfo = professionInfoDAO.getprofessionInfoByProfessionInfoAndCollege(profession, college);
						System.out.println("professionInfo=="+professionInfo);
						if(professionInfo==null)
						{
							professionInfo = new ProfessionInfo(); 
							professionInfo.setPiCollege(college);
							professionInfo.setPiProfession(profession);
							professionInfoDAO.updateEntity(professionInfo);
						}
						//创建teacher实例
						TeacherInfo teacher = new TeacherInfo();
						teacher.setUserLogin(userLogin);
						teacher.setTiSkills(skillDetail);
						teacher.setTiSign("T");
						teacher.setTiName(name);
						teacher.setTiJob(job);
						teacher.setTiAge(age);
						teacher.setTiAddress("");
						teacher.setProfessionInfo(professionInfo);
						teacherInfoDAO.updateEntity(teacher);

					} 
					catch (Exception e) 
					{
						//记录保存错误行
						errors.add(cells);
						count++;
						System.out.println("Add Teacher By Xls Error!");
						continue;
					}
				}
			}
		}finally{
			// 最后关闭资源，释放内存
			if(wb!=null)
				wb.close();
			if(file1!=null)
				file1.delete();
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
				Label label1 = new Label(0, 0,"教师姓名");
				Label label2 = new Label(1, 0,"教师编号");
				Label label3 = new Label(2, 0,"教师职称(可空)");
				Label label4 = new Label(3, 0,"教师年龄(可空)");
				Label label5 = new Label(4, 0,"教师技能详情(可空)");
				Label label6 = new Label(5, 0,"专业名称");
				Label label7 = new Label(6, 0,"所属学院");
				ws.addCell(label1);
				ws.addCell(label2);
				ws.addCell(label3);
				ws.addCell(label4);
				ws.addCell(label5);
				ws.addCell(label6);
				ws.addCell(label7);
				
				for (int i = 1; i < errors.size(); i++)
				{
					for (int j = 0; j < errors.get(i).length; j++)
					{
						System.out.println("内容"+i+"行"+j+"列"+errors.get(i)[j].getContents());
						Label label = new Label(j, i,errors.get(i)[j].getContents());
						// 将定义好的单元格添加到工作表中
						ws.addCell(label);
					}
				}
				// 写入
				book.write();
				System.out.println("导入失败内容写出成功");
			} catch (Exception e) {
				System.out.println("导入失败内容写出失败");
				throw new EntityException(" StudentInfoServiceImpl addTeacherByXls(...) Error!!",e);
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
	public int initTeacherInfoPassword(String teacherInfoId)
	{
		try
		{
			TeacherInfo teacherInfo = teacherInfoDAO.findById(teacherInfoId);
			UserLogin userLogin = teacherInfo.getUserLogin();
			userLogin.setUlPassword(Constant.INIT_PASSWORD);
			userLoginDAO.updateEntity(userLogin);
		} 
		catch (Exception e)
		{
			throw new EntityException(
					"Error! TeacherInfoServiceImpl.initTeacherInfoPassword(String teacherInfoId) ",
					e);
		}
		return Constant.RESULT_SUCCESS;
	}

	@Override
	public TeacherInfo getTeacherInfoByTeacherInfoUserLogin(UserLogin userLogin) 
	{
		return teacherInfoDAO.getTeacherInfoByUserLoginId(userLogin.getUlId().toString());
	}

	@Override
	public TeacherInfo getTeacherInfoByTeacherInfoID(String teacherInfoId)
	{
		return teacherInfoDAO.findById(Integer.valueOf(teacherInfoId));
	}

	@Override
	public int deleteTeacherInfo(String teacherInfoIDStr) 
	{
		String[] teacherInfoIDStrs;

		if (teacherInfoIDStr != null && !teacherInfoIDStr.equals("")) 
		{
			teacherInfoIDStrs = teacherInfoIDStr.split(Constant.LINE);
			for (String teacherInfoID : teacherInfoIDStrs) 
			{
				
				try
				{
					TeacherInfo teacherInfo = teacherInfoDAO
							.findById(teacherInfoID);
					userLoginDAO.turnTransient(teacherInfo.getUserLogin());
					teacherInfoDAO.turnTransient(teacherInfo);// 删除
				} 
				catch (Exception e) 
				{
					throw new EntityException(
							"Error! TeacherInfoServiceImpl.deleteTeacherInfo(String teacherInfoIDStr) ",
							e);
				}
			}

			return Constant.RESULT_SUCCESS;
		}

		return Constant.RESULT_FAIL;

	}

	@Override
	public int saveOrUpdateTeacherInfo(TeacherInfo teacherInfo,
			UserLogin userLogin, String professionInfoID, MultipartFile file) 
	{
		if (userLogin.getUlId() == null)// 验证用户名是否已经存在
		{
			UserLogin result = userLoginDAO.getUserLoginByName(userLogin.getUlName());
			if (result != null)
				return Constant.RESULT_EXIST;
		}
		userLogin.setUlPassword(Constant.INIT_PASSWORD);// 设置初始密码
		userLogin.setUlSign("tea");
		userLoginDAO.updateEntity(userLogin);
		ProfessionInfo professionInfo = new ProfessionInfo();
		/*
		 * if (file == null && teacherInfo.getTiId() != null) {
		 * System.out.println("进来"); String Address =
		 * teacherInfoDAO.findById(teacherInfo.getTiId()) .getTiAddress();
		 * teacherInfo.setTiAddress(Address); }
		 */

		if (professionInfoID != null && !professionInfoID.equals("")) 
		{
			professionInfo = professionInfoDAO.findById(professionInfoID);
		} else
			return Constant.RESULT_FAIL;

		teacherInfo.setTiSign("T");
		teacherInfo.setUserLogin(userLogin);
		teacherInfo.setProfessionInfo(professionInfo);
		teacherInfoDAO.updateEntity(teacherInfo);
		/****************** 保存照片 ************************/
		if (file != null) {
			System.out.println("进来了");
			String path = TeacherInfoConstant.TEACHER_IMG_PATH;// 储存地址
			File dirFlie = new File(path);
			String fileName = String.valueOf(teacherInfo.getTiId())
					+ FileOperateUtil.getExtension(file.getOriginalFilename());
			File targetFile = new File(path, fileName);
			System.out.println("dirFlie.exists()=" + dirFlie.exists());
			if (!dirFlie.isDirectory()) // 如果文件夹不存在 则创建文件夹
				dirFlie.mkdirs();

			try 
			{
				file.transferTo(targetFile);// 保存
			} 
			catch (Exception e) 
			{
				// file Exception
				e.printStackTrace();
			}
			teacherInfo.setTiAddress(fileName);
			teacherInfoDAO.updateEntity(teacherInfo);
		}
		/******************************************/

		return Constant.RESULT_SUCCESS;
	}

	@Override
	public List<TeacherInfo> getTeacherInfosPages(Page<TeacherInfo> page,
			int[] pageParams) 
	{
		List<TeacherInfo> results = new ArrayList<>();
		StringBuilder resultsHQL = new StringBuilder(All_TEACHERINFOS);
		try
		{
			results = teacherInfoDAO.findByPage(resultsHQL.toString(),
					pageParams[0], pageParams[1]);

			page.setTotalCount(teacherInfoDAO.getCount(resultsHQL.toString()));
			page.setResult(results);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return results;
	}

	@Override
	public TeacherInfo getTeacherInfoIDByOBJ(String userLoginId) 
	{

		String hql = All_TEACHERINFOS + CommonConstant.ONE_EQUALS_ONE;
		if (userLoginId != null && !userLoginId.equals("")) 
		{
			hql += " AND ti.userLogin.ulId=" + userLoginId;
		}

		return teacherInfoDAO.getUniqueResultByHQL(hql);
	}

	@Override
	public List<TeacherInfo> getTeacherInfos() 
	{
		return teacherInfoDAO.findAll();
	}

	@Override
	public List<TeacherInfo> getLessTeacherInfoForSearch(String collegeName,
			String professionName, String teacherName, String jobName) 
	{
		collegeName = StringUtils.trimToEmpty(collegeName);
		professionName = StringUtils.trimToEmpty(professionName);
		teacherName = StringUtils.trimToEmpty(teacherName);
		jobName = StringUtils.trimToEmpty(jobName);

		StringBuilder resultsHQL = new StringBuilder(All_TEACHERINFOS)
				.append(CommonConstant.ONE_EQUALS_ONE);

		resultsHQL.append(" AND ti.professionInfo.piCollege LIKE '%")
				.append(collegeName).append("%' ");
		resultsHQL.append(" AND ti.tiJob LIKE '%").append(jobName)
				.append("%' ");
		resultsHQL.append(" AND ti.professionInfo.piProfession LIKE '%")
				.append(professionName).append("%' ");
		resultsHQL.append(" AND ti.tiName LIKE '%").append(teacherName)
				.append("%' ");

		return teacherInfoDAO.getListByHQL(resultsHQL.toString());

	}

	@Override
	public List<TeacherInfo> getTeacherInfosPagesForSearch(
			Page<TeacherInfo> page, int[] pageParams, String collegeName,
			String professionName, String teacherName, String jobName) 
	{

		collegeName = StringUtils.trimToEmpty(collegeName);
		professionName = StringUtils.trimToEmpty(professionName);
		teacherName = StringUtils.trimToEmpty(teacherName);
		jobName = StringUtils.trimToEmpty(jobName);

		List<TeacherInfo> results = new ArrayList<>();

		StringBuilder resultsHQL = new StringBuilder(All_TEACHERINFOS)
				.append(CommonConstant.ONE_EQUALS_ONE);
		resultsHQL.append(" AND ti.professionInfo.piCollege LIKE '%")
				.append(collegeName).append("%' ");
		resultsHQL.append(" AND ti.tiJob LIKE '%").append(jobName)
				.append("%' ");
		resultsHQL.append(" AND ti.professionInfo.piProfession LIKE '%")
				.append(professionName).append("%' ");
		resultsHQL.append(" AND ti.tiName LIKE '%").append(teacherName)
				.append("%' ");

		try 
		{
			results = teacherInfoDAO.findByPage(resultsHQL.toString(),
					pageParams[0], pageParams[1]);

			page.setTotalCount(teacherInfoDAO.getCount(resultsHQL.toString()));
			page.setResult(results);

		}
		catch (Exception e)
		{
			throw new EntityException(
					"Error! TeacherInfoServiceImpl.getTeacherInfosPagesForSearch(...) ",
					e);
		}

		return results;
	}

}
