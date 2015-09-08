/*
 * @project		MyEBook
 * @author		Oliver.xyy
 * @file_name	Spider.java
 * @pub_time	2015-6-8 14:38:46
 * @copyright	Oliver.xyy All Rights Reserved.
 */
package crwaler;


import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;  
import org.jsoup.nodes.Element;  
import org.jsoup.select.Elements;  

import service.BookService;
import service.SearchService;
import util.BlockingQueue;

public class Spider {
	private final String url = "http://ebooksworld.in/";
	private BookService bookService = new BookService();
	private SearchService searchService = new SearchService();
	private String charset = "GBK";
	private Map<String,String> bookItem = new HashMap<String,String>();
	//private Set<String> unURLSeeds = new HashSet<String>();
	private Set<String> URLSeeds = new HashSet<String>();
	//private Set<String> seedUrls = new HashSet<String>();
	private BlockingQueue urlQueue = new BlockingQueue(10000);  
	public void init(){
		//this.bookItem = this.searchService.getURLItems();
		this.URLSeeds.add(url);
		this.urlQueue.add(url);
		while(!this.urlQueue.isEmpty()){
			this.parserHtml(this.getHtmlByUrl(this.urlQueue.remove()), this.charset);
			/*i++;
			if(i>10){
				System.exit(0);
			}*/
		}
	}
	/** 
     * 根据URL获得所有的html信息 
     * @param url 
     * @return html
     */  
    public String getHtmlByUrl(String url){  
        String html = null;  
        HttpClient httpClient = new DefaultHttpClient();//创建httpClient对象  
        HttpGet httpget = new HttpGet(url);//以get方式请求该URL  
        try {  
            HttpResponse responce = httpClient.execute(httpget);//得到responce对象  
            int resStatu = responce.getStatusLine().getStatusCode();//返回码  
            if (resStatu==HttpStatus.SC_OK) {//200正常  其他就不对  
                //获得相应实体  
                HttpEntity entity = responce.getEntity();  
                if (entity!=null) {  
                    html = EntityUtils.toString(entity);//获得html源代码 
                }  
            }  
        } catch (Exception e) {  
            System.out.println("访问【"+url+"】出现异常!");  
            e.printStackTrace();  
        } finally {  
            httpClient.getConnectionManager().shutdown();  
        }  
        //System.out.println(html);
        return html;  
    }
    public void parserHtml(String html,String charset){
    	/*try {
			html = new String(html.getBytes("ISO-8859-1"),charset);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        if (html!=null&&!"".equals(html)) {  
            Document doc = Jsoup.parse(html);  
            //Elements meta= doc.select("meta"); 
            Elements linksElements = doc.select("a");
            //div#page>div#content>div#main>div.left>div#recommend>ul>li>a
            //以上代码的意思是 找id为“page”的div里面   id为“content”的div里面   id为“main”的div里面   class为“left”的div里面   id为“recommend”的div里面ul里面li里面a标签  
            for (Element item:linksElements) {
                String href = item.attr("href");
                String text = item.text();
                this.analyseUrl(href,text);
            }  
        }
    }
    public String getCharset(Elements meta){
    	String charset = "utf-8";
    	for (Element item:meta) {
            if(item.toString().toLowerCase().indexOf("gb2312")>-1){
            	charset = "gb2312";
            }
            else if(item.toString().toLowerCase().indexOf("gbk")>-1){
            	charset = "gbk";
            }
            else{
            	return "utf-8";
            }
        }
		return charset;
    }
    public void analyseUrl(String href,String text){
    	/*
    	 * http://ebooksworld.in/
    	 * */
    	if(href.indexOf("http://ebooksworld.in/")>-1){}
    	else{
    		if(href.indexOf("/library/")>-1){
        		href = "http://ebooksworld.in"+href;
        	}
    	}
    	//System.out.println(href);
    	if(href.indexOf("http://ebooksworld.in/")>-1){
        	if(href.matches("http://ebooksworld[\\.]in/library/.+")||href.matches("http://ebooksworld[\\.]in/pages/[\\d]+")){
        		if(!this.URLSeeds.contains(href)){
        			this.URLSeeds.add(href);
            		this.urlQueue.add(href);
        		}
        	}
    	}
    	if(href.indexOf("http://downloads.ziddu.com/download/")>-1){
    		this.bookItem.put(href, text);
			this.bookService.addBook(href, text);
			System.out.println("将【"+href+"】插入数据库！");
    		//System.out.println(text.trim());
    	}
    	/*
    	 * http://www.jb51.net/
    	 * */
    	/*if(href.indexOf("http://www.jb51.net/books/")>-1){}
    	else{
    		if(href.indexOf("/books/")>-1){
        		href = "http://www.jb51.net"+href;
        	}
    		else if(href.matches("list.+[\\.]htm[l]?")){
    			href = "http://www.jb51.net/books/"+href;
    		}
    	}
    	if(href.indexOf("http://www.jb51.net/books/")>-1){
    		String end = href.substring(href.lastIndexOf("/")+1,href.length());
        	if(end.matches("list.+[\\.]htm[l]?")){
        		if(!this.URLSeeds.contains(href)){
        			this.URLSeeds.add(href);
            		this.urlQueue.add(href);
        		}
        	}
        	else if(end.matches("\\d+[\\.]htm[l]?")){
        		if(!this.bookItem.containsKey(href)){
        			this.bookItem.put(href, text);
        			this.bookService.addBook(href, text);
            		System.out.println("将【"+href+"】插入数据库！");
        		}
        	}
    	}*/
    	/*
    	 * kindle114规则(爬取失败)
    	 * 1、编码不需要转码
    	 * 2、有些href的text为空
    	 * 3、有些href的内容确定，所以需要删选，暂定pdf,azw3,mobi,epub
    	 * 4、主要的url的text里面包括span
    	 * */
    	/*if(href.indexOf("http://www.kindle114.com/")>-1){}
    	else{
    		if(href.matches("thread.+[\\.]html")){
    			href = "http://www.kindle114.com/"+href;
    		}
    		else if(href.matches("forum.+")){
    			href = "http://www.kindle114.com/"+href;
    		}
    	}*/
    	//System.out.println(href);
    	/*if(href.indexOf("http://www.kindle114.com/")>-1){
    		String end = href.substring(href.lastIndexOf("/")+1,href.length());
    		//System.out.println(end);
        	if(end.matches("thread.+[\\.]html")){
        		if(!this.bookItem.containsKey(href)){
        			this.bookItem.put(href, text);
        			text += "";
        			if(text!=""&&text.replaceAll("\\s","")!=""){
        				if(text.indexOf("pdf")>-1||text.indexOf("azw3")>-1||text.indexOf("mobi")>-1||text.indexOf("epub")>-1){
                			this.bookService.addBook(href, text);
        					System.out.println("将【"+href+"】插入数据库！");
                    		System.out.println(text);
        				}
        			}
        		}
        	}
        	else{
        		if(!this.URLSeeds.contains(href)){
        			this.URLSeeds.add(href);
            		this.urlQueue.add(href);
        		}
        	}
    	}*/
	}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
