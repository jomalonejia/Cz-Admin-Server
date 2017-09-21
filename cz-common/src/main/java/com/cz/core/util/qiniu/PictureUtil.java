package com.cz.core.util.qiniu;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * Created by jomalone_jia on 2017/7/25.
 */
public class PictureUtil {

    Configuration cfg = new Configuration(Zone.zone0());
    //...其他参数参考类注释
    UploadManager uploadManager = new UploadManager(cfg);
    //...生成上传凭证，然后准备上传
    String accessKey = "geOVmGh_6pimqcr-AVkh_pL_t6DYrQOUJ-Fzidl0";
    String secretKey = "Fe6gPfNFQCiyEG6f1gfgq6KgvV-GrAW209oTtAvl";
    String bucket = "jomalone-jia";
    //默认不指定key的情况下，以文件内容的hash值作为文件名
    String key = null;
    Auth auth = Auth.create(accessKey, secretKey);


    private PictureUtil(){}

    private static volatile PictureUtil pictureUtil;

    public static PictureUtil getInstance(){
        if(pictureUtil ==null){
            synchronized (PictureUtil.class){
                if(pictureUtil == null){
                    pictureUtil = new PictureUtil();
                }
            }
        }
        return pictureUtil;
    }

    public String uploadPicture(MultipartFile file) {
        DefaultPutRet putRet = null;
        try {
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(file.getInputStream(), key, upToken, null, null);
                //解析上传成功的结果
                putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            } catch (QiniuException ex) {
                Response r = ex.response;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }finally {
            return putRet.hash;
        }
    }

    public String uploadPicture(String uploadString) {
        DefaultPutRet putRet = null;
        try {
            byte[] uploadBytes = uploadString.getBytes("utf-8");
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(uploadBytes, key, upToken);
                //解析上传成功的结果
                putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            } catch (QiniuException ex) {
                Response r = ex.response;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }finally {
            return putRet.hash;
        }
    }

    public Object deletePicture(String picHash){
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            Response response = bucketManager.delete(bucket, picHash);
            return response;
        } catch (QiniuException ex) {
            return ex.response.toString();
        }
    }

}
