package dev.vivienhuang.mavenwebdemo.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class MemberPermissionPK implements Serializable{
	
	private String account;

	private String permission;
	
	public MemberPermissionPK() {
		super();
	}

	public MemberPermissionPK(String account, String permission) {
		super();
		this.account = account;
		this.permission = permission;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MemberPermissionPK other = (MemberPermissionPK) obj;
        if ((this.account == null) ?
            (other.account != null) : !this.account.equals(other.account)) {
            return false;
        }
        if ((this.permission == null) ?
            (other.permission != null) : !this.permission.equals(other.permission)) {
            return false;
        }
        return true;
	}
	
	@Override
	public int hashCode() {
		int hash = 5;
        hash = 73 * hash + (this.account != null ? this.account.hashCode() : 0);
        hash = 73 * hash + (this.permission != null ? this.permission.hashCode() : 0);
        return hash;	
    }
}
