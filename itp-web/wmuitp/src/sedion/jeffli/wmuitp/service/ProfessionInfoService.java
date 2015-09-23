package sedion.jeffli.wmuitp.service;

import java.util.List;

import sedion.jeffli.wmuitp.entity.ProfessionInfo;
import sedion.jeffli.wmuitp.util.Page;

public interface ProfessionInfoService {
	
	/**
	 * 获得全部专业详情
	 */
	List<ProfessionInfo> findAllProfessionInfo();
	
	/**
	 *  通过id删除专业详情
	 * @param professionInfoIdStr            要删除id
	 */
	int deleteProfessionInfo(String professionInfoIdStr);
	
	/**
	 *  增加或者修改专业详情
	 * @param professionInfo       专业详情
	 */
	int saveOrUpdateProfessionInfo(ProfessionInfo professionInfo);
	
	/**
	 * 根据ID获取专业详情
	 * @param professionInfoID    专业详情id
	 */
	ProfessionInfo getProfessionInfoByID(String professionInfoID);
	
	/**
	 * 获取专业信息列表
	 * @param page			分页类
	 * @param pageParams	分页参数
	 */
	public List<ProfessionInfo> getProfessionInfosPages(Page<ProfessionInfo> page,int[] pageParams);
}
