package sedion.jeffli.wmuitp.service;

import java.util.List;

import sedion.jeffli.wmuitp.entity.*;
import sedion.jeffli.wmuitp.util.Page;

public interface ClassInfoService {

	/**
	 * 获得教师常用的前几个班级
	 * @param userLogin   某个教师
	 * @return
	 */
	List<ClassInfo> findCommonClassInfo(UserLogin userLogin);

	/**
	 * 获得所有班级
	 */
	List<ClassInfo> findAllClassInfo();
	
	/**
	 * 删除 班级
	 * @param classInfoIdStr  班级ids
	 */
	int deleteClassInfo(String classInfoIdStr);
	
	/**
	 * 保存或者修改 班级
	 * @param classInfo  班级实例
	 */
	int saveOrUpdateClassInfo(ClassInfo classInfo);
	
	/**
	 * 根据ID获取班级详情
	 * @param classInfoId    班级详情id
	 */
	ClassInfo getClassInfoByClassInfoID(String classInfoId);
	
	/**
	 * 获取班级信息列表
	 * @param page			分页类
	 * @param pageParams	分页参数
	 */
	public List<ClassInfo> getClassInfosPages(Page<ClassInfo> page,int[] pageParams);
	
	/**
	 * 获取班级信息列表
	 * @param page			分页类
	 * @param pageParams	分页参数
	 */
	public List<ClassInfo> getClassInfosPagesBySearch(Page<ClassInfo> page,int[] pageParams,String className,String professionName,String collegeName);
	
	/**
	 * 获得全部班级或者获得搜索内容
	 * @param className 班级名字
	 * @param professionName 专业名字
	 * @param collegeName	学院名字
	 * @return
	 */
	List<ClassInfo> getClassInfosBySearch(String className,String professionName,String collegeName);

}
