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

	@TableField(value = "first_name")
	private String firstName;

	@TableField(value = "last_name")
	private String lastName;

	private String email;

	private Boolean enabled;

	@TableField(value = "last_password_reset_date")
	private Date lastPasswordResetDate;

	@TableField(value = "img_url")
	private String imgUrl;

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


	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", firstname='" + firstName + '\'' +
				", lastname='" + lastName + '\'' +
				", email='" + email + '\'' +
				", enabled=" + enabled +
				", lastPasswordResetDate=" + lastPasswordResetDate +
				", imgUrl='" + imgUrl + '\'' +
				", roles=" + roles +
				'}';
	}
}