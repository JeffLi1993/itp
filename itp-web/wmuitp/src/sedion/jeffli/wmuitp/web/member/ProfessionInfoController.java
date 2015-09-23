package sedion.jeffli.wmuitp.web.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import sedion.jeffli.wmuitp.base.MainBase;
import sedion.jeffli.wmuitp.constant.EntityConstant;
import sedion.jeffli.wmuitp.constant.ProfessionInfoConstant;
import sedion.jeffli.wmuitp.constant.ProfessionInfoConstantWeb;
import sedion.jeffli.wmuitp.constant.main.Constant;
import sedion.jeffli.wmuitp.constant.main.ConstantWeb;
import sedion.jeffli.wmuitp.entity.ProfessionInfo;
import sedion.jeffli.wmuitp.exception.EntityException;
import sedion.jeffli.wmuitp.util.Page;
import sedion.jeffli.wmuitp.util.PageUtil;

@RequestMapping(value = "/professionInfo")
@Controller
public class ProfessionInfoController extends MainBase 
{

	@RequestMapping(value = "/professionInfos")
	public ModelAndView professionInfos()//显示所有专业
	{	
		ModelAndView mav = new ModelAndView(ProfessionInfoConstantWeb.getProfessionInfoListView());
		try 
		{
			
			Page<ProfessionInfo> page = new Page<>(PageUtil.PAGE_SIZE);
			int[] pageParams = PageUtil.init(page,request);//分页初始化
			professionInfoService.getProfessionInfosPages(page, pageParams);
			
			mav.addObject(ConstantWeb.getPage(), page);
			
		}
		catch (Exception e) 
		{
			throw new EntityException(" ProfessionInfoController professionInfos() Error!!", e);
		}
		
		return mav;
	}


	
	@RequestMapping(value = "/showProfessionInfoAdd")
	public ModelAndView showProfessionInfoAdd(String professionInfoId)//显示专业添加
	{	
		System.out.println("professionInfoId:"+professionInfoId);
		
		ModelAndView mav = new ModelAndView(ProfessionInfoConstantWeb.getProfessionInfoAddEditView());
		
		if(professionInfoId != null && !professionInfoId.equals(""))
		{
			ProfessionInfo professionInfo  =  professionInfoService.getProfessionInfoByID(professionInfoId);
			
			mav.addObject(ProfessionInfoConstant.getProfessionInfoObject(), professionInfo);
		}
		
		return mav;
	}
	
	
	@RequestMapping(value = "/professionInfoAdd")
	public @ResponseBody String professionInfoAdd(ProfessionInfo professionInfo) //专业添加
	{
		int sign = professionInfoService.saveOrUpdateProfessionInfo(professionInfo);
		System.out.println(sign);
		System.out.println(sign == Constant.RESULT_SUCCESS);
		if(sign == Constant.RESULT_SUCCESS)
			return successResponse(EntityConstant.getSaveEntitySuccess(),
							 ProfessionInfoConstant.getProfessionInfoUrl(), 
							 ProfessionInfoConstant.getProfessionInfoRef());
		
		return failResponse(EntityConstant.getSaveEntityFailure());
		
	}
	
	
	@RequestMapping(value = "/paperInfoDelete")
	public @ResponseBody String paperInfoDelete(String ojbIdStr)//专业删除
	{
		int sign = professionInfoService.deleteProfessionInfo(ojbIdStr);
		
		if(sign == Constant.RESULT_SUCCESS)
			return successResponse(EntityConstant.getDeleteEntitySuccess(),
							 ProfessionInfoConstant.getProfessionInfoUrl() ,
							 ProfessionInfoConstant.getProfessionInfoRef());
		
		return failResponse(EntityConstant.getDeleteEntityFailure());
	}
	
}
