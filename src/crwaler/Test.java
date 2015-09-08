/*
 * @project		MyEBook
 * @author		Oliver.xyy
 * @file_name	Test.java
 * @pub_time	2015-6-8 14:38:47
 * @copyright	Oliver.xyy All Rights Reserved.
 */
package crwaler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import config.Config;
import util.Tools;

public class Test {

	public static void main(String[] args) {
		/*BlockingQueue urlQueue = new BlockingQueue(100);  
		urlQueue.add("111");
		urlQueue.add("112");
		urlQueue.add("113");
		urlQueue.add("114");
		urlQueue.add("115");
		urlQueue.add("116");
		System.out.println(urlQueue.dequeue());
		System.out.println(urlQueue.dequeue());
		System.out.println(urlQueue.dequeue());
		System.out.println(urlQueue.dequeue());
	    int i = 0;
		while(!urlQueue.isEmpty()){
			System.out.println("删除"+i+urlQueue.dequeue());
			urlQueue.add("115");
			urlQueue.add("116");
			i++;
			if(i>10){
				System.exit(0);
			}
		}*/
		Spider spider = new Spider();
		SimpleDateFormat DF = new SimpleDateFormat("yyyyMMddHHmmssSSS");//设置日期格式
		long startTime = Long.parseLong(DF.format(new Date()));// new Date()为获取当前系统时间
		spider.init();
		long endTime = Long.parseLong(DF.format(new Date()));// new Date()为获取当前系统时间
		System.out.println(Tools.getRunTime(startTime,endTime));
		/*SimpleDateFormat DF = new SimpleDateFormat("yyyyMMddHHmmssSSS");//设置日期格式
		long startTime = Long.parseLong(DF.format(new Date()));// new Date()为获取当前系统时间
		Config config = new Config();
		System.out.println(config.getMap().get("pageItemsNum"));
		long endTime = Long.parseLong(DF.format(new Date()));// new Date()为获取当前系统时间
		System.out.println(Tools.getRunTime(startTime,endTime));*/
		/*String q = "http://img.jb51.net/url.htm?url=http://kuai.xunlei.com/d/APNWCMFKUSYD?p=20395";
		String a = "123432.html";
		String b = "list_1-23432.html";
		System.out.println(q.matches("http://img\\.jb51\\.net/url\\.htm\\?url=http://kuai\\.xunlei\\.com/.+"));*/
		/*String end = "thread-38405-1-1.html";
		System.out.println(end.matches("thread-(\\d){5}-(\\d){1}-(\\d){1}[\\.]html"));*/
		/*String[] vals = "ssss搜索".split("");
		for(String s : vals){
			System.out.println(s);
		}
		System.out.println(vals[4]);*/
		
		/*Pattern p = Pattern.compile("jquery", Pattern.CASE_INSENSITIVE);  
		Matcher m = p.matcher("超实用的jQuery代码段 PDF扫描版[12MB]");  
		String result = m.replaceAll("<em>jquery</em>");  
		System.out.println(m.find());
		System.out.println(m.group());
		System.out.println(result);*/
		/*
		Pattern p=Pattern.compile("[JQuery]",Pattern.CASE_INSENSITIVE); 
		Matcher m=p.matcher("jquery"); 
		m.groupCount();   //返回2,因为有2组 
		m.start(1);   //返回0 返回第一组匹配到的子字符串在字符串中的索引号 
		m.start(2);   //返回3 
		m.end(1);   //返回3 返回第一组匹配到的子字符串的最后一个字符在字符串中的索引位置. 
		m.end(2);   //返回7 
		while(m.find()){
			System.out.println(m.group());   //返回aaa,返回第一组匹配到的子字符串 
		}
		m.group(2);   //返回2223,返回第二组匹配到的子字符串 
		*/
		
		
		
		
		
		
		
		
		
		
		
	}
	 

}
