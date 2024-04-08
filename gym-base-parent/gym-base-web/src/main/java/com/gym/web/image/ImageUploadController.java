package com.gym.web.image;

import com.gym.utils.ResultUtils;
import com.gym.utils.ResultVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author yangbohan
 * @Date 2024/4/8 17:09
 */
@RestController
@RequestMapping("/api/upload/")
public class ImageUploadController {
    // 图片上传的路径
    @Value("${web.uploadpath}")
    private String webUploadPath;

    @RequestMapping("/uploadImage")
    @ResponseBody
    public ResultVo uploadImage(@RequestParam("file") MultipartFile file) {
        String url = null;
        String fileName = file.getOriginalFilename();
        // 扩展名
        String fillExtenionName = fileName.substring(fileName.indexOf("."));
        // 生成新的名称
        String newName = UUID.randomUUID().toString() + fillExtenionName;
        String filePath = webUploadPath + newName;
        File fileDir = new File(filePath);
        if (!fileDir.exists()){
            // 设置权限
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(filePath, newName);
        try {
            file.transferTo(targetFile);
            url = "/"+targetFile.getName();
        } catch (Exception e) {
            return null;
        }
        return ResultUtils.success("成功","/image"+url);
    }
}
