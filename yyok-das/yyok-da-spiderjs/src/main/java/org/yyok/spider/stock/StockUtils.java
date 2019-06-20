package org.yyok.spider.stock;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
/**
 * 传递网页链接
 * 返回网页源码
 * @author lqh
 *
 */
public class StockUtils {
    //第一次获取网页源码
    public static String getHtmlByUrl(String url) throws IOException{
        String html = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();//创建httpClient对象
        HttpGet httpget = new HttpGet(url);
        try {
            HttpResponse responce = httpClient.execute(httpget);
            int resStatu = responce.getStatusLine().getStatusCode();
            if (resStatu == HttpStatus.SC_OK) {

                HttpEntity entity = responce.getEntity();
                if (entity != null) {
                    html = EntityUtils.toString(entity);//获得html源代码
                }
            }
        } catch (Exception e) {
            System.out.println("访问【"+url+"】出现异常!");
            e.printStackTrace();
        } finally {
            //释放连接
            httpClient.close();
        }
        return html;
    }
}