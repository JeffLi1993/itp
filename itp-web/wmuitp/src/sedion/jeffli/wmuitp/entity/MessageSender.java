package sedion.jeffli.wmuitp.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * MessageSender entity. @author MyEclipse Persistence Tools
 */

public class MessageSender implements java.io.Serializable {

	// Fields

	private Integer msId;
	private UserLogin userLogin;
	private String msTopic;
	private String msContent;
	private String msSendTime;
	private String msDelete;
	private Set messageReceives = new HashSet(0);

	// Constructors

	/** default constructor */
	public MessageSender() {
	}

	/** full constructor */
	public MessageSender(UserLogin userLogin, String msTopic, String msContent,
			String msSendTime, String msDelete, Set messageReceives) {
		this.userLogin = userLogin;
		this.msTopic = msTopic;
		this.msContent = msContent;
		this.msSendTime = msSendTime;
		this.msDelete = msDelete;
		this.messageReceives = messageReceives;
	}

	// Property accessors

	public Integer getMsId() {
		return this.msId;
	}

	public void setMsId(Integer msId) {
		this.msId = msId;
	}

	public UserLogin getUserLogin() {
		return this.userLogin;
	}

	public void setUserLogin(UserLogin userLogin) {
		this.userLogin = userLogin;
	}

	public String getMsTopic() {
		return this.msTopic;
	}

	public void setMsTopic(String msTopic) {
		this.msTopic = msTopic;
	}

	public String getMsContent() {
		return this.msContent;
	}

	public void setMsContent(String msContent) {
		this.msContent = msContent;
	}

	public String getMsSendTime() {
		return this.msSendTime;
	}

	public void setMsSendTime(String msSendTime) {
		this.msSendTime = msSendTime;
	}

	public String getMsDelete() {
		return this.msDelete;
	}

	public void setMsDelete(String msDelete) {
		this.msDelete = msDelete;
	}

	public Set getMessageReceives() {
		return this.messageReceives;
	}

	public void setMessageReceives(Set messageReceives) {
		this.messageReceives = messageReceives;
	}

}