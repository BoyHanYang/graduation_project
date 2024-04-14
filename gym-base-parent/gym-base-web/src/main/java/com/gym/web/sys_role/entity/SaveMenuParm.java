package com.gym.web.sys_role.entity;

import lombok.Data;

import java.util.List;

/**
 * @Author yangbohan
 * @Date 2024/4/14 19:58
 */
@Data
public class SaveMenuParm {
    private Integer roleId;
    private List<Integer> list;
}
