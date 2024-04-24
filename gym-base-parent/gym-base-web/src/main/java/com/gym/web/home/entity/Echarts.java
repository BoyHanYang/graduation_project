package com.gym.web.home.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author yangbohan
 * @Date 2024/4/24 0:55
 */
@Data
public class Echarts {
    private List<String> names = new ArrayList<>();
    private List<Integer> values = new ArrayList<>();

}
