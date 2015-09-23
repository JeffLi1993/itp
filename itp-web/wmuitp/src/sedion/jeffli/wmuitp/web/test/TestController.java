package sedion.jeffli.wmuitp.web.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import sedion.jeffli.wmuitp.base.MainBase;

@RequestMapping(value = "/test")
@Controller
public class TestController extends MainBase 
{
	@RequestMapping(value = "/aa")
	public ModelAndView aa()
	{	
		System.out.println("...");
		System.out.println("...");
		System.out.println("...");
		return new ModelAndView("redirect:/test/js");
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
