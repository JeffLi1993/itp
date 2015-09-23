package sedion.jeffli.wmuitp.entity;

// Generated 2014-4-17 18:23:10 by Hibernate Tools 3.4.0.CR1

/**
 * Contact generated by hbm2java
 */
public class Contact implements java.io.Serializable {

	private Integer contactId;
	private Addressbook addressbook;
	private String contactName;
	private String contactPhone;
	private String contactQq;
	private String contactEmail;
	private String contactUnit;
	private String contactDepartment;
	private String contactAddress;
	private String contactRemark;

	public Contact() {
	}

	public Contact(Addressbook addressbook, String contactName,
			String contactPhone, String contactQq, String contactEmail,
			String contactUnit, String contactDepartment,
			String contactAddress, String contactRemark) {
		this.addressbook = addressbook;
		this.contactName = contactName;
		this.contactPhone = contactPhone;
		this.contactQq = contactQq;
		this.contactEmail = contactEmail;
		this.contactUnit = contactUnit;
		this.contactDepartment = contactDepartment;
		this.contactAddress = contactAddress;
		this.contactRemark = contactRemark;
	}

	public Integer getContactId() {
		return this.contactId;
	}

	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}

	public Addressbook getAddressbook() {
		return this.addressbook;
	}

	public void setAddressbook(Addressbook addressbook) {
		this.addressbook = addressbook;
	}

	public String getContactName() {
		return this.contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPhone() {
		return this.contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContactQq() {
		return this.contactQq;
	}

	public void setContactQq(String contactQq) {
		this.contactQq = contactQq;
	}

	public String getContactEmail() {
		return this.contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactUnit() {
		return this.contactUnit;
	}

	public void setContactUnit(String contactUnit) {
		this.contactUnit = contactUnit;
	}

	public String getContactDepartment() {
		return this.contactDepartment;
	}

	public void setContactDepartment(String contactDepartment) {
		this.contactDepartment = contactDepartment;
	}

	public String getContactAddress() {
		return this.contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getContactRemark() {
		return this.contactRemark;
	}

	public void setContactRemark(String contactRemark) {
		this.contactRemark = contactRemark;
	}

}