package sedion.jeffli.wmuitp.web.admin;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import sedion.jeffli.wmuitp.base.MainBase;
import sedion.jeffli.wmuitp.constant.AdminWebConstant;
import sedion.jeffli.wmuitp.constant.database.CommonConstant;
import sedion.jeffli.wmuitp.constant.database.MD5;
import sedion.jeffli.wmuitp.constant.main.Constant;
import sedion.jeffli.wmuitp.entity.UserLogin;
import sedion.jeffli.wmuitp.util.session.AdminUtil;

@RequestMapping(value = "/admin")
@Controller
public class AdminController extends MainBase 
{
	 @Autowired 
	 public HttpSession session; 
	 
	@RequestMapping({"","/index"})
	public ModelAndView index() 
	{
		ModelAndView mav = new ModelAndView(AdminWebConstant.ADMIN_LOGIN_VIEW);
		return mav;
	}
	
	/**
	 * 登录
	 * @param loginName	用户名
	 * @param password	密码
	 * @return
	 */
	@RequestMapping(value = "/login")
	public String login(String loginName, String password) 
	{
		//清除session
//		if(AdminUtil.getUserLoginFromHttpSession(session)!=null)
//			session.removeAttribute("admin");
//		if(AdminUtil.getUserLoginIDFromHttpSession(session)!=null)
//			session.removeAttribute("adminID");
		
		String  pwd = password;
		UserLogin userLogin = userLoginService.findUserLoginByULName(loginName);
		if(userLogin != null )
		{
			//设置密码用MD5加密**********
			password = MD5.EncryptionByMD5(password);
			
			if(userLogin.getUlPassword().equals(password)&&!userLogin.getUlSign().equals("stu"))
			{
				System.out.println(userLogin.getUlName()+"：登录成功！");
				userLogin.setUlPassword(pwd);
				AdminUtil.saveAdminToSession(session, userLogin);		//Admin	   存session
				AdminUtil.saveAdminIDToSession(session, userLogin);		//AdminID存session
				
				if(userLogin.getUlSign().equals(CommonConstant.TEACHER_PER))
				{
					
					AdminUtil.saveTeacherIDToSession(session, 			//teacherName存session
							teacherInfoService.getTeacherInfoIDByOBJ(userLogin.getUlId().toString()));
				
				}
				return AdminWebConstant.ADMIN_INDEX_VIEW;
			}
			else
			{
				
				System.out.println(userLogin.getUlName()+"：密码不正确！或者是非管理员老师账号");
				
				return AdminWebConstant.ADMIN_LOGIN_VIEW;
				
			}
		}
		else
		{
			
			System.out.println("用户不存在!");
			
			return AdminWebConstant.ADMIN_LOGIN_VIEW;
			
		}
	}
	/*老师端-显示修改密码*/
	@RequestMapping(value = "/showChangeUserpwdByUserLogin")
	public ModelAndView showChangeUserpwdByUserLogin(String ulName,String ulPassword) throws ParseException
	{	
		UserLogin userLogin = userLoginService.findUserLoginByULName(ulName);
		ModelAndView mav = new ModelAndView(AdminWebConstant.getAdminChangeUserpwdByUserloginView());
		mav.addObject("userLoginId",userLogin.getUlId());
		return mav;
	}
	
	/*学生端-显示修改密码*/
	@RequestMapping(value = "/showChangeUserpwdByUserLoginApp")
	public ModelAndView showChangeUserpwdByUserLoginApp(String ulName,String ulPassword) throws ParseException
	{	
		UserLogin userLogin = userLoginService.findUserLoginByULName(ulName);
		ModelAndView mav = new ModelAndView(AdminWebConstant.getAdminChangeUserpwdByUserloginAppView());
		mav.addObject("userLoginId",userLogin.getUlId());
		return mav;
	}
	
	/*后台显示修改密码*/
	@RequestMapping(value = "/changeUserpwdByUserLogin")
	public @ResponseBody String  changeUserpwdByUserLogin(String userLoginId,String ulPassword) throws ParseException
	{	
		System.out.println("AdminController.changeUserpwdByUserLogin()" + userLoginId +"..."+ ulPassword);
		
		String oldUserLoginId = AdminUtil.getUserLoginFromHttpSession(session).getUlId().toString();
		if(!userLoginId.equals(oldUserLoginId))
			return failResponse("修改密码失败！");
	
		int sign = userLoginService.changeUserpwdByUserLogin(userLoginId,ulPassword);
		
		if( sign == Constant.RESULT_SUCCESS )
			return successResponse("修改密码成功！请重新登录下","","");
	
		return failResponse("修改密码失败！");
	}
	
	/*App显示修改密码*/
	@RequestMapping(value = "/changeUserpwdByUserLoginApp")
	public @ResponseBody String  changeUserpwdByUserLoginApp(String userLoginId,String ulPassword) throws ParseException
	{	
		System.out.println("AdminController.changeUserpwdByUserLogin()" + userLoginId +"..."+ ulPassword);
		
		int sign = userLoginService.changeUserpwdByUserLogin(userLoginId,ulPassword);
		
		if( sign == Constant.RESULT_SUCCESS )
			return successResponse("修改密码成功！请重新登录下","","");
	
		return failResponse("修改密码失败！");
	}
}
