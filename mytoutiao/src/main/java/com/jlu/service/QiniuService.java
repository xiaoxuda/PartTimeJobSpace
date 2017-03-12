package com.jlu.service;

import com.alibaba.fastjson.JSONObject;
import com.jlu.utils.ToutiaoUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

/**
 * Created by Administrator on 2016/7/19.
 */
@Service
public class QiniuService {
    private static final Logger logger = LoggerFactory.getLogger(QiniuService.class);
    //设置好账号的ACCESS_KEY和SECRET_KEY
    String ACCESS_KEY = "bY-xyYOZtEnA3rHGciL625lZQo6vAeThRWCpDOEk";
    String SECRET_KEY = "ZKEzmroehWWSrlcXQ9hP6FrQM6X_ItCd52zCF6r-";
    //要上传的空间
    String bucketname = "toutiao";
    //上传到七牛后保存的文件名
    String key = "my-java.png";
    //上传文件的路径
    String FilePath = "/.../...";

    //密钥配置
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    //创建上传对象
    UploadManager uploadManager = new UploadManager();

    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    public String getUpToken(){
        return auth.uploadToken(bucketname);
    }

    public String saveImage(MultipartFile file) throws IOException{
        try {
            int doPos=file.getOriginalFilename().lastIndexOf(".");
            if(doPos < 0){
                return null;
            }
            String fileExt=file.getOriginalFilename().substring(doPos+1).toLowerCase();
            if(!ToutiaoUtil.isFileAllowed(fileExt)){//先判断不符合，退出
                return null;
            }

            String fileName = UUID.randomUUID().toString().replaceAll("-","")+"."+fileExt;
            //调用put方法上传
            Response res = uploadManager.put(file.getBytes(),fileName,getUpToken());
            //打印返回的信息
            if(res.isOK() && res.isJson()){
                return ToutiaoUtil.QINIU_DOMAIN_PEFIX + JSONObject.parseObject(res.bodyString()).get("key");
            }else{
                logger.error("七牛异常："+res.bodyString());
                return null;
            }
        } catch (QiniuException e) {
            // 请求失败时打印的异常的信息
            logger.error("七牛异常："+e.getMessage());
            return null;
        }
    }

}
