package com.gym.web.sys_role.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author yangbohan
 * @Date 2024/4/12 20:03
 */

@Data
public class RoleAssignParm implements Serializable {
    //用户id
    private Integer userId;
    //角色id
    private Integer roleId;
}