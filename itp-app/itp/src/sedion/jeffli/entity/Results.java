package sedion.jeffli.entity;

public class Results implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	public 	static final String RESULTS_NAME   = "resultsName";
	private int results;
	private int ulId;
	
	public Results()
	{
	}
	
	public Results(int results, int ulId)
	{
		super();
		this.results = results;
		this.ulId = ulId;
	}
	
	public int getResults()
	{
		return results;
	}

	public void setResults(int results)
	{
		this.results = results;
	}

	public int getUlId()
	{
		return ulId;
	}

	public void setUlId(int ulId)
	{
		this.ulId = ulId;
	}

	
	
}
