package com.framework.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.net.URLEncoder;

/**
 * http://cms.dxw.info
 * 短信网的发送工具类
 * @author ChenDang
 * @date 2020/2/3 0003
 */
public class SmsSendUtil {

    //普通短信
    public static String sendsms(String mobile,String content) throws Exception {
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod("http://api.1cloudsp.com/api/v2/single_send");
        postMethod.getParams().setContentCharset("UTF-8");
        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());

        String accesskey = "qp5BQ70jXnGLge3v"; //用户开发key
        String accessSecret = "yigQ8wdatRqpXZWZOV3xG6s0UZ8URFbJ";//开发密钥

        NameValuePair[] data = {
                new NameValuePair("accesskey", accesskey),
                new NameValuePair("secret", accessSecret),
                new NameValuePair("sign", "【活鲜鲜】"),
                new NameValuePair("templateId", "176274"),
                new NameValuePair("mobile", mobile),
                new NameValuePair("content", URLEncoder.encode(content, "utf-8"))//（示例模板：{1}您好，您的订单于{2}已通过{3}发货，运单号{4}）
        };
        postMethod.setRequestBody(data);
        postMethod.setRequestHeader("Connection", "close");

        int statusCode = httpClient.executeMethod(postMethod);
        if(statusCode == 200){
            JSONObject jsonObject = JSONObject.parseObject(postMethod.getResponseBodyAsString());
            if(jsonObject == null){
                return "1";
            }
            String code = jsonObject.getString("code");
            if(!"0".equals(code)){
                return "1";
            }
            return code;
        }else{
            return "1";
        }
    }

    public static void main(String[] args) throws Exception {
        SmsSendUtil t = new SmsSendUtil();
        //普通短信
        //t.sendsms("18250752939","9090");
    }
}
