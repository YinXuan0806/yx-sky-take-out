package com.sky.controller.admin;

import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import com.sky.utils.AliOSSUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/admin/common")
@Api(tags = "通用接口")
@Slf4j
public class CommonController {

    @Autowired
    private AliOSSUtil aliOSSUtils;
    /**
     * 文件上传
     */
    @RequestMapping("/upload")
    @ApiOperation("文件上传")
    public Result<String> download(MultipartFile file) {
        log.info("文件长传，文件名：{}",file.getOriginalFilename());

        //调用阿里云oss工具类进行文件上传
        String url = null;
        try {
            url = aliOSSUtils.upload(file);
            log.info("文件上传完成，文件上传的url：{}",url);
            return Result.success(url);
        } catch (IOException e) {
            log.error("文件上传失败：{}",e);
        }
        return Result.error(MessageConstant.UPLOAD_FAILED);
    }
}
