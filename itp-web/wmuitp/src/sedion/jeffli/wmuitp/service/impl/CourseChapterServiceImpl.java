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
import sedion.jeffli.wmuitp.dao.CourseTeacherRelationDAO;
import sedion.jeffli.wmuitp.dao.impl.CourseChapterDAOImpl;
import sedion.jeffli.wmuitp.dao.impl.CourseDAOImpl;
import sedion.jeffli.wmuitp.dao.impl.CourseTeacherRelationDAOImpl;
import sedion.jeffli.wmuitp.entity.Course;
import sedion.jeffli.wmuitp.entity.CourseChapter;
import sedion.jeffli.wmuitp.entity.CourseTeacherRelation;
import sedion.jeffli.wmuitp.entity.UserLogin;
import sedion.jeffli.wmuitp.exception.EntityException;
import sedion.jeffli.wmuitp.service.CourseChapterService;
import sedion.jeffli.wmuitp.util.Page;
import sedion.jeffli.wmuitp.util.session.AdminUtil;


//是一个泛化的概念，仅仅表示一个组件 (Bean) ，可以作用在任何层次。
//@Service 通常作用在业务层，但是目前该功能与 @Component 相同。
@Service
public class CourseChapterServiceImpl implements CourseChapterService 
{
	
	private static final String All_COURSE_CHAPTER	 	= "FROM CourseChapter AS cc ";
	private static final String All_COURSE			 	= "FROM Course AS c ";
	private static final String All_COURSE_TEACHER_RELATION			 	= "FROM CourseTeacherRelation AS ctr ";
	
	@Autowired
	private CourseDAO    	 courseDAO;
	@Autowired
	private CourseChapterDAO courseChapterDAO;
	@Autowired
	private CourseTeacherRelationDAO courseTeacherRelationDAO;
	@Autowired 
	public HttpSession 		 session; 

