package dev.vivienhuang.mavenwebdemo.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="Member")
public class MemberVO {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Uid")
	private Integer uid;
	
	@Column(name="Account")
	@NotEmpty(message = "請填入帳號")
	private String account;
	
	@Column(name="Password")
	@NotEmpty(message = "請填入密碼")
	private String password;
	
	@Column(name="FirstName")
	@NotEmpty(message = "請填入名字")
	private String firstName;
	
	@Column(name="LastName")
	@NotEmpty(message = "請填入姓")
	private String lastName;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="PhoneCode")
	private String phoneCode;
	
	@Column(name="PhoneNumber")
	private String phoneNumber;
	
	@Column(name="State")
	private String state;
	
	@Column(name="Type")
	private String type;
	  
	@Column(name="CreateDate")
	private Timestamp createDate;
	
	@Column(name="UpdateDate")
	private Timestamp updateDate;

	public MemberVO() {
		super();
	}

	public MemberVO(String account, String password, String firstName, String lastName, String email, String phoneCode,
			String phoneNumber, String state, String type, Timestamp createDate) {
		super();
		this.account = account;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneCode = phoneCode;
		this.phoneNumber = phoneNumber;
		this.state = state;
		this.type = type;
		this.createDate = createDate;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneCode() {
		return phoneCode;
	}

	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "MemberVO [uid=" + uid + ", account=" + account + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", phoneCode=" + phoneCode + ", phoneNumber="
				+ phoneNumber + ", state=" + state + ", type=" + type + ", createDate=" + createDate + ", updateDate="
				+ updateDate + "]";
	}
	
	
	
}
