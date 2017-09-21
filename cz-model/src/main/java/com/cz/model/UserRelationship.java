package com.cz.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * Created by jomalone_jia on 2017/8/8.
 */
@TableName("backend_user_relationship")
public class UserRelationship implements Serializable{
    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;

    @TableField(value = "user1_id")
    private Long User1Id;
    @TableField(value = "user2_id")
    private Long User2Id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser1Id() {
        return User1Id;
    }

    public void setUser1Id(Long user1Id) {
        User1Id = user1Id;
    }

    public Long getUser2Id() {
        return User2Id;
    }

    public void setUser2Id(Long user2Id) {
        User2Id = user2Id;
    }
}
