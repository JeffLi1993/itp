package sedion.jeffli.wmuitp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sedion.jeffli.wmuitp.constant.database.CommonConstant;
import sedion.jeffli.wmuitp.constant.database.MD5;
import sedion.jeffli.wmuitp.constant.main.Constant;
import sedion.jeffli.wmuitp.dao.UserLoginDAO;
import sedion.jeffli.wmuitp.entity.UserLogin;
import sedion.jeffli.wmuitp.exception.EntityException;
import sedion.jeffli.wmuitp.service.UserLoginService;
import sedion.jeffli.wmuitp.util.Page;


//是一个泛化的概念，仅仅表示一个组件 (Bean) ，可以作用在任何层次。
//@Service 通常作用在业务层，但是目前该功能与 @Component 相同。
@Service
public class UserLoginServiceImpl implements UserLoginService 
{
	private static final String ALL_USER_LOGIN      = "FROM UserLogin AS ul ";
	
	@Autowired
	private UserLoginDAO userLoginDAO;

	@Override
	public int saveOrUpdateUserLogin(UserLogin userLogin)
	{
		userLoginDAO.updateEntity(userLogin);
		
		return Constant.RESULT_SUCCESS;
	}
	
	public List<UserLogin> findAllUserLogin()
	{
		return userLoginDAO.findAll();
	}
	
	public UserLogin findUserLoginById(Integer objId) 
	{
		return userLoginDAO.findById(objId);
	}
	
	public UserLogin findUserLoginByULName(String ulName)
	{
		return userLoginDAO.getUserLoginByName(ulName);
	}

	
	@Override
	public int checkUserLoginByPhone(String username, String password)
	{
		
		UserLogin  login = userLoginDAO.getUserLoginByName(username);	//获取是否有这个用户
 		if(login != null)
		{
			if(login.getUlPassword().equals(password))
			{
				return Constant.RESULT_SUCCESS;
			}
			return Constant.RESULT_FAIL;
		}
		return Constant.RESULT_FAIL;
	}
	
	@Override
	public List<UserLogin> getUserLoginPages(Page<UserLogin> page,int[] pageParams,String UserName,String UserId,String UserType)
	{
		List<UserLogin> results = new ArrayList<>();

		String hql = ALL_USER_LOGIN 
				+ CommonConstant.ONE_EQUALS_ONE;
		 UserName = StringUtils.trimToEmpty(UserName);
		 UserId	  = StringUtils.trimToEmpty(UserId);
		 UserType = StringUtils.trimToEmpty(UserType);
		 
		 if(UserName!="")
			 hql += " AND ul.ulName LIKE '%"+UserName+"%' ";
		 if(UserId!="")
			 hql += " AND ul.ulId LIKE '%"+UserId+"%' ";
		 if(UserType!="")
			 hql += " AND ul.ulSign LIKE '%"+UserType+"%' ";
		try
		{
			results = userLoginDAO.findByPage(hql, pageParams[0], pageParams[1]);
			
			page.setTotalCount(userLoginDAO.getCount(hql));
			page.setResult(results);
			
		} 
		catch (Exception e)
		{
			throw new EntityException("Error! getUserLoginPages. ",e);
		}

		return results;
	}

	@Override
	public int changeUserpwdByUserLogin(String userLoginId, String ulPassword)
	{
		UserLogin userLogin = userLoginDAO.findById(Integer.valueOf(userLoginId));
		
		if (StringUtils.isNotEmpty(ulPassword))
		{
			ulPassword = MD5.EncryptionByMD5(ulPassword);
			userLogin.setUlPassword(ulPassword);
		}
		else 
			return Constant.RESULT_FAIL;
			
		userLoginDAO.updateEntity(userLogin);
		
		return Constant.RESULT_SUCCESS;
	}
}
