package org.yyok.spider.jobs;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Classfiles {

    static String baseUrl="https://jobs.51job.com/";

    public static void main(String[] args) throws UnsupportedEncodingException {
        JobSprider js = new JobSprider();
        Document dm = js.getResultPage(baseUrl,"");
        Elements ems = dm.getElementsByTag("a");
        for (Element em :ems){
            System.out.print(em.getElementsByTag("href"));
            System.out.println("  "+ em.text());


        }
    }

}
