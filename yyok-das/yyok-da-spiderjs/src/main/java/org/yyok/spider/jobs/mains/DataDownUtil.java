package org.yyok.spider.jobs.mains;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DataDownUtil {

    /**
     * 读取网站源码信息工具类
     * @author lqh
     * @version v1.0
     * @param url 请求连接
     * @param encoding 编码集
     * @return buffer
     */
    public static String getHtmlResourceByUrl(String url,String encoding){
        // 存储源代码容器
        StringBuffer buffer = new StringBuffer();
        URL urlObj = null;
        URLConnection uc = null;
        InputStreamReader isr = null;
        BufferedReader reader = null;

        try {
            // 建立网络连接
            urlObj = new URL(url);
            // 打开网络连接
            uc = urlObj.openConnection();
            // 建立文件写入流
            isr = new InputStreamReader(uc.getInputStream(),encoding);
            // 建立缓存流写入流
            reader = new BufferedReader(isr);
            // 建立临时文件
            String temp = null;
            while((temp = reader.readLine()) !=null){
                buffer.append(temp+"\n"); // 追加内容（一边读一边写）
            }

        } catch (MalformedURLException e) {

            e.printStackTrace();
            System.out.println("没有联网,检查设置");
        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("打开网络连接失败，请稍后重试");
        }finally{
            if(isr!=null){
                try {
                    isr.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }


        return buffer.toString();
    }
    /***
     * 操作具体网站类
     * @author lqh
     * @version v1.0
     * @param url 请求连接
     * @param encoding 编码集
     * @return maps
     */
    public static List<HashMap<String,String>> getJobInfo(String url,String encoding){
        // 1.根据网站和页面的编码集获取网页源代码
        String html = getHtmlResourceByUrl(url, encoding);
        // 2.解析源代码
        Document document = Jsoup.parse(html);
        // 获取外层的div id="newlist_list_content_table"
        Element element= document.getElementById("newlist_list_content_table");
        // 获取工资结果集的列表 class="newlist"
        Elements elements= document.getElementsByClass("newlist");

        // 创建一个List集合
        List<HashMap<String,String>> maps = new ArrayList<HashMap<String,String>>();
        for (Element el :elements) {
            HashMap<String,String> map = new HashMap<String,String>();
            // 获取a连接
            String link = el.getElementsByTag("a").attr("href");
            System.out.println(link);
            // 获取公司名称
            String textTitle = el.getElementsByClass("gsmc").text();
            // 获取职位名称
            String jobName = el.getElementsByClass("zwmc").text();
            // 获取工资
            String money = el.getElementsByClass("zwyx").text();
            // 获取工资地点
            String address = el.getElementsByClass("gzdd").text();
            // 获取发布时间
            String fadate = el.getElementsByClass("gxsj").text();
            map.put("textTitle",textTitle);
            map.put("jobName",jobName);
            map.put("money",money);
            map.put("address",address);
            map.put("fadate",fadate);
            map.put("link",link);
            maps.add(map);
        }

        return maps;
    }
    /**
     * 解析a链接里面的内容
     * @author hackxhao
     * @version v1.0
     */
    public static List<HashMap<String,String>> getLinkInfo(String url,String encoding){
        // 1.根据网站和页面的编码集获取网页源代码
        String html = getHtmlResourceByUrl(url, encoding);
        // 2.解析源代码
        Document document = Jsoup.parse(html);
        // 获取工资结果集的列表 class="tab-inner-cont"
        Elements elements= document.select("div.tab-inner-cont > p");
        System.out.println(elements);
        List<HashMap<String,String>> maps = new ArrayList<HashMap<String,String>>();

        for (Element el :elements) {
            HashMap<String,String> map = new HashMap<String,String>();
            // 获取a连接

            // 获取描述
            String ms = el.getElementsByTag("p").text();

            map.put("ms",ms);

            maps.add(map);
        }

        return maps;
    }



    /**
     * java 入口
     * @param args
     */
    public static void main(String[] args) {
        String url = "http://www.51job.com/";
        String encoding = "UTF-8";
        String html = getHtmlResourceByUrl(url, encoding);
        System.out.println(html);
    }

}