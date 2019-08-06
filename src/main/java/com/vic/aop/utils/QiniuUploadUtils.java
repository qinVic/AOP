package com.vic.aop.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 上传图片到七牛云 工具类
 *
 * @author qinquan
 * @date 2019/8/6
 */
@Configuration
public class QiniuUploadUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(QiniuUploadUtils.class);

    /**
     * 访问key
     */
    @Value("${qiniu.config.accessKey}")
    private String accessKey;
    /**
     * 密钥
     */
    @Value("${qiniu.config.secretKey}")
    private String secretKey;
    /**
     * 存储空间名称
     */
    @Value("${qiniu.config.bucket}")
    private String bucket;
    /**
     * 访问地址前缀
     */
    @Value("${qiniu.config.prefix}")
    private String prefix;

    private UploadManager uploadManager;

    public QiniuUploadUtils() {
        //构造一个带指定Zone对象的配置类
        com.qiniu.storage.Configuration cfg = new com.qiniu.storage.Configuration(Zone.zone2());
        //...其他参数参考类注释
        uploadManager = new UploadManager(cfg);
    }

    /**
     * 上传文件
     *
     * @param imageName 文件名
     * @param bytes 字节数组
     * @return 返回当前文件的路径
     */
    public String upload(String imageName, byte[] bytes) {
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket, imageName);
        try {
            Response response = uploadManager.put(bytes, imageName, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return prefix + putRet.key + "?t=" + System.currentTimeMillis();
        } catch (QiniuException ex) {
            LOGGER.error("【上传七牛云失败...】, error:{}", ex.getMessage());
        }
        return null;
    }

}
