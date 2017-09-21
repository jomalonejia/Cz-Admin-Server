package com.cz.core.util.qiniu;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jomalone_jia on 2017/9/21.
 */
public class PictureBase64Util {

    private PictureBase64Util(){}

    private static volatile PictureBase64Util pictureBase64Util;

    public static PictureBase64Util getInstance(){
        if(pictureBase64Util ==null){
            synchronized (PictureUtil.class){
                if(pictureBase64Util == null){
                    pictureBase64Util = new PictureBase64Util();
                }
            }
        }
        return pictureBase64Util;
    }

    public static  String Generate(String value) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(value.getBytes(StandardCharsets.UTF_8));
        return DatatypeConverter.printHexBinary(hash);
    }

    public  List<String> getBase64Image(String content){
        List<String> allMatches = new ArrayList<String>();
        Pattern pattern = Pattern.compile( "<img src=\"(.*?)\">" );
        Matcher m = pattern.matcher( content);
        while(m.find()){
            allMatches.add(m.group(1));
        }
        return allMatches;
    }
}
