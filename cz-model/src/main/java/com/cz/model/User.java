package com.cz.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * 系统用户表
 *
 */
@TableName("backend_user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId()
	private Long id;

	private String username;

	private String password;

	private String fullname;

	private Boolean enabled;

	@TableField(value = "last_password_reset_date")
	private Date lastPasswordResetDate;

	private String profile;

	@TableField(exist = false)
	private List<Role> roles = new ArrayList<Role>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Date getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}

	public void setLastPasswordResetDate(Date lastPasswordResetDate) {
		this.lastPasswordResetDate = lastPasswordResetDate;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile (String profile) {
		this.profile = profile;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", fullname='" + fullname + '\'' +
				", enabled=" + enabled +
				", lastPasswordResetDate=" + lastPasswordResetDate +
				", profile='" + profile + '\'' +
				", roles=" + roles +
				'}';
	}
}