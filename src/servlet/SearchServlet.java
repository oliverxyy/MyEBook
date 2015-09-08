package servlet;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import config.Config;
import service.SearchService;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
@SuppressWarnings("rawtypes")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Config config = new Config();
	private static int pageItemsNum = Integer.parseInt(config.getMap().get("pageItemsNum"));
	private SearchService search = new SearchService();

    /**
     * Default constructor. 
     */
    public SearchServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse re6-sponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String op = request.getParameter("op").toString();
		if(op.equals("search")){
			String content = request.getParameter("content");
			String page = request.getParameter("page");
			Map[] rows = getResults(content);
			request.setAttribute("content", content);
			request.setAttribute("count", rows.length);
			if(page==null){
				request.setAttribute("rows", pagination(rows,1));
				session.setAttribute("index", 1);
				request.setAttribute("pagination", getPagination(pageItemsNum, 1));
			}
			else if(page.matches("\\d+")){
				session.setAttribute("index", Integer.parseInt(page));
				request.setAttribute("rows", pagination(rows,Integer.parseInt(page)));
				request.setAttribute("pagination", getPagination(pageItemsNum, Integer.parseInt(page)));
			}
			else if(page.equals("lastPage")){
				session.setAttribute("index", Integer.parseInt(session.getAttribute("index").toString())-1);
				request.setAttribute("rows", pagination(rows,Integer.parseInt(session.getAttribute("index").toString())));
				request.setAttribute("pagination", getPagination(pageItemsNum, Integer.parseInt(session.getAttribute("index").toString())));
			}
			else if(page.equals("nextPage")){
				session.setAttribute("index", Integer.parseInt(session.getAttribute("index").toString())+1);
				request.setAttribute("rows", pagination(rows,Integer.parseInt(session.getAttribute("index").toString())));
				request.setAttribute("pagination", getPagination(pageItemsNum, Integer.parseInt(session.getAttribute("index").toString())));
			}
		}
		else{
			
		}
		request.getRequestDispatcher("search.jsp").forward(request, response);
	}
	public Map[] pagination(Map[] results,int page){
		Map[] rows = new Map[pageItemsNum];
		int row_length = results.length;
		for(int i=0;i<pageItemsNum;i++){
			if(pageItemsNum*(page-1)+i<row_length){
				rows[i] = results[pageItemsNum*(page-1)+i];
			}
			else{
				rows[i] = null;
			}
		}
		return rows;
	}
	/*public void ajaxResponse(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		PrintWriter out = response.getWriter(); 
            response.setHeader("Cache-Control", "no-store");  
            response.setHeader("Pragma", "no-store");  
            response.setDateHeader("Expires", 0);
			out.print("YES");
			out.close();
			
	}*/
	@SuppressWarnings("unchecked")
	public Map[] getResults(String content){
		if(content!=null){
			content = content.trim();
			Object[] vals = content.split("\\s+");
			Map[] rows = this.search.search(vals);
			int i = 0;
			for(Map m : rows){
				for(Object o : vals){
					String text = token_replaceAll(m.get("text").toString(),o.toString(),"<em>"+o.toString()+"</em>");
					m.put("text", text);
					rows[i] = m;
				}
			}
			
			return rows;
		}
		else{
			return null;
		}
	}
	public String tokenizer(String keyword){
		System.out.println("keyword；"+keyword);
		String[] vals = keyword.split("");
		String temp = "";
		for(String s : vals){
			if(s!=""||s!=null){
				System.out.println(s.getBytes());
				temp += "["+s+"]";
			}
		}
		System.out.println("temp；"+temp);
		return temp;
	}
	/*public Map[] highlight(MapObject[] vals){
		
		return null;
	}*/
	public String getPagination(int pageItemsNum,int index){
    	String pagination = "";
    	if(index==1){
    		for(int i=1;i<=pageItemsNum;i++){
            	if(i==1){
            		pagination += "<button formaction='SearchServlet?op=search' type='submit' class='current'>"+i+"</button>";
            	}
            	else{
            		pagination += "<button formaction='SearchServlet?op=search&page="+i+"'  type='submit'>"+i+"</button>";
            	}
            }
    		pagination += "<button formaction='SearchServlet?op=search&page=nextPage'  type='submit'>下一页</button>";
    	}
    	else if(index>1&&index<6){
        	for(int i=1;i<=pageItemsNum;i++){
            	if(i==1){
            		pagination += "<button formaction='SearchServlet?op=search&page=lastPage'>上一页</button>";
            		pagination += "<button formaction='SearchServlet?op=search' type='submit'>"+i+"</button>";
            	}
            	else if(i==index){
            		pagination += "<button formaction='SearchServlet?op=search&page="+i+"'  type='submit' class='current'>"+i+"</button>";
            	}
            	else{
            		pagination += "<button formaction='SearchServlet?op=search&page="+i+"'  type='submit'>"+i+"</button>";
            	}
            }
    		pagination += "<button formaction='SearchServlet?op=search&page=nextPage'  type='submit'>下一页</button>";
        }
    	else{
        	for(int i=0;i<pageItemsNum;i++){
            	if(i==0){
            		pagination += "<button formaction='SearchServlet?op=search&page=lastPage'>上一页</button>";
            		pagination += "<button formaction='SearchServlet?op=search&page="+(i+index-5)+"'  type='submit'>"+(i+index-5)+"</button>";
            	}
            	else if(i==5){
            		pagination += "<button formaction='SearchServlet?op=search&page="+index+"' type='submit' class='current'>"+index+"</button>";
            	}
            	else{
            		pagination += "<button formaction='SearchServlet?op=search&page="+(i+index-5)+"'  type='submit'>"+(i+index-5)+"</button>";
            	}
            }
    		pagination += "<button formaction='SearchServlet?op=search&page=nextPage'  type='submit'>下一页</button>";
    	}
		return pagination;
	}
	public String token_replaceAll(String input, String regex, String replacement) {  
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);  
		Matcher m = p.matcher(input);  
		String result = m.replaceAll(replacement);  
		return result;  
	} 
	
	
	
}
