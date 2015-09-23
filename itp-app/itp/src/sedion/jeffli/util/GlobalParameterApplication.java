package sedion.jeffli.util;

import android.app.Application;

public class GlobalParameterApplication extends Application
{
	//用户唯一标识
	private int ulId;
	private String userName;
	private String userPass;
	
	/**
	 * @return the ulId
	 */
	public int getUlId()
	{
		return ulId;
	}

	/**
	 * @param ulId the ulId to set
	 */
	public void setUlId(int ulId)
	{
		this.ulId = ulId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	
	
}
