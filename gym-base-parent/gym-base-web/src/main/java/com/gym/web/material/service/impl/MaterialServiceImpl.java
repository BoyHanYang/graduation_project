package com.gym.web.material.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gym.web.material.entity.Material;
import com.gym.web.material.service.MaterialService;
import com.gym.web.material.mapper.MaterialMapper;
import org.springframework.stereotype.Service;

/**
* @author AdminHan
* @description 针对表【material】的数据库操作Service实现
* @createDate 2024-04-08 21:03:15
*/
@Service
public class MaterialServiceImpl extends ServiceImpl<MaterialMapper, Material>
    implements MaterialService{

}




