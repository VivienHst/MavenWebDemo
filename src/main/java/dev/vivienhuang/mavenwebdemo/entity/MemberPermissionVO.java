package dev.vivienhuang.mavenwebdemo.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="MemberPermission")
public class MemberPermissionVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
    private MemberPermissionPK memberPermissionPK;
    
	public MemberPermissionVO() {
		super();
	}
	public MemberPermissionVO(String account, String permission) {
		super();
		this.memberPermissionPK = new MemberPermissionPK(account, permission);
	}

	public MemberPermissionVO(MemberPermissionPK memberPermissionPK) {
		super();
		this.memberPermissionPK = memberPermissionPK;
	}

	public MemberPermissionPK getMemberPermissionPK() {
		return memberPermissionPK;
	}

	public void setMemberPermissionPK(MemberPermissionPK memberPermissionPK) {
		this.memberPermissionPK = memberPermissionPK;
	}

	@Override
	public String toString() {
		return "MemberPermissionVO [memberPermissionPK=" + memberPermissionPK + "]";
	}
    
//	@Column(name="Account")
//	private String account;
//	
//	@Column(name="Permission")
//	private String permission;
//
//	public MemberPermissionVO() {
//		super();
//	}
//
//	public MemberPermissionVO(String account, String permission) {
//		super();
//		this.account = account;
//		this.permission = permission;
//	}
//
//	public String getAccount() {
//		return account;
//	}
//
//	public void setAccount(String account) {
//		this.account = account;
//	}
//
//	public String getPermission() {
//		return permission;
//	}
//
//	public void setPermission(String permission) {
//		this.permission = permission;
//	}
//
//	@Override
//	public String toString() {
//		return "MemberPermissionVO [account=" + account + ", permission=" + permission + "]";
//	}
	
}