	@Override
	public int addCourseChapterByXls(MultipartFile xlsFile)
	{
		//保存错误的列表
		List<Cell[]> errors = new ArrayList<>();
		File file = null;
		Workbook wb = null;
		int count=0;
		try {
			if(new File("E:"+File.separator+"cache"+File.separator).exists())
			{
				new File("E:"+File.separator+"cache"+File.separator).mkdir();
			}
			
			file=new File("E:"+File.separator+"cache"+File.separator, Calendar.getInstance().getTimeInMillis()+ ".xls");
			try {
				xlsFile.transferTo(file);
			} catch (IllegalStateException e1) {
				System.out.println("读取文件异常");
				e1.printStackTrace();
			} catch (IOException e1) {
				System.out.println("读取文件异常");
				e1.printStackTrace();
			}
			  
	          try {
	             //构造Workbook（工作薄）对象
	             wb=Workbook.getWorkbook(file);
	          } catch (BiffException e) {
	             e.printStackTrace();
	          } catch (IOException e) {
	             e.printStackTrace();
	         }
	         
	         if(wb==null)
	             return -1;
	         //获得了Workbook对象之后，就可以通过它得到Sheet（工作表）对象了
	         Sheet[] sheet = wb.getSheets();
	         
	          if(sheet!=null&&sheet.length>0){
	             //对每个工作表进行循环
	              for(int i=0;i<sheet.length;i++){
	                 //得到当前工作表的行数
	                 int rowNum = sheet[i].getRows();
	                 /******************************/
	                 Cell[] cells = null;
	                  for(int j=1;j<rowNum;j++){
	                     //得到当前行的所有单元格
	                	  try {
	                	 cells = sheet[i].getRow(j);
	                     String CourseChapterName=cells[0].getContents().trim();
	                     String CourseName=cells[1].getContents().trim();
	                     String Note=cells[2].getContents().trim();
	                     //判断题目或者答案是否为空
	                     if(CourseChapterName.equals("")||CourseName.equals(""))
	                     {
	                    	 throw new Exception();
	                     }
	                     //获得基本课程
	                     StringBuilder hql = new StringBuilder(All_COURSE) ;
	         			 hql.append(CommonConstant.ONE_EQUALS_ONE);
	         			 hql.append(" AND c.CName = '");
	         			 hql.append(CourseName);
	         			 hql.append("' ");
	         			Course course=courseDAO.findByHQL(hql.toString());
	         			if(course==null)
	         			{
	         				throw new Exception();
	         			}
	         			//保存题目
	         			CourseChapter courseChapter=new CourseChapter();
	         			courseChapter.setCcName(CourseChapterName);
	         			courseChapter.setCourse(course);
	         			courseChapter.setCcSign(Note);
	         			
	         			courseChapterDAO.updateEntity(courseChapter);
	                    
	                	  } catch (Exception e) {
	                		errors.add(cells);
	  						count++;
	  						System.out.println("Add courseChapter By Xls Fail!");
	  						continue;
	  					}
	                	  
	                 }
	             }
	         }
		} finally{
			//最后关闭资源，释放内存
			if(wb!=null)
				wb.close();
			if(file!=null)
				file.delete();
		}
    
       
		
        //整理错误数据
		if(errors.size()!=0)
		{
			WritableWorkbook  book = null;
			OutputStream os = null;
			try {
				UserLogin ul=AdminUtil.getUserLoginFromHttpSession(session);
				File f = new File("E:/cache/",ul.getUlName() + ".xls");
				if(f.exists())
				{
					f.delete();
				}
//				f.createTempFile(ul.getUlName(), ".xls");
				
				os = new FileOutputStream("E:/cache/"+ul.getUlName() + ".xls");   
				book = Workbook.createWorkbook(os);   
				WritableSheet ws = book.createSheet("Test Sheet 1",0);  
				//写入首行信息
				Label label1=new Label(0,0,"课程进度"); 
				Label label2=new Label(1,0,"所属课程"); 
				Label label3=new Label(2,0,"备注"); 
				ws.addCell(label1);ws.addCell(label2);ws.addCell(label3);
				 
				for(int i=1;i<=errors.size();i++)
				{
					for(int j=0;j<errors.get(i-1).length;j++)
					{
//						System.out.println("内容"+i+"行"+j+"列"+errors.get(i)[j].getContents());
						Label label=new Label(j,i,errors.get(i-1)[j].getContents()); 
						//将定义好的单元格添加到工作表中  
						ws.addCell(label);
					}
				}
				//写入
				book.write();  
				System.out.println("导入失败内容写出成功");
			} catch (Exception e) {
				System.out.println("导入失败内容写出失败");
				throw new EntityException(" CourseChapterServiceImpl addCourseChapterByXls(...) Error!!", e);
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
	public int deleteCourseChapter(String courseChapterIdStr)
	{
		String[] courseChapterIdStrs;
		
		if(StringUtils.isNotEmpty(courseChapterIdStr))
		{
			courseChapterIdStrs = courseChapterIdStr.split(Constant.LINE);
			for (String courseChapterId : courseChapterIdStrs)
			{
				try
				{
					CourseChapter courseChapter = courseChapterDAO.findById(Integer.valueOf(courseChapterId));
					courseChapterDAO.turnTransient(courseChapter);//删除
				}
				catch (Exception e)
				{
					throw new EntityException("CourseChapterServiceImpl deleteCourseChapter(String courseChapterIdStr) Error", e);
				}
				
			}
			
			return Constant.RESULT_SUCCESS;
		}	
		return Constant.RESULT_FAIL;
	}
	
	@Override
	public int saveOrUpdateCourseChapter(String courseID,CourseChapter courseChapter)
	{
		Course course = null ;
		
		if (StringUtils.isNotEmpty(courseID))
		{
			course = courseDAO.findById(Integer.valueOf(courseID));
		}
		else 
			return Constant.RESULT_FAIL;
		try
		{
			courseChapter.setCourse(course);
			
			courseChapterDAO.updateEntity(courseChapter);
		} 
		catch (Exception e)
		{
			throw new EntityException("CourseChapterServiceImpl saveOrUpdateCourseChapter(String courseID,CourseChapter courseChapter) Error", e);
		}
		
		return Constant.RESULT_SUCCESS;
	}
	
	@Override
	public CourseChapter getCourseChapterById(String courseChapterID)
	{
		return courseChapterDAO.findById(Integer.valueOf(courseChapterID));
	}
	
	@Override
	public List<CourseChapter> getCourseChapters()
	{
		return courseChapterDAO.getListByHQL(All_COURSE_CHAPTER);
	}

	@Override
	public List<CourseChapter> getCourseChaptersByTeaSession(String courseName)
	{
		UserLogin ul = AdminUtil.getUserLoginFromHttpSession(session);
		courseName = StringUtils.trimToNull(courseName);
		StringBuilder hql = new StringBuilder(All_COURSE_TEACHER_RELATION).append(CommonConstant.ONE_EQUALS_ONE)
				.append(" AND ctr.teacherInfo.userLogin=?");
		if(courseName!=null)
			hql.append(" AND ctr.course.CName ='").append(courseName).append("' ");
		
		List<CourseTeacherRelation> courseTeacherRelations = courseTeacherRelationDAO.getListByHQL(hql.toString(),ul);
		List<Course> courses = new ArrayList<>();
		for(CourseTeacherRelation courseTeacherRelation : courseTeacherRelations)
			courses.add(courseTeacherRelation.getCourse());
		List<CourseChapter> courseChapters = new ArrayList<>();
		for(Course course : courses)
			courseChapters.addAll(course.getCourseChapters());
		return courseChapters;
	}

	@Override
	public List<CourseChapter> getCourseChaptersBySearch(String courseName)
	{
		courseName = StringUtils.trimToNull(courseName);
		StringBuilder hql = new StringBuilder(All_COURSE_TEACHER_RELATION).append(CommonConstant.ONE_EQUALS_ONE);
		if(courseName!=null)
			hql.append(" AND ctr.course.CName ='").append(courseName).append("' ");		
		
		List<CourseTeacherRelation> courseTeacherRelations = courseTeacherRelationDAO.getListByHQL(hql.toString());
		List<Course> courses = new ArrayList<>();
		for(CourseTeacherRelation courseTeacherRelation : courseTeacherRelations)
			courses.add(courseTeacherRelation.getCourse());
		List<CourseChapter> courseChapters = new ArrayList<>();
		for(Course course : courses)
			courseChapters.addAll(course.getCourseChapters());
		return courseChapters;
	}
	@Override
	public List<CourseChapter> getCourseChaptersPages(Page<CourseChapter> page, int[] pageParams)
	{
		List<CourseChapter> results = new ArrayList<>();

		StringBuilder resultsHQL = new StringBuilder(All_COURSE_CHAPTER);
		
		try
		{
			results = courseChapterDAO.findByPage(resultsHQL.toString(), pageParams[0], pageParams[1]);
			
			page.setTotalCount(courseChapterDAO.getCount(resultsHQL.toString()));
			page.setResult(results);
			
		} 
		catch (Exception e)
		{
			throw new EntityException(" getCourseChaptersPages(Page<CourseChapter> page, int[] pageParams) Error", e);
		}

		return results;
	}
	@Override
	public List<CourseChapter> getCourseChaptersPagesSearchByCourseName(Page<CourseChapter> page, int[] pageParams,String courseName)
	{
		List<CourseChapter> results = new ArrayList<>();
		
		StringBuilder resultsHQL = new StringBuilder(All_COURSE_CHAPTER).append(CommonConstant.ONE_EQUALS_ONE);
		if(StringUtils.trimToNull(courseName)!=null)
				resultsHQL.append(" AND cc.course.CName='").append(courseName).append("' ");
		
		try
		{
			results = courseChapterDAO.findByPage(resultsHQL.toString(), pageParams[0], pageParams[1]);
			
			page.setTotalCount(courseChapterDAO.getCount(resultsHQL.toString()));
			page.setResult(results);
			
		} 
		catch (Exception e)
		{
			throw new EntityException(" getCourseChaptersPages(Page<CourseChapter> page, int[] pageParams) Error", e);
		}
		
		return results;
	}

}
