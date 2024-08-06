package com.mini.common.utils.minio;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.mini.common.enums.DevFileBucketAuthEnum;
import com.mini.common.exception.service.EModeServiceException;
import io.minio.*;
import io.minio.http.Method;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.FileTypeMap;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhl
 */
@Slf4j
@Component
public class MinIoUtil {

    private MinioClient client;

    @Value("${minio.accessKey}")
    private String minioAccessKey;

    @Value("${minio.secretKey}")
    private String minioSecretKey;

    @Value("${minio.endPoint}")
    private String minioEndPoint;

    @Value("${minio.defaultBucketName}")
    private String minioDefaultBucketName;

    @Value("${minio.appStaticBucketName}")
    private String appStaticBucketName;


    /**
     * 初始化操作的客户端
     */
    private void initClient() {
        /* accessKey */
        if (ObjectUtil.isEmpty(minioAccessKey)) {
            throw new EModeServiceException("MINIO文件操作客户端未正确配置：accessKey为空");
        }

        /* secretKey */
        if (ObjectUtil.isEmpty(minioSecretKey)) {
            throw new EModeServiceException("MINIO文件操作客户端未正确配置：secretKey为空");
        }

        /* endpoint */
        if (ObjectUtil.isEmpty(minioEndPoint)) {
            throw new EModeServiceException("MINIO文件操作客户端未正确配置：endpoint为空");
        }

        /* 默认BucketName */
        if (ObjectUtil.isEmpty(minioDefaultBucketName)) {
            throw new EModeServiceException("MINIO文件操作客户端未正确配置：defaultBucketName为空");
        }
        client = MinioClient.builder().endpoint(minioEndPoint).credentials(minioAccessKey, minioSecretKey).build();
    }

    /**
     * 获取默认存储桶名称
     **/
    public String getDefaultBucketName() {
        initClient();
        return minioDefaultBucketName;
    }

    /**
     * 销毁操作的客户端
     */
    public void destroyClient() {
        // 无需
    }

    /**
     * 获取操作的客户端
     */
    public MinioClient getClient() {
        return client;
    }

    /**
     * 查询存储桶是否存在
     * 例如：传入参数examplebucket-1250000000，返回true代表存在此桶
     */
    public boolean doesBucketExist(String bucketName) {
        try {
            initClient();
            BucketExistsArgs bucketExistsArgs = BucketExistsArgs.builder().bucket(bucketName).build();
            client.bucketExists(bucketExistsArgs);
        } catch (Exception e) {
            throw new EModeServiceException(e.getMessage());
        }
        return false;
    }

    /**
     * 设置预定义策略
     * 预定义策略如公有读、公有读写、私有读
     *
     * @param bucketName            桶名称
     * @param devFileBucketAuthEnum 存储桶权限
     */
    public void setBucketAcl(String bucketName, DevFileBucketAuthEnum devFileBucketAuthEnum) {
        setFileAcl(bucketName, "*", devFileBucketAuthEnum);
    }

