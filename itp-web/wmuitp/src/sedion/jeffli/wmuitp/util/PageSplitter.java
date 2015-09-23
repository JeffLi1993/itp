package sedion.jeffli.wmuitp.util;


/** 分页类：根据总记录数和分页大小处理分页 */
public class PageSplitter
{
	public static final int DEFAULT_PAGE_SIZE = 10;
	
	private int rowCount;//总条数
	private int currentPage;//当前页
	private int pageSize;//每页显示多少条
	private int pageCount;//页标数字多少个 

		
	public PageSplitter(int rowCount)
	{
		this(rowCount, DEFAULT_PAGE_SIZE);
	}
	
	public PageSplitter(int rowCount, int pageSize)
	{
		this.currentPage	= 1;
		this.rowCount		= rowCount;
		
		setPageSize(pageSize);
	}
	
	private void adjustPageCount()
	{
		pageCount = (rowCount + pageSize - 1) / pageSize;
	}
	
	/** 获取当前页的记录范围（从 1 开始） */
	public Range<Integer> getCurrentRange()
	{
		Range<Integer> range = null;
		
		if(currentPage >= 1 && currentPage <= pageCount)
		{
			int begin	= (currentPage - 1) * pageSize;
			int end		= (currentPage < pageCount) ? (begin + pageSize) : rowCount;
			
			range = new Range<Integer>(begin + 1, end);
		}

		return range;
	}

	/** 获取当前页号（从 1 开始） */
	public int getCurrentPage()
	{
		return currentPage;
	}

	/** 设置当前页号（从 1 开始） */
	public boolean setCurrentPage(int page)
	{
		if(page >= 1 && page <= pageCount)
		{
			currentPage = page;
			return true;
		}
		
		return false;
	}
	
	/** 转到下一页 */
	public boolean nextPage()
	{
		return setCurrentPage(currentPage + 1);
	}

	/** 转到上一页 */
	public boolean prePage()
	{
		return setCurrentPage(currentPage - 1);
	}
	
	/** 获取分页大小 */
	public int getPageSize()
	{
		return pageSize;
	}

	/** 设置分页大小 */
	public void setPageSize(int size)
	{
		if(size <= 0)
			size = DEFAULT_PAGE_SIZE;
		
		int index = (currentPage - 1) * pageSize;
		
		pageSize = size;
				
		if(index > pageSize)
			currentPage = (index + pageSize - 1) / pageSize;
		else
			currentPage = 1;
	
		adjustPageCount();
	}

	/** 获取总页数 */
	public int getPageCount()
	{
		return pageCount;
	}
	
	public static void main(String[] args)
	{
		final int PAGE_SIZE	= 10;
		final int ROW_COUNT	= 39;
		
		PageSplitter splitter = new PageSplitter(ROW_COUNT, PAGE_SIZE);
		
		for(int i = 1; i <= splitter.getPageCount(); i++)
		{
        		splitter.setCurrentPage(i);
        		Range<Integer> range = splitter.getCurrentRange();
        		System.out.println(String.format("Page %d: index from %d to %d", splitter.getCurrentPage(), range.getBegin(), range.getEnd()));
		}
		
		System.out.println("---------------------------------");
		
		splitter = new PageSplitter(ROW_COUNT, PAGE_SIZE);
		
		if(splitter.getPageCount() > 0)
		{
        		do
        		{
        			Range<Integer> range = splitter.getCurrentRange();
                		System.out.println(String.format("Page %d: index from %d to %d", splitter.getCurrentPage(), range.getBegin(), range.getEnd()));
        		} while(splitter.nextPage());
		}
		
		System.out.println("---------------------------------");
		
		splitter = new PageSplitter(ROW_COUNT, PAGE_SIZE);
		splitter.setCurrentPage(splitter.getPageCount());
		
		if(splitter.getPageCount() > 0)
		{
        		do
        		{
        			Range<Integer> range = splitter.getCurrentRange();
                		System.out.println(String.format("Page %d: index from %d to %d", splitter.getCurrentPage(), range.getBegin(), range.getEnd()));
        		} while(splitter.prePage());
		}
	}
}
