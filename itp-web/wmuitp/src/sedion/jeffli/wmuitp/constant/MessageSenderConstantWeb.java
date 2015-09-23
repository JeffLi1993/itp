package sedion.jeffli.wmuitp.constant;

public class MessageSenderConstantWeb {
	
	public static final String MESSAGE_SENDER_BASE				    = "/messageSender";
	public static final String WEB_BASE					    		= MESSAGE_SENDER_BASE + "/web";
	public static final String WEB_APP_BASE							= MESSAGE_SENDER_BASE + "/webApp";
	
	/**
	 * views about  MessageSender
	 */
	public static final String MESSAGE_SENDER_LIST_VIEW	 		= WEB_BASE + "/messageSender.list";
	public static final String MESSAGE_SENDER_ADD_VIEW 			= WEB_BASE + "/messageSender.add";
	public static final String MESSAGE_SENDER_DEATIL	 	    = WEB_BASE + "/messageSender.detail";
	public static final String MESSAGE_SENDER_LOOKUP 	    	= WEB_BASE + "/messageSender.lookup";
	
	public static String getMessageSenderListView()
	{
		return MESSAGE_SENDER_LIST_VIEW;
	}
	public static String getMessageSenderAddView()
	{
		return MESSAGE_SENDER_ADD_VIEW;
	}
	public static String getMessageSenderDetail()
	{
		return MESSAGE_SENDER_DEATIL;
	}
	public static String getMessageSenderLookup()
	{
		return MESSAGE_SENDER_LOOKUP;
	}
}
