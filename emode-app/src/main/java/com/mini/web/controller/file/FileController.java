package com.mini.web.controller.file;

import com.mini.biz.file.CommonFileBiz;
import com.mini.common.annotation.OptLog;
import com.mini.common.utils.minio.MinIoUtil;
import com.mini.common.utils.webmvc.Restful;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author zhl
 * @create 2024/5/16 15:18
 */
@Tag(name = "统一文件上传接口")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/sys-file")
public class FileController {

    private final CommonFileBiz commonFileBiz;

    private final MinIoUtil minIoUtil;

    @OptLog
    @Operation(summary = "小程序静态文件批量上传")
    @PostMapping(path = "/upload-mf", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Restful<Map<String, String>> uploadAppStatic(@RequestParam("files") MultipartFile[] files) {
        return Restful.OBJECT(minIoUtil.storageAppStaticFileWithReturnUrl(files)).build();
    }

    @OptLog
    @Operation(summary = "单业务文件上传")
    @PostMapping(path = "/upload-single-mf-business", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Restful<Long> uploadBusiness(@RequestParam("file") MultipartFile file) {
        return Restful.OBJECT(commonFileBiz.singleFileUpload(file)).build();
    }


}
