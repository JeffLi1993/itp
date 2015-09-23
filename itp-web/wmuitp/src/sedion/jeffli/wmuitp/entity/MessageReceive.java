package sedion.jeffli.wmuitp.entity;

/**
 * MessageReceive entity. @author MyEclipse Persistence Tools
 */

public class MessageReceive implements java.io.Serializable {

	// Fields

	private Integer mrId;
	private UserLogin userLogin;
	private MessageSender messageSender;
	private String mrRead;
	private String mrDelete;

	// Constructors

	/** default constructor */
	public MessageReceive() {
	}

	/** full constructor */
	public MessageReceive(UserLogin userLogin, MessageSender messageSender,
			String mrRead, String mrDelete) {
		this.userLogin = userLogin;
		this.messageSender = messageSender;
		this.mrRead = mrRead;
		this.mrDelete = mrDelete;
	}

	// Property accessors

	public Integer getMrId() {
		return this.mrId;
	}

	public void setMrId(Integer mrId) {
		this.mrId = mrId;
	}

	public UserLogin getUserLogin() {
		return this.userLogin;
	}

	public void setUserLogin(UserLogin userLogin) {
		this.userLogin = userLogin;
	}

	public MessageSender getMessageSender() {
		return this.messageSender;
	}

	public void setMessageSender(MessageSender messageSender) {
		this.messageSender = messageSender;
	}

	public String getMrRead() {
		return this.mrRead;
	}

	public void setMrRead(String mrRead) {
		this.mrRead = mrRead;
	}

	public String getMrDelete() {
		return this.mrDelete;
	}

	public void setMrDelete(String mrDelete) {
		this.mrDelete = mrDelete;
	}

}