package com.cz.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * Created by jomalone_jia on 2017/7/6.
 */
@TableName("sys_user_role")
public class UserRole implements Serializable{

    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;
    @TableField(value = "user_id")
    private Long UserId;
    @TableField(value = "role_id")
    private Long RoleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public Long getRoleId() {
        return RoleId;
    }

    public void setRoleId(Long roleId) {
        RoleId = roleId;
    }

    public UserRole() {
    }

    public UserRole(Long userId, Long roleId) {
        UserId = userId;
        RoleId = roleId;
    }
}
