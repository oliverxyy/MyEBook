/*
 * @project		MyEBook
 * @author		Oliver.xyy
 * @file_name	BookService.java
 * @pub_time	2015-6-8 14:38:58
 * @copyright	Oliver.xyy All Rights Reserved.
 */
package service;

import dao.DbHelperImpl;
import dao.IDbHelper;

public class BookService {
	private IDbHelper dao = new DbHelperImpl();
	public void addBook(String url,String text){
		String sql="insert into book(url,text) values(?,?)";
		Object[] params = {url,text};
		this.dao.update(sql, params);
	}
}
