package sedion.jeffli.wmuitp.web.dialog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import sedion.jeffli.wmuitp.constant.StudentInfoConstantWeb;

@RequestMapping(value = "/dialog")
@Controller
public class DialogController {

	@RequestMapping(value = "/dialogs")
	public ModelAndView dialogs(String msg,String urlD)
	{
		System.out.println("*****1*");
		ModelAndView mav = new ModelAndView(StudentInfoConstantWeb.getStudentInfoAddXlsError());
		mav.addObject("urlD", urlD);
		mav.addObject("msg", msg);
		return mav;
	}
}
