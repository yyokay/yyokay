package org.yyok.spider.stock.mains;

import java.io.IOException;

import org.apache.http.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.yyok.spider.stock.StockUtils;


/**
 * @author lqh
 */
public class StockMains {

    public static void main(String[] args) throws ParseException, IOException {
        String content = StockUtils.getHtmlByUrl(
                "http://fa.163.com/zx/gjs/1/");
        parserHtml(content);
    }


    public static void parserHtml(String content) throws ParseException, IOException {
        Document doc = Jsoup.parse(content);
        Elements links = doc.getElementsByClass("g-news").select("dl");
        for (Element e : links) {
            System.out.println("新闻标题:" + e.select("a").text().toString());
            //获取页面链接
            Elements linkHref = e.select("a");
            //截取时间字符串
            Elements timeStr = e.select("span[class=f-fr]");
            //简略信息
            Elements comment = e.select("span[class=f-fl f-ofe u-digest]");
            System.out.println("新闻链接:" + linkHref.attr("href"));
            System.out.println("发布时间:" + timeStr.text());
            System.out.println("简要信息:" + comment.text().toString());

            System.out.println("=============================================================");
        }

    }
}