/*
 * @project		MyEBook
 * @author		Oliver.xyy
 * @file_name	SearchService.java
 * @pub_time	2015-6-8 14:38:58
 * @copyright	Oliver.xyy All Rights Reserved.
 */
package service;

import java.util.HashMap;
import java.util.Map;

import util.Tools;
import dao.DbHelperImpl;
import dao.IDbHelper;

@SuppressWarnings("rawtypes")
public class SearchService {
	private IDbHelper dao = new DbHelperImpl();
	
	public Map[] search(Object[] params){
		String sql = "select url,text from book where text like "+this.getParamsString(params);
		String sql2 = "select url,text from book where text like "+this.getParamsString(Tools.reverse(params));
		System.out.println(sql+"\n"+sql2);
		Map[] row1 = this.dao.select(sql);
		Map[] row2 = this.dao.select(sql2);
		int length1 = row1.length;
		int length2 = row2.length;
		Map[] rows = new Map[length1+length2];
		for(int i = 0;i<length1+length2; i++){
			if(i<length1){
				rows[i] = row1[i];
			}
			else{
				rows[i] = row2[i-length1];
			}
		}
		return row1;
	}
	public String getParamsString(Object[] params){
		String paramsString = "";
		int length0 = params.length;
		if(length0<2){
			paramsString = "'%"+params[0]+"%'";
		}
		else{
			for(int i = 0; i <length0; i++ ){
				if(i == 0){
					paramsString = "'";
					paramsString += "%"+params[i];
				}
				else if(i ==length0-1){
					paramsString += "%"+params[i];
					paramsString += "%'";
				}
				else{
					paramsString += "%"+params[i];
				}
			}
		}
		return paramsString;
	}
	/*public Set<String> getURLSeeds(){
		String sql="select url from book";
		Map[] rows = this.dao.select(sql);
		Set<String> URLSeeds = new HashSet<String>();
		for(Map m:rows){
			URLSeeds.add(m.get("url").toString());
		}
		return URLSeeds;
	}*/
	public Map<String,String> getURLItems(){
		String sql="select url from book";
		Map[] rows = this.dao.select(sql);
		Map<String,String> URLItems = new HashMap<String,String>();
		for(Map m:rows){
			URLItems.put(m.get("url").toString(), "1");
		}
		return URLItems;
	}
}
