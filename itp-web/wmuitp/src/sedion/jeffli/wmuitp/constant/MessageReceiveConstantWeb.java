package sedion.jeffli.wmuitp.constant;

public class MessageReceiveConstantWeb {

	public static final String MESSAGE_RECEIVE_BASE				    = "/messageReceive";
	public static final String WEB_BASE					    		= MESSAGE_RECEIVE_BASE + "/web";
	public static final String WEB_APP_BASE							= MESSAGE_RECEIVE_BASE + "/webApp";
	
	/**
	 * views about  messageReceive
	 */
	public static final String MESSAGE_RECEIVE_LIST_VIEW	 		= WEB_BASE + "/messageReceive.list";
	public static final String MESSAGE_RECEIVE_DEATIL	 		    = WEB_BASE + "/messageReceive.detail";
	
	public static String getMessageReceiveListView()
	{
		return MESSAGE_RECEIVE_LIST_VIEW;
	}
	public static String getMessageReceiveDetail()
	{
		return MESSAGE_RECEIVE_DEATIL;
	}

}
