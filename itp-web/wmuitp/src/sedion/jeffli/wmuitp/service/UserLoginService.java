package sedion.jeffli.wmuitp.service;
import java.util.List;

import sedion.jeffli.wmuitp.entity.UserLogin;
import sedion.jeffli.wmuitp.util.Page;

public interface UserLoginService
{
	/**
	 * 保存或者修改用户
	 * @param userLogin  用户
	 * @return
	 */
	int saveOrUpdateUserLogin(UserLogin userLogin);
	
	/**
	 *  获取用户
	 * @param objId		用户名ID
	 * @return
	 */
	public UserLogin findUserLoginById(Integer objId);
	
	/**
	 * 获取用户
	 * @param ulName	用户名
	 * @return
	 */
	public UserLogin findUserLoginByULName(String ulName);

	/**
	 * 手机端登录
	 * @param username	用户名
	 * @param password	密码
	 * @return
	 */
	public int  checkUserLoginByPhone(String username, String password);

	/**
	 * 获取所有登录名
	 * @return
	 */
	public List<UserLogin> findAllUserLogin();

	/**
	 * 获取用户名
	 * @param page			分页类
	 * @param pageParams	分页参数
	 */
	public List<UserLogin> getUserLoginPages(Page<UserLogin> page,int[] pageParams,String UserName,String UserId,String UserType);

	int changeUserpwdByUserLogin(String userLoginId, String ulPassword);
}
