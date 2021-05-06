package com.lv.spring;

import com.google.common.net.MediaType;
import org.junit.runner.Request;
import org.springframework.web.bind.annotation.RequestBody;

import javax.xml.ws.Response;

public class request {
    public static void main(String[] args) {


    }


//    public void get() {
//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        MediaType mediaType = MediaType.parse("application/json");
//        RequestBody body = RequestBody.create(mediaType, "{\n    \"username\": \"08192799\",\n    \"xqm\": \"2\",\n    \"xnm\": \"2020\"\n}");
//        Request request = new Request.Builder()
//                .url("https://cumt.zyuanlee.cn/api/study/course/")
//                .method("POST", body)
//                .addHeader("Content-Type", "application/json")
//                .build();
//        Response response = client.newCall(request).execute();
//    }
}
