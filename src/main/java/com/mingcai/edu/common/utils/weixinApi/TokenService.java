package com.mingcai.edu.common.utils.weixinApi;
import com.google.gson.Gson;
import com.mingcai.edu.common.utils.StringUtils;
import com.mingcai.edu.common.wx.wxApi;
import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.URL;

/***
 * 处理获取tokenUrl请求
 */
public class TokenService{
    private static Logger logger = LoggerFactory.getLogger(TokenService.class);
    public static Token token=new Token();

    /***
     *
     * @param httpurl 请求路径
     * @param methed //请求方式  GET/POST
     * @return
     */
    public static String SeendHttp(String httpurl,String methed,String param){

        Token token=null;
        if(StringUtils.isEmpty(methed)){
            methed="GET";
        }
        try{
            //修改appID，secret
            String tokenUrl=httpurl;
            //建立连接
            URL url = new URL(tokenUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            httpUrlConn.setSSLSocketFactory(ssf);
            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(methed);
            if(StringUtils.isNotEmpty(param)) {//设置发送body内容
                PrintWriter writer = new PrintWriter(httpUrlConn.getOutputStream());
                writer.write(param);
                writer.flush();
                writer.close();
            }
            // 取得输入流
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            //读取响应内容
            StringBuffer buffer = new StringBuffer();
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            httpUrlConn.disconnect();
    //输出返回结果
            logger.info(buffer.toString());

            return buffer.toString();
        }catch (Exception e){
            logger.warn(e.toString());
        }
        return "";
    }
    /**
     * @Title  创建微信发送请求post实体
     * url 带token  data 请求的json对象字符串
     * @return String
     */
    public static String post(String url,String data) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "Content-Type");
        httpPost.setEntity(new StringEntity(data, "utf-8"));
        CloseableHttpResponse response = httpclient.execute(httpPost);
        String resp;
        try {
            HttpEntity entity = response.getEntity();
            resp = EntityUtils.toString(entity, "utf-8");
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }

        return resp;
    }
    public void TokenValue(){
        String res = SeendHttp(wxApi.getMessage(wxApi.GETTOKEN,"corpid=ww4ef5a724c1534989&corpsecret=SMXYgBhY0feHXDM4CSgO78XxyP52thvI9wZ7wXCsusI"),"GET","");
        Gson gson = new Gson();
        token = gson.fromJson(res, Token.class);
    }
    public static void main(String[] args)   {
        new TokenService().TokenValue();
//        String gsonuser = TokenService.SeendHttp(wxApi.getMessage(wxApi.SendMsgToUser, ""), "POST","{\"touser\" : \"edgarJiang\",\"msgtype\" : \"text\",\"agentid\" : 1000006, \"text\" : { \"content\" : \"你的快递已到，请携带工卡前往邮件中心领取。\\n出发前可查看<a href=\\\"http://work.weixin.qq.com\\\">邮件中心视频实况</a>，聪明避开排队。\" },\"safe\":0 }");//推送消息
//        Gson gson = new Gson();
//        Token  token = gson.fromJson(gsonuser, Token.class);
    }
}