    /**
     * 判断是否存在文件
     *
     * @param bucketName 桶名称
     * @param key        唯一标示id，例如a.txt, doc/a.txt
     */
    public boolean isExistingFile(String bucketName, String key) {
        try {
            initClient();
            GetObjectArgs getObjectArgs = GetObjectArgs.builder().bucket(bucketName).object(key).build();
            InputStream object = client.getObject(getObjectArgs);
            return !ObjectUtil.isEmpty(object);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 存储文件，不返回地址
     *
     * @param bucketName 桶名称
     * @param key        唯一标示id，例如a.txt, doc/a.txt
     * @param file       文件
     */
    public void storageFile(String bucketName, String key, File file) {
        BufferedInputStream inputStream;
        try {
            inputStream = FileUtil.getInputStream(file);
        } catch (IORuntimeException e) {
            throw new EModeServiceException("获取文件流异常，名称是：{}" + file.getName());
        }
        storageFile(bucketName, key, inputStream);
    }

    /**
     * 存储文件，不返回地址
     *
     * @param bucketName    桶名称
     * @param key           唯一标示id，例如a.txt, doc/a.txt
     * @param multipartFile 文件
     */
    public void storageFile(String bucketName, String key, MultipartFile multipartFile) {
        InputStream inputStream;
        try {
            inputStream = multipartFile.getInputStream();
        } catch (IOException e) {
            throw new EModeServiceException("获取文件流异常，名称是：{}" + multipartFile.getName());
        }
        try {
            initClient();
            PutObjectArgs putObjectArgs = PutObjectArgs.builder().bucket(bucketName).object(key)
                    .contentType(multipartFile.getContentType()).stream(inputStream, inputStream.available(), -1).build();
            client.putObject(putObjectArgs);
        } catch (Exception e) {
            throw new EModeServiceException(e.getMessage());
        } finally {
            IoUtil.close(inputStream);
        }
    }

    /**
     * 存储文件，不返回地址
     *
     * @param bucketName 桶名称
     * @param key        唯一标示id，例如a.txt, doc/a.txt
     * @param bytes      文件字节数组
     */
    public void storageFile(String bucketName, String key, byte[] bytes) {
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            initClient();
            byteArrayInputStream = new ByteArrayInputStream(bytes);
            PutObjectArgs putObjectArgs = PutObjectArgs.builder().bucket(bucketName).object(key)
                    .contentType(getFileContentType(key)).stream(byteArrayInputStream, bytes.length, -1).build();
            client.putObject(putObjectArgs);
        } catch (Exception e) {
            throw new EModeServiceException(e.getMessage());
        } finally {
            IoUtil.close(byteArrayInputStream);
        }
    }

    /**
     * 存储文件，不返回地址
     *
     * @param bucketName  桶名称
     * @param key         唯一标示id，例如a.txt, doc/a.txt
     * @param inputStream 文件流
     */
    public void storageFile(String bucketName, String key, InputStream inputStream) {
        try {
            initClient();
            PutObjectArgs putObjectArgs = PutObjectArgs.builder().bucket(bucketName).object(key)
                    .contentType(getFileContentType(key)).stream(inputStream, inputStream.available(), -1).build();
            client.putObject(putObjectArgs);
        } catch (Exception e) {
            throw new EModeServiceException(e.getMessage());
        } finally {
            IoUtil.close(inputStream);
        }
    }

    /**
     * 存储文件，返回外网地址
     *
     * @param bucketName 桶名称
     * @param key        唯一标示id，例如a.txt, doc/a.txt
     * @param file       文件
     */
    public String storageFileWithReturnUrl(String bucketName, String key, File file) {
        storageFile(bucketName, key, file);
        setFileAcl(bucketName, key, DevFileBucketAuthEnum.PUBLIC_READ);
        return getFileAuthUrl(bucketName, key);
    }

    /**
     * 存储文件，返回外网地址 appStatic
     *
     * @param key           唯一标示id，例如a.txt, doc/a.txt
     * @param multipartFile 文件
     */
    public String storageAppStaticFileWithReturnUrl(String key, MultipartFile multipartFile) {
        storageFile(appStaticBucketName, key, multipartFile);
        return getPreviewUrl(key, appStaticBucketName);
    }

    /**
     * 存储文件，返回外网地址 appStatic  批量上传
     */
    public Map<String, String> storageAppStaticFileWithReturnUrl(MultipartFile[] files) {
        Map<String, String> resultMap = new HashMap<>();
        Arrays.stream(files).forEach(e -> {
            String filename = e.getOriginalFilename();
            storageFile(appStaticBucketName, filename, e);
            resultMap.put(filename, getPreviewUrl(filename, appStaticBucketName));
        });
        return resultMap;
    }


    /**
     * 存储文件，返回外网地址 appStatic  单文件上传
     */
    public String storageAppStaticFileWithReturnUrl(MultipartFile file) {
        String filename = file.getOriginalFilename();
        storageFile(appStaticBucketName, filename, file);
        return filename;
    }

    /**
     * 存储文件，返回外网地址 bucket  单文件上传
     */
    public String storageDefaultFileWithReturnUrl(MultipartFile file) {
        String filename = file.getOriginalFilename();
        storageFile(minioDefaultBucketName, filename, file);
        return filename;
    }


    /**
     * 存储文件，返回外网地址 系统其他文件
     *
     * @param key           唯一标示id，例如a.txt, doc/a.txt
     * @param multipartFile 文件
     */
    public String storageSysFileWithReturnUrl(String key, MultipartFile multipartFile) {
        storageFile(minioDefaultBucketName, key, multipartFile);
        setFileAcl(minioDefaultBucketName, key, DevFileBucketAuthEnum.PUBLIC_READ);
        return getPreviewUrl(key, minioDefaultBucketName);
    }


    /**
     * 存储文件，返回外网地址
     *
     * @param bucketName 桶名称
     * @param key        唯一标示id，例如a.txt, doc/a.txt
     * @param bytes      文件字节数组
     */
    public String storageFileWithReturnUrl(String bucketName, String key, byte[] bytes) {
        storageFile(bucketName, key, bytes);
        setFileAcl(bucketName, key, DevFileBucketAuthEnum.PUBLIC_READ);
        return getFileAuthUrl(bucketName, key);
    }

    /**
     * 存储文件，返回外网地址
     *
     * @param bucketName  桶名称
     * @param key         唯一标示id，例如a.txt, doc/a.txt
     * @param inputStream 文件流
     */
    public String storageFileWithReturnUrl(String bucketName, String key, InputStream inputStream) {
        storageFile(bucketName, key, inputStream);
        setFileAcl(bucketName, key, DevFileBucketAuthEnum.PUBLIC_READ);
        return getFileAuthUrl(bucketName, key);
    }

    /**
     * 获取某个bucket下的文件字节
     *
     * @param bucketName 桶名称
     * @param key        唯一标示id，例如a.txt, doc/a.txt
     */
    public byte[] getFileBytes(String bucketName, String key) {
        try {
            initClient();
            GetObjectArgs getObjectArgs = GetObjectArgs.builder().bucket(bucketName).object(key).build();
            InputStream inputStream = client.getObject(getObjectArgs);
            return IoUtil.readBytes(inputStream);
        } catch (Exception e) {
            throw new EModeServiceException(e.getMessage());
        }
    }

    /**
     * 设置文件访问权限管理
     *
     * @param bucketName            桶名称
     * @param key                   唯一标示id，例如a.txt, doc/a.txt
     * @param devFileBucketAuthEnum 文件权限
     */
    public void setFileAcl(String bucketName, String key, DevFileBucketAuthEnum devFileBucketAuthEnum) {
        try {
            JSONObject configObject = JSONUtil.createObj().set("Version", "2012-10-17");
            JSONArray statementArray = JSONUtil.createArray();
            JSONArray actionArray = JSONUtil.createArray();
            if (devFileBucketAuthEnum.equals(DevFileBucketAuthEnum.PUBLIC_READ)) {
                actionArray.put("s3:GetObject");
            }
            if (devFileBucketAuthEnum.equals(DevFileBucketAuthEnum.PUBLIC_READ_WRITE)) {
                actionArray.put("s3:GetObject");
                actionArray.put("s3:PutObject");
            }
            JSONObject statementObject = JSONUtil.createObj();
            statementObject.set("Effect", "Allow").set("Principal", JSONUtil.createObj().set("AWS", JSONUtil.createArray().put("*")))
                    .set("Action", actionArray).set("Resource", JSONUtil.createArray().put("arn:aws:s3:::" + bucketName + "/*"));
            statementArray.put(statementObject);
            configObject.set("Statement", statementArray);
            String config = JSONUtil.toJsonStr(configObject);
            SetBucketPolicyArgs setBucketPolicyArgs = SetBucketPolicyArgs.builder().bucket(bucketName).config(config).build();
            client.setBucketPolicy(setBucketPolicyArgs);
        } catch (Exception e) {
            throw new EModeServiceException(e.getMessage());
        }
    }

    /**
     * 拷贝文件
     *
     * @param originBucketName 源文件桶
     * @param originFileKey    源文件名称
     * @param newBucketName    新文件桶
     * @param newFileKey       新文件名称
     */
    public void copyFile(String originBucketName, String originFileKey, String newBucketName, String newFileKey) {
        try {
            initClient();
            CopySource copySource = CopySource.builder().bucket(originBucketName).object(originFileKey).build();
            CopyObjectArgs copyObjectArgs = CopyObjectArgs.builder().source(copySource).bucket(newBucketName).object(newFileKey).build();
            client.copyObject(copyObjectArgs);
        } catch (Exception e) {
            throw new EModeServiceException(e.getMessage());
        }
    }

    /**
     * 获取文件的下载地址（带鉴权和有效时间的），生成外网地址
     *
     * @param bucketName    文件桶
     * @param key           唯一标示id，例如a.txt, doc/a.txt
     * @param timeoutMillis 时效
     */
    public String getFileAuthUrl(String bucketName, String key, Long timeoutMillis) {
        try {
            initClient();
            GetPresignedObjectUrlArgs getPresignedObjectUrlArgs = GetPresignedObjectUrlArgs.builder().bucket(bucketName)
                    .object(key).method(Method.GET).expiry(timeoutMillis.intValue()).build();
            return client.getPresignedObjectUrl(getPresignedObjectUrlArgs);
        } catch (Exception e) {
            throw new EModeServiceException(e.getMessage());
        }
    }

    /**
     * 获取文件的下载地址（永久的，文件必须为公有读），生成外网地址
     *
     * @param bucketName 文件桶
     * @param key        唯一标示id，例如a.txt, doc/a.txt
     */
    public String getFileAuthUrl(String bucketName, String key) {
        try {
            initClient();
            return minioEndPoint + StrPool.SLASH + bucketName + StrPool.SLASH + key;
        } catch (Exception e) {
            throw new EModeServiceException(e.getMessage());
        }
    }

    /**
     * 删除文件
     *
     * @param bucketName 文件桶
     * @param key        唯一标示id，例如a.txt, doc/a.txt
     */
    public void deleteFile(String bucketName, String key) {
        try {
            RemoveObjectArgs removeObjectArgs = RemoveObjectArgs.builder().bucket(bucketName).object(key).build();
            client.removeObject(removeObjectArgs);
        } catch (Exception e) {
            throw new EModeServiceException(e.getMessage());
        }
    }

    /**
     * 根据文件名获取ContentType
     **/
    private String getFileContentType(String key) {
        // 根据文件名获取contentType
        String contentType = "application/octet-stream";
        if (key.contains(".")) {
            contentType = FileTypeMap.getDefaultFileTypeMap().getContentType(key);
        }
        return contentType;
    }

    /**
     * 获取预览接口
     */
    public String getPreviewUrl(String fileName, String bucketName) {
        initClient();
        if (StringUtils.isNotBlank(fileName)) {
            bucketName = StringUtils.isNotBlank(bucketName) ? bucketName : minioDefaultBucketName;
            try {
                client.statObject(StatObjectArgs.builder().bucket(bucketName).object(fileName).build());
                return client.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().method(Method.GET).bucket(bucketName).object(fileName).build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
