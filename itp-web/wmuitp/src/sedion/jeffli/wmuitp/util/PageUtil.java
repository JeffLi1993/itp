package sedion.jeffli.wmuitp.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;


import sedion.jeffli.wmuitp.entity.SubjectInfor;

/**
 * 分页工具
 * 
 */
public class PageUtil {

	public static int PAGE_SIZE 			= 20;
	public static int MAX_SIZE 				= 9999;
	
	public static final String PAGE_NUM_STR = "pageNum";
	
	/**
	 * 初始化分页
	 * @param page		page对象
	 * @param request	请求体
	 * @return
	 */
	public static int[] init(Page<?> page, HttpServletRequest request) {
		
		int pageNum = Integer.parseInt(StringUtils.defaultIfBlank(request.getParameter(PAGE_NUM_STR), "1"));
		
		page.setPageNo(Integer.valueOf(pageNum));
		page.setPageSize(page.getPageSize());
		
		int firstResult = page.getFirst() - 1;
		int maxResults = page.getPageSize();
		
		return new int[] { firstResult, maxResults };
	}
	
	public static int[] init(Page<?> page, String pageNum) {
		
		page.setPageNo(Integer.valueOf(pageNum));
		page.setPageSize(page.getPageSize());
		
		int firstResult = page.getFirst() - 1;
		int maxResults = page.getPageSize();
		
		return new int[] { firstResult, maxResults };
	}
	
	public static int[] init(Page<SubjectInfor> page, String numPerPage,String pageNum)
	{
		if(pageNum != null)
			page.setPageNo(Integer.valueOf(pageNum));
		page.setPageSize(page.getPageSize());
		
		int firstResult = page.getFirst() - 1;
		int maxResults = page.getPageSize();
		
		return new int[] { firstResult, maxResults };
	}

}
