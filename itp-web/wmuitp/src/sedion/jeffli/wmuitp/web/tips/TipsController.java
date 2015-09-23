package sedion.jeffli.wmuitp.web.tips;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import sedion.jeffli.wmuitp.base.MainBase;
import sedion.jeffli.wmuitp.constant.CourseInfoConstantWeb;
import sedion.jeffli.wmuitp.entity.courseInfo.Tips;

@RequestMapping(value = "/tips")
@Controller
public class TipsController extends MainBase 
{
	@RequestMapping(value = "/tip")
	public ModelAndView tips(String kindName,String desription,String courseInfoId)
	{	
		Tips tips = new Tips();
		tips.setKindName(kindName);
		tips.setDescreption(desription);
		
		ModelAndView mav = new ModelAndView(CourseInfoConstantWeb.getTips());
		mav.addObject("tip", tips);
		mav.addObject("courseInfoId", courseInfoId);
		return mav;
	}
	
	@RequestMapping(value = "/js")
	public ModelAndView bb(HttpServletResponse response) throws IOException
	{	
		
		System.out.println("...");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//out.println("<script type='text/javascript'>Boxy.confirm('确认还是不确认？', function() {});</script>");
		out.println("<script type='text/javascript'>alert(Boxy);</script>");
		return null;
	}
}
