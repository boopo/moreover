package com.lv.spring.service.impl;

import com.lv.spring.service.OssService;
import com.tencent.cloud.CosStsClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.TreeMap;

@Service
public class OssServiceImpl implements OssService {

    @Value("${tencentOss.SecretId}")
    private String SecretId;

    @Value("${tencentOss.SecretKey}")
    private String SecretKey;

    @Value(("${tencentOss.bucket}"))
    private String bucket;

    @Value(("${tencentOss.region}"))
    private String region;

    @Override
    public String getToken() {
        TreeMap<String, Object> config = new TreeMap<String, Object>();
        try {

            // 云 api 密钥 SecretId
            config.put("secretId", SecretId);
            // 云 api 密钥 SecretKey
            config.put("secretKey", SecretKey);
            System.out.println(SecretId);
            System.out.println(SecretKey);

            // 设置域名
            //config.put("host", "sts.internal.tencentcloudapi.com");

            // 临时密钥有效时长，单位是秒
            config.put("durationSeconds", 3600);

            // 换成你的 bucket
            config.put("bucket", bucket);
            // 换成 bucket 所在地区
            config.put("region", region);

            // 这里改成允许的路径前缀，可以根据自己网站的用户登录态判断允许上传的具体路径，例子： a.jpg 或者 a/* 或者 * (使用通配符*存在重大安全风险, 请谨慎评估使用)
            config.put("allowPrefix", "*");
            // 可以通过 allowPrefixes 指定前缀数组
//            config.put("allowPrefixes", new String[] {
//                    "exampleobject",
//                    "exampleobject2"
//            });

            // 密钥的权限列表。简单上传和分片需要以下的权限，其他权限列表请看 https://cloud.tencent.com/document/product/436/31923
            String[] allowActions = new String[] {
                    // 简单上传
                    "name/cos:PutObject",
                    "name/cos:PostObject",
                    // 分片上传
                    "name/cos:InitiateMultipartUpload",
                    "name/cos:ListMultipartUploads",
                    "name/cos:ListParts",
                    "name/cos:UploadPart",
                    "name/cos:CompleteMultipartUpload"
            };
            config.put("allowActions", allowActions);

            JSONObject credential = CosStsClient.getCredential(config);
            return  credential.toString(4);
        } catch (Exception e) {
            throw new IllegalArgumentException("no valid secret !");
        }

    }
}
