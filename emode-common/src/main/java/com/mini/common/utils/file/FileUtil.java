package com.mini.common.utils.file;

import com.mini.common.constant.FileConstant;
import com.mini.common.exception.service.EModeServiceException;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author zhl
 * @create 2024/5/16 14:38
 */
public class FileUtil {

    private FileUtil() {
    }

    public static Map<String, String> getFileBaseSource(MultipartFile file) {
        if (Objects.isNull(file)) {
            throw new EModeServiceException("文件不可为空");
        }
        Map<String, String> map = new HashMap<>(16);

        String originalFilename = file.getOriginalFilename();
        map.put(FileConstant.ORIGINAL_FILE_NAME, originalFilename);
        assert originalFilename != null;
        map.put(FileConstant.FILE_NAME, originalFilename.substring(0, originalFilename.lastIndexOf(".")));
        map.put(FileConstant.FILE_SUFFIX, originalFilename.substring(originalFilename.lastIndexOf(".")));
        map.put(FileConstant.FILE_TYPE, file.getContentType());
        map.put(FileConstant.FILE_SIZE, String.valueOf(file.getSize() / 1024));
        return map;
    }


}
