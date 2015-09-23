package sedion.jeffli.wmuitp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sedion.jeffli.wmuitp.constant.main.Constant;
import sedion.jeffli.wmuitp.dao.ProfessionInfoDAO;
import sedion.jeffli.wmuitp.entity.ProfessionInfo;
import sedion.jeffli.wmuitp.exception.EntityException;
import sedion.jeffli.wmuitp.service.ProfessionInfoService;
import sedion.jeffli.wmuitp.util.Page;

//是一个泛化的概念，仅仅表示一个组件 (Bean) ，可以作用在任何层次。
//@Service 通常作用在业务层，但是目前该功能与 @Component 相同。
@Service
public class ProfessionInfoServiceImpl implements ProfessionInfoService
{

	private static final String ALL_PROFESSION_INFO = "FROM ProfessionInfo AS pi";

	@Autowired
	private ProfessionInfoDAO professionInfoDAO;

	@Override
	public List<ProfessionInfo> findAllProfessionInfo() 
	{
		return professionInfoDAO.findAll();
	}

	@Override
	public int deleteProfessionInfo(String professionInfoIdStr)
	{
		String[] ProfessionInfoIdStrs;

		if (professionInfoIdStr != null && !professionInfoIdStr.equals(""))
		{
			ProfessionInfoIdStrs = professionInfoIdStr.split("-");
			for (String professionInforId : ProfessionInfoIdStrs)
			{
				try 
				{
					ProfessionInfo professionInfo = professionInfoDAO
							.findById(Integer.valueOf(professionInforId));
					professionInfoDAO.turnTransient(professionInfo);// 删除
				}
				catch (Exception e)
				{
					throw new EntityException(
							"Error!  delete ProfessionInfo. ", e);
				}
			}

			return Constant.RESULT_SUCCESS;
		}

		return Constant.RESULT_FAIL;
	}

	@Override
	public int saveOrUpdateProfessionInfo(ProfessionInfo professionInfo)
	{
		professionInfoDAO.updateEntity(professionInfo);
		return Constant.RESULT_SUCCESS;
	}

	@Override
	public List<ProfessionInfo> getProfessionInfosPages(
			Page<ProfessionInfo> page, int[] pageParams) 
	{
		List<ProfessionInfo> results = new ArrayList<>();

		StringBuffer resultsHQL = new StringBuffer(ALL_PROFESSION_INFO);// stringBuffer在进行字符串处理时，不生成新的对象，在内存使用上要优于String类。

		try 
		{
			results = professionInfoDAO.findByPage(resultsHQL.toString(),
					pageParams[0], pageParams[1]);

			page.setTotalCount(professionInfoDAO.getCount(resultsHQL.toString()));
			page.setResult(results);

		} 
		catch (Exception e)
		{
			throw new EntityException(
					"Error! ProfessionInfoServiceImpl.getProfessionInfosPages(...) ",e);
		}

		return results;
	}

	@Override
	public ProfessionInfo getProfessionInfoByID(String professionInfoID) 
	{
		return professionInfoDAO.findById(professionInfoID);
	}

}
