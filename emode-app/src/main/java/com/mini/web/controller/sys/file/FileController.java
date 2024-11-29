package com.mini.web.controller.sys.file;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.biz.file.CommonFileBiz;
import com.mini.common.annotation.OptLog;
import com.mini.common.utils.minio.MinIoUtil;
import com.mini.common.utils.webmvc.Restful;
import com.mini.file.model.query.SysFileQuery;
import com.mini.file.model.vo.SysFileVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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
    @Operation(summary = "小程序静态文件批量上传-不计入库")
    @PostMapping(path = "/upload-mf", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Restful<Map<String, String>> uploadAppStatic(@RequestParam("files") MultipartFile[] files) {
        return Restful.OBJECT(minIoUtil.storageAppStaticFileWithReturnUrl(files)).build();
    }

    @OptLog
    @Operation(summary = "单业务文件上传-同名覆盖")
    @PostMapping(path = "/upload-single-mf-business", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Restful<Long> uploadBusiness(@RequestParam("file") MultipartFile file) {
        return Restful.OBJECT(commonFileBiz.singleFileUpload(file)).build();
    }

    @OptLog
    @Operation(summary = "业务文件列表")
    @GetMapping(path = "/page")
    public Restful<IPage<SysFileVo>> page(@ParameterObject SysFileQuery query) {
        return Restful.OBJECT(commonFileBiz.page(query)).build();
    }

    @OptLog
    @Operation(summary = "单文件删除")
    @GetMapping(path = "/del/{id}")
    public Restful<Void> del(@PathVariable("id") Long id) {
        commonFileBiz.del(id);
        return Restful.SUCCESS().build();
    }


}
